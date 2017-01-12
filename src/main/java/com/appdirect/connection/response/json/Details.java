package com.appdirect.connection.response.json;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "type", "marketplace", "applicationUuid", "flag", "creator", "payload", "returnUrl", "links" })
public class Details {

   @JsonProperty("type")
   private String type;
   @JsonProperty("marketplace")
   private Marketplace marketplace;
   @JsonProperty("applicationUuid")
   private Object applicationUuid;
   @JsonProperty("flag")
   private String flag;
   @JsonProperty("creator")
   private Creator creator;
   @JsonProperty("payload")
   private Payload payload;
   @JsonProperty("returnUrl")
   private String returnUrl;
   @JsonProperty("links")
   private List<Object> links = null;
   @JsonIgnore
   private Map<String, Object> additionalProperties = new HashMap<>();

   @JsonProperty("type")
   public String getType() {
      return type;
   }

   @JsonProperty("type")
   public void setType(String type) {
      this.type = type;
   }

   @JsonProperty("marketplace")
   public Marketplace getMarketplace() {
      return marketplace;
   }

   @JsonProperty("marketplace")
   public void setMarketplace(Marketplace marketplace) {
      this.marketplace = marketplace;
   }

   @JsonProperty("applicationUuid")
   public Object getApplicationUuid() {
      return applicationUuid;
   }

   @JsonProperty("applicationUuid")
   public void setApplicationUuid(Object applicationUuid) {
      this.applicationUuid = applicationUuid;
   }

   @JsonProperty("flag")
   public String getFlag() {
      return flag;
   }

   @JsonProperty("flag")
   public void setFlag(String flag) {
      this.flag = flag;
   }

   @JsonProperty("creator")
   public Creator getCreator() {
      return creator;
   }

   @JsonProperty("creator")
   public void setCreator(Creator creator) {
      this.creator = creator;
   }

   @JsonProperty("payload")
   public Payload getPayload() {
      return payload;
   }

   @JsonProperty("payload")
   public void setPayload(Payload payload) {
      this.payload = payload;
   }

   @JsonProperty("returnUrl")
   public String getReturnUrl() {
      return returnUrl;
   }

   @JsonProperty("returnUrl")
   public void setReturnUrl(String returnUrl) {
      this.returnUrl = returnUrl;
   }

   @JsonProperty("links")
   public List<Object> getLinks() {
      return links;
   }

   @JsonProperty("links")
   public void setLinks(List<Object> links) {
      this.links = links;
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
