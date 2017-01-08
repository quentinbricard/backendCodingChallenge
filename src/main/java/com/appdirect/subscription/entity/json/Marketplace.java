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
@JsonPropertyOrder({ "partner", "baseUrl" })
public class Marketplace {

   @JsonProperty("partner")
   private String partner;
   @JsonProperty("baseUrl")
   private String baseUrl;
   @JsonIgnore
   private Map<String, Object> additionalProperties = new HashMap<String, Object>();

   @JsonProperty("partner")
   public String getPartner() {
      return partner;
   }

   @JsonProperty("partner")
   public void setPartner(String partner) {
      this.partner = partner;
   }

   @JsonProperty("baseUrl")
   public String getBaseUrl() {
      return baseUrl;
   }

   @JsonProperty("baseUrl")
   public void setBaseUrl(String baseUrl) {
      this.baseUrl = baseUrl;
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