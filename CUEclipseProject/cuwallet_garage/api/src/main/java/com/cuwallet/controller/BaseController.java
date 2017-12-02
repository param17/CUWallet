package com.cuwallet.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public abstract class BaseController {

	protected UriComponents buildURIComponent(UriComponentsBuilder uriComponentsBuilder, String path, Object... orderedPathArgs) {
		return uriComponentsBuilder.path(path).buildAndExpand(orderedPathArgs);
	}

	protected void setLocationHeader(HttpHeaders httpHeaders, UriComponents uriComponents) {
		httpHeaders.setLocation(uriComponents.toUri());
	}

}
