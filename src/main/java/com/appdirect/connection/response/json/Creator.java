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
@JsonPropertyOrder({ "uuid", "openId", "email", "firstName", "lastName", "language", "locale", "address", "attributes" })
public class Creator {

   @JsonProperty("uuid")
   private String uuid;
   @JsonProperty("openId")
   private String openId;
   @JsonProperty("email")
   private String email;
   @JsonProperty("firstName")
   private String firstName;
   @JsonProperty("lastName")
   private String lastName;
   @JsonProperty("language")
   private String language;
   @JsonProperty("locale")
   private Object locale;
   @JsonProperty("address")
   private Object address;
   @JsonProperty("attributes")
   private Object attributes;
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

   @JsonProperty("openId")
   public String getOpenId() {
      return openId;
   }

   @JsonProperty("openId")
   public void setOpenId(String openId) {
      this.openId = openId;
   }

   @JsonProperty("email")
   public String getEmail() {
      return email;
   }

   @JsonProperty("email")
   public void setEmail(String email) {
      this.email = email;
   }

   @JsonProperty("firstName")
   public String getFirstName() {
      return firstName;
   }

   @JsonProperty("firstName")
   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   @JsonProperty("lastName")
   public String getLastName() {
      return lastName;
   }

   @JsonProperty("lastName")
   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   @JsonProperty("language")
   public String getLanguage() {
      return language;
   }

   @JsonProperty("language")
   public void setLanguage(String language) {
      this.language = language;
   }

   @JsonProperty("locale")
   public Object getLocale() {
      return locale;
   }

   @JsonProperty("locale")
   public void setLocale(Object locale) {
      this.locale = locale;
   }

   @JsonProperty("address")
   public Object getAddress() {
      return address;
   }

   @JsonProperty("address")
   public void setAddress(Object address) {
      this.address = address;
   }

   @JsonProperty("attributes")
   public Object getAttributes() {
      return attributes;
   }

   @JsonProperty("attributes")
   public void setAttributes(Object attributes) {
      this.attributes = attributes;
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