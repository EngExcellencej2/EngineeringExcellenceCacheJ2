package com.impetus.eej2.cache.entity;

import java.io.Serializable;
import java.util.Date;

import com.impetus.eej2.cache.utils.IEIECacheConstants;

/**
 * \
 * 
 * @author perwaiz.ali
 *         <p>
 *         An entity to represent EIE Response from EIE Cache.
 *         </p>
 * @version 0.1
 */
public class EIEResponse implements Serializable {

	private static final long serialVersionUID = 7966240255235119001L;
	public static final String RESPONSE_KEY_VALUE_SEPARATOR=":";

	private String id;

	private String countryCode;

	private String telephoneNumber;
	
	//Mobile Network Code
	private String mnc;
	
	//Mobile Country Code
	private String mcc;

	//Service Provider Identifier (ID)
	private String spId;

	private Date createdDate;

	private Long requestType;

	private String responseString;

	private Long status;

	//International Mobile Subscriber Identity
	private Long imsi;

	//Home Location Register
	private Long hlr;

	//Mobile Service Code
	private Long msc;
	
	//Telephone Number (TN) type
	private String tnType;

	private String supplierType;

	private Long supplierId;

	private Integer timeToLive;

	// data availability in db 
	private Boolean isDataFound;
	
	// message related to record found or not in db
	private String statusMessage;


	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}




	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}




	/**
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}




	/**
	 * @param countryCode the countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}




	/**
	 * @return the telephoneNumber
	 */
	public String getTelephoneNumber() {
		return telephoneNumber;
	}




	/**
	 * @param telephoneNumber the telephoneNumber to set
	 */
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}




	/**
	 * @return the mnc
	 */
	public String getMnc() {
		return mnc;
	}




	/**
	 * @param mnc the mnc to set
	 */
	public void setMnc(String mnc) {
		this.mnc = mnc;
	}




	/**
	 * @return the mcc
	 */
	public String getMcc() {
		return mcc;
	}




	/**
	 * @param mcc the mcc to set
	 */
	public void setMcc(String mcc) {
		this.mcc = mcc;
	}




	/**
	 * @return the spId
	 */
	public String getSpId() {
		return spId;
	}




	/**
	 * @param spId the spId to set
	 */
	public void setSpId(String spId) {
		this.spId = spId;
	}




	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}




	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}




	/**
	 * @return the requestType
	 */
	public Long getRequestType() {
		return requestType;
	}




	/**
	 * @param requestType the requestType to set
	 */
	public void setRequestType(Long requestType) {
		this.requestType = requestType;
	}




	/**
	 * @return the responseString
	 */
	public String getResponseString() {
		return responseString;
	}




	/**
	 * @param responseString the responseString to set
	 */
	public void setResponseString(String responseString) {
		this.responseString = responseString;
	}




	/**
	 * @return the status
	 */
	public Long getStatus() {
		return status;
	}




	/**
	 * @param status the status to set
	 */
	public void setStatus(Long status) {
		this.status = status;
	}




	/**
	 * @return the imsi
	 */
	public Long getImsi() {
		return imsi;
	}




	/**
	 * @param imsi the imsi to set
	 */
	public void setImsi(Long imsi) {
		this.imsi = imsi;
	}




	/**
	 * @return the hlr
	 */
	public Long getHlr() {
		return hlr;
	}




	/**
	 * @param hlr the hlr to set
	 */
	public void setHlr(Long hlr) {
		this.hlr = hlr;
	}




	/**
	 * @return the msc
	 */
	public Long getMsc() {
		return msc;
	}




	/**
	 * @param msc the msc to set
	 */
	public void setMsc(Long msc) {
		this.msc = msc;
	}




	/**
	 * @return the tnType
	 */
	public String getTnType() {
		return tnType;
	}




	/**
	 * @param tnType the tnType to set
	 */
	public void setTnType(String tnType) {
		this.tnType = tnType;
	}




	/**
	 * @return the supplierType
	 */
	public String getSupplierType() {
		return supplierType;
	}




	/**
	 * @param supplierType the supplierType to set
	 */
	public void setSupplierType(String supplierType) {
		this.supplierType = supplierType;
	}




	/**
	 * @return the supplierId
	 */
	public Long getSupplierId() {
		return supplierId;
	}




	/**
	 * @param supplierId the supplierId to set
	 */
	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}




	/**
	 * @return the timeToLive
	 */
	public Integer getTimeToLive() {
		return timeToLive;
	}




	/**
	 * @param timeToLive the timeToLive to set
	 */
	public void setTimeToLive(Integer timeToLive) {
		this.timeToLive = timeToLive;
	}




	/**
	 * @return the isDataFound
	 */
	public Boolean getIsDataFound() {
		return isDataFound;
	}




	/**
	 * @param isDataFound the isDataFound to set
	 */
	public void setIsDataFound(Boolean isDataFound) {
		this.isDataFound = isDataFound;
	}

    


	/**
	 * @return the statusMessage
	 */
	public String getStatusMessage() {
		return statusMessage;
	}




	/**
	 * @param statusMessage the statusMessage to set
	 */
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}




	/**
	 * <p>
	 * Returns <code>EIEResponse</code> object attributes.
	 * </p>
	 */
	public String toString(){
		StringBuilder sb = new StringBuilder("EIE Response [");
		sb.append(IEIECacheConstants.ID).append(RESPONSE_KEY_VALUE_SEPARATOR).append(id)
				.append(IEIECacheConstants.COUNTRY_CODE).append(RESPONSE_KEY_VALUE_SEPARATOR).append(countryCode)
				.append(IEIECacheConstants.TELEPHONE_NUMBER).append(RESPONSE_KEY_VALUE_SEPARATOR).append(telephoneNumber)
				.append(IEIECacheConstants.MNC).append(RESPONSE_KEY_VALUE_SEPARATOR).append(mnc)
				.append(IEIECacheConstants.MCC).append(RESPONSE_KEY_VALUE_SEPARATOR).append(mcc)
				.append(IEIECacheConstants.SPID).append(RESPONSE_KEY_VALUE_SEPARATOR).append(spId)
				.append(IEIECacheConstants.CREATED_DATE).append(RESPONSE_KEY_VALUE_SEPARATOR).append(createdDate)
				.append(IEIECacheConstants.REQUEST_TYPE).append(RESPONSE_KEY_VALUE_SEPARATOR).append(requestType)
				.append(IEIECacheConstants.RESPONSE_STRING).append(RESPONSE_KEY_VALUE_SEPARATOR).append(responseString)
				.append(IEIECacheConstants.STATUS).append(RESPONSE_KEY_VALUE_SEPARATOR).append(status)
				.append(IEIECacheConstants.IMSI).append(RESPONSE_KEY_VALUE_SEPARATOR).append(imsi)
				.append(IEIECacheConstants.HLR).append(RESPONSE_KEY_VALUE_SEPARATOR).append(hlr)
				.append(IEIECacheConstants.MSC).append(RESPONSE_KEY_VALUE_SEPARATOR).append(msc)
				.append(IEIECacheConstants.TN_TYPE).append(RESPONSE_KEY_VALUE_SEPARATOR).append(tnType)
				.append(IEIECacheConstants.SUPPLIER_TYPE).append(RESPONSE_KEY_VALUE_SEPARATOR).append(supplierType)
				.append(IEIECacheConstants.SUPPLIER_ID).append(RESPONSE_KEY_VALUE_SEPARATOR).append(supplierId).append("]");
		return sb.toString();
	}

}
