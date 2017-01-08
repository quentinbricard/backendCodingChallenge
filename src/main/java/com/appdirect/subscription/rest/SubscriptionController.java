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

import com.appdirect.oauth.RequestValidator;
import com.appdirect.oauth.exception.OAuthException;
import com.appdirect.subscription.entity.CreateSubscriptionResponse;
import com.appdirect.subscription.exception.SubscriptionException;
import com.appdirect.subscription.service.CancelSubscription;
import com.appdirect.subscription.service.CreateSubscription;
import com.google.common.base.Strings;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

   private final RequestValidator requestValidator;
   private final CreateSubscription createSubscription;
   private final CancelSubscription cancelSubscription;

   @Autowired
   public SubscriptionController(final RequestValidator requestValidator, CreateSubscription createSubscription, CancelSubscription cancelSubscription) {
      this.requestValidator = requestValidator;
      this.createSubscription = createSubscription;
      this.cancelSubscription = cancelSubscription;
   }

   @RequestMapping(path = "/create", method = {RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
   public ResponseEntity<String> createSubscriptionNotGet(@RequestParam("eventUrl") final String eventUrl, final Model model) {
      return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
   }
   
   @RequestMapping(path = "/create", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<CreateSubscriptionResponse> createSubscription(@RequestParam("eventUrl") final String eventUrl, 
         @RequestParam("oauth_consumer_key") final String oauthConsumerKey, @RequestParam("oauth_nonce") final String oauthNonce, 
         @RequestParam("oauth_signature") final String oauthSignature, @RequestParam("oauth_signature_method") final String oauthSignatureMethod, 
         @RequestParam("oauth_timestamp") final String oauthTimestamp, @RequestParam("oauth_version") final String oauthVersion, final Model model) {
      
      CreateSubscriptionResponse createSubscriptionResponse;
      if(Strings.isNullOrEmpty(eventUrl)) {
         createSubscriptionResponse = new CreateSubscriptionResponse("", null, "", "Event url is null");
         return new ResponseEntity<CreateSubscriptionResponse>(createSubscriptionResponse, HttpStatus.OK);
      }
      
      try {
         requestValidator.validateOauthParameters(oauthConsumerKey, oauthNonce, oauthSignature, oauthSignatureMethod, oauthTimestamp, oauthVersion);
      } catch(OAuthException e) {
         createSubscriptionResponse = new CreateSubscriptionResponse(false, null, "", "Error during oauth validation");
         return new ResponseEntity<CreateSubscriptionResponse>(createSubscriptionResponse, HttpStatus.OK);
      }
      
      try {
         createSubscription.createSubscription(eventUrl);
      } catch(SubscriptionException e) {
         createSubscriptionResponse = new CreateSubscriptionResponse(false, null, "", "Error during subscription creation");
         return new ResponseEntity<CreateSubscriptionResponse>(createSubscriptionResponse, HttpStatus.OK);
      }
      createSubscriptionResponse = new CreateSubscriptionResponse(true, null, "", "Account created successfully");
      return new ResponseEntity<CreateSubscriptionResponse>(createSubscriptionResponse, HttpStatus.OK);
   }
   
   @RequestMapping(path = "/cancel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<CreateSubscriptionResponse> cancelSubscription(@RequestParam("eventUrl") final String eventUrl, 
         @RequestParam("oauth_consumer_key") final String oauthConsumerKey, @RequestParam("oauth_nonce") final String oauthNonce, 
         @RequestParam("oauth_signature") final String oauthSignature, @RequestParam("oauth_signature_method") final String oauthSignatureMethod, 
         @RequestParam("oauth_timestamp") final String oauthTimestamp, @RequestParam("oauth_version") final String oauthVersion, final Model model) {
      
      CreateSubscriptionResponse createSubscriptionResponse;
      if(Strings.isNullOrEmpty(eventUrl)) {
         createSubscriptionResponse = new CreateSubscriptionResponse("", null, "", "Event url is null");
         return new ResponseEntity<CreateSubscriptionResponse>(createSubscriptionResponse, HttpStatus.OK);
      }
      
      try {
         requestValidator.validateOauthParameters(oauthConsumerKey, oauthNonce, oauthSignature, oauthSignatureMethod, oauthTimestamp, oauthVersion);
      } catch(OAuthException e) {
         createSubscriptionResponse = new CreateSubscriptionResponse(false, null, "", "Error during oauth validation");
         return new ResponseEntity<CreateSubscriptionResponse>(createSubscriptionResponse, HttpStatus.OK);
      }
      
      try {
         cancelSubscription.cancelSubscription(eventUrl);
      } catch(SubscriptionException e) {
         createSubscriptionResponse = new CreateSubscriptionResponse(false, null, "", "Error during subscription cancellation");
         return new ResponseEntity<CreateSubscriptionResponse>(createSubscriptionResponse, HttpStatus.OK);
      }
      createSubscriptionResponse = new CreateSubscriptionResponse(true, null, "", "Account canceled successfully");
      return new ResponseEntity<>(HttpStatus.OK);
   }

}
