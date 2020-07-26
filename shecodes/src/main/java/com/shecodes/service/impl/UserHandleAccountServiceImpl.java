package com.shecodes.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shecodes.dao.AccountDao;
import com.shecodes.entity.Role;
import com.shecodes.entity.User;
import com.shecodes.service.UserHandleAccountService;

@Service
public class UserHandleAccountServiceImpl implements UserHandleAccountService {
	
	@Autowired
	public AccountDao accountDao;
	
	@Override
	public Long getUserIdFromUserName(String userName) {
		return accountDao.getUserIdFromUserName(userName);
	}
	@Override
	public User getUserInfo(Long userId) {
		return accountDao.getUserInfo(userId);
	}
	@Override
	public List<Role> getLstRoleOfUser(Long userId) {
		return accountDao.getListRoleOfUser(userId);
	}
	
	
}
