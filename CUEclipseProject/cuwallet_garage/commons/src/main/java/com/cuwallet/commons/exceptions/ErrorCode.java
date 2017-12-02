package com.cuwallet.commons.exceptions;

public enum ErrorCode {
	
	
	REVIEW_ALREADY_SUBMITTED("review.already.submitted"),
	RATING_ALREADY_SUBMITTED("rating.already.submitted"),
	INPUT_INVALID_PAYLOAD("invalid.request.payload"), 
	INVALID_QUERY_PARAM_VALUE("input.query.param.value"),
	INVALID_REQUEST_PAYLOAD_DATATYPE("invalid.request.payload.datatype"),
	RESOURCE_NOT_FOUND("resource.not.found"), 
	MISC_CLIENT_ERROR("misc.client.error"), 
	MISC_SERVER_ERROR("misc.server.error"),
	AUTHORIZATION_ERROR("authorization.error"),
	AUTHENTICATION_ERROR("authentication.error"),
	FORBIDDEN_ERROR("forbidden.error"),
	POGID_MAPPING_ALREADY_SUBMITTED("pogid.mapping.already.submitted"),
	PROFANE_CONTENT_ERROR("profane.content.error"),
	CHILD_CANNOT_BE_PARENT("child.id.cannot.be.parent"),
	CANNOT_CREATE_JOB("job.email.error"),
    POGID_NOT_EXISTS("ids.not.exist.in.table"),
	INVALID_IMG_TYPE("invalid.img");

	private String resourceBundleProperty;

	private ErrorCode(String resourceBundleProperty) {
		this.resourceBundleProperty = resourceBundleProperty;
	}

	public String getResourceBundlePropertyName() {
		return resourceBundleProperty;
	}

}
