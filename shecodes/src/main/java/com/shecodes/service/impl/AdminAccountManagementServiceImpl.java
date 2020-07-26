package com.shecodes.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shecodes.dao.AccountDao;
import com.shecodes.entity.User;
import com.shecodes.service.AdminAccountManagementService;

/**
 * 
 * @author ntmduyen
 *
 */
@Service
public class AdminAccountManagementServiceImpl implements AdminAccountManagementService {

	@Autowired
	AccountDao accountDao;

	/**
	 * Get list account of user
	 */
	@Override
	public List<User> getListUser() {
		return accountDao.getAllUser();
	}

}
