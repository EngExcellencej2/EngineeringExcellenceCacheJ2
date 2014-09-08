package com.impetus.eej2.cache.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.impetus.eej2.cache.dao.IEIECacheDao;
import com.impetus.eej2.cache.entity.EIERequest;
import com.impetus.eej2.cache.entity.EIEResponse;
import com.impetus.eej2.cache.exception.EieCacheCheckedException;
import com.impetus.eej2.cache.factory.DaoCreationFactory;

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
	public EIECacheServiceImpl(String driverType) {
		init(driverType);
	}

	/**
	 * Inits the.
	 */
	private void init(String driverType) {
		
		ieieCacheDao = DaoCreationFactory.getDaoObject(driverType);
	}

	public EIEResponse getEIEresponse(EIERequest eieReq) throws EieCacheCheckedException
			 {
		logger.info("inside getEIEresponse of  EIECacheServiceImpl {}", eieReq);
		EIEResponse eieResponse = null;
				eieResponse = ieieCacheDao.getEIEResponse(eieReq);
		return eieResponse;
	}

	public Boolean addEIEexternalResponse(EIEResponse eieRes)
			throws EieCacheCheckedException {
		logger.info("inside addEIEexternalResponse of  EIECacheServiceImpl {}",
				eieRes);
		Boolean writeFlag = false;
		writeFlag = ieieCacheDao.addEIEExternalReponse(eieRes);
		return writeFlag;
	}

}
