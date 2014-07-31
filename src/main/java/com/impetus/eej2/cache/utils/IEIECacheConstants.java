package com.impetus.eej2.cache.utils;

/**
 * @author hitesh.pawar
 * <p>
 * Defines EIE Cache Utils Constants.
 * </p>
 *
 */
public interface IEIECacheConstants {
    
	/**
	 * <p>
	 * Data Center name where application is installed
	 * <p>
	 */
	public static final String LOCAL_DC_NAME = "LOCAL_DC_NAME";
	
	/**
	 * <p>
	 * Nodes of Local Data Center
	 * </p>
	 */
	public static final String NODES = "NODES";
	
	/**
	 * <p>
	 * Core number of connections per host.For the provided distance, this will set the number of connections initially created and kept open to each host of that distance.
	 * </p>
	 */
	public static final String CORE_CONN_PER_HOST = "CORE_CONN_PER_HOST";
	
	/**
	 * <p>
	 * Maximum number of connections per host.For the provided distance, this will set the number of connections that can be created per host at that distance.
	 * </p>
	 */
	public static final String MAX_CONN_PER_HOST = "MAX_CONN_PER_HOST";
	
	/**
	 * <p>
	 * number of simultaneous requests on all connections to an host after which more connections are created. If all the connections opened to an host at distance distance connection are handling more than this number of simultaneous requests and there is less than getMaxConnectionsPerHost(com.datastax.driver.core.HostDistance) connections open to this host, a new connection is open. 
     *  Note that a given connection cannot handle more than 128 simultaneous requests (protocol limitation). 
     *  The default value for this option is 100 for LOCAL and REMOTE hosts.The value ranges between 0 and 128.
	 * </p>
	 */
	public static final String MAX_SIMULTANEOUS_REQ_PER_CONN_THRESHHOLD = "MAX_SIMULTANEOUS_REQ_PER_CONN_THRESHHOLD";
	
	/**
	 * <p>
	 * Constant for Data Stax Cassandra Driver
	 * </p>
	 */
	public static final String DATA_STAX_DRIVER = "DATASTAX";
	
	//Add constants for other Cassandra Driver like Hector etc.
	
	
	/**
	 * <p>
	 * Number of simultaneous requests on a connection below which connections in excess are reclaimed.
     * If an opened connection to an host at distance distance handles less than this number of simultaneous requests and there is more than getCoreConnectionsPerHost(com.datastax.driver.core.HostDistance) connections open to this host, the connection is closed. 
     * The default value for this option is 25 for LOCAL and REMOTE hosts.The value ranges between 0 and 128.
	 * </p>
	 */
	public static final String MIN_SIMULTANEOUS_REQ_PER_CONN_THRESHHOLD = "MIN_SIMULTANEOUS_REQ_PER_CONN_THRESHHOLD";	
	public static final String ID = "ID";
	public static final String COUNTRY_CODE = "countryCode";
	public static final String TELEPHONE_NUMBER = "telephoneNumber";
	public static final String MNC = "MNC";
	public static final String MCC = "MCC";
	public static final String SPID = "SPID";
	public static final String CREATED_DATE = "Created Date";
	public static final String REQUEST_TYPE = "Request Type";
	public static final String RESPONSE_STRING = "Response String";
	public static final String STATUS = "Status";
	public static final String IMSI = "IMSI";
	public static final String HLR = "HLR";
	public static final String MSC = "MSC";
	public static final String TN_TYPE = "TN_TYPE";
	public static final String SUPPLIER_TYPE = "Supplier Type";
	public static final String SUPPLIER_ID = "Supplier ID";
	
}
