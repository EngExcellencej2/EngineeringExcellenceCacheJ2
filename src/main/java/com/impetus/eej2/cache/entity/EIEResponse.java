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

	private String mnc;

	private String mcc;

	private String spId;

	private Date createdDate;

	private Long requestType;

	private String responseString;

	private Long status;

	private Long imsi;

	private Long hlr;

	private Long msc;

	private String tnType;

	private String supplierType;

	private Long supplierId;

	private Integer timeToLive;

	

	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getCountryCode() {
		return countryCode;
	}



	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}



	public String getTelephoneNumber() {
		return telephoneNumber;
	}



	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}



	public String getMnc() {
		return mnc;
	}



	public void setMnc(String mnc) {
		this.mnc = mnc;
	}



	public String getMcc() {
		return mcc;
	}



	public void setMcc(String mcc) {
		this.mcc = mcc;
	}



	public String getSpId() {
		return spId;
	}



	public void setSpId(String spId) {
		this.spId = spId;
	}



	public Date getCreatedDate() {
		return createdDate;
	}



	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}



	public Long getRequestType() {
		return requestType;
	}



	public void setRequestType(Long requestType) {
		this.requestType = requestType;
	}



	public String getResponseString() {
		return responseString;
	}



	public void setResponseString(String responseString) {
		this.responseString = responseString;
	}



	public Long getStatus() {
		return status;
	}



	public void setStatus(Long status) {
		this.status = status;
	}



	public Long getImsi() {
		return imsi;
	}



	public void setImsi(Long imsi) {
		this.imsi = imsi;
	}



	public Long getHlr() {
		return hlr;
	}



	public void setHlr(Long hlr) {
		this.hlr = hlr;
	}



	public Long getMsc() {
		return msc;
	}



	public void setMsc(Long msc) {
		this.msc = msc;
	}



	public String getTnType() {
		return tnType;
	}



	public void setTnType(String tnType) {
		this.tnType = tnType;
	}



	public String getSupplierType() {
		return supplierType;
	}



	public void setSupplierType(String supplierType) {
		this.supplierType = supplierType;
	}



	public Long getSupplierId() {
		return supplierId;
	}



	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}



	public Integer getTimeToLive() {
		return timeToLive;
	}



	public void setTimeToLive(Integer timeToLive) {
		this.timeToLive = timeToLive;
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
