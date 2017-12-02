package com.cuwallet.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cuwallet.commons.constant.UriConstants.BankDetails;
import com.cuwallet.commons.constant.UriConstants.User;
import com.cuwallet.commons.dto.BankAmountInformation;
import com.cuwallet.commons.dto.BankAmountInformationRequest;
import com.cuwallet.commons.dto.UserBankInformation;
import com.cuwallet.commons.dto.UserBankInformationRequest;
import com.cuwallet.commons.service.ServiceResponse;
import com.cuwallet.commons.util.Protocol;
import com.cuwallet.service.util.IPaymentService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;

@RestController
@Api(value = "payment", description = "Payment information")
public class PaymentGatewayController extends BaseController {

	@Autowired
	private IPaymentService paymentService;
	
	@ApiOperation(value = "add bank details for a user")
	@ApiResponse(code = 200, message = "Successfully account details has been added", response = ServiceResponse.class)
	@RequestMapping(value = BankDetails.USER_BANK_DETAILS, method = RequestMethod.POST)
	public ResponseEntity<ServiceResponse> addBankDetails(@Valid @RequestBody UserBankInformationRequest request){
		ServiceResponse response = new ServiceResponse();
		boolean status = paymentService.addBankDetails(request.getUserBankInformation());
		if (status) {
			response.setMessage("account Details has been added");
			response.setCode(HttpStatus.OK.toString());
		}
		else {
			response.setMessage("Account details are already present or invalid details");
			response.setCode(HttpStatus.EXPECTATION_FAILED.toString());
		}
   		response.setProtocol(Protocol.PROTOCOL_JSON);
   		return new ResponseEntity<ServiceResponse>(response, HttpStatus.OK);
	}
	
	@ApiOperation(value = "add dummy bank details")
	@ApiResponse(code = 200, message = "Successfully dummy bank details has been added", response = ServiceResponse.class)
	@RequestMapping(value = BankDetails.BANK_DETAILS , method = RequestMethod.POST)
	public ResponseEntity<ServiceResponse> addDummyBankDetails(@Valid @RequestBody BankAmountInformationRequest request){
		ServiceResponse response = new ServiceResponse();
		boolean status = paymentService.addDummyBankDetails(request.getBankAmountInformation());
		if (status) {
			response.setMessage("bank Details has been added");
			response.setCode(HttpStatus.OK.toString());
		}
		else {
			response.setMessage("Unsuccessful to add bank details or it's already present");
			response.setCode(HttpStatus.EXPECTATION_FAILED.toString());
		}
   		response.setProtocol(Protocol.PROTOCOL_JSON);
   		return new ResponseEntity<ServiceResponse>(response, HttpStatus.OK);
	}
	
	
}
