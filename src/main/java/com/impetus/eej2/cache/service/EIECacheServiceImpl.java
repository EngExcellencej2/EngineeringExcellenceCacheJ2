package com.impetus.eej2.cache.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.impetus.eej2.cache.dao.EIECacheDaoImpl;
import com.impetus.eej2.cache.dao.IEIECacheDao;
import com.impetus.eej2.cache.entity.EIERequest;
import com.impetus.eej2.cache.entity.EIEResponse;

/**
 * <p>
 * An implementation of <code>IEIECacheService</code> to integrate with Application DAO Layer.
 * </p>
 * @author hitesh.pawar
 *
 */
public class EIECacheServiceImpl implements IEIECacheService {

	private static final Logger logger = LoggerFactory.getLogger(EIECacheServiceImpl.class);
	
	public EIEResponse getEIEresponse(EIERequest eieReq) {
		logger.info("inside getEIEresponse of  EIECacheServiceImpl {}",eieReq);
		IEIECacheDao ieieCacheDao = new EIECacheDaoImpl();
		return ieieCacheDao.getEIEResponse(eieReq);
	}

	public Boolean addEIEexternalResponse(EIEResponse eieRes) {
		logger.info("inside addEIEexternalResponse of  EIECacheServiceImpl {}",eieRes);
		IEIECacheDao ieieCacheDao = new EIECacheDaoImpl();
		return ieieCacheDao.addEIEExternalReponse(eieRes);
	}

}
