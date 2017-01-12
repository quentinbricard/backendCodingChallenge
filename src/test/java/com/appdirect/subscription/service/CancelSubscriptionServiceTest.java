package com.appdirect.subscription.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.appdirect.model.account.entity.AccountEntity;
import com.appdirect.model.account.entity.AccountStatus;
import com.appdirect.model.account.repository.AccountRepository;
import com.appdirect.subscription.entity.json.DetailsSubscription;
import com.appdirect.subscription.entity.json.Payload;
import com.appdirect.subscription.exception.ErrorCodes;
import com.appdirect.subscription.exception.SubscriptionException;
import com.appdirect.subscription.service.impl.CancelSubscriptionService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(PowerMockRunner.class)
@PrepareForTest(CancelSubscriptionService.class)
public class CancelSubscriptionServiceTest {

   private static final String ACCOUNT_ID = "accountId";
   private static final String COMPANY_NAME = "company";
   private static final String DUMMY_URL = "DUMMY_URL";
   
   private CancelSubscription cancelSubscription;
   
   private RequestHandler requestHandlerMock;
   private AccountRepository accountRepositoryMock;
   
   @Mock
   private ObjectMapper mapperMock;
   
   private DetailsSubscription detailsSubscription;
   
   @Before
   public void setUp() throws Exception {
      requestHandlerMock = mock(RequestHandler.class);
      accountRepositoryMock = mock(AccountRepository.class);
      whenNew(ObjectMapper.class).withNoArguments().thenReturn(mapperMock);
      cancelSubscription = new CancelSubscriptionService(requestHandlerMock, accountRepositoryMock);
      
      detailsSubscription = new DetailsSubscription();
      Payload payload = new Payload();
      com.appdirect.subscription.entity.json.Account account = new com.appdirect.subscription.entity.json.Account();
      account.setAccountIdentifier(ACCOUNT_ID);
      payload.setAccount(account);
      detailsSubscription.setPayload(payload);
   }
   
   @Test
   public void testCancelSuccessful() throws JsonParseException, JsonMappingException, IOException {
      AccountEntity account = new AccountEntity(COMPANY_NAME, AccountStatus.FREE_TRIAL.getStatus());
      when(accountRepositoryMock.findById(ACCOUNT_ID)).thenReturn(account);
      // Declare response as a String so the right signature is found
      String response = null;
      when(mapperMock.readValue(response, DetailsSubscription.class)).thenReturn(detailsSubscription);
      AccountEntity accountResult = cancelSubscription.cancelSubscription(DUMMY_URL);
      assertNotNull(accountResult);
      // Since repository is not actually called
      assertNull(account.getId());
      assertEquals(COMPANY_NAME, account.getName());
      assertEquals(AccountStatus.CANCELED.getStatus(), account.getStatus());
   }
   
   @SuppressWarnings("unchecked")
   @Test
   public void testErrorJSon() throws JsonParseException, JsonMappingException, IOException {
      // Declare response as a String so the right signature is found
      String response = null;
      when(mapperMock.readValue(response, DetailsSubscription.class)).thenThrow(IOException.class);
      try {
         cancelSubscription.cancelSubscription(DUMMY_URL);
         fail("An exception should have occured");
      } catch(SubscriptionException e) {
         assertEquals(CancelSubscription.ACTION, e.getAction());
      }
   }
   
   @Test
   public void testNoAccountFound() throws JsonParseException, JsonMappingException, IOException {
      // Declare response as a String so the right signature is found
      String response = null;
      when(mapperMock.readValue(response, DetailsSubscription.class)).thenReturn(detailsSubscription);
      try {
         cancelSubscription.cancelSubscription(DUMMY_URL);
         fail("An exception should have occured");
      } catch(SubscriptionException e) {
         assertEquals(CancelSubscription.ACTION, e.getAction());
         assertEquals(ErrorCodes.ACCOUNT_NOT_FOUND, e.getErrorCode());
      }
   }
}
