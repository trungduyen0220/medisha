package com.shecodes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.shecodes.entity.DrugStore;
import com.shecodes.service.SearchService;

@Controller
public class StoreController {

	@Autowired
	public SearchService searchService;

	/**
	 * 
	 * @author ntmduyen
	 * @datetime Jul 25, 2020 - 2:48:59 AM
	 * @param model
	 * @param session
	 * @param request
	 * @return
	 */
	@GetMapping("/store")
	public String index(Model model) {
		List<DrugStore> lstDrugStore = searchService.getAllStore();
		for(DrugStore drugStore : lstDrugStore) {
			drugStore.setLstMedicineByStore(searchService.getAllMedicineOfStore(drugStore));
			drugStore.setNumberOfMedicine(drugStore.getLstMedicineByStore().size());
		}
		model.addAttribute("listDrugStore", lstDrugStore);
		return "store";
	}
}
