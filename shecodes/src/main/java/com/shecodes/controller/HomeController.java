package com.shecodes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.shecodes.service.MedicineService;

/**
 * 
 * @author ntmduyen
 *
 */
@Controller
public class HomeController {

	@Autowired
	public MedicineService medicineService;

	/**
	 * 
	 * @author ntmduyen
	 * @datetime Jul 25, 2020 - 10:20:57 AM
	 * @param model
	 * @return
	 */
	@GetMapping(value= {"/","/home"})
	public String index(Model model) {
		return "home";
	}
}
