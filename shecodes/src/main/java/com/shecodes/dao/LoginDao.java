package com.shecodes.dao;

import org.springframework.stereotype.Service;

@Service
public class LoginDao {
	public boolean isAuthenticated(String username, String password) {
		return true;
	}
}
