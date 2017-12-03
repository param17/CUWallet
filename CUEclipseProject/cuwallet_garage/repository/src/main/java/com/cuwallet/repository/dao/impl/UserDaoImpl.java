package com.cuwallet.repository.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.cuwallet.commons.dto.NewUser;
import com.cuwallet.commons.dto.UserInformation;
import com.cuwallet.repository.dao.IUserDao;

@Repository("userDaoImpl")
public class UserDaoImpl implements IUserDao {

	@Resource(name = "userCassandraDao")
	private IUserDao userDao;

	@Override
	public void setNewUser(NewUser newUser) {
		userDao.setNewUser(newUser);
	}

	@Override
	public boolean hasUser(String userId) {
		return userDao.hasUser(userId);
	}

	@Override
	public UserInformation getUserInfo(String userId) {
		return userDao.getUserInfo(userId);
	}

	@Override
	public boolean validUser(String userId) {
		return userDao.validUser(userId);
	}

	@Override
	public List<UserInformation> getAllUserInfo() {
		return userDao.getAllUserInfo();
	}

	@Override
	public void deleteUserInfo(String userId, String phoneNo) {
		userDao.deleteUserInfo(userId, phoneNo);

	}

}
