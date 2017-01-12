package com.appdirect.connection.response.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Account {

   @JsonProperty("accountIdentifier")
   private String accountIdentifier;
   @JsonProperty("status")
   private String status;
   @JsonProperty("parentAccountIdentifier")
   private String parentAccountIdentifier;
   
   @JsonProperty("accountIdentifier")
   public String getAccountIdentifier() {
      return accountIdentifier;
   }

   @JsonProperty("accountIdentifier")
   public void setAccountIdentifier(String accountIdentifier) {
      this.accountIdentifier = accountIdentifier;
   }
   
   @JsonProperty("status")
   public String getStatus() {
      return status;
   }
   
   @JsonProperty("status")
   public void setStatus(String status) {
      this.status = status;
   }

   @JsonProperty("parentAccountIdentifier")
   public String getParentAccountIdentifier() {
      return parentAccountIdentifier;
   }

   @JsonProperty("parentAccountIdentifier")
   public void setParentAccountIdentifier(String parentAccountIdentifier) {
      this.parentAccountIdentifier = parentAccountIdentifier;
   }
   
}
