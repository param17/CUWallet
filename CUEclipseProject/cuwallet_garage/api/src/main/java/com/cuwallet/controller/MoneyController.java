package com.cuwallet.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cuwallet.commons.constant.UriConstants.Money;
import com.cuwallet.commons.dto.MoneyInformationRequest;
import com.cuwallet.commons.dto.MoneyWallet;
import com.cuwallet.commons.dto.WalletMoneyResponse;
import com.cuwallet.commons.service.ServiceResponse;
import com.cuwallet.commons.util.Protocol;
import com.cuwallet.service.util.IMoneyService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;

@RestController
@Api(value = "money", description = "Money information")
public class MoneyController extends BaseController {

	@Autowired
	private IMoneyService moneyService;

	@ApiOperation(value = "send money to user")
	@ApiResponse(code = 200, message = "Successfully money has been sent", response = ServiceResponse.class)
	@RequestMapping(value = Money.SEND_MONEY, method = RequestMethod.POST)
	public ResponseEntity<ServiceResponse> sendMoney(@Valid @RequestBody MoneyInformationRequest request) {
		ServiceResponse response = new ServiceResponse();
		boolean status = moneyService.sendMoney(request.getMoneyInformation());
		if (status) {
			response.setMessage("money has been transferred");
			response.setCode(HttpStatus.OK.toString());
		} else {
			response.setMessage("ERROR : low balance");
			response.setCode(HttpStatus.EXPECTATION_FAILED.toString());
		}
		response.setProtocol(Protocol.PROTOCOL_JSON);
		return new ResponseEntity<ServiceResponse>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "add money for a user")
	@ApiResponse(code = 200, message = "Successfully money has been added", response = ServiceResponse.class)
	@RequestMapping(value = Money.ADD_MONEY, method = RequestMethod.PUT)
	public ResponseEntity<ServiceResponse> addMoney(@RequestParam(value = "email_id", required = true) String emailId,
			@RequestParam(value = "phone_no", required = true) String phoneNo,
			@RequestParam(value = "transaction_amount", required = true) double amount) {
		ServiceResponse response = new ServiceResponse();
		boolean status = moneyService.addMoney(emailId, phoneNo, amount);
		if (status) {
			response.setMessage("money has been transferred");
			response.setCode(HttpStatus.OK.toString());
		} else {
			response.setMessage("ERROR : low balance in your bank account");
			response.setCode(HttpStatus.EXPECTATION_FAILED.toString());
		}
		response.setProtocol(Protocol.PROTOCOL_JSON);
		return new ResponseEntity<ServiceResponse>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "Get wallet money for a user")
	@ApiResponse(code = 200, message = "Successfully wallet has been fetched", response = WalletMoneyResponse.class)
	@RequestMapping(value = Money.GET_WALLET_MONEY, method = RequestMethod.GET)
	public ResponseEntity<WalletMoneyResponse> getWalletMoney(
			@RequestParam(value = "email_id", required = true) String emailId,
			@RequestParam(value = "phone_no", required = true) String phoneNo) {
		WalletMoneyResponse response = new WalletMoneyResponse();
		MoneyWallet wallet = moneyService.getWalletMoney(emailId, phoneNo);
		response.setMoneyWallet(wallet);
		response.setMessage("money has been fetched");
		response.setCode(HttpStatus.OK.toString());
		response.setProtocol(Protocol.PROTOCOL_JSON);
		return new ResponseEntity<WalletMoneyResponse>(response, HttpStatus.OK);
	}
}
