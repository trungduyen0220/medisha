package com.shecodes.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import com.shecodes.dao.MedicineDao;
import com.shecodes.entity.Role;
import com.shecodes.entity.User;
import com.shecodes.service.LoginService;
import com.shecodes.service.UserHandleAccountService;
import com.shecodes.utils.Constants;

/**
 * 
 * @author ntmduyen
 *
 */
@Controller
public class LoginController {

	private static final Logger logger = LogManager.getLogger(MedicineDao.class);
	
	@Autowired
	public UserHandleAccountService handleUserService;

	@Autowired
	public LoginService loginService;

	/**
	 * 
	 * @author ntmduyen
	 * @datetime Jul 24, 2020 - 10:21:10 AM
	 * @param model
	 * @return
	 */
	@GetMapping("/login")
	public String index(Model model) {
		return "login";
	}

	/**
	 * Go to homepage after login successfully
	 * 
	 * @author ntmduyen
	 * @datetime Jul 24, 2020 - 10:38:43 AM
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@PostMapping("/login")
	public String toHomePage(@RequestParam(value = "username") String username, @RequestParam(value = "password", required = true) String password, Model model, HttpSession httpSession) {
		Long userId = handleUserService.getUserIdFromUserName(username);

		String screenTo = "login";
		if (loginService.isAuthenticated(username, password)) {
			List<Role> lstRoleOfUser = handleUserService.getLstRoleOfUser(userId);
			User user = handleUserService.getUserInfo(userId);
			for (Role role : lstRoleOfUser) {
				logger.info("Role is: " + role.getRoleName());
				if (role.getRoleName().equals(Constants.ROLE_STORE_OWNER)) {
					screenTo = "redirect:/store-owner/medicine";
				}
				
				if (role.getRoleName().equals(Constants.ROLE_ADMIN)) {
					screenTo = "redirect:/admin/account";
				}

				if (role.getRoleName().equals(Constants.ROLE_USER)
						|| role.getRoleName().equals(Constants.ROLE_SHIPPER)) {
					screenTo = "redirect:/home";
				}
			}

			if (StringUtils.isEmpty(screenTo)) {
				return "login";
			}

			httpSession.setAttribute("user", user);
			return screenTo;
		}

		model.addAttribute("error", "1");
		logger.info("Go to screen: " + screenTo);
		return "login";
	}
}
