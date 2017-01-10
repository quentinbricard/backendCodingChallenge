package com.appdirect.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appdirect.account.entity.AccountEntity;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, String> {
   AccountEntity findById(String id);
}
