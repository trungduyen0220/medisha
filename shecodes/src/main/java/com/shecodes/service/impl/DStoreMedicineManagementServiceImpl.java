package com.shecodes.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shecodes.dao.MedicineDao;
import com.shecodes.entity.Medicine;
import com.shecodes.service.DStoreMedicineManagementService;

/**
 * @author ntmduyen
 */
@Service
public class DStoreMedicineManagementServiceImpl implements DStoreMedicineManagementService {

	@Autowired
	public MedicineDao medicineDao;
	
	/**
	 * 
	 * @author ntmduyen
	 * @datetime Jul 25, 2020 - 1:46:49 AM
	 * @return
	 */
	@Override
	public List<Medicine> getAllMedicine() {
		return medicineDao.getListAllMedicine();
	}

}
