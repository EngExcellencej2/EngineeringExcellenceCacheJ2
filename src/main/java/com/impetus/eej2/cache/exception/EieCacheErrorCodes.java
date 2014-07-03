package com.impetus.eej2.cache.exception;

public enum EieCacheErrorCodes {
	UNSUCCESSFULL_READ("unsuccesfullRead",
			"not able to read data from database"), UNSUCCESSFULL_WRITE(
			"unsuccessfullwrite", "not able to write data in db");

	private String errorCode;
	private String errorMessage;

	private EieCacheErrorCodes(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	EieCacheErrorCodes(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

}
