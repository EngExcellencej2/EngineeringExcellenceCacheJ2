package com.impetus.eej2.cache.utils;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Cluster.Builder;
import com.datastax.driver.core.HostDistance;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.PoolingOptions;
import com.datastax.driver.core.ProtocolOptions;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.policies.DCAwareRoundRobinPolicy;
import com.datastax.driver.core.policies.FallthroughRetryPolicy;
import com.impetus.eej2.cache.exception.EIECacheCheckedException;
import com.impetus.eej2.cache.exception.EIECacheRunTimeException;

/**
 * @author hitesh.pawar
 *         <p>
 *         A Cassandra connection utility to get Session object.The utility
 *         class is singleton in nature. Implemented through enum to counter
 *         singleton breaking scenarios.
 *         </p>
 *         <p>
 *         As per driver specification session is thread-safe and one session
 *         instance is enough for an application. The session maintains multiple
 *         connections to the cluster nodes, provides policies to choose which
 *         node to use for each query (round-robin on all nodes of the cluster
 *         by default), and handles retries for failed queries when it makes
 *         sense.
 *         </p>
 * @version 0.1
 */

public enum CassandraConnectionUtils {

	CONN;

	private static Session session;

	private static Cluster cluster;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CassandraConnectionUtils.class);

	static {
		LOGGER.info("Initialize EIE Cache Cassandra Cluster environment");
		try {
			Properties prop = PropertyReader.loadProperties();
			Builder builder = null;
			builder = Cluster.builder();
			String localDC = prop.getProperty(IEIECacheConstants.LOCAL_DC_NAME);
			String nodes = prop.getProperty(IEIECacheConstants.NODES);
			String[] nodesList = nodes.split(",");
			int coreConnPerHost = Integer.parseInt(prop
					.getProperty(IEIECacheConstants.CORE_CONN_PER_HOST));
			int maxConnPerHost = Integer.parseInt(prop
					.getProperty(IEIECacheConstants.MAX_CONN_PER_HOST));
			int maxSimultaneousReq = Integer
					.parseInt(prop
							.getProperty(IEIECacheConstants.MAX_SIMULTANEOUS_REQ_PER_CONN_THRESHHOLD));
			int minSimultaneousReq = Integer
					.parseInt(prop
							.getProperty(IEIECacheConstants.MIN_SIMULTANEOUS_REQ_PER_CONN_THRESHHOLD));
			builder.addContactPoints(nodesList);
			LOGGER.debug("Trying to connect to Cassandra Cluster with [Data Center::"
					+ localDC + " and Nodes::" + nodesList);
			LOGGER.info("Set Up Session Pool for connection");
			PoolingOptions poolingOptions = new PoolingOptions();
			poolingOptions.setCoreConnectionsPerHost(HostDistance.LOCAL,
					coreConnPerHost);
			poolingOptions.setMaxConnectionsPerHost(HostDistance.LOCAL,
					maxConnPerHost);
			poolingOptions.setMaxSimultaneousRequestsPerConnectionThreshold(
					HostDistance.LOCAL, maxSimultaneousReq);
			poolingOptions.setMinSimultaneousRequestsPerConnectionThreshold(
					HostDistance.LOCAL, minSimultaneousReq);
			builder.withPoolingOptions(poolingOptions);
			builder.withLoadBalancingPolicy(new DCAwareRoundRobinPolicy(localDC));
			builder.withRetryPolicy(FallthroughRetryPolicy.INSTANCE);
			cluster = builder.build();
			cluster.getConfiguration().getProtocolOptions()
					.setCompression(ProtocolOptions.Compression.LZ4);
			cluster.init();
			session = cluster.connect();
		} catch (RuntimeException exception) {
			LOGGER.error(
					"Could not establish connection to Cassandra Cluster ",
					exception);
			throw new EIECacheRunTimeException(
					"Error occurred while connecting to Cassandra Cluster::"
							+ exception);
		}

		Metadata metadata = cluster.getMetadata();
		LOGGER.info(String.format("Connected to cluster '%s' on %s.",
				metadata.getClusterName(), metadata.getAllHosts()));
	}

	public Session getSession() {
		return session;
	}

}
