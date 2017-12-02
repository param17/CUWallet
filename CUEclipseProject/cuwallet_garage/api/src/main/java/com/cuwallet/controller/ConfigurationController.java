package com.cuwallet.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cuwallet.commons.constant.ConfigurationType;
import com.cuwallet.commons.constant.UriConstants.Configuration;
import com.cuwallet.commons.dto.MailConfigrationRequest;
import com.cuwallet.commons.dto.MailConfiguration;
import com.cuwallet.commons.service.ServiceResponse;
import com.cuwallet.commons.util.Protocol;
import com.cuwallet.service.util.IConfigurationService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * Created by ajay on 4/2/16.
 */


@RestController
@Api(value = "configuration", description = "Configuration for Exercises")
public class ConfigurationController extends BaseController{

    @Autowired
    private IConfigurationService sampleService;
    
    private String responseMessage = "Successful";
    
    /*private final static Logger LOGGER = LoggerFactory.getLogger(ConfigurationController.class);*/
    
    @ApiOperation(value = "Set Configuration settings for mail")
   	@RequestMapping(value = Configuration.SET_MAIL_CONFIGURATION, method = RequestMethod.POST,  consumes = "application/json")
   	public ResponseEntity<ServiceResponse> setMailConfigration(
   			@Valid @RequestBody MailConfigrationRequest request) {
    	
    	request.setRequestProtocol(Protocol.PROTOCOL_JSON);
    	request.setResponseProtocol(Protocol.PROTOCOL_JSON);
   		MailConfiguration oldMailConfiguration = (MailConfiguration) sampleService
   				.getConfiguration(ConfigurationType.MAIL_CONFIG);
   		oldMailConfiguration.mergeMailConfigration(request
   				.getMailConfiguration());
   		sampleService.setConfiguration(ConfigurationType.MAIL_CONFIG,
   				request.getMailConfiguration());
    	//sampleService.setConfiguration(ConfigurationType.MAIL_CONFIG, request.getMailConfiguration()	);
   		ServiceResponse response = new ServiceResponse();
   		response.setMessage(responseMessage);
   		response.setCode(HttpStatus.OK.toString());
   		response.setProtocol(Protocol.PROTOCOL_JSON);
   		return new ResponseEntity<ServiceResponse>(response, HttpStatus.OK);
   	}
}