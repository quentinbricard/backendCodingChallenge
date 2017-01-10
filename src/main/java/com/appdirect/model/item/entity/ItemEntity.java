package com.appdirect.model.item.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="items")
public class ItemEntity {

   private String id;
   private int quantity;
   private String unit;
   
   /**
    * Empty constructor
    */
   public ItemEntity() {
      // no-op
   }

   public ItemEntity(int quantity, String unit) {
      this.quantity = quantity;
      this.unit = unit;
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
   public int getQuantity() {
      return quantity;
   }

   public void setQuantity(int quantity) {
      this.quantity = quantity;
   }

   @Column
   public String getUnit() {
      return unit;
   }

   public void setUnit(String unit) {
      this.unit = unit;
   }
   
}
