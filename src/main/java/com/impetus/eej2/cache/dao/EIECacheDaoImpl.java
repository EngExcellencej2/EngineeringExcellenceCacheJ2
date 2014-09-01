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
import com.impetus.eej2.cache.exception.EieCacheErrorCodes;
import com.impetus.eej2.cache.exception.EieCacheException;
import com.impetus.eej2.cache.utils.CassandraConnectionUtils;

/**
 * <p>
 * An implementation of <code>IEIECacheDao</code> to fetch records from Cassandra Cluster.
 * </p>
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
	public EIEResponse getEIEResponse(EIERequest eieReq) {
		logger.info("inside getEIEResponse of EIECacheDaoImpl {}",eieReq);
		String rowID = getRowID(eieReq);
		PreparedStatement preparedStatement = null;
		ResultSet rowSet = null;
		EIEResponse eieRes = null;
		try {
			if (session != null) {
				preparedStatement = session.prepare(GET_RECORD_FROM_CACHE).setConsistencyLevel(ConsistencyLevel.LOCAL_ONE);
				preparedStatement.enableTracing();
			}
			
			BoundStatement boundStatement = preparedStatement.bind(rowID);
			rowSet = session.execute(boundStatement);
			ExecutionInfo executionInfo=rowSet.getExecutionInfo();
			logger.info("QUERY TRACE"+executionInfo.getQueryTrace());
			logger.info("QUERIED HOST"+executionInfo.getQueriedHost());
			logger.info("TRIED HOST"+executionInfo.getTriedHosts());
			Date currentDate = new Date();
			
			for(Row row : rowSet)
            {	
            	Date createdDate = row.getDate("created_date");
            	int diff = (int) ((currentDate.getTime()-createdDate.getTime())/1000);
            	
            	if(diff<=eieReq.getTTL())
            	{	
            		eieRes = new EIEResponse();
    				eieRes.setId(row.getString("row_id"));
    				eieRes.setMNC(row.getString("mnc"));
    				eieRes.setMCC(row.getString("mcc"));
    				eieRes.setSPID(row.getString("spid"));
    				eieRes.setCrDate(row.getDate("created_date"));
    				eieRes.setReqType(row.getLong("request_type"));
    				eieRes.setResString(row.getString("response_string"));
    				eieRes.setStatus(row.getLong("status"));
    				eieRes.setIMSI(row.getLong("imsi"));
    				eieRes.setHLR(row.getLong("hlr"));
    				eieRes.setMSC(row.getLong("msc"));
    				eieRes.setTN_Type(row.getString("tn_type"));
    				eieRes.setSupplierId(row.getLong("supplier_id"));
    				eieRes.setSupplierType(row.getString("supplier_type"));
    				break;	
            	}
            	else
            	{
            		continue;
            	}
            	
            }
			
		
			

		} catch (Exception exception) {
			logger.error("getting exception inside EIECacheDaoImpl.getEIEResponse");
			throw new EieCacheException(EieCacheErrorCodes.UNSUCCESSFULL_READ,
					EieCacheErrorCodes.UNSUCCESSFULL_READ.getErrorMessage(),
					exception.getMessage());
		} /*finally {
			Do not close our singleton session.
			session.close();
		}*/
		return eieRes;
	}

	/**
	 * <p>
	 * Get unique hash Code as Row Id for Cassandra Column Family.
	 * </p> 
	 * @param eieReq
	 * @return rowId for Cassandra record
	 */
	private String getRowID(EIERequest eieReq) {
		String src = eieReq.getCC() + "_" + eieReq.getTN();
		return src;
	}

	@Override
	public Boolean addEIEExternalReponse(EIEResponse eieRes) {
		// Session session = CassandraConnectionUtils.CONN.getSession();
		logger.info("inside addEIEExternalReponse of EIECacheDaoImpl {}",
				eieRes);
		try {
			PreparedStatement preparedStatement = session.prepare(
					INSERT_RECORD_IN_CACHE).setConsistencyLevel(
					ConsistencyLevel.LOCAL_ONE);
			preparedStatement = session.prepare(GET_RECORD_FROM_CACHE).setConsistencyLevel(ConsistencyLevel.LOCAL_ONE);
			preparedStatement.enableTracing();
			BoundStatement boundStatement = preparedStatement.bind(
					eieRes.getCc() + "_" + eieRes.getTN(), eieRes.getMNC(),
					eieRes.getMCC(), eieRes.getSPID(), new Date(),
					eieRes.getReqType(), eieRes.getResString(),
					eieRes.getStatus(), eieRes.getIMSI(), eieRes.getHLR(),
					eieRes.getMSC(), eieRes.getTN_Type(),
					eieRes.getSupplierId(), eieRes.getSupplierType(),
					eieRes.getTTL());
			session.execute(boundStatement);
			return true;

		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		} /*finally {
			 Do not close our singleton session.
			 session.close();
		}*/

	}

}
