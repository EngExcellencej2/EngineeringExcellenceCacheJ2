package com.impetus.eej2.cache.dao;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.ExecutionInfo;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.exceptions.InvalidTypeException;
import com.datastax.driver.core.exceptions.NoHostAvailableException;
import com.datastax.driver.core.exceptions.QueryExecutionException;
import com.datastax.driver.core.exceptions.QueryValidationException;
import com.datastax.driver.core.exceptions.UnsupportedFeatureException;
import com.impetus.eej2.cache.entity.EIERequest;
import com.impetus.eej2.cache.entity.EIEResponse;
import com.impetus.eej2.cache.exception.EieCacheCheckedException;
import com.impetus.eej2.cache.exception.EieCacheErrorCodes;
import com.impetus.eej2.cache.utils.CassandraConnectionUtils;

/**
 * <p>
 * An implementation of <code>IEIECacheDao</code> to fetch records from
 * Cassandra Cluster.
 * </p>
 * 
 * @author perwaiz.ali
 */

public class EIECacheDaoImpl implements IEIECacheDao {
	private static final Logger logger = LoggerFactory
			.getLogger(EIECacheDaoImpl.class);

	private static final String GET_RECORD_FROM_CACHE = "SELECT * from eie_cache_db.eie_proxydbcache  where row_id = ?";
	private static final String INSERT_RECORD_IN_CACHE = "INSERT INTO eie_cache_db.eie_proxydbcache "
			+ "(row_id,mnc,mcc,spid,created_date,request_type,response_string,status,imsi,hlr,msc,tn_type,supplier_id,supplier_type)"
			+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)" + "USING TTL ?";

	Session session = CassandraConnectionUtils.CONN.getSession();

	@Override
	public EIEResponse getEIEResponse(EIERequest eieReq)
			throws EieCacheCheckedException {
		logger.info("inside getEIEResponse of EIECacheDaoImpl {}", eieReq);
		String rowID = getRowID(eieReq);
		PreparedStatement preparedStatement = null;
		ResultSet rowSet = null;
		EIEResponse eieRes = new EIEResponse();
		eieRes.setIsDataFound(false);
		eieRes.setStatusMessage("record is not found for input TN and CC");
	
		try {
			if (session != null) {
				
				preparedStatement = session.prepare(GET_RECORD_FROM_CACHE)
						.setConsistencyLevel(ConsistencyLevel.LOCAL_ONE);
				preparedStatement.enableTracing();
				BoundStatement boundStatement = preparedStatement.bind(rowID);
				rowSet = session.execute(boundStatement);
				ExecutionInfo executionInfo = rowSet.getExecutionInfo();
				logger.info("QUERY TRACE" + executionInfo.getQueryTrace());
				logger.info("QUERIED HOST" + executionInfo.getQueriedHost());
				logger.info("TRIED HOST" + executionInfo.getTriedHosts());
				Date currentDate = new Date();

				for (Row row : rowSet) {
					Date createdDate = row.getDate("created_date");
					int diff = (int) ((currentDate.getTime() - createdDate
							.getTime()) / 1000);

					if (diff <= eieReq.getTimeToLive()) {
						eieRes.setId(row.getString("row_id"));
						eieRes.setMnc(row.getString("mnc"));
						eieRes.setMcc(row.getString("mcc"));
						eieRes.setSpId(row.getString("spid"));
						eieRes.setCreatedDate(row.getDate("created_date"));
						eieRes.setRequestType(row.getLong("request_type"));
						eieRes.setResponseString(row.getString("response_string"));
						eieRes.setStatus(row.getLong("status"));
						eieRes.setImsi(row.getLong("imsi"));
						eieRes.setHlr(row.getLong("hlr"));
						eieRes.setMsc(row.getLong("msc"));
						eieRes.setTnType(row.getString("tn_type"));
						eieRes.setSupplierId(row.getLong("supplier_id"));
						eieRes.setSupplierType(row.getString("supplier_type"));
						eieRes.setIsDataFound(true);
						eieRes.setStatusMessage("record found in database for input TelePhoneNumber and CountryCode");
						break;
					} else {
						continue;
					}

				}

			} else {
				logger.error("session is null found");
				throw new EieCacheCheckedException(
						EieCacheErrorCodes.UNSUCCESSFULL_READ,
						"session is null found");
			}
		} catch (NoHostAvailableException noHostAvailableException) {
			logger.error("no host in the cluster can be contacted successfully to execute this query ",
					noHostAvailableException);
			throw new EieCacheCheckedException(
					EieCacheErrorCodes.UNSUCCESSFULL_READ,
					"no host in the cluster can be contacted successfully to execute this query",
					noHostAvailableException.toString());
		}
		catch (QueryExecutionException queryExecutionException ) {
			logger.error("an exception thrown by Cassandra when it cannot execute the query with the requested consistency level successfully",
					queryExecutionException);
			throw new EieCacheCheckedException(
					EieCacheErrorCodes.UNSUCCESSFULL_READ,
					"an exception thrown by Cassandra when it cannot execute the query with the requested consistency level successfully",
					queryExecutionException.toString());
		}
		catch (QueryValidationException queryValidationException) {
			logger.error(" found syntax error, unauthorized or any other validation problem ",
					queryValidationException);
			throw new EieCacheCheckedException(
					EieCacheErrorCodes.UNSUCCESSFULL_READ,
					"found syntax error, unauthorized or any other validation problem",
					queryValidationException.toString());
		}
		catch (UnsupportedFeatureException unsupportedFeatureException) {
			logger.error("BatchStatement, ResultSet paging and binary values in RegularStatement may be not supported ",
					unsupportedFeatureException);
			throw new EieCacheCheckedException(
					EieCacheErrorCodes.UNSUCCESSFULL_READ,
					"BatchStatement, ResultSet paging and binary values in RegularStatement may be not supported",
					unsupportedFeatureException.toString());
		}
		catch (IllegalArgumentException illegalArgumentException) {
			logger.error("more values are provided than there is of bound variables in this statement. ",
					illegalArgumentException);
			throw new EieCacheCheckedException(
					EieCacheErrorCodes.UNSUCCESSFULL_READ,
					"more values are provided than there is of bound variables in this statement.",
					illegalArgumentException.toString());
		} catch (InvalidTypeException invalidTypeException) {
			logger.error("any of the provided value is not of correct type to be bound to the corresponding bind variable ",
					invalidTypeException);
			throw new EieCacheCheckedException(
					EieCacheErrorCodes.UNSUCCESSFULL_READ,
					"any of the provided value is not of correct type to be bound to the corresponding bind variable",
					invalidTypeException.toString());
		} catch (NullPointerException nullPointerException) {
			logger.error("null value found ",
					nullPointerException);
			throw new EieCacheCheckedException(
					EieCacheErrorCodes.UNSUCCESSFULL_READ,
					"null value found",
					nullPointerException.toString());
		} catch (Exception exception) {
			logger.error("error during reading data ", exception);
			throw new EieCacheCheckedException(
					EieCacheErrorCodes.UNSUCCESSFULL_READ,
					"error during reading data", exception.toString());
		}
		/*
		 * finally { Do not close our singleton session. session.close(); }
		 */
		return eieRes;
	}

	/**
	 * <p>
	 * Get unique hash Code as Row Id for Cassandra Column Family.
	 * </p>
	 * 
	 * @param eieReq
	 * @return rowId for Cassandra record
	 */
	private String getRowID(EIERequest eieReq) {
		String src = eieReq.getCountryCode() + "_" + eieReq.getTelephoneNumber();
		return src;
	}

	@Override
	public Boolean addEIEExternalReponse(EIEResponse eieRes)
			throws EieCacheCheckedException {
		// Session session = CassandraConnectionUtils.CONN.getSession();
		logger.info("inside addEIEExternalReponse of EIECacheDaoImpl {}",
				eieRes);
		Boolean writeflag = false;
		try {
			if(session!=null)
			{
			PreparedStatement preparedStatement = session.prepare(
					INSERT_RECORD_IN_CACHE).setConsistencyLevel(
					ConsistencyLevel.LOCAL_ONE);
			preparedStatement = session.prepare(INSERT_RECORD_IN_CACHE)
					.setConsistencyLevel(ConsistencyLevel.LOCAL_ONE);
			preparedStatement.enableTracing();
			BoundStatement boundStatement = preparedStatement.bind(
					eieRes.getCountryCode() + "_" + eieRes.getTelephoneNumber(), eieRes.getMnc(),
					eieRes.getMcc(), eieRes.getSpId(), new Date(),
					eieRes.getRequestType(), eieRes.getResponseString(),
					eieRes.getStatus(), eieRes.getImsi(), eieRes.getHlr(),
					eieRes.getMsc(), eieRes.getTnType(),
					eieRes.getSupplierId(), eieRes.getSupplierType(),
					eieRes.getTimeToLive());
			session.execute(boundStatement);
			writeflag = true;
			
			}
			else
			{
				logger.error("session is  null found");
				throw new EieCacheCheckedException(
						EieCacheErrorCodes.UNSUCCESSFULL_WRITE,
						"session  is null");
			}
		} catch (NoHostAvailableException noHostAvailableException) {
			logger.error("no host in the cluster can be contacted successfully to execute this query ",
					noHostAvailableException);
			throw new EieCacheCheckedException(
					EieCacheErrorCodes.UNSUCCESSFULL_WRITE,
					"no host in the cluster can be contacted successfully to execute this query",
					noHostAvailableException.toString());
		}
		catch (QueryExecutionException queryExecutionException ) {
			logger.error("an exception thrown by Cassandra when it cannot execute the query with the requested consistency level successfully",
					queryExecutionException);
			throw new EieCacheCheckedException(
					EieCacheErrorCodes.UNSUCCESSFULL_WRITE,
					"an exception thrown by Cassandra when it cannot execute the query with the requested consistency level successfully",
					queryExecutionException.toString());
		}
		catch (QueryValidationException queryValidationException) {
			logger.error(" found syntax error, unauthorized or any other validation problem ",
					queryValidationException);
			throw new EieCacheCheckedException(
					EieCacheErrorCodes.UNSUCCESSFULL_WRITE,
					"found syntax error, unauthorized or any other validation problem",
					queryValidationException.toString());
		}
		catch (UnsupportedFeatureException unsupportedFeatureException) {
			logger.error("BatchStatement, ResultSet paging and binary values in RegularStatement may be not supported ",
					unsupportedFeatureException);
			throw new EieCacheCheckedException(
					EieCacheErrorCodes.UNSUCCESSFULL_WRITE,
					"BatchStatement, ResultSet paging and binary values in RegularStatement may be not supported",
					unsupportedFeatureException.toString());
		}
		catch (IllegalArgumentException illegalArgumentException) {
			illegalArgumentException.printStackTrace();
			logger.error("more values are provided than there is of bound variables in this statement. ",
					illegalArgumentException);
			throw new EieCacheCheckedException(
					EieCacheErrorCodes.UNSUCCESSFULL_WRITE,
					"more values are provided than there is of bound variables in this statement.",
					illegalArgumentException.toString());
		} catch (InvalidTypeException invalidTypeException) {
			logger.error("any of the provided value is not of correct type to be bound to the corresponding bind variable ",
					invalidTypeException);
			throw new EieCacheCheckedException(
					EieCacheErrorCodes.UNSUCCESSFULL_WRITE,
					"any of the provided value is not of correct type to be bound to the corresponding bind variable",
					invalidTypeException.toString());
		} catch (NullPointerException nullPointerException) {
			logger.error("null value found ",
					nullPointerException);
			throw new EieCacheCheckedException(
					EieCacheErrorCodes.UNSUCCESSFULL_WRITE,
					"null value found",
					nullPointerException.toString());
		}
		catch (Exception exception) {
			logger.error("error during writing data ", exception);
			throw new EieCacheCheckedException(
					EieCacheErrorCodes.UNSUCCESSFULL_WRITE,
					"error during reading data", exception.toString());
		}
		finally {
			// Do not close our singleton session.
			// session.close();
		}
		return  writeflag; 

	}

}
