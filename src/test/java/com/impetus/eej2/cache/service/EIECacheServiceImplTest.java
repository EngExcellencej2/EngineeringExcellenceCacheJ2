package com.impetus.eej2.cache.service;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.impetus.eej2.cache.entity.EIERequest;
import com.impetus.eej2.cache.entity.EIEResponse;
import com.impetus.eej2.cache.service.EIECacheServiceImpl;

public class EIECacheServiceImplTest {
	private static String TN = "12345678";
	private static String CC = "320";
	private static String id = "101";
	private static String mCC = "1234";
	private static String mNC = "567";
	private static String resString = "responseString";
	private static String sPID = "222";
	private static String supplierType = "supplierType";
	private static String tN_Type = "Home";
	private static Date crDate = new Date();
	private static Long hLR = 10l;
	private static Long iMSI = 11l;
	private static Long mSC = 22l;
	private static Long reqType = 33l;
	private static Long status = 501l;
	private static Long supplierId = 55555l;

	private static Boolean response;

	EIERequest eieRequest;
	EIEResponse eieResponse;
	EIECacheServiceImpl eieCacheServiceImpl;

	@Before
	public void createObject() {
		eieCacheServiceImpl = new EIECacheServiceImpl();
	}

	
	

	@Test
	public void testAddEIEexternalResponse() {

		eieResponse = new EIEResponse();
		eieResponse.setCountryCode(CC);
		eieResponse.setCreatedDate(crDate);
		eieResponse.setHlr(hLR);
		eieResponse.setId(id);
		eieResponse.setImsi(iMSI);
		eieResponse.setMcc(mCC);
		eieResponse.setMnc(mNC);
		eieResponse.setMsc(mSC);
		eieResponse.setRequestType(reqType);
		eieResponse.setResponseString(resString);
		eieResponse.setSpId(sPID);
		eieResponse.setStatus(status);
		eieResponse.setSupplierId(supplierId);
		eieResponse.setSupplierType(supplierType);
		eieResponse.setTelephoneNumber(TN);
		eieResponse.setTnType(tN_Type);
		eieResponse.setTimeToLive(300);
		response = eieCacheServiceImpl.addEIEexternalResponse(eieResponse);
		Assert.assertEquals(true, response);
	}

	
	
	@Test
	public void testGetEIEresponse() {

		eieRequest = new EIERequest();
		eieRequest.setCountryCode(CC);
		eieRequest.setTelephoneNumber(TN);
		eieRequest.setTimeToLive(200);
		eieResponse = eieCacheServiceImpl.getEIEresponse(eieRequest);
		Assert.assertNotNull(eieResponse);
	}
	
}
