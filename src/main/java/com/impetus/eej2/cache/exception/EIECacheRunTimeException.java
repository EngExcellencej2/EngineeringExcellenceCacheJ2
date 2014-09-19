package com.impetus.eej2.cache.exception;

/**
 * @author sharad.agarwal
 * <p>
 * An extension of Checked Exception. The utility exceptions are wrapped in this Custom unchecked Exception
 * </p>
 *
 */
public class EIECacheRunTimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EIECacheErrorCodes errorCode;
	private String errorMessage;
	private String actualerror;

	public EIECacheRunTimeException(EIECacheErrorCodes errorCode, String errorMessage) {

		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public EIECacheRunTimeException(String errorMessage) {
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

	
	/**
	 * <p>
	 * Constructor for EieCacheException. Sets error code, error message and actual error for the Run Time exception.
	 * </p>
	 * @param errorCode
	 * @param errorMessage
	 * @param actualerror
	 */
	public EIECacheRunTimeException(EIECacheErrorCodes errorCode, String errorMessage,
			String actualerror) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.actualerror = actualerror;
	}

	@Override
	public String toString() {
		return errorMessage + " : " + actualerror;
	}

}
