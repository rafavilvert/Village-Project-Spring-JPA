package com.api.villagedevin.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.api.villagedevin.model.persistence.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

}
