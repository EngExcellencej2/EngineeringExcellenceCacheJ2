package com.impetus.eej2.cache.exception;

public class EieCacheException extends RuntimeException {

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
