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
@JsonPropertyOrder({ "unit", "quantity" })
public class Item {

   @JsonProperty("unit")
   private String unit;
   @JsonProperty("quantity")
   private Integer quantity;
   @JsonIgnore
   private Map<String, Object> additionalProperties = new HashMap<String, Object>();

   @JsonProperty("unit")
   public String getUnit() {
      return unit;
   }

   @JsonProperty("unit")
   public void setUnit(String unit) {
      this.unit = unit;
   }

   @JsonProperty("quantity")
   public Integer getQuantity() {
      return quantity;
   }

   @JsonProperty("quantity")
   public void setQuantity(Integer quantity) {
      this.quantity = quantity;
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