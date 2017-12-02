package com.cuwallet.commons.service;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.cuwallet.commons.util.Protocol;

public class ServiceResponse implements Serializable {

	private static final long serialVersionUID = -3871687648953885320L;
	
	@NotNull
	@NotEmpty
	String message;
	
	@NotNull
	@NotEmpty
	String code;
	
	Protocol protocol;

	public Protocol getProtocol() {
		return protocol;
	}

	public void setProtocol(Protocol protocol) {
		this.protocol = protocol;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
