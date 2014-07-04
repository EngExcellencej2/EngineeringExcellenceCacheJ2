package com.impetus.eej2.cache.dao;

import com.impetus.eej2.cache.entity.EIERequest;
import com.impetus.eej2.cache.entity.EIEResponse;

/**
 * @author perwaiz.ali
 * @version 1.0
 */
public interface IEIECacheDao {

	/**
	 * <p>
	 * Gets the response from the Cassandra database based on the input request.
	 * In case data in not found in the database Exception is thrown.
	 * </p>
	 * 
	 * @param eieReq
	 * @return the <code>EIEReponse to service Layer
	 */
	public EIEResponse getEIEResponse(EIERequest eieReq);

	/**
	 * <p>
	 * Insert the record in the Cassandra database from the EIEResponse.
	 *  Returns True in case data is written successfully else False.
	 * </p>
	 * 
	 * @param eieRes
	 * @return the output in <code>boolean</code> format to service layer
	 */
	public Boolean addEIEExternalReponse(EIEResponse eieRes);
}
