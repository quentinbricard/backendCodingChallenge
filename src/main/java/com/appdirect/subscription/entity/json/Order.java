package com.appdirect.subscription.entity.json;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.beust.jcommander.internal.Lists;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "editionCode", "addonOfferingCode", "pricingDuration", "items" })
public class Order {

   @JsonProperty("editionCode")
   private String editionCode;
   @JsonProperty("addonOfferingCode")
   private Object addonOfferingCode;
   @JsonProperty("pricingDuration")
   private String pricingDuration;
   @JsonProperty("items")
   private List<Item> items = Lists.newArrayList();
   @JsonIgnore
   private Map<String, Object> additionalProperties = new HashMap<>();

   @JsonProperty("editionCode")
   public String getEditionCode() {
      return editionCode;
   }

   @JsonProperty("editionCode")
   public void setEditionCode(String editionCode) {
      this.editionCode = editionCode;
   }

   @JsonProperty("addonOfferingCode")
   public Object getAddonOfferingCode() {
      return addonOfferingCode;
   }

   @JsonProperty("addonOfferingCode")
   public void setAddonOfferingCode(Object addonOfferingCode) {
      this.addonOfferingCode = addonOfferingCode;
   }

   @JsonProperty("pricingDuration")
   public String getPricingDuration() {
      return pricingDuration;
   }

   @JsonProperty("pricingDuration")
   public void setPricingDuration(String pricingDuration) {
      this.pricingDuration = pricingDuration;
   }

   @JsonProperty("items")
   public List<Item> getItems() {
      return items;
   }

   @JsonProperty("items")
   public void setItems(List<Item> items) {
      this.items = items;
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