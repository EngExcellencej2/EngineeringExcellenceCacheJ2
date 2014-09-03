package com.impetus.eej2.cache.driver;

import java.util.Date;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.impetus.eej2.cache.entity.EIERequest;
import com.impetus.eej2.cache.entity.EIEResponse;
import com.impetus.eej2.cache.service.EIECacheServiceImpl;
import com.impetus.eej2.cache.service.IEIECacheService;

public class CacheDriver {

	//constant logger
	private static final Logger logger = LoggerFactory.getLogger(CacheDriver.class);
	
	public static void main(String args[]) {
		logger.info("inside main method of CacheDriver");
		IEIECacheService cacheService = new EIECacheServiceImpl();
		/*readCache(cacheService);*/
		
		writeCache(cacheService);

	}

	private static void writeCache(IEIECacheService cacheService) {
		logger.info("inside writeCache method of CacheDriver");
		EIEResponse eieResponse=new EIEResponse();
		eieResponse.setCountryCode("CC");
		eieResponse.setCreatedDate(new Date());
		eieResponse.setHlr(12345L);
		eieResponse.setId("ID");
		eieResponse.setImsi(987L);
		eieResponse.setMcc("MCC");
		eieResponse.setMnc("MNC");
		eieResponse.setMsc(457L);
		eieResponse.setRequestType(20L);
		eieResponse.setResponseString(null);
		eieResponse.setSpId("SPID");
		eieResponse.setStatus(0L);
		eieResponse.setSupplierId(90L);
		eieResponse.setSupplierType("supplirType");
		eieResponse.setTelephoneNumber("TN");
		eieResponse.setTnType("TN_TYPE");
		
		
		
		logger.info("Record written in Cache"+cacheService.addEIEexternalResponse(eieResponse));
	}

	private static void readCache(IEIECacheService cacheService) {
		logger.info("inside readCache method of CacheDriver");
		EIERequest eieRequest = new EIERequest();
		eieRequest.setCountryCode("30");
		eieRequest.setTelephoneNumber("6977413285");
		EIEResponse eieResponse = cacheService.getEIEresponse(eieRequest);
		
		logger.info("COUNTRY_CODE " + eieResponse.getCountryCode() + "\n" + "ID "
				+ eieResponse.getId() + "\n" + "MCC " + eieResponse.getMcc()
				+ "\n" + "MNC " + eieResponse.getMnc() + "\n"
				+ "RESPONSE_STRING " + eieResponse.getResponseString() + "\n"
				+ "SPID " + eieResponse.getSpId() + "\n" + "TelephoneNumber "
				+ eieResponse.getTelephoneNumber() + "\n" + "TN_TYPE "
				+ eieResponse.getTnType() + "\n" + "HLR "
				+ eieResponse.getHlr() + "\n" + "IMSI " + eieResponse.getImsi()
				+ "\n" + "MSC " + eieResponse.getMsc() + "\n" + "REQUEST_TYPE "
				+ eieResponse.getRequestType() + "\n" + "STATUS "
				+ eieResponse.getStatus() + "\n" + "CREATED_DT "
				+ eieResponse.getCreatedDate());
	}
	
	//configure the log4j file
	static
	{
		PropertyConfigurator.configure("src\\log4j.properties");
	}

}
