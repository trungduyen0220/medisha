package com.shecodes.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.shecodes.entity.Role;
import com.shecodes.entity.User;

@Service
@Transactional
public interface UserHandleAccountService {
	public List<Role> getLstRoleOfUser(Long userId);
	public Long getUserIdFromUserName(String userName);
	public User getUserInfo(Long userId);
}
