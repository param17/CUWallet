package com.cuwallet.commons.exceptions;


public class ClientException extends ReviewException {

	private static final long serialVersionUID = 1L;

	public ClientException(ErrorCode errorCode, Object... args) {
		super(errorCode, args);
	}

	public ClientException(ErrorCode errorCode, Throwable underlyingException, Object... args) {
		super(errorCode, underlyingException, args);
	}

}
