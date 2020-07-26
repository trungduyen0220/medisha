package com.shecodes.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shecodes.entity.DrugStore;

@Repository
public interface DrugStoreRepository extends CrudRepository<DrugStore, Long>  {

}
