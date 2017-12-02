package com.cuwallet.service.util;

import com.cuwallet.commons.dto.NewUser;
import com.cuwallet.commons.dto.UserInformation;

public interface IUserService {

	public void setNewUser(NewUser newUser);

	public boolean hasUser(String userId);

	public UserInformation getUserInfo(String userId);

	public boolean validUser(String userId);
	
}