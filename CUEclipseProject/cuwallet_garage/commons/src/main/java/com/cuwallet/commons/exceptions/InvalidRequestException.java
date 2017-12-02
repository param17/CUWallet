package com.cuwallet.commons.exceptions;


public class InvalidRequestException extends ClientException {

	private static final long serialVersionUID = 1L;

	public InvalidRequestException(ErrorCode errorCode, Object... args) {
		super(errorCode, args);
	}

	public InvalidRequestException(ErrorCode errorCode, Throwable underlyingException, Object... args) {
		super(errorCode, underlyingException, args);
	}

}
