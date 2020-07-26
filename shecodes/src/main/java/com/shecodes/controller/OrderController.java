package com.shecodes.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shecodes.entity.FormSearch;
import com.shecodes.entity.Medicine;
import com.shecodes.service.SearchService;

@Controller
public class OrderController {
	
	@Autowired
	public SearchService searchService;
	
	@GetMapping("/submitOrder")
	public String index(@RequestParam(name = "storeId") Long storeId, Model model) {
		List<FormSearch> lstFormSearch = new ArrayList<>();
		lstFormSearch.add(new FormSearch("Zyrtec","10","viên"));
		lstFormSearch.add(new FormSearch("Efferalgan","10","viên"));
		lstFormSearch.add(new FormSearch("Eugica","10","viên"));

		List<Medicine> lstMedicine = searchService.showMedicineByStoreAfterSearching(lstFormSearch, storeId);		
		int totalPrice = 0;
		for(Medicine medicine : lstMedicine) {
			totalPrice += medicine.getPrice() / 100 * (100 - medicine.getSalePercent());
			medicine.setMedicineUnit("viên");
			medicine.setMedicineAmount("10");
		}
		model.addAttribute("lstMedicine", lstMedicine);
		model.addAttribute("totalPrice", totalPrice);
		return "cart";
	}
}
