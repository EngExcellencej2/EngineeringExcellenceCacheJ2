package com.impetus.eej2.cache.exception;

/**
 * @author deepali.choudhary
 * <p>
 * An extension of Checked Exception. The utility exceptions are wrapped in this Custom checked Exception
 * </p>
 * @version 0.2
 */
public class EIECacheCheckedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EIECacheErrorCodes errorCode;
	private  String errorMessage;
	private  String actualerror;
    
	/**
	 * <p>
	 * Constructor for EieCacheException. Sets error code, error message for the exception.
	 * </p>
	 * @param errorCode
	 * @param errorMessage
	 */
	public EIECacheCheckedException(EIECacheErrorCodes errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	/**
	 * <p>
	 * Constructor for EieCacheException. Sets error code, error message and actual error for the exception.
	 * </p>
	 * @param errorCode
	 * @param errorMessage
	 * @param actualerror
	 */
	public EIECacheCheckedException(EIECacheErrorCodes errorCode, String errorMessage,
			String actualerror) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.actualerror = actualerror;
	}

	
	public EIECacheCheckedException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	public EIECacheErrorCodes getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public String getActualerror() {
		return actualerror;
	}

	
    
	
	@Override
	public String toString() {
		return errorMessage + " : " + actualerror;
	}

}
