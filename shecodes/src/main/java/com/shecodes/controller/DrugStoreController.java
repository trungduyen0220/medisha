package com.shecodes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shecodes.entity.DrugStore;
import com.shecodes.entity.Medicine;
import com.shecodes.service.DrugStoreService;
import com.shecodes.service.MedicineService;

@Controller
public class DrugStoreController {

	@Autowired
	public DrugStoreService drugStoreService;

	@Autowired
	public MedicineService medicineService;

	/**
	 * 
	 * @author ntmduyen
	 * @datetime Jul 26, 2020 - 6:35:19 AM
	 * @param storeId
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/drugStore")
	public String getBookDetail(@RequestParam(name = "storeId") Long storeId, Model model) {
		DrugStore drugStore = drugStoreService.getDrugStoreDetail(storeId);
		List<Medicine> lstMedicineOfDrugStore = medicineService.getAllMedicineOfStore(drugStore.getStoreId());
		drugStore.setLstMedicineByStore(lstMedicineOfDrugStore);
		drugStore.setNumberOfMedicine(drugStore.getLstMedicineByStore().size());
		model.addAttribute("drugStore", drugStore);
		return "storeDetail";
	}
}
