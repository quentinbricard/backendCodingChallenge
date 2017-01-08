package com.appdirect.oauth.account.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="accounts")
public class Account {

   private String id;
   private String name;
   private int status;
   
   /**
    * Empty constructor
    */
   public Account() {
      // no-op
   }

   public Account(String name, int status) {
      this.name = name;
      this.status = status;
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
   public String getName() {
      return name;
   }
   
   public void setName(String name) {
      this.name = name;
   }

   @Column
   public int getStatus() {
      return status;
   }

   public void setStatus(int status) {
      this.status = status;
   }
}
