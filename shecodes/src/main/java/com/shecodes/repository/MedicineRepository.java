package com.shecodes.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shecodes.entity.Medicine;

@Repository  
public interface MedicineRepository extends CrudRepository<Medicine, Long> {

}
