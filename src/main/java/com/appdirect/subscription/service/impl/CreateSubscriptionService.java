package com.appdirect.subscription.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.appdirect.oauth.RequestSigner;
import com.appdirect.oauth.account.entity.Account;
import com.appdirect.oauth.account.entity.AccountStatus;
import com.appdirect.oauth.account.repository.AccountRepository;
import com.appdirect.subscription.entity.json.DetailsSubscription;
import com.appdirect.subscription.exception.SubscriptionException;
import com.appdirect.subscription.service.CreateSubscription;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.HttpHeaders;

/**
 * <p>Service in charge of creating a subscription from a simple URL.</p>
 * @author quentinbricard
 *
 */
@Service
public class CreateSubscriptionService implements CreateSubscription {
   
   private static final Logger LOGGER = LoggerFactory.getLogger(CreateSubscriptionService.class);
   
   private final RequestSigner requestSigner;
   private final AccountRepository accountRepository;
   
   private final ObjectMapper mapper = new ObjectMapper();
   
   @Autowired
   public CreateSubscriptionService(final RequestSigner requestSigner, final AccountRepository accountRepository) {
      this.requestSigner = requestSigner;
      this.accountRepository = accountRepository;
   }
   
   /*
    * (non-Javadoc)
    * @see com.appdirect.subscription.service.CreateSubscription#createSubscription(java.lang.String)
    */
   @Override
   public Account createSubscription(String eventUrl) {

      HttpURLConnection connection = requestSigner.getSignedConnection(eventUrl);
      // Always manipulate Json
      connection.setRequestProperty(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
      try {
         connection.connect();
         int code = connection.getResponseCode();
         if(code != HttpStatus.OK.value()) {
            LOGGER.error("Code {} different than {} excepted", code, HttpStatus.OK.value());
            return null;
         }
         // To close automatically the streams
         try(InputStream inputStream = connection.getInputStream();
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            // Actual copy
            FileCopyUtils.copy(inputStream, outputStream);
            String responseData = new String(outputStream.toByteArray());
            LOGGER.debug("Response: {}", responseData);
            
            // Map json to object
            DetailsSubscription detailsSubscription = mapper.readValue(responseData, DetailsSubscription.class);
            // Create account from json
            Account account = new Account(detailsSubscription.getPayload().getCompany().getName(), AccountStatus.FREE_TRIAL.getStatus());
            accountRepository.save(account);
            return account;
         }
      } catch(IOException e) {
         throw new SubscriptionException(ACTION, "Error connection to URL " + eventUrl, e);
      }
   }

}
