package com.impetus.eej2.cache.exception;

public class EIECacheCheckedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EIECacheErrorCodes errorCode;
	private String errorMessage;
	private String actualerror;

	public EIECacheCheckedException(EIECacheErrorCodes errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	/**
	 * <p>
	 * Constructor for EieCacheException. Sets error code, error message and actual error for the Run Time exception.
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

	public void setErrorCode(EIECacheErrorCodes errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public String getActualerror() {
		return actualerror;
	}

	public void setActualerror(String actualerror) {
		this.actualerror = actualerror;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
    
	
	@Override
	public String toString() {
		return errorMessage + " : " + actualerror;
	}

}
