package com.impetus.eej2.cache.exception;

/**
 * @author sharad.agarwal
 * <p>
 * Exception Framework For EIE Cache Utils.
 * </p>
 *
 */
public class EieCacheException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EieCacheErrorCodes errorCode;
	private String errorMessage;
	private String actualerror;

	public EieCacheException(EieCacheErrorCodes errorCode, String errorMessage) {

		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public EieCacheException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	public EieCacheErrorCodes getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(EieCacheErrorCodes errorCode) {
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
    
	/**
	 * <p>
	 * Constructor for EieCacheException. Sets error code, error message and actual error for the Run Time exception.
	 * </p>
	 * @param errorCode
	 * @param errorMessage
	 * @param actualerror
	 */
	public EieCacheException(EieCacheErrorCodes errorCode, String errorMessage,
			String actualerror) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.actualerror = actualerror;
	}

	@Override
	public String toString() {
		return errorMessage + "due to" + actualerror;
	}

}
