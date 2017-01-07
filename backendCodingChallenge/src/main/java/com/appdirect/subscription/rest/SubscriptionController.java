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
import com.appdirect.subscription.service.CreateSubscription;
import com.google.common.base.Strings;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {

   private final RequestValidator requestValidator;
   private final CreateSubscription createSubscription;

   @Autowired
   public SubscriptionController(final RequestValidator requestValidator, CreateSubscription createSubscription) {
      this.requestValidator = requestValidator;
      this.createSubscription = createSubscription;
   }

   @RequestMapping(path = "/create", method = {RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
   public ResponseEntity<String> createSubscriptionNotGet(@RequestParam("eventUrl") final String eventUrl, final Model model) {
      return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
   }
   
   @RequestMapping(path = "/create", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<String> createSubscription(@RequestParam("eventUrl") final String eventUrl, final Model model) {
      if(Strings.isNullOrEmpty(eventUrl)) {
         return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }
      createSubscription.createSubscription(eventUrl);
      return new ResponseEntity<>(HttpStatus.OK);
   }

}
