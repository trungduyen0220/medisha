package com.shecodes.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.shecodes.entity.Medicine;

@Service
@Transactional
public interface ImageService {
	public List<Medicine> getLstMedicineFromImage() ;
}
