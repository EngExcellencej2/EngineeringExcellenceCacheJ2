package com.impetus.eej2.cache.utils;

import java.util.HashMap;
import java.util.Map;

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

	private   Session session;

	private   Cluster cluster;
	
	private String Node;

	/**
	 * <p>
	 * Return singleton session object
	 * </p>
	 * 
	 * @return
	 */
	public Session getSession() {
		PropertyReader propertyReader =  new PropertyReader();
		Map< String, String> hashMap = new HashMap<>();
		hashMap = propertyReader.printThemAll();
		Node = hashMap.get("NODE");
		System.out.println(Node);
		cluster = Cluster.builder().addContactPoint(Node).build();
		session = cluster.connect();
		return session;
	}
	
	
	
	

}
