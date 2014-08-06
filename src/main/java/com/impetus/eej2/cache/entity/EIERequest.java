package com.impetus.eej2.cache.entity;

/**
 * <p>
 * An entry to represent input request to EIE Cache utils
 * </p>
 * 
 * @author hitesh.pawar
 * @version 0.1
 */
public class EIERequest {

	/**
	 * <p>
	 * Returns <code>EIERequest</code> object attributes.
	 * </p>
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder("EIE Request [");
		sb.append("TN:" + TN + "CC:" + CC + "]");
		return sb.toString();
	}

	private String TN;

	private String CC;

	private int TTL;

	public int getTTL() {
		return TTL;
	}

	public void setTTL(int tTL) {
		TTL = tTL;
	}

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
