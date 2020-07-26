package com.shecodes.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.shecodes.entity.Medicine;
import com.shecodes.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService {
	
	/**
	 * 
	 * @author ntmduyen
	 * @datetime Jul 25, 2020 - 12:34:21 PM
	 * @return
	 */
	public List<Medicine> getLstMedicineFromImage() {
		
		Random rd = new Random();
		List<Medicine> lstMedicine = new ArrayList<>();
		Medicine med1 = new Medicine(rd.nextLong());
		lstMedicine.add(med1);
		Medicine med2 = new Medicine(rd.nextLong());
		lstMedicine.add(med2);
		return lstMedicine;

	}
}
