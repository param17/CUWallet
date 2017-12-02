package com.cuwallet.commons.exceptions;

public class ForbiddenException extends ClientException{
	
	private static final long serialVersionUID = 1L;

	public ForbiddenException(ErrorCode errorCode, Object... args) {
		super(errorCode, args);
	}

	public ForbiddenException(ErrorCode errorCode, Throwable underlyingException, Object... args) {
		super(errorCode, underlyingException, args);
	}
	
}
