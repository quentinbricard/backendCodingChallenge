package com.appdirect.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity {

   private String id;
   private String email;
   private String firstName;
   private String lastName;

   /**
    * Empty constructor
    */
   public UserEntity() {
      // no-op
   }

   public UserEntity(String id, String email, String firstName, String lastName) {
      this.id = id;
      this.email = email;
      this.firstName = firstName;
      this.lastName = lastName;
   }

   @Id
   @Column
   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   @Column
   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   @Column
   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   @Column
   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }
}
