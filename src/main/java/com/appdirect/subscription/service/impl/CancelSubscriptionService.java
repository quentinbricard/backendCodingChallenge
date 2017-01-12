package com.appdirect.subscription.service.impl;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appdirect.connection.error.ErrorCodes;
import com.appdirect.connection.request.RequestHandler;
import com.appdirect.connection.response.json.Details;
import com.appdirect.model.entity.AccountEntity;
import com.appdirect.model.entity.AccountStatus;
import com.appdirect.model.repository.AccountRepository;
import com.appdirect.subscription.exception.SubscriptionException;
import com.appdirect.subscription.service.CancelSubscription;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>Service in charge of canceling a subscription from a simple URL.</p>
 * @author quentinbricard
 *
 */
@Service
public class CancelSubscriptionService implements CancelSubscription {
   
   private static final Logger LOGGER = LoggerFactory.getLogger(CancelSubscriptionService.class);
   
   private final RequestHandler requestHandler;
   private final AccountRepository accountRepository;
   
   private final ObjectMapper mapper = new ObjectMapper();
   
   @Autowired
   public CancelSubscriptionService(final RequestHandler requestHandler, final AccountRepository accountRepository) {
      this.requestHandler = requestHandler;
      this.accountRepository = accountRepository;
   }
   
   /*
    * (non-Javadoc)
    * @see com.appdirect.subscription.service.CancelSubscription#cancelSubscription(java.lang.String)
    */
   @Override
   public AccountEntity cancelSubscription(String eventUrl) {

      // Call URL
      String responseData = requestHandler.executeRequest(ACTION, eventUrl);
            
      // Map json to object
      Details detailsSubscription = null;
      try {
         detailsSubscription = mapper.readValue(responseData, Details.class);
      } catch(IOException e) {
         throw new SubscriptionException(ACTION, "Error mapping json " + responseData + " to object", ErrorCodes.INVALID_RESPONSE, e);
      }
      // retrieve account
      String accountIdentifier = detailsSubscription.getPayload().getAccount().getAccountIdentifier();
      AccountEntity account = accountRepository.findById(accountIdentifier);
      if(account == null) {
         throw new SubscriptionException(ACTION, "Account with identifier " + accountIdentifier + " has not been found", ErrorCodes.ACCOUNT_NOT_FOUND);
      }
      account.setStatus(AccountStatus.CANCELED.getStatus());
      accountRepository.save(account);
      LOGGER.debug("Account {} canceled successfully", account.getName());
      return account;
   }

}
