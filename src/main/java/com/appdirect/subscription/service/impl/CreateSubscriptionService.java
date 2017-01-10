package com.appdirect.subscription.service.impl;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appdirect.model.account.entity.AccountEntity;
import com.appdirect.model.account.entity.AccountStatus;
import com.appdirect.model.account.repository.AccountRepository;
import com.appdirect.model.item.entity.ItemEntity;
import com.appdirect.model.order.entity.OrderEntity;
import com.appdirect.subscription.entity.json.DetailsSubscription;
import com.appdirect.subscription.entity.json.Item;
import com.appdirect.subscription.entity.json.Order;
import com.appdirect.subscription.exception.SubscriptionException;
import com.appdirect.subscription.service.CreateSubscription;
import com.appdirect.subscription.service.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>Service in charge of creating a subscription from a simple URL.</p>
 * @author quentinbricard
 *
 */
@Service
public class CreateSubscriptionService implements CreateSubscription {
   
   private static final Logger LOGGER = LoggerFactory.getLogger(CreateSubscriptionService.class);
   
   private final RequestHandler requestHandler;
   private final AccountRepository accountRepository;
   
   private final ObjectMapper mapper = new ObjectMapper();
   
   @Autowired
   public CreateSubscriptionService(final RequestHandler requestHandler, final AccountRepository accountRepository) {
      this.requestHandler = requestHandler;
      this.accountRepository = accountRepository;
   }
   
   /*
    * (non-Javadoc)
    * @see com.appdirect.subscription.service.CreateSubscription#createSubscription(java.lang.String)
    */
   @Override
   public AccountEntity createSubscription(String eventUrl) {

      // Call URL
      String responseData = requestHandler.executeRequest(ACTION, eventUrl);
      
      // Map json to object
      DetailsSubscription detailsSubscription = null;
      try {
         detailsSubscription = mapper.readValue(responseData, DetailsSubscription.class);
      } catch(IOException e) {
         throw new SubscriptionException(ACTION, "Error mapping json " + responseData + " to object", e);
      }
      // Create account from json
      AccountEntity account = new AccountEntity(detailsSubscription.getPayload().getCompany().getName(), AccountStatus.FREE_TRIAL.getStatus());
      Order order = detailsSubscription.getPayload().getOrder();
      OrderEntity orderEntity = new OrderEntity(order.getEditionCode(), order.getEditionCode());
      for(Item item: order.getItems()) {
         ItemEntity itemEntity = new ItemEntity(item.getQuantity(), item.getUnit());
         orderEntity.getItems().add(itemEntity);
      }
      account.setOrder(orderEntity);
      accountRepository.save(account);
      LOGGER.debug("Account '{}' saved successfully", account.getName());
      return account;
   }

}
