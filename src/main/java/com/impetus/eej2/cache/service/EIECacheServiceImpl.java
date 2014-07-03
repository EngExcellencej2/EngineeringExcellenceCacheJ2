package com.impetus.eej2.cache.service;

import com.impetus.eej2.cache.dao.EIECacheDaoImpl;
import com.impetus.eej2.cache.dao.IEIECacheDao;
import com.impetus.eej2.cache.entity.EIERequest;
import com.impetus.eej2.cache.entity.EIEResponse;

public class EIECacheServiceImpl implements IEIECacheService {

	public EIEResponse getEIEresponse(EIERequest eieReq) {
		IEIECacheDao ieieCacheDao = new EIECacheDaoImpl();
		return ieieCacheDao.getEIEResponse(eieReq);
	}

	public Boolean addEIEexternalResponse(EIEResponse eieRes) {
		IEIECacheDao ieieCacheDao = new EIECacheDaoImpl();
		return ieieCacheDao.addEIEExternalReponse(eieRes);
	}

}
