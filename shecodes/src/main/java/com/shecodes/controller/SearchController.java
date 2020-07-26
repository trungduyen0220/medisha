package com.shecodes.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.shecodes.entity.DrugStore;
import com.shecodes.entity.FormSearch;
import com.shecodes.entity.Medicine;
import com.shecodes.service.MedicineService;
import com.shecodes.service.SearchService;

@Controller
public class SearchController {
	
	@Autowired
	public MedicineService medicineService;
	
	@Autowired
	public SearchService searchService;
	
	@PostMapping("/search")
	public String getToSearchPage(Model model, HttpSession session, HttpServletRequest request) throws SQLException {
		List<FormSearch> lstFormSearch = new ArrayList<>();
		lstFormSearch.add(new FormSearch("Zyrtec","10","viên"));
		lstFormSearch.add(new FormSearch("Efferalgan","10","viên"));
		lstFormSearch.add(new FormSearch("Eugica","10","viên"));

		List<DrugStore> lstDrugStore = searchService.getListStoreAfterSearching(lstFormSearch);
		int totalPrice = 0;
		for(DrugStore drugStore : lstDrugStore) {
			drugStore.setLstMedicineByStore(searchService.showMedicineByStoreAfterSearching(lstFormSearch, drugStore));
			for(Medicine medicine : drugStore.getLstMedicineByStore()) {
				totalPrice += (medicine.getPrice() * (100 - medicine.getSalePercent() )/100 );
			}
			drugStore.setNumberOfMedicine(drugStore.getLstMedicineByStore().size());
			drugStore.setTotalPrice(totalPrice);
			totalPrice = 0;
		}
		
		model.addAttribute("listDrugStore", lstDrugStore);
		model.addAttribute("searchTag", 1);
		return "store";
	}
	
	/**
	 * 
	 * @author ntmduyen
	 * @datetime Jul 25, 2020 - 4:17:48 PM
	 * @param lstFormSearch
	 * @return
	 */
	public ResponseEntity<?>  getListFormSearch(@ModelAttribute(value = "lstFormSearch") String lstFormSearch){
		return ResponseEntity.ok(lstFormSearch);
	}
}
