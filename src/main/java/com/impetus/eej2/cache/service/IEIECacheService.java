package com.impetus.eej2.cache.service;

import com.impetus.eej2.cache.entity.EIERequest;
import com.impetus.eej2.cache.entity.EIEResponse;
import com.impetus.eej2.cache.exception.EieCacheCheckedException;

/**
 * <p>
 * A Service utility which integrates with Application DAO Layer <code>IEIECacheDao</code>.
 * Any client using this utility needs to invoke methods upon this class.
 * </p>
 * @author ankit.kothari
 * @version 1.0
 * 
 */
public interface IEIECacheService {

	/**
	 * <p>
	 * Gets the response from the Cassandra database based on the input request.
	 * In case data is not found in the database Exception is thrown.
	 * </p>
	 * @param eieReq
	 * @return the <code>EIEResponse</code> to EIE Engine
	 */
	public EIEResponse getEIEresponse(EIERequest eieReq) throws EieCacheCheckedException;

	/**
	 * <p>
	 * Insert the record in the Cassandra database from the EIEResponse.
	 *  Returns True in case data is written successfully else False.
	 * </p>
	 * 
	 * @param eieRes
	 * @return output in <code>boolean</code> format to EIE Engine
	 * 
	 */
	public Boolean addEIEexternalResponse(EIEResponse eieRes) throws EieCacheCheckedException;

}
