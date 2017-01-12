package com.appdirect.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appdirect.model.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
   UserEntity findById(String id);
}
