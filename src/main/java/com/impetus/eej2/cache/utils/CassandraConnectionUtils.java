package com.impetus.eej2.cache.utils;

import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Cluster.Builder;
import com.datastax.driver.core.HostDistance;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.PoolingOptions;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.policies.DCAwareRoundRobinPolicy;
import com.datastax.driver.core.policies.FallthroughRetryPolicy;
import com.impetus.eej2.cache.exception.EieCacheException;

public enum CassandraConnectionUtils {

	/**
	 * @author hitesh.pawar
	 *         <p>
	 *         A Cassandra connection utility to get Session object.The utility
	 *         class is singleton in nature. Implemented through enum to counter
	 *         singleton breaking scenarios.
	 *         </p>
	 *         <p>
	 *         As per driver specification session is thread-safe and one
	 *         session instance is enough for an application. The session
	 *         maintains multiple connections to the cluster nodes, provides
	 *         policies to choose which node to use for each query (round-robin
	 *         on all nodes of the cluster by default), and handles retries for
	 *         failed queries when it makes sense.
	 *         </p>
	 * @version 0.1
	 */

	CONN;

	private static Session session;

	private static Cluster cluster;
	
	private static final Logger logger = LoggerFactory.getLogger(CassandraConnectionUtils.class);

	/**
	 * <p>
	 * Initialize Cassandra Cluster environment from Property Configuration.
	 * </p>
	 * 
	 * @return
	 */
	public static void initializeClusterEnv(){
		logger.info("Initialize EIE Cache Cassandra Cluster environment");
		try{
			Properties prop = PropertyReader.loadProperties();
			Builder builder = null;
			builder = Cluster.builder();
			String localDC = prop.getProperty(IEIECacheConstants.LOCAL_DC_NAME);
			String nodes = prop.getProperty(IEIECacheConstants.NODES);
			String[] nodesList = nodes.split(",");
			int coreConnPerHost = Integer.parseInt(IEIECacheConstants.CORE_CONN_PER_HOST);
			int maxConnPerHost =  Integer.parseInt(IEIECacheConstants.MAX_CONN_PER_HOST);
			int maxSimultaneousReq = Integer.parseInt(prop.getProperty(IEIECacheConstants.MAX_SIMULTANEOUS_REQ_PER_CONN_THRESHHOLD));
			int minSimultaneousReq = Integer.parseInt(prop.getProperty(IEIECacheConstants.MIN_SIMULTANEOUS_REQ_PER_CONN_THRESHHOLD));
			builder.addContactPoints(nodesList);
			logger.debug("Trying to connect to Cassandra Cluster with [Data Center::"+localDC+" and Nodes::"+nodesList);
			logger.info("Set Up Session Pool for connection");
			PoolingOptions poolingOptions = new PoolingOptions();
			poolingOptions.setCoreConnectionsPerHost(HostDistance.LOCAL,coreConnPerHost);
			poolingOptions.setMaxConnectionsPerHost(HostDistance.LOCAL,maxConnPerHost);
			poolingOptions.setMaxSimultaneousRequestsPerConnectionThreshold(HostDistance.LOCAL, maxSimultaneousReq);
			poolingOptions.setMinSimultaneousRequestsPerConnectionThreshold(HostDistance.LOCAL, minSimultaneousReq);
			builder.withPoolingOptions(poolingOptions);
			builder.withLoadBalancingPolicy(new DCAwareRoundRobinPolicy(localDC));
			builder.withRetryPolicy(FallthroughRetryPolicy.INSTANCE);
			cluster = builder.build();
			cluster.init();
			session = cluster.connect();
		}
		catch(Exception e){
			logger.error("Could not establish connection to Cassandra Cluster");
			throw new EieCacheException("Error occurred while connecting to Cassandra Cluster::"+e);
		}
		
        Metadata metadata = cluster.getMetadata();
        logger.info(String.format("Connected to cluster '%s' on %s.", metadata.getClusterName(), metadata.getAllHosts()));
	}
	
	public Session getSession() {
		return session;
	}
	
	
	
	

}
