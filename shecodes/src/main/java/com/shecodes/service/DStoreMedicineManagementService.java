package com.shecodes.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.shecodes.entity.Medicine;

/**
 * 
 * @author ntmduyen
 *
 */
@Service
@Transactional
public interface DStoreMedicineManagementService {
	public List<Medicine> getAllMedicine();
}
