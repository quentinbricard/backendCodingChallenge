package com.appdirect.subscription.service.impl;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appdirect.connection.error.ErrorCodes;
import com.appdirect.connection.request.RequestHandler;
import com.appdirect.connection.response.json.Item;
import com.appdirect.connection.response.json.Order;
import com.appdirect.connection.response.json.Details;
import com.appdirect.model.entity.AccountEntity;
import com.appdirect.model.entity.ItemEntity;
import com.appdirect.model.entity.OrderEntity;
import com.appdirect.model.repository.AccountRepository;
import com.appdirect.subscription.exception.SubscriptionException;
import com.appdirect.subscription.service.ChangeSubscription;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>Service in charge of changing a subscription from a simple URL.</p>
 * @author quentinbricard
 *
 */
@Service
public class ChangeSubscriptionService implements ChangeSubscription {
   
   private static final Logger LOGGER = LoggerFactory.getLogger(ChangeSubscriptionService.class);
   
   private final RequestHandler requestHandler;
   private final AccountRepository accountRepository;
   
   private final ObjectMapper mapper = new ObjectMapper();
   
   @Autowired
   public ChangeSubscriptionService(final RequestHandler requestHandler, final AccountRepository accountRepository) {
      this.requestHandler = requestHandler;
      this.accountRepository = accountRepository;
   }

   /*
    * (non-Javadoc)
    * @see com.appdirect.subscription.service.ChangeSubscription#changeSubscription(java.lang.String)
    */
   @Override
   public AccountEntity changeSubscription(String eventUrl) {

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
      OrderEntity orderEntity = account.getOrder();
      Order order = detailsSubscription.getPayload().getOrder();
      orderEntity.setEditionCode(order.getEditionCode());
      orderEntity.setPricingDuration(order.getPricingDuration());
      orderEntity.getItems().clear();
      for(Item item: order.getItems()) {
         ItemEntity itemEntity = new ItemEntity(item.getQuantity(), item.getUnit());
         orderEntity.getItems().add(itemEntity);
      }
      accountRepository.save(account);
      LOGGER.debug("Account {} changed successfully", account.getName());
      return account;
   }

}
