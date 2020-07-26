package com.shecodes.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shecodes.dao.MedicineDao;
import com.shecodes.entity.FormSearch;
import com.shecodes.entity.Medicine;
import com.shecodes.service.MedicineService;

@Service
public class MedicineServiceImpl implements MedicineService {

	@Autowired
	public MedicineDao medicineDao;
	
	@Override
	public List<Medicine> getAllMedicine() {
		return medicineDao.getListAllMedicine();
	}

	@Override
	public List<Medicine> getAllMedicineOfStore(Long storeId) {
		return medicineDao.getAllMedicineOfStore(storeId);
	}

	@Override
	public List<Medicine> getMedicineBySearchForm(List<FormSearch> formSearch) throws SQLException {
		return medicineDao.getMedicineFromSearchForm(formSearch);
	}

	@Override
	public Medicine getMedicineFromId(Long medicineId) {
		return null;
	}

}
