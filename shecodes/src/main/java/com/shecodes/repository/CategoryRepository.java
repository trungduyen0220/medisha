package com.shecodes.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shecodes.entity.Medicine;

/**
 * 
 * @author ntmduyen
 *
 */
@Repository  
public interface CategoryRepository extends CrudRepository<Medicine, Long> {

}
