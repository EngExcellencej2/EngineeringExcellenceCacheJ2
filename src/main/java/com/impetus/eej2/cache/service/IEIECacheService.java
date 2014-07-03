package com.impetus.eej2.cache.service;

import com.impetus.eej2.cache.entity.EIERequest;
import com.impetus.eej2.cache.entity.EIEResponse;

/**
 * @author ankit.kothari
 * @version 1.0
 * 
 */
public interface IEIECacheService {

	/**
	 * <p>
	 * Gets the response from the Cassandra database based on the input request.
	 * In case data in not found in the database Exception is thrown.
	 * </p>
	 * 
	 * @param eieReq
	 * @return
	 */
	public EIEResponse getEIEresponse(EIERequest EieRequest);

	/**
	 * <p>
	 * Insert the record in the Cassandra database from the EIEResponse.
	 *  Returns True in case data is written successfully else False.
	 * </p>
	 * 
	 * @param eieRes
	 * @return
	 */
	public Boolean addEIEexternalResponse(EIEResponse EieResponse);

}
