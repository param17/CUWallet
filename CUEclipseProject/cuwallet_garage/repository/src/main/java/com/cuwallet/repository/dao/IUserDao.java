package com.cuwallet.repository.dao;

import java.util.List;

import com.cuwallet.commons.dto.NewUser;
import com.cuwallet.commons.dto.UserInformation;

public interface IUserDao {

	void setNewUser(NewUser newUser);

	boolean hasUser(String userId);

	UserInformation getUserInfo(String userId);

	boolean validUser(String userId);

	List<UserInformation> getAllUserInfo();

}
