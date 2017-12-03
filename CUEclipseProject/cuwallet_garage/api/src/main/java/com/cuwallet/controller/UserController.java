	package com.cuwallet.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cuwallet.commons.constant.UriConstants.User;
import com.cuwallet.commons.dto.AllUserInfoResponse;
import com.cuwallet.commons.dto.NewUser;
import com.cuwallet.commons.dto.NewUserCreationRequest;
import com.cuwallet.commons.dto.UserInfoResponse;
import com.cuwallet.commons.dto.UserInformation;
import com.cuwallet.commons.service.ServiceResponse;
import com.cuwallet.commons.util.Protocol;
import com.cuwallet.service.util.IMoneyService;
import com.cuwallet.service.util.IUserService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;

@RestController
@Api(value = "user", description = "User information")
public class UserController extends BaseController{

	/*private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);*/
	
	@Autowired
	private IUserService userServices;
	
	@Autowired
	private IMoneyService moneyService;
	
	@ApiOperation(value = "create new user")
	@ApiResponse(code = 200, message = "Successfully new user has been created", response = ServiceResponse.class)
	@RequestMapping(value = User.NEW_USER, method = RequestMethod.POST)
	public ResponseEntity<ServiceResponse> setNewUser(@Valid @RequestBody NewUserCreationRequest user){
		ServiceResponse response = new ServiceResponse();
		if (!userServices.hasUser(user.getNewUser().getEmaiId())) {
			userServices.setNewUser(user.getNewUser());
			moneyService.addMoney(user.getNewUser().getEmaiId(), user.getNewUser().getPhoneNo(), 0.0);
			response.setMessage("Successful");
		} else {
			response.setMessage("user is already exist");
		}
   		response.setCode(HttpStatus.OK.toString());
   		response.setProtocol(Protocol.PROTOCOL_JSON);
   		return new ResponseEntity<ServiceResponse>(response, HttpStatus.OK);
	}
	
	@ApiOperation(value = "get user info for registered users")
	@ApiResponse(code = 200, message = "Successfully user info is fetched", response = UserInfoResponse.class)
	@RequestMapping(value = User.GET_USER_INFO, method = RequestMethod.GET)
	public ResponseEntity<UserInfoResponse> getUserInfo(@RequestParam(value = "user_id", required = true) String userId){
		UserInformation user = null;
		user = userServices.getUserInfo(userId);
		UserInfoResponse response = new UserInfoResponse();
		if (user == null) {
			response.setMessage("User is not created");
		}
		else{
			response.setMessage("Successful");
		}
		response.setUser(user);
		response.setCode(HttpStatus.OK.toString());
		response.setProtocol(Protocol.PROTOCOL_JSON);
		return new ResponseEntity<UserInfoResponse>(response, HttpStatus.OK);
	}
	
	@ApiOperation(value = "update existing user")
	@ApiResponse(code = 200, message = "Successfully user has been updated", response = ServiceResponse.class)
	@RequestMapping(value = User.UPDATE_USER_INFO, method = RequestMethod.POST)
	private ResponseEntity<ServiceResponse> updateExistingUser(@Valid @RequestBody NewUserCreationRequest user) {
		ServiceResponse response = new ServiceResponse();
		if (userServices.validUser(user.getNewUser().getEmaiId())) {
			userServices.setNewUser(user.getNewUser());
			response.setMessage("Successful");
		} else {
			response.setMessage("incorrect username or password or invalid user");
		}
		response.setCode(HttpStatus.OK.toString());
		response.setProtocol(Protocol.PROTOCOL_JSON);
		return new ResponseEntity<ServiceResponse>(response, HttpStatus.OK);
	}
	
	@ApiOperation(value = "get all registered user info")
	@ApiResponse(code = 200, message = "Successfully list of user info is fetched", response = AllUserInfoResponse.class)
	@RequestMapping(value = User.GET_ALL_USER_INFO, method = RequestMethod.GET)
	public ResponseEntity<AllUserInfoResponse> getUserInfo(){
		List<UserInformation> userInformation = userServices.getAllUserInfo();
		AllUserInfoResponse response = new AllUserInfoResponse();
		if (userInformation == null) {
			response.setMessage("No user in the database");
		}
		else{
			response.setMessage("Successful");
		}
		response.setUserInformation(userInformation);
		response.setCode(HttpStatus.OK.toString());
		response.setProtocol(Protocol.PROTOCOL_JSON);
		return new ResponseEntity<AllUserInfoResponse>(response, HttpStatus.OK);
	}
	
	@ApiOperation(value = "delete complete user info")
	@ApiResponse(code = 200, message = "Successfully list of user info is deleted", response = ServiceResponse.class)
	@RequestMapping(value = User.GET_ALL_USER_INFO, method = RequestMethod.GET)
	public ResponseEntity<ServiceResponse> deleteUserInfo(@RequestParam(value = "user_id", required = true) String userId,
			@RequestParam(value = "phone_number", required = true) String phoneNo
			){
		ServiceResponse response = new ServiceResponse();
		userServices.deleteUserInfo(userId, phoneNo);
		response.setMessage("user info is successfully deleted");
		response.setCode(HttpStatus.OK.toString());
		response.setProtocol(Protocol.PROTOCOL_JSON);
		return new ResponseEntity<ServiceResponse>(response, HttpStatus.OK);
	}
}
