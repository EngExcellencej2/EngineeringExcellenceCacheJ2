package com.impetus.eej2.cache.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.impetus.eej2.cache.dao.EIECacheDaoImpl;
import com.impetus.eej2.cache.entity.EIERequest;
import com.impetus.eej2.cache.entity.EIEResponse;
import com.impetus.eej2.cache.exception.EIECacheCheckedException;
import com.impetus.eej2.cache.factory.DaoCreationFactory;
import com.impetus.eej2.cache.service.EIECacheServiceImpl;

/**
 * JUnit test class for EIECacheServiceImpl
 * 
 * @author EEJ2
 * 
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(DaoCreationFactory.class)
public class EIECacheServiceImplTest {
	
	
	private static String TN = "12345678";
	private static String CC = "320";
	private static String mCC = "1234";
	private static String mNC = "567";
	private static int timeToLive = 100;	
	private static Boolean response;

	EIERequest eieRequest = null;
	EIEResponse eieResponse = null;
	EIECacheServiceImpl eieCacheServiceImpl = null;
	@Mock
	EIECacheDaoImpl eieCacheDaoImplMock;
	

	/**
	 * This method creates the EIECacheServiceImpl instance
	 */
	@Before
	public void createObject() {
		MockitoAnnotations.initMocks(this);
		PowerMockito.mockStatic(DaoCreationFactory.class);
		Mockito.when(DaoCreationFactory.getDaoObject(Matchers.any(String.class))).thenReturn(eieCacheDaoImplMock);
		eieCacheServiceImpl = new EIECacheServiceImpl("Datastax");
	}
	
	

	/**
	 * This method tests the write operation to the Cassandra DB 
	 * @throws EIECacheCheckedException 
	 */
	@Test
	public void testAddEIEexternalResponse() throws EIECacheCheckedException {
		eieResponse = new EIEResponse();
		eieResponse.setTelephoneNumber(TN);
		eieResponse.setMcc(mCC);
		
		Mockito.when(eieCacheDaoImplMock.addEIEExternalReponse(eieResponse)).thenReturn(true);
		
		response = eieCacheServiceImpl.addEIEexternalResponse(eieResponse);
		Assert.assertEquals(true, response);
	}
	
	
	/**
	 * This method tests the read operation from the Cassandra DB
	 * The record inserted above is read back. 
	 * @throws Exception 
	 */
	@Test
	public void testGetEIEresponse() throws Exception{
		
		eieRequest = new EIERequest();
		eieRequest.setCountryCode(CC);
		eieRequest.setTelephoneNumber(TN);		
		eieRequest.setTimeToLive(timeToLive);
		
		eieResponse = new EIEResponse();
		eieResponse.setTelephoneNumber(TN);
		
		Mockito.when(eieCacheDaoImplMock.getEIEResponse(eieRequest)).thenReturn(eieResponse);
		
		eieResponse = eieCacheServiceImpl.getEIEresponse(eieRequest);
		Assert.assertEquals(eieResponse.getTelephoneNumber(), TN);
		
	}
	
	/**
	 * This method tests the write operation to the Cassandra DB 
	 * @throws EIECacheCheckedException 
	 */
	@Test(expected = Exception.class)
	public void testAddEIEexternalResponseException() throws EIECacheCheckedException {
		eieResponse = new EIEResponse();
		eieResponse.setTelephoneNumber(TN);
		eieResponse.setCountryCode(CC);
		eieResponse.setMnc(mNC);
		eieResponse.setMcc(mCC);
		
		Mockito.when(eieCacheDaoImplMock.addEIEExternalReponse(eieResponse)).thenThrow(new EIECacheCheckedException("General Exception"));
		
		eieCacheServiceImpl.addEIEexternalResponse(eieResponse);
	}
	
	/**
	 * This method tests the read operation to the Cassandra DB 
	 * @throws EIECacheCheckedException 
	 */
	@Test(expected = Exception.class)
	public void testGetEIEresponseException() throws EIECacheCheckedException {
		eieRequest = new EIERequest();
		eieRequest.setCountryCode(CC);
		eieRequest.setTelephoneNumber(TN);		
		eieRequest.setTimeToLive(timeToLive);

		Mockito.when(eieCacheDaoImplMock.getEIEResponse(eieRequest)).thenThrow(new EIECacheCheckedException("General Exception"));
		
		eieCacheServiceImpl.getEIEresponse(eieRequest);
	}

}