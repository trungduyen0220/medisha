package com.shecodes.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shecodes.dao.DrugStoreDao;
import com.shecodes.entity.DrugStore;
import com.shecodes.service.DrugStoreService;

@Service
public class DrugStoreServiceImpl implements DrugStoreService {

	@Autowired
	public DrugStoreDao drugStoreDao;
	
	@Override
	public DrugStore getDrugStoreDetail(Long drugStoreId) {
		return drugStoreDao.getDrugStoreDetail(drugStoreId);
	}

}
