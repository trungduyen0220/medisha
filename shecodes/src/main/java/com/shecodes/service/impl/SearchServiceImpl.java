package com.shecodes.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shecodes.dao.SearchDao;
import com.shecodes.entity.DrugStore;
import com.shecodes.entity.FormSearch;
import com.shecodes.entity.Medicine;
import com.shecodes.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService{

	@Autowired
	public SearchDao searchDao;
	
	/**
	 * 
	 * @author ntmduyen
	 * @datetime Jul 26, 2020 - 2:56:20 AM
	 * @param lstFormSearch
	 * @return
	 */
	@Override
	public List<DrugStore> getListStoreAfterSearching(List<FormSearch> lstFormSearch) {
		return searchDao.getListStoreAfterSearching(lstFormSearch);
	}

	/**
	 * 
	 * @author ntmduyen
	 * @datetime Jul 26, 2020 - 2:56:23 AM
	 * @param lstFormSearch
	 * @param drugStore
	 * @return
	 */
	@Override
	public List<Medicine> showMedicineByStoreAfterSearching(List<FormSearch> lstFormSearch,
			DrugStore drugStore) {
		return searchDao.showMedicineByStoreAfterSearching(lstFormSearch, drugStore);
	}

	/**
	 * 
	 * @author ntmduyen
	 * @datetime Jul 26, 2020 - 2:56:26 AM
	 * @return
	 */
	@Override
	public List<DrugStore> getAllStore() {
		return searchDao.getAllStore();
	}

	@Override
	public List<Medicine> getAllMedicineOfStore(DrugStore lstDrugStore) {
		return searchDao.getAllMedicineByStore(lstDrugStore);
	}

	@Override
	public List<Medicine> showMedicineByStoreAfterSearching(List<FormSearch> lstFormSearch, Long storeId) {
		return searchDao.showMedicineByStoreAfterSearching(lstFormSearch, storeId);
	}

}
