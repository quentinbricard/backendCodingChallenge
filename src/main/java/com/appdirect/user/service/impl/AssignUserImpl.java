package com.appdirect.user.service.impl;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appdirect.connection.error.ErrorCodes;
import com.appdirect.connection.request.RequestHandler;
import com.appdirect.connection.response.json.Details;
import com.appdirect.connection.response.json.User;
import com.appdirect.model.entity.AccountEntity;
import com.appdirect.model.entity.UserEntity;
import com.appdirect.model.repository.AccountRepository;
import com.appdirect.user.exception.UserException;
import com.appdirect.user.service.AssignUser;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AssignUserImpl implements AssignUser {

   private static final Logger LOGGER = LoggerFactory.getLogger(AssignUserImpl.class);
   
   private final RequestHandler requestHandler;
   private final AccountRepository accountRepository;
   
   private final ObjectMapper mapper = new ObjectMapper();
   
   @Autowired
   public AssignUserImpl(final RequestHandler requestHandler, final AccountRepository accountRepository) {
      this.requestHandler = requestHandler;
      this.accountRepository = accountRepository;
   }

   /*
    * (non-Javadoc)
    * @see com.appdirect.user.service.AssignUser#assignUser(java.lang.String)
    */
   @Override
   public UserEntity assignUser(String eventUrl) {

      // Call URL
      String responseData = requestHandler.executeRequest(ACTION, eventUrl);
      
      // Map json to object
      Details detailsUser = null;
      try {
         detailsUser = mapper.readValue(responseData, Details.class);
      } catch(IOException e) {
         throw new UserException(ACTION, "Error mapping json " + responseData + " to object", ErrorCodes.INVALID_RESPONSE, e);
      }
      String accountIdentifier = detailsUser.getPayload().getAccount().getAccountIdentifier();
      AccountEntity account = accountRepository.findById(accountIdentifier);
      if(account == null) {
         throw new UserException(ACTION, "Account with identifier " + accountIdentifier + " has not been found", ErrorCodes.ACCOUNT_NOT_FOUND);
      }
      User user = detailsUser.getPayload().getUser();
      UserEntity userEntity = new UserEntity(user.getUuid(), user.getEmail(), user.getFirstName(), user.getLastName());
      account.getUsers().add(userEntity);
      accountRepository.save(account);
      LOGGER.debug("User '{} {}' saved successfully", user.getFirstName(), user.getLastName());
      return userEntity;
   }
}
