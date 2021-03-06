package com.appdirect.model.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.beust.jcommander.internal.Lists;

@Entity
@Table(name="accounts")
public class AccountEntity {

   private String id;
   private String name;
   private int status;
   private OrderEntity order;
   private List<UserEntity> users = Lists.newArrayList();
   
   /**
    * Empty constructor
    */
   public AccountEntity() {
      // no-op
   }

   public AccountEntity(String name, int status) {
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

   @OneToOne(cascade = CascadeType.ALL)
   public OrderEntity getOrder() {
      return order;
   }

   public void setOrder(OrderEntity order) {
      this.order = order;
   }
   
   @OneToMany(cascade = CascadeType.ALL)
   public List<UserEntity> getUsers() {
      return users;
   }

   @SuppressWarnings("unused")
   private void setUsers(List<UserEntity> users) {
      this.users = users;
   }
}
