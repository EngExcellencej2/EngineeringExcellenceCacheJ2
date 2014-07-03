package com.impetus.eej2.cache.entity;

import java.util.Date;

/**
 * \
 * 
 * @author perwaiz.ali
 *         <p>
 *         An entity to represent EIE Response from EIE Cache.
 *         </p>
 * @version 0.1
 */
public class EIEResponse {

	private String id;

	private String cc;

	private String TN;

	private String MNC;

	private String MCC;

	private String SPID;

	private Date crDate;

	private Long reqType;

	private String resString;

	private Long status;

	private Long IMSI;

	private Long HLR;

	private Long MSC;

	private String TN_Type;

	private String supplierType;

	private Long supplierId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getTN() {
		return TN;
	}

	public void setTN(String tN) {
		TN = tN;
	}

	public String getMNC() {
		return MNC;
	}

	public void setMNC(String mNC) {
		MNC = mNC;
	}

	public String getMCC() {
		return MCC;
	}

	public void setMCC(String mCC) {
		MCC = mCC;
	}

	public String getSPID() {
		return SPID;
	}

	public void setSPID(String sPID) {
		SPID = sPID;
	}

	public Date getCrDate() {
		return crDate;
	}

	public void setCrDate(Date crDate) {
		this.crDate = crDate;
	}

	public Long getReqType() {
		return reqType;
	}

	public void setReqType(Long reqType) {
		this.reqType = reqType;
	}

	public String getResString() {
		return resString;
	}

	public void setResString(String resString) {
		this.resString = resString;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getIMSI() {
		return IMSI;
	}

	public void setIMSI(Long iMSI) {
		IMSI = iMSI;
	}

	public Long getHLR() {
		return HLR;
	}

	public void setHLR(Long hLR) {
		HLR = hLR;
	}

	public Long getMSC() {
		return MSC;
	}

	public void setMSC(Long mSC) {
		MSC = mSC;
	}

	public String getTN_Type() {
		return TN_Type;
	}

	public void setTN_Type(String tN_Type) {
		TN_Type = tN_Type;
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

}
