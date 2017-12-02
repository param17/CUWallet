package com.cuwallet.commons.service;

import java.io.Serializable;

import com.cuwallet.commons.util.Protocol;

public class ServiceRequest implements Serializable {

	private static final long serialVersionUID = -8103314484670490911L;
	
	Protocol requestProtocol;
	
	Protocol responseProtocol;

	public Protocol getRequestProtocol() {
		return requestProtocol;
	}

	public void setRequestProtocol(Protocol requestProtocol) {
		this.requestProtocol = requestProtocol;
	}

	public Protocol getResponseProtocol() {
		return responseProtocol;
	}

	public void setResponseProtocol(Protocol responseProtocol) {
		this.responseProtocol = responseProtocol;
	}

}
