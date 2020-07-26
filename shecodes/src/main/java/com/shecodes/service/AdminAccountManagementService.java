package com.shecodes.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shecodes.entity.User;

/**
 * 
 * @author ntmduyen
 *
 */
@Service
public interface AdminAccountManagementService {
	public List<User> getListUser();
}