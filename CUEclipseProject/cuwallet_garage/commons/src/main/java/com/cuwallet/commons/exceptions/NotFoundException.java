package com.cuwallet.commons.exceptions;

public class NotFoundException extends ClientException {

	private static final long serialVersionUID = 1L;

	public NotFoundException(ErrorCode errorCode, Object... args) {
		super(errorCode, args);
	}

	public NotFoundException(ErrorCode errorCode, Throwable underlyingException, Object... args) {
		super(errorCode, underlyingException, args);
	}
}
