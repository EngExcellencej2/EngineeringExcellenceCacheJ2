package com.impetus.eej2.cache.driver;

import java.util.Date;

import com.impetus.eej2.cache.entity.EIERequest;
import com.impetus.eej2.cache.entity.EIEResponse;
import com.impetus.eej2.cache.service.EIECacheServiceImpl;
import com.impetus.eej2.cache.service.IEIECacheService;

public class CacheDriver {

	public static void main(String args[]) {
		IEIECacheService cacheService = new EIECacheServiceImpl();
		/*readCache(cacheService);*/
		
		writeCache(cacheService);

	}

	private static void writeCache(IEIECacheService cacheService) {
		EIEResponse eieResponse=new EIEResponse();
		eieResponse.setCc("CC");
		eieResponse.setCrDate(new Date());
		eieResponse.setHLR(12345L);
		eieResponse.setId("ID");
		eieResponse.setIMSI(987L);
		eieResponse.setMCC("MCC");
		eieResponse.setMNC("MNC");
		eieResponse.setMSC(457L);
		eieResponse.setReqType(20L);
		eieResponse.setResString(null);
		eieResponse.setSPID("SPID");
		eieResponse.setStatus(0L);
		eieResponse.setSupplierId(90L);
		eieResponse.setSupplierType("supplirType");
		eieResponse.setTN("TN");
		eieResponse.setTN_Type("TN_TYPE");
		
		
		
		System.out.println("Record written in Cache"+cacheService.addEIEexternalResponse(eieResponse));
	}

	private static void readCache(IEIECacheService cacheService) {
		EIERequest eieRequest = new EIERequest();
		eieRequest.setCC("30");
		eieRequest.setTN("6977413285");
		EIEResponse eieResponse = cacheService.getEIEresponse(eieRequest);
		
		System.out.print("COUNTRY_CODE " + eieResponse.getCc() + "\n" + "ID "
				+ eieResponse.getId() + "\n" + "MCC " + eieResponse.getMCC()
				+ "\n" + "MNC " + eieResponse.getMNC() + "\n"
				+ "RESPONSE_STRING " + eieResponse.getResString() + "\n"
				+ "SPID " + eieResponse.getSPID() + "\n" + "TN "
				+ eieResponse.getTN() + "\n" + "TN_TYPE "
				+ eieResponse.getTN_Type() + "\n" + "HLR "
				+ eieResponse.getHLR() + "\n" + "IMSI " + eieResponse.getIMSI()
				+ "\n" + "MSC " + eieResponse.getMSC() + "\n" + "REQUEST_TYPE "
				+ eieResponse.getReqType() + "\n" + "STATUS "
				+ eieResponse.getStatus() + "\n" + "CREATED_DT "
				+ eieResponse.getCrDate());
	}

}
