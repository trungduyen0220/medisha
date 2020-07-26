/**
 * @author ntmduyen
 * @filename MedicineStoreController.java
 * @datetime Jul 23, 2020 - tim11:11:20 AM
 */
package com.shecodes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shecodes.entity.Medicine;
import com.shecodes.service.MedicineService;

/**
 * @author ntmduyen
 *
 */
@Controller
public class UserMedicineController {
	
	@Autowired
	public MedicineService medicineService;
	
	/**
	 * Get medicine detail information
	 * @author ntmduyen
	 * @datetime Jul 23, 2020 - 1:47:51 PM
	 * @param medicineId
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/medicine")
	public String getMedicineDetail(@RequestParam(name = "medicineId") Long medicineId, Model model) {
        Medicine medicine = medicineService.getMedicineFromId(medicineId);
        model.addAttribute("medicine", medicine);
        return "medicineDetail";
	}
}
