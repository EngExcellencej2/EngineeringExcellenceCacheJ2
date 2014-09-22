package com.impetus.eej2.cache.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.exceptions.NoHostAvailableException;
import com.datastax.driver.core.exceptions.QueryExecutionException;
import com.datastax.driver.core.exceptions.QueryValidationException;
import com.datastax.driver.core.exceptions.UnsupportedFeatureException;

/**
 * @author sharad.agarwal
 *         <p>
 *        An Exception Handling utility to handle different Type of exception 
 *         </p>
 * @version 0.1
 */
public class ExceptionHandlerTemplate {

	/**
	 * constant logger
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ExceptionHandlerTemplate.class);

	
    /**
     * private constructor
     */
	private ExceptionHandlerTemplate() {
		super();
		// TODO Auto-generated constructor stub
	}



	/**
	 * 
	 * @param exception
	 * @param uniqueIdentifier
	 * @throws EIECacheCheckedException
	 */
	public static final void handleException(EIECacheErrorCodes cacheErrorCodes,Exception exception,
			String uniqueIdentifier) throws EIECacheCheckedException {
		if (exception instanceof NoHostAvailableException) {
			LOGGER.error(
					"for "+uniqueIdentifier+ " no host in the cluster can be contacted successfully to execute this query ",
					exception);
			throw new EIECacheCheckedException(
					cacheErrorCodes,
					"for "+uniqueIdentifier+ " no host in the cluster can be contacted successfully to execute this query",
					exception.toString());
		}
		if (exception instanceof QueryExecutionException) {
			LOGGER.error(
					"for "+uniqueIdentifier+ " an exception thrown by Cassandra when it cannot execute the query with the requested consistency level successfully",
					exception);
			throw new EIECacheCheckedException(
					cacheErrorCodes,
					"for "+uniqueIdentifier+ " an exception thrown by Cassandra when it cannot execute the query with the requested consistency level successfully",
					exception.toString());
		}
		if (exception instanceof QueryValidationException) {
			LOGGER.error(
					"for "+uniqueIdentifier+ " found syntax error, unauthorized or any other validation problem ",
					exception);
			throw new EIECacheCheckedException(
					cacheErrorCodes,
					"for "+uniqueIdentifier+ " found syntax error, unauthorized or any other validation problem",
					exception.toString());
		}
		if (exception instanceof UnsupportedFeatureException) {
			LOGGER.error(
					"for "+uniqueIdentifier+ " BatchStatement, ResultSet paging and binary values in RegularStatement may be not supported ",
					exception);
			throw new EIECacheCheckedException(
					cacheErrorCodes,
					"for "+uniqueIdentifier+ " BatchStatement, ResultSet paging and binary values in RegularStatement may be not supported",
					exception.toString());
		} else {
			LOGGER.error(	"for "+uniqueIdentifier+ "error during reading data ", exception);
			throw new EIECacheCheckedException(
					cacheErrorCodes,
					"for "+uniqueIdentifier+ "error during reading data", exception.toString());
		}
	}
}
