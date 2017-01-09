package com.appdirect.subscription.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appdirect.subscription.entity.SubscriptionResponse;
import com.appdirect.subscription.exception.SubscriptionException;
import com.appdirect.subscription.service.CancelSubscription;
import com.appdirect.subscription.service.ChangeSubscription;
import com.appdirect.subscription.service.CreateSubscription;
import com.google.common.base.Strings;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

   private static final String EVENT_URL_IS_NULL = "Event url is null";
   private final CreateSubscription createSubscription;
   private final CancelSubscription cancelSubscription;
   private final ChangeSubscription changeSubscription;

   @Autowired
   public SubscriptionController(CreateSubscription createSubscription, CancelSubscription cancelSubscription , ChangeSubscription changeSubscription) {
      this.createSubscription = createSubscription;
      this.cancelSubscription = cancelSubscription;
      this.changeSubscription = changeSubscription;
   }

   @RequestMapping(path = "/create", method = {RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
   public ResponseEntity<String> createSubscriptionNotGet(@RequestParam("eventUrl") final String eventUrl, final Model model) {
      return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
   }
   
   @RequestMapping(path = "/create", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<SubscriptionResponse> createSubscription(@RequestParam("eventUrl") final String eventUrl, final Model model) {
      
      SubscriptionResponse createSubscriptionResponse;
      if(Strings.isNullOrEmpty(eventUrl)) {
         createSubscriptionResponse = new SubscriptionResponse("", null, "", EVENT_URL_IS_NULL);
         return new ResponseEntity<SubscriptionResponse>(createSubscriptionResponse, HttpStatus.OK);
      }

      try {
         createSubscription.createSubscription(eventUrl);
      } catch(SubscriptionException e) {
         createSubscriptionResponse = new SubscriptionResponse(false, null, "", "Error during subscription creation");
         return new ResponseEntity<SubscriptionResponse>(createSubscriptionResponse, HttpStatus.OK);
      }
      createSubscriptionResponse = new SubscriptionResponse(true, null, "", "Account created successfully");
      return new ResponseEntity<SubscriptionResponse>(createSubscriptionResponse, HttpStatus.OK);
   }
   
   @RequestMapping(path = "/cancel", method = {RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
   public ResponseEntity<String> cancelSubscriptionNotGet(@RequestParam("eventUrl") final String eventUrl, final Model model) {
      return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
   }
   
   @RequestMapping(path = "/cancel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<SubscriptionResponse> cancelSubscription(@RequestParam("eventUrl") final String eventUrl, final Model model) {
      
      SubscriptionResponse cancelSubscriptionResponse;
      if(Strings.isNullOrEmpty(eventUrl)) {
         cancelSubscriptionResponse = new SubscriptionResponse("", null, "", EVENT_URL_IS_NULL);
         return new ResponseEntity<SubscriptionResponse>(cancelSubscriptionResponse, HttpStatus.OK);
      }

      try {
         cancelSubscription.cancelSubscription(eventUrl);
      } catch(SubscriptionException e) {
         cancelSubscriptionResponse = new SubscriptionResponse(false, null, "", "Error during subscription cancellation");
         return new ResponseEntity<SubscriptionResponse>(cancelSubscriptionResponse, HttpStatus.OK);
      }
      cancelSubscriptionResponse = new SubscriptionResponse(true, null, "", "Account canceled successfully");
      return new ResponseEntity<>(HttpStatus.OK);
   }
   
   @RequestMapping(path = "/change", method = {RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
   public ResponseEntity<String> changeSubscriptionNotGet(@RequestParam("eventUrl") final String eventUrl, final Model model) {
      return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
   }
   
   @RequestMapping(path = "/change", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<SubscriptionResponse> changeSubscription(@RequestParam("eventUrl") final String eventUrl, final Model model) {
      
      SubscriptionResponse changeSubscriptionResponse;
      if(Strings.isNullOrEmpty(eventUrl)) {
         changeSubscriptionResponse = new SubscriptionResponse("", null, "", EVENT_URL_IS_NULL);
         return new ResponseEntity<SubscriptionResponse>(changeSubscriptionResponse, HttpStatus.OK);
      }
      
      try {
         changeSubscription.changeSubscription(eventUrl);
      } catch(SubscriptionException e) {
         changeSubscriptionResponse = new SubscriptionResponse(false, null, "", "Error during subscription changing");
         return new ResponseEntity<SubscriptionResponse>(changeSubscriptionResponse, HttpStatus.OK);
      }
      changeSubscriptionResponse = new SubscriptionResponse(true, null, "", "Account changed successfully");
      return new ResponseEntity<>(HttpStatus.OK);
   }

}
