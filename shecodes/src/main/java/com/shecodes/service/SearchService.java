package com.shecodes.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.shecodes.entity.DrugStore;
import com.shecodes.entity.FormSearch;
import com.shecodes.entity.Medicine;

@Service
@Transactional
public interface SearchService {
	/**
	 * 
	 * @author ntmduyen
	 * @datetime Jul 25, 2020 - 4:37:36 PM
	 * @param lstFormSearch
	 * @return
	 */
	public List<DrugStore> getListStoreAfterSearching(List<FormSearch> lstFormSearch);
	
	/**
	 * 
	 * @author ntmduyen
	 * @datetime Jul 25, 2020 - 4:37:36 PM
	 * @param lstFormSearch
	 * @return
	 */
	public List<Medicine> showMedicineByStoreAfterSearching(List<FormSearch> lstFormSearch, DrugStore lstDrugStore);
	public List<Medicine> showMedicineByStoreAfterSearching(List<FormSearch> lstFormSearch, Long storeId);

	/**
	 * 
	 * @author ntmduyen
	 * @datetime Jul 26, 2020 - 2:56:44 AM
	 * @return
	 */
	public List<DrugStore> getAllStore();
	
	/**
	 * 
	 * @author ntmduyen
	 * @datetime Jul 26, 2020 - 2:59:32 AM
	 * @return
	 */
	public List<Medicine> getAllMedicineOfStore(DrugStore lstDrugStore);
}
