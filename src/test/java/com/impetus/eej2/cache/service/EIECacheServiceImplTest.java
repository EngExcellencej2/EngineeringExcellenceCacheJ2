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
		eieResponse.setCc(CC);
		eieResponse.setCrDate(crDate);
		eieResponse.setHLR(hLR);
		eieResponse.setId(id);
		eieResponse.setIMSI(iMSI);
		eieResponse.setMCC(mCC);
		eieResponse.setMNC(mNC);
		eieResponse.setMSC(mSC);
		eieResponse.setReqType(reqType);
		eieResponse.setResString(resString);
		eieResponse.setSPID(sPID);
		eieResponse.setStatus(status);
		eieResponse.setSupplierId(supplierId);
		eieResponse.setSupplierType(supplierType);
		eieResponse.setTN(TN);
		eieResponse.setTN_Type(tN_Type);
		eieResponse.setTTL(300);
		response = eieCacheServiceImpl.addEIEexternalResponse(eieResponse);
		Assert.assertEquals(true, response);
	}

	
	
	@Test
	public void testGetEIEresponse() {

		eieRequest = new EIERequest();
		eieRequest.setCC(CC);
		eieRequest.setTN(TN);
		eieRequest.setTTL(200);
		eieResponse = eieCacheServiceImpl.getEIEresponse(eieRequest);
		Assert.assertNotNull(eieResponse);
	}
	
}
