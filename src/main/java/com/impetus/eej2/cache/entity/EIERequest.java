package com.impetus.eej2.cache.entity;

import java.io.Serializable;

import com.impetus.eej2.cache.utils.IEIECacheConstants;

/**
 * <p>
 * An entry to represent input request to EIE Cache utils
 * </p>
 * 
 * @author hitesh.pawar
 * @version 0.1
 */
public class EIERequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3210508747283739871L;
	public static final String REQUEST_KEY_VALUE_SEPARATOR=":";

	/**
	 * <p>
	 * Returns <code>EIERequest</code> object attributes.
	 * </p>
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder("EIE Request [");
		sb.append(IEIECacheConstants.TELEPHONE_NUMBER).append(REQUEST_KEY_VALUE_SEPARATOR).append(telephoneNumber)
		.append(IEIECacheConstants.COUNTRY_CODE).append(REQUEST_KEY_VALUE_SEPARATOR).append(countryCode).append("]");
		return sb.toString();
	}

	private String telephoneNumber;

	private String countryCode;
	
	private Integer timeToLive;
    
	/**
	 * 
	 * @return telephoneNumber 
	 */
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
    
	/**
	 * 
	 * @param telephoneNumber
	 */
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
    
	/**
	 * 
	 * @return countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}
    
	/**
	 * 
	 * @param countryCode
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
    
	/**
	 * 
	 * @return timeToLive
	 */
	public Integer getTimeToLive() {
		return timeToLive;
	}
    
	/**
	 * 
	 * @param timeToLive
	 */
	public void setTimeToLive(Integer timeToLive) {
		this.timeToLive = timeToLive;
	}

	

	

}
