package com.shecodes.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shecodes.dao.LoginDao;
import com.shecodes.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	public LoginDao loginDao;
	
	public boolean isAuthenticated(String username, String password) {
		return loginDao.isAuthenticated(username, password);
	}
}
