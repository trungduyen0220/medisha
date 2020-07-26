package com.shecodes.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shecodes.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>   {

}
