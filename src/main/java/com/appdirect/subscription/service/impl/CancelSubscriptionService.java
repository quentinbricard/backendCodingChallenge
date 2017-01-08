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
import com.appdirect.subscription.service.CancelSubscription;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.HttpHeaders;

/**
 * <p>Service in charge of canceling a subscription from a simple URL.</p>
 * @author quentinbricard
 *
 */
@Service
public class CancelSubscriptionService implements CancelSubscription {
   
   private static final Logger LOGGER = LoggerFactory.getLogger(CancelSubscriptionService.class);
   
   private final RequestSigner requestSigner;
   private final AccountRepository accountRepository;
   
   private final ObjectMapper mapper = new ObjectMapper();
   
   @Autowired
   public CancelSubscriptionService(final RequestSigner requestSigner, final AccountRepository accountRepository) {
      this.requestSigner = requestSigner;
      this.accountRepository = accountRepository;
   }
   
   /*
    * (non-Javadoc)
    * @see com.appdirect.subscription.service.CancelSubscription#cancelSubscription(java.lang.String)
    */
   @Override
   public Account cancelSubscription(String eventUrl) {

      HttpURLConnection connection = requestSigner.getSignedConnection(eventUrl);
      // Always manipulate Json
      connection.setRequestProperty(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
      String responseData = null;
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
            responseData = new String(outputStream.toByteArray());
            LOGGER.debug("Response: {}", responseData);
         }
      } catch(IOException e) {
         throw new SubscriptionException(ACTION, "Error connection to URL " + eventUrl, e);
      }
            
      // Map json to object
      DetailsSubscription detailsSubscription = null;
      try {
         detailsSubscription = mapper.readValue(responseData, DetailsSubscription.class);
      } catch(IOException e) {
         throw new SubscriptionException(ACTION, "Error mapping json " + responseData + " to object", e);
      }
      // retrieve account
      String accountIdentifier = detailsSubscription.getPayload().getAccount().getAccountIdentifier();
      Account account = accountRepository.findById(accountIdentifier);
      if(account == null) {
         throw new SubscriptionException(ACTION, "Account with identifier " + accountIdentifier + " has not been found");
      }
      account.setStatus(AccountStatus.CANCELED.getStatus());
      accountRepository.save(account);
      return account;
   }

}
