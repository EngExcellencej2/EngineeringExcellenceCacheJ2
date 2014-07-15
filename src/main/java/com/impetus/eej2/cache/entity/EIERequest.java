package com.impetus.eej2.cache.entity;

/**
 * @author hitesh.pawar
 *         <p>
 *         An entry to represent input request to EIE Cache utils
 *         </p>
 * @version 0.1
 */
public class EIERequest {
	
	/**
	 * <p>
	 * Returns <code>EIERequest</code> object attributes.
	 * </p>
	 */
	public String toString(){
		StringBuilder sb = new StringBuilder("EIE Request [");
		sb.append("TN:"+TN+"CC:"+CC+"]");
		return sb.toString();
	}

	private String TN;

	private String CC;

	public String getTN() {
		return TN;
	}

	public void setTN(String tN) {
		TN = tN;
	}

	public String getCC() {
		return CC;
	}

	public void setCC(String cC) {
		CC = cC;
	}

}
