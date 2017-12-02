package com.cuwallet.commons.exceptions;

public abstract class ReviewException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private ErrorCode errorCode;
	
	private Object[] messagePositionalArgs;

	public ReviewException(ErrorCode errorCode, Object... messagePositionalArgs) {
		this.errorCode = errorCode;
		this.messagePositionalArgs = messagePositionalArgs;
	}

	public ReviewException(ErrorCode errorCode, Throwable underlyingException, Object... messagePositionalArgs) {
		super(underlyingException);
		this.errorCode = errorCode;
		this.messagePositionalArgs = messagePositionalArgs;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public Object[] getMessagePositionalArgs() {
		return messagePositionalArgs;
	}
}
