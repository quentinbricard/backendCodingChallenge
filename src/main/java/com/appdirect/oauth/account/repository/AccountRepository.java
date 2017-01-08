package com.appdirect.oauth.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appdirect.oauth.account.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
   Account findById(String id);
}
