package com.appdirect.subscription.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appdirect.model.account.entity.AccountEntity;
import com.appdirect.subscription.entity.SubscriptionResponse;
import com.appdirect.subscription.exception.SubscriptionException;
import com.appdirect.subscription.service.CancelSubscription;
import com.appdirect.subscription.service.ChangeSubscription;
import com.appdirect.subscription.service.CreateSubscription;
import com.google.common.base.Strings;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {
   
   private static final Logger LOGGER = LoggerFactory.getLogger(SubscriptionController.class);
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
      
      if(Strings.isNullOrEmpty(eventUrl)) {
         return buildResponseEntityFromSubscriptionResponse(false, null, "", EVENT_URL_IS_NULL, HttpStatus.OK);
      }

      AccountEntity account = null;
      try {
         account = createSubscription.createSubscription(eventUrl);
      } catch(SubscriptionException e) {
         LOGGER.error("Error during subscription creation", e);
         return buildResponseEntityFromSubscriptionResponse(false, null, "", "Error during subscription creation", HttpStatus.OK);
      }
      return buildResponseEntityFromSubscriptionResponse(true, account.getId(), null, "Account created successfully", HttpStatus.OK);
   }
   
   @RequestMapping(path = "/cancel", method = {RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
   public ResponseEntity<String> cancelSubscriptionNotGet(@RequestParam("eventUrl") final String eventUrl, final Model model) {
      return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
   }
   
   @RequestMapping(path = "/cancel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<SubscriptionResponse> cancelSubscription(@RequestParam("eventUrl") final String eventUrl, final Model model) {
      
      if(Strings.isNullOrEmpty(eventUrl)) {
         return buildResponseEntityFromSubscriptionResponse(false, null, "", EVENT_URL_IS_NULL, HttpStatus.OK);
      }

      AccountEntity account = null;
      try {
         account = cancelSubscription.cancelSubscription(eventUrl);
      } catch(SubscriptionException e) {
         LOGGER.error("Error during subscription cancellation", e);
         return buildResponseEntityFromSubscriptionResponse(false, null, "", "Error during subscription cancellation", HttpStatus.OK);
      }
      return buildResponseEntityFromSubscriptionResponse(true, account.getId(), null, "Account canceled successfully", HttpStatus.OK);
   }
   
   @RequestMapping(path = "/change", method = {RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
   public ResponseEntity<String> changeSubscriptionNotGet(@RequestParam("eventUrl") final String eventUrl, final Model model) {
      return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
   }
   
   @RequestMapping(path = "/change", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<SubscriptionResponse> changeSubscription(@RequestParam("eventUrl") final String eventUrl, final Model model) {
      
      if(Strings.isNullOrEmpty(eventUrl)) {
         return buildResponseEntityFromSubscriptionResponse(false, null, "", EVENT_URL_IS_NULL, HttpStatus.OK);
      }
      
      AccountEntity account = null;
      try {
         account = changeSubscription.changeSubscription(eventUrl);
      } catch(SubscriptionException e) {
         LOGGER.error("Error during subscription change", e);
         return buildResponseEntityFromSubscriptionResponse(false, null, "", "Error during subscription changing", HttpStatus.OK);
      }
      return buildResponseEntityFromSubscriptionResponse(true, account.getId(), null, "Account changed successfully", HttpStatus.OK);
   }
   
   private ResponseEntity<SubscriptionResponse> buildResponseEntityFromSubscriptionResponse(boolean success, 
         String accountIdentifier, String errorCode, String message, HttpStatus httpStatus) {
      SubscriptionResponse changeSubscriptionResponse = new SubscriptionResponse(success, accountIdentifier, errorCode, message);
      return new ResponseEntity<>(changeSubscriptionResponse, httpStatus);
   }
}
