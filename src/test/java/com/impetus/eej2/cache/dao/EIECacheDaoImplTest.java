package com.impetus.eej2.cache.dao;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.impetus.eej2.cache.entity.EIERequest;
import com.impetus.eej2.cache.entity.EIEResponse;
import com.impetus.eej2.cache.exception.EIECacheCheckedException;


/**
 * JUnit test class for EIECacheServiceImpl
 * 
 * @author EEJ2
 * 
 */
public class EIECacheDaoImplTest {
	
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
	private static Integer writeTTL = 864000; //10 days
	private static Integer readTTL = 8640000; //100 days
	
	private static Boolean response = false;
	private static EIERequest eieRequest = null;
	private static EIEResponse eieResponse = null;
	private static EIECacheDaoImpl eieCacheDaoImpl = null;

	/**
	 * This method creates the EIECacheServiceImpl instance
	 */
	@Before
	public void createObject() {
		eieCacheDaoImpl = new EIECacheDaoImpl();
	}
	
	/**
	 * This method tests the write operation to the Cassandra DB 
	 * @throws EIECacheCheckedException 
	 */
	@Test
	public void testAddEIEexternalResponse() throws EIECacheCheckedException{

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
		eieResponse.setTimeToLive(writeTTL);
		response = eieCacheDaoImpl.addEIEExternalReponse(eieResponse);
		Assert.assertEquals(true, response);
	}
	
	/**
	 * This method tests the read operation from the Cassandra DB
	 * The record inserted above is read back.
	 * @throws EIECacheCheckedException 
	 */
	@Test
	public void testGetEIEresponse() throws EIECacheCheckedException{

		eieRequest = new EIERequest();
		eieRequest.setCountryCode(CC);
		eieRequest.setTelephoneNumber(TN);		
		eieRequest.setTimeToLive(readTTL);
		eieResponse = eieCacheDaoImpl.getEIEResponse(eieRequest);
		Assert.assertEquals("Home", eieResponse.getTnType());
	}
}
