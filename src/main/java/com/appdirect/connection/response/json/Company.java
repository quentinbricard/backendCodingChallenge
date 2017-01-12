package com.appdirect.connection.response.json;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "uuid", "externalId", "name", "email", "phoneNumber", "website", "country" })
public class Company {

   @JsonProperty("uuid")
   private String uuid;
   @JsonProperty("externalId")
   private Object externalId;
   @JsonProperty("name")
   private String name;
   @JsonProperty("email")
   private String email;
   @JsonProperty("phoneNumber")
   private String phoneNumber;
   @JsonProperty("website")
   private String website;
   @JsonProperty("country")
   private String country;
   @JsonIgnore
   private Map<String, Object> additionalProperties = new HashMap<String, Object>();

   @JsonProperty("uuid")
   public String getUuid() {
      return uuid;
   }

   @JsonProperty("uuid")
   public void setUuid(String uuid) {
      this.uuid = uuid;
   }

   @JsonProperty("externalId")
   public Object getExternalId() {
      return externalId;
   }

   @JsonProperty("externalId")
   public void setExternalId(Object externalId) {
      this.externalId = externalId;
   }

   @JsonProperty("name")
   public String getName() {
      return name;
   }

   @JsonProperty("name")
   public void setName(String name) {
      this.name = name;
   }

   @JsonProperty("email")
   public String getEmail() {
      return email;
   }

   @JsonProperty("email")
   public void setEmail(String email) {
      this.email = email;
   }

   @JsonProperty("phoneNumber")
   public String getPhoneNumber() {
      return phoneNumber;
   }

   @JsonProperty("phoneNumber")
   public void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
   }

   @JsonProperty("website")
   public String getWebsite() {
      return website;
   }

   @JsonProperty("website")
   public void setWebsite(String website) {
      this.website = website;
   }

   @JsonProperty("country")
   public String getCountry() {
      return country;
   }

   @JsonProperty("country")
   public void setCountry(String country) {
      this.country = country;
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