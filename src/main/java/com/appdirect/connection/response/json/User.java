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
@JsonPropertyOrder({ "email", "firstName", "language", "lastName", "locale", "openId", "uuid" })
public class User {

   @JsonProperty("email")
   private String email;
   @JsonProperty("firstName")
   private String firstName;
   @JsonProperty("language")
   private String language;
   @JsonProperty("lastName")
   private String lastName;
   @JsonProperty("locale")
   private String locale;
   @JsonProperty("openId")
   private String openId;
   @JsonProperty("uuid")
   private String uuid;
   @JsonIgnore
   private Map<String, Object> additionalProperties = new HashMap<>();

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

   @JsonProperty("language")
   public String getLanguage() {
      return language;
   }

   @JsonProperty("language")
   public void setLanguage(String language) {
      this.language = language;
   }

   @JsonProperty("lastName")
   public String getLastName() {
      return lastName;
   }

   @JsonProperty("lastName")
   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   @JsonProperty("locale")
   public String getLocale() {
      return locale;
   }

   @JsonProperty("locale")
   public void setLocale(String locale) {
      this.locale = locale;
   }

   @JsonProperty("openId")
   public String getOpenId() {
      return openId;
   }

   @JsonProperty("openId")
   public void setOpenId(String openId) {
      this.openId = openId;
   }

   @JsonProperty("uuid")
   public String getUuid() {
      return uuid;
   }

   @JsonProperty("uuid")
   public void setUuid(String uuid) {
      this.uuid = uuid;
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
