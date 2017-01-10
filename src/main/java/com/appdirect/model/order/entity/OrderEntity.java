package com.appdirect.model.order.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.appdirect.model.item.entity.ItemEntity;
import com.google.common.collect.Lists;

@Entity
@Table(name="orders")
public class OrderEntity {

   private String id;
   private String editionCode;
   private String pricingDuration;
   private List<ItemEntity> items = Lists.newArrayList();
   
   /**
    * Empty constructor
    */
   public OrderEntity() {
      // no-op
   }

   public OrderEntity(String editionCode, String pricingDuration) {
      this.editionCode = editionCode;
      this.pricingDuration = pricingDuration;
   }

   @Id
   @GenericGenerator(name = "generator", strategy = "com.appdirect.IdGenerator")
   @GeneratedValue(generator = "generator")  
   @Column(name = "id", nullable = false, updatable = false)
   public String getId() {
      return id;
   }
   
   public void setId(String id) {
      this.id = id;
   }
   
   @Column
   public String getEditionCode() {
      return editionCode;
   }
   
   public void setEditionCode(String editionCode) {
      this.editionCode = editionCode;
   }

   @Column
   public String getPricingDuration() {
      return pricingDuration;
   }

   public void setPricingDuration(String pricingDuration) {
      this.pricingDuration = pricingDuration;
   }

   @OneToMany(cascade = CascadeType.ALL)
   public List<ItemEntity> getItems() {
      return items;
   }

   @SuppressWarnings("unused")
   private void setItems(List<ItemEntity> items) {
      this.items = items;
   }
}
