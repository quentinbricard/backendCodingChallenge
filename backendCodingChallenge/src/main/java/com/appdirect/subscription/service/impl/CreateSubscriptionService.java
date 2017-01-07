package com.appdirect.subscription.service.impl;

import java.io.IOException;
import java.net.HttpURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.appdirect.oauth.RequestSigner;
import com.appdirect.subscription.exception.SubscriptionException;
import com.appdirect.subscription.service.CreateSubscription;

@Service
public class CreateSubscriptionService implements CreateSubscription {
   
   private static final Logger LOGGER = LoggerFactory.getLogger(CreateSubscriptionService.class);
   
   private final RequestSigner requestSigner;
   
   @Autowired
   public CreateSubscriptionService(final RequestSigner requestSigner) {
      this.requestSigner = requestSigner;
   }
   
   @Override
   public void createSubscription(String eventUrl) {

      HttpURLConnection connection = requestSigner.getSignedConnection(eventUrl);
      try {
         connection.connect();
         int code = connection.getResponseCode();
         if(code != HttpStatus.OK.value()) {
            LOGGER.error("Code {} different than {} excepted", code, HttpStatus.OK.value());
            return;
         }
         String response = connection.getResponseMessage();
         LOGGER.debug(response);
      } catch(IOException e) {
         throw new SubscriptionException(ACTION, "Error connection to URL " + eventUrl, e);
      }
   }

}
