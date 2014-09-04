package com.impetus.eej2.cache.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.exceptions.InvalidTypeException;
import com.datastax.driver.core.exceptions.NoHostAvailableException;
import com.datastax.driver.core.exceptions.QueryExecutionException;
import com.datastax.driver.core.exceptions.QueryValidationException;
import com.datastax.driver.core.exceptions.UnsupportedFeatureException;
import com.impetus.eej2.cache.dao.EIECacheDaoImpl;
import com.impetus.eej2.cache.dao.IEIECacheDao;
import com.impetus.eej2.cache.entity.EIERequest;
import com.impetus.eej2.cache.entity.EIEResponse;
import com.impetus.eej2.cache.exception.EieCacheCheckedException;
import com.impetus.eej2.cache.exception.EieCacheErrorCodes;

/**
 * <p>
 * An implementation of <code>IEIECacheService</code> to integrate with
 * Application DAO Layer.
 * </p>
 * 
 * @author hitesh.pawar
 * 
 */
public class EIECacheServiceImpl implements IEIECacheService {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory
			.getLogger(EIECacheServiceImpl.class);

	/** The ieie cache dao. */
	private IEIECacheDao ieieCacheDao;

	/**
	 * Instantiates a new EIE cache service impl.
	 */
	public EIECacheServiceImpl() {
		init();
	}

	/**
	 * Inits the.
	 */
	private void init() {
		ieieCacheDao = new EIECacheDaoImpl();
	}

	public EIEResponse getEIEresponse(EIERequest eieReq)
			throws EieCacheCheckedException {
		logger.info("inside getEIEresponse of  EIECacheServiceImpl {}", eieReq);
		EIEResponse eieResponse = null;
		try {
			eieResponse = ieieCacheDao.getEIEResponse(eieReq);
		} catch (NoHostAvailableException noHostAvailableException) {
			logger.error(
					"no host in the cluster can be contacted successfully to execute this query ",
					noHostAvailableException);
			throw new EieCacheCheckedException(
					EieCacheErrorCodes.UNSUCCESSFULL_READ,
					"no host in the cluster can be contacted successfully to execute this query",
					noHostAvailableException.toString());
		} catch (QueryExecutionException queryExecutionException) {
			logger.error(
					"an exception thrown by Cassandra when it cannot execute the query with the requested consistency level successfully",
					queryExecutionException);
			throw new EieCacheCheckedException(
					EieCacheErrorCodes.UNSUCCESSFULL_READ,
					"an exception thrown by Cassandra when it cannot execute the query with the requested consistency level successfully",
					queryExecutionException.toString());
		} catch (QueryValidationException queryValidationException) {
			logger.error(
					" found syntax error, unauthorized or any other validation problem ",
					queryValidationException);
			throw new EieCacheCheckedException(
					EieCacheErrorCodes.UNSUCCESSFULL_READ,
					"found syntax error, unauthorized or any other validation problem",
					queryValidationException.toString());
		} catch (UnsupportedFeatureException unsupportedFeatureException) {
			logger.error(
					"BatchStatement, ResultSet paging and binary values in RegularStatement may be not supported ",
					unsupportedFeatureException);
			throw new EieCacheCheckedException(
					EieCacheErrorCodes.UNSUCCESSFULL_READ,
					"BatchStatement, ResultSet paging and binary values in RegularStatement may be not supported",
					unsupportedFeatureException.toString());
		} catch (IllegalArgumentException illegalArgumentException) {
			logger.error(
					"more values are provided than there is of bound variables in this statement. ",
					illegalArgumentException);
			throw new EieCacheCheckedException(
					EieCacheErrorCodes.UNSUCCESSFULL_READ,
					"more values are provided than there is of bound variables in this statement.",
					illegalArgumentException.toString());
		} catch (InvalidTypeException invalidTypeException) {
			logger.error(
					"any of the provided value is not of correct type to be bound to the corresponding bind variable ",
					invalidTypeException);
			throw new EieCacheCheckedException(
					EieCacheErrorCodes.UNSUCCESSFULL_READ,
					"any of the provided value is not of correct type to be bound to the corresponding bind variable",
					invalidTypeException.toString());
		} catch (NullPointerException nullPointerException) {
			logger.error("null value found ", nullPointerException);
			throw new EieCacheCheckedException(
					EieCacheErrorCodes.UNSUCCESSFULL_READ, "null value found",
					nullPointerException.toString());
		} catch (Exception exception) {
			logger.error("error during reading data ", exception);
			throw new EieCacheCheckedException(
					EieCacheErrorCodes.UNSUCCESSFULL_READ,
					"error during reading data", exception.toString());
		}

		return eieResponse;
	}

	public Boolean addEIEexternalResponse(EIEResponse eieRes)
			throws EieCacheCheckedException {
		logger.info("inside addEIEexternalResponse of  EIECacheServiceImpl {}",
				eieRes);
		Boolean writeFlag = false;
		try
		{
			writeFlag = ieieCacheDao.addEIEExternalReponse(eieRes);
		}
		catch (NoHostAvailableException noHostAvailableException) {
			logger.error("no host in the cluster can be contacted successfully to execute this query ",
					noHostAvailableException);
			throw new EieCacheCheckedException(
					EieCacheErrorCodes.UNSUCCESSFULL_WRITE,
					"no host in the cluster can be contacted successfully to execute this query",
					noHostAvailableException.toString());
		}
		catch (QueryExecutionException queryExecutionException ) {
			logger.error("an exception thrown by Cassandra when it cannot execute the query with the requested consistency level successfully",
					queryExecutionException);
			throw new EieCacheCheckedException(
					EieCacheErrorCodes.UNSUCCESSFULL_WRITE,
					"an exception thrown by Cassandra when it cannot execute the query with the requested consistency level successfully",
					queryExecutionException.toString());
		}
		catch (QueryValidationException queryValidationException) {
			logger.error(" found syntax error, unauthorized or any other validation problem ",
					queryValidationException);
			throw new EieCacheCheckedException(
					EieCacheErrorCodes.UNSUCCESSFULL_WRITE,
					"found syntax error, unauthorized or any other validation problem",
					queryValidationException.toString());
		}
		catch (UnsupportedFeatureException unsupportedFeatureException) {
			logger.error("BatchStatement, ResultSet paging and binary values in RegularStatement may be not supported ",
					unsupportedFeatureException);
			throw new EieCacheCheckedException(
					EieCacheErrorCodes.UNSUCCESSFULL_WRITE,
					"BatchStatement, ResultSet paging and binary values in RegularStatement may be not supported",
					unsupportedFeatureException.toString());
		}
		catch (IllegalArgumentException illegalArgumentException) {
			logger.error("more values are provided than there is of bound variables in this statement. ",
					illegalArgumentException);
			throw new EieCacheCheckedException(
					EieCacheErrorCodes.UNSUCCESSFULL_WRITE,
					"more values are provided than there is of bound variables in this statement.",
					illegalArgumentException.toString());
		} catch (InvalidTypeException invalidTypeException) {
			logger.error("any of the provided value is not of correct type to be bound to the corresponding bind variable ",
					invalidTypeException);
			throw new EieCacheCheckedException(
					EieCacheErrorCodes.UNSUCCESSFULL_WRITE,
					"any of the provided value is not of correct type to be bound to the corresponding bind variable",
					invalidTypeException.toString());
		} catch (NullPointerException nullPointerException) {
			logger.error("null value found ",
					nullPointerException);
			throw new EieCacheCheckedException(
					EieCacheErrorCodes.UNSUCCESSFULL_WRITE,
					"null value found",
					nullPointerException.toString());
		}
		catch (Exception exception) {
			logger.error("error during writing data ", exception);
			throw new EieCacheCheckedException(
					EieCacheErrorCodes.UNSUCCESSFULL_WRITE,
					"error during reading data", exception.toString());
		}

		return writeFlag;
	}

}
