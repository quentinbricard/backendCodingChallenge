package com.appdirect.user.service.impl;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appdirect.connection.error.ErrorCodes;
import com.appdirect.connection.request.RequestHandler;
import com.appdirect.connection.response.json.Details;
import com.appdirect.model.entity.AccountEntity;
import com.appdirect.model.entity.UserEntity;
import com.appdirect.model.repository.AccountRepository;
import com.appdirect.model.repository.UserRepository;
import com.appdirect.user.exception.UserException;
import com.appdirect.user.service.UnassignUser;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UnassignUserImpl implements UnassignUser {

   private static final Logger LOGGER = LoggerFactory.getLogger(UnassignUserImpl.class);
   
   private final RequestHandler requestHandler;
   private final UserRepository userRepository;
   private final AccountRepository accountRepository;
   
   private final ObjectMapper mapper = new ObjectMapper();
   
   @Autowired
   public UnassignUserImpl(final RequestHandler requestHandler, final UserRepository userRepository, final AccountRepository accountRepository) {
      this.requestHandler = requestHandler;
      this.userRepository = userRepository;
      this.accountRepository = accountRepository;
   }

   /*
    * (non-Javadoc)
    * @see com.appdirect.user.service.UnassignUser#unassignUser(java.lang.String)
    */
   @Override
   public UserEntity unassignUser(String eventUrl) {

      // Call URL
      String responseData = requestHandler.executeRequest(ACTION, eventUrl);
      
      // Map json to object
      Details detailsUser = null;
      try {
         detailsUser = mapper.readValue(responseData, Details.class);
      } catch(IOException e) {
         throw new UserException(ACTION, "Error mapping json " + responseData + " to object", ErrorCodes.INVALID_RESPONSE, e);
      }
      String userIdentifier = detailsUser.getPayload().getUser().getUuid();
      UserEntity userEntity = userRepository.findById(userIdentifier);
      if(userEntity == null) {
         throw new UserException(ACTION, "User with identifier " + userIdentifier + " has not been found", ErrorCodes.ACCOUNT_NOT_FOUND);
      }
      String accountIdentifier = detailsUser.getPayload().getAccount().getAccountIdentifier();
      AccountEntity account = accountRepository.findById(accountIdentifier);
      if(account == null) {
         throw new UserException(ACTION, "Account with identifier " + accountIdentifier + " has not been found", ErrorCodes.ACCOUNT_NOT_FOUND);
      }
      account.getUsers().remove(userEntity);
      accountRepository.save(account);
      LOGGER.debug("User '{} {}' removed successfully", userEntity.getFirstName(), userEntity.getLastName());
      return userEntity;
   }
}
