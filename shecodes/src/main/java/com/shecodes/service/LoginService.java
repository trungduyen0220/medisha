package com.shecodes.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
@Transactional
public interface LoginService {
	public boolean isAuthenticated(String username, String password);
}
