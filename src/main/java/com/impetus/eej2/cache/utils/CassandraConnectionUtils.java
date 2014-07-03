package com.impetus.eej2.cache.utils;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

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

	private static final Session session;

	private static final Cluster cluster;

	private static final String NODE = "";

	static {
		cluster = Cluster.builder().addContactPoint("localhost").build();
		session = cluster.connect();
	}

	/**
	 * <p>
	 * Return singleton session object
	 * </p>
	 * 
	 * @return
	 */
	public Session getSession() {
		return session;
	}

}
