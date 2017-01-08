package com.appdirect.subscription.entity.json;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "user", "company", "account", "addonInstance", "addonBinding", "order", "notice", "configuration" })
public class Payload {

   @JsonProperty("user")
   private Object user;
   @JsonProperty("company")
   private Company company;
   @JsonProperty("account")
   private Account account;
   @JsonProperty("addonInstance")
   private Object addonInstance;
   @JsonProperty("addonBinding")
   private Object addonBinding;
   @JsonProperty("order")
   private Order order;
   @JsonProperty("notice")
   private Object notice;
   @JsonProperty("configuration")
   private Configuration configuration;
   @JsonIgnore
   private Map<String, Object> additionalProperties = new HashMap<String, Object>();

   @JsonProperty("user")
   public Object getUser() {
      return user;
   }

   @JsonProperty("user")
   public void setUser(Object user) {
      this.user = user;
   }

   @JsonProperty("company")
   public Company getCompany() {
      return company;
   }

   @JsonProperty("company")
   public void setCompany(Company company) {
      this.company = company;
   }

   @JsonProperty("account")
   public Account getAccount() {
      return account;
   }

   @JsonProperty("account")
   public void setAccount(Account account) {
      this.account = account;
   }

   @JsonProperty("addonInstance")
   public Object getAddonInstance() {
      return addonInstance;
   }

   @JsonProperty("addonInstance")
   public void setAddonInstance(Object addonInstance) {
      this.addonInstance = addonInstance;
   }

   @JsonProperty("addonBinding")
   public Object getAddonBinding() {
      return addonBinding;
   }

   @JsonProperty("addonBinding")
   public void setAddonBinding(Object addonBinding) {
      this.addonBinding = addonBinding;
   }

   @JsonProperty("order")
   public Order getOrder() {
      return order;
   }

   @JsonProperty("order")
   public void setOrder(Order order) {
      this.order = order;
   }

   @JsonProperty("notice")
   public Object getNotice() {
      return notice;
   }

   @JsonProperty("notice")
   public void setNotice(Object notice) {
      this.notice = notice;
   }

   @JsonProperty("configuration")
   public Configuration getConfiguration() {
      return configuration;
   }

   @JsonProperty("configuration")
   public void setConfiguration(Configuration configuration) {
      this.configuration = configuration;
   }

   @JsonAnyGetter
   public Map<String, Object> getAdditionalProperties() {
      return this.additionalProperties;
   }

   @JsonAnySetter
   public void setAdditionalProperty(String name, Object value) {
      this.additionalProperties.put(name, value);
   }

}