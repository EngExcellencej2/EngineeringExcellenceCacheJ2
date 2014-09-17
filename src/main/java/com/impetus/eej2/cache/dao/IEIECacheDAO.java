package com.impetus.eej2.cache.dao;

import com.impetus.eej2.cache.entity.EIERequest;
import com.impetus.eej2.cache.entity.EIEResponse;
import com.impetus.eej2.cache.exception.EIECacheCheckedException;

/**
 * <p>
 * A Dao utility to fetch cache records from Cassandra Cluster. The implementation DAO will be Cassandra Driver specific.
 * </p>
 * @author perwaiz.ali
 * @version 1.0
 */
public interface IEIECacheDAO {

	/**
	 * <p>
	 * Gets the response from the Cassandra database based on the input request.
	 * In case data in not found in the database null response is returned.
	 * When any exception is thrown while fetching data, the exception is wrapped up in <code>EieCacheException</code>
	 * </p>
	 * 
	 * @param eieReq
	 * @return the <code>EIEReponse to service Layer
	 */
	public EIEResponse getEIEResponse(EIERequest eieReq) throws EIECacheCheckedException;

	/**
	 * <p>
	 * Insert the record in the Cassandra database from the EIEResponse.
	 *  Returns True in case data is written successfully else False.
	 * </p>
	 * 
	 * @param eieRes
	 * @return the output in <code>boolean</code> format to service layer
	 */
	public Boolean addEIEExternalReponse(EIEResponse eieRes) throws EIECacheCheckedException;
}
