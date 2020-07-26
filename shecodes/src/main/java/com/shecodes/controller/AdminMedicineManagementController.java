package com.shecodes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.shecodes.entity.Medicine;
import com.shecodes.service.DStoreMedicineManagementService;

/**
 * 
 * @author ntmduyen
 *
 */
@Controller
public class AdminMedicineManagementController {

	@Autowired
	public DStoreMedicineManagementService medicineManagement;
	
	/**
	 * Get list medicine
	 * @author ntmduyen
	 * @datetime Jul 25, 2020 - 10:19:53 AM
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/medicine")  
	public String getListAccount(Model model) {
		List<Medicine> lstMedicine = medicineManagement.getAllMedicine();
		model.addAttribute("lstMedicine", lstMedicine);
		return "admin/listMedicine";
	}
}
