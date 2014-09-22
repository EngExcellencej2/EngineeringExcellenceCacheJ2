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
import com.impetus.eej2.cache.entity.EIERequest;
import com.impetus.eej2.cache.entity.EIEResponse;
import com.impetus.eej2.cache.exception.EIECacheCheckedException;
import com.impetus.eej2.cache.exception.EIECacheErrorCodes;
import com.impetus.eej2.cache.exception.ExceptionHandlerTemplate;
import com.impetus.eej2.cache.utils.CassandraConnectionUtils;

/**
 * <p>
 * An implementation of <code>IEIECacheDao</code> to fetch records from
 * Cassandra Cluster.
 * </p>
 * 
 * @author perwaiz.ali
 */

public class EIECacheDataStaxDAOImpl implements IEIECacheDAO {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(EIECacheDataStaxDAOImpl.class);

	private static final String GET_RECORD_FROM_CACHE = "SELECT * from eie_cache_db.eie_proxydbcache  where row_id = ?";
	private static final String INSERT_RECORD_IN_CACHE = "INSERT INTO eie_cache_db.eie_proxydbcache "
			+ "(row_id,mnc,mcc,spid,created_date,request_type,response_string,status,imsi,hlr,msc,tn_type,supplier_id,supplier_type)"
			+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)" + "USING TTL ?";

	Session session = CassandraConnectionUtils.CONN.getSession();

	@Override
	public EIEResponse getEIEResponse(EIERequest eieReq)
			throws EIECacheCheckedException {
		LOGGER.info("inside getEIEResponse of EIECacheDaoImpl {}", eieReq);
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
				LOGGER.info("QUERY TRACE" + executionInfo.getQueryTrace());
				LOGGER.info("QUERIED HOST" + executionInfo.getQueriedHost());
				LOGGER.info("TRIED HOST" + executionInfo.getTriedHosts());
				Date currentDate = new Date();

				for (Row row : rowSet) {
					Date createdDate = row.getDate("created_date");
					int diff = (int) ((currentDate.getTime() - createdDate
							.getTime()) / 1000);

					if (diff <= eieReq.getTimeToLive()) {
						LOGGER.info("Condition met, populate EIEResponse");
						eieRes.setId(row.getString("row_id"));
						eieRes.setMnc(row.getString("mnc"));
						eieRes.setMcc(row.getString("mcc"));
						eieRes.setSpId(row.getString("spid"));
						eieRes.setCreatedDate(row.getDate("created_date"));
						eieRes.setRequestType(row.getLong("request_type"));
						eieRes.setResponseString(row
								.getString("response_string"));
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
					} 
				}

			} else {
				LOGGER.error("session is null found", eieReq);
				throw new EIECacheCheckedException(
						EIECacheErrorCodes.NULL_SEESION,
						"session is null found");
			}
		} catch (Exception exception) {
			ExceptionHandlerTemplate.handleException(EIECacheErrorCodes.UNSUCCESSFULL_READ,exception,
					eieReq.toString());
		}

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
		return eieReq.getCountryCode() + "_" + eieReq.getTelephoneNumber();
	}

	@Override
	public Boolean addEIEExternalReponse(EIEResponse eieRes)
			throws EIECacheCheckedException {
		LOGGER.info("inside addEIEExternalReponse of EIECacheDaoImpl {}",
				eieRes);
		Boolean writeflag = false;
		try {
			if (session != null) {
				PreparedStatement preparedStatement = session.prepare(
						INSERT_RECORD_IN_CACHE).setConsistencyLevel(
						ConsistencyLevel.LOCAL_ONE);
				preparedStatement.enableTracing();
				BoundStatement boundStatement = preparedStatement.bind(
						eieRes.getCountryCode() + "_"
								+ eieRes.getTelephoneNumber(), eieRes.getMnc(),
						eieRes.getMcc(), eieRes.getSpId(), new Date(),
						eieRes.getRequestType(), eieRes.getResponseString(),
						eieRes.getStatus(), eieRes.getImsi(), eieRes.getHlr(),
						eieRes.getMsc(), eieRes.getTnType(),
						eieRes.getSupplierId(), eieRes.getSupplierType(),
						eieRes.getTimeToLive());
				session.execute(boundStatement);
				writeflag = true;

			} else {
				LOGGER.error("session is  null found", eieRes);
				throw new EIECacheCheckedException(
						EIECacheErrorCodes.NULL_SEESION, "session  is null");
			}
		} catch (Exception exception) {
			ExceptionHandlerTemplate.handleException(EIECacheErrorCodes.UNSUCCESSFULL_WRITE,exception,
					eieRes.toString());
		}
		return writeflag;

	}

}
