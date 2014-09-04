package com.impetus.eej2.cache.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.impetus.eej2.cache.dao.EIECacheDaoImpl;
import com.impetus.eej2.cache.dao.IEIECacheDao;
import com.impetus.eej2.cache.entity.EIERequest;
import com.impetus.eej2.cache.entity.EIEResponse;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * An implementation of <code>IEIECacheService</code> to integrate with
 * Application DAO Layer.
 * </p>
 * 
 * @author hitesh.pawar
 * 
 */
public class EIECacheServiceImpl implements IEIECacheService {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory
			.getLogger(EIECacheServiceImpl.class);

	/** The ieie cache dao. */
	private IEIECacheDao ieieCacheDao;

	/**
	 * Instantiates a new EIE cache service impl.
	 */
	public EIECacheServiceImpl() {
		init();
	}

	/**
	 * Inits the.
	 */
	private void init() {

		ieieCacheDao = new EIECacheDaoImpl();
	}

	/* (non-Javadoc)
	 * @see com.impetus.eej2.cache.service.IEIECacheService#getEIEresponse(com.impetus.eej2.cache.entity.EIERequest)
	 */
	public EIEResponse getEIEresponse(EIERequest eieReq) {
		logger.info("inside getEIEresponse of  EIECacheServiceImpl {}", eieReq);

		return ieieCacheDao.getEIEResponse(eieReq);
	}

	/* (non-Javadoc)
	 * @see com.impetus.eej2.cache.service.IEIECacheService#addEIEexternalResponse(com.impetus.eej2.cache.entity.EIEResponse)
	 */
	public Boolean addEIEexternalResponse(EIEResponse eieRes) {
		logger.info("inside addEIEexternalResponse of  EIECacheServiceImpl {}",
				eieRes);

		return ieieCacheDao.addEIEExternalReponse(eieRes);
	}

}
