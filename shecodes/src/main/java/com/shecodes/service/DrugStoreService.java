package com.shecodes.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shecodes.entity.DrugStore;

/**
 * 
 */
@Service
@Transactional
public interface DrugStoreService {
	/**
	 * 
	 * @author ntmduyen
	 * @datetime Jul 26, 2020 - 2:50:46 AM
	 * @param drugStoreId
	 * @return
	 */
	public DrugStore getDrugStoreDetail(Long drugStoreId);
}
