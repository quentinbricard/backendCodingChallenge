package com.appdirect.model.item.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appdirect.model.item.entity.ItemEntity;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, String> {
}
