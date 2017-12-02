package com.cuwallet.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cuwallet.commons.dto.NewUser;
import com.cuwallet.commons.dto.UserInformation;
import com.cuwallet.repository.dao.IUserDao;
import com.cuwallet.service.util.IUserService;

@Service
public class UserServiceImpl implements IUserService {

//	private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Resource(name = "userDaoImpl")
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
	
    


}
