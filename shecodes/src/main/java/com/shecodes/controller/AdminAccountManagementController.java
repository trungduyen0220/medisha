package com.shecodes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.shecodes.entity.User;
import com.shecodes.service.AdminAccountManagementService;

/**
 * 
 * @author ntmduyen
 *
 */
@Controller
public class AdminAccountManagementController {

	@Autowired
	public AdminAccountManagementService accountManagement;
	
	/**
	 * Get list account
	 * @author ntmduyen
	 * @datetime Jul 25, 2020 - 10:19:53 AM
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/account")  
	public String getListAccount(Model model) {
		List<User> userList = accountManagement.getListUser();
		model.addAttribute("userList", userList);
		return "admin/listAccount";
	}
}
