package com.impetus.eej2.cache.dao;

import com.impetus.eej2.cache.entity.EIERequest;
import com.impetus.eej2.cache.entity.EIEResponse;

/**
 * @author perwaiz.ali
 * @version 1.0 testing
 */
public interface IEIECacheDao {

	/**
	 * <p>
	 * Gets the response from the Cassandra database based on the input request.
	 * In case data in not found in the database Exception is thrown.
	 * </p>
	 * 
	 * @param eieReq
	 * @return
	 */
	public EIEResponse getEIEResponse(EIERequest eieReq);

	/**
	 * <p>
	 * Insert the record in the Cassandra database from the EIEResponse.
	 *  Returns True in case data is written successfully else False.
	 * </p>
	 * 
	 * @param eieRes
	 * @return
	 */
	public Boolean addEIEExternalReponse(EIEResponse eieRes);
}
