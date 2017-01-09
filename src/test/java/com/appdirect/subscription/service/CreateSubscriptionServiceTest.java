package com.appdirect.subscription.service;

import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import com.appdirect.oauth.account.repository.AccountRepository;
import com.appdirect.subscription.service.impl.CreateSubscriptionService;

public class CreateSubscriptionServiceTest {

   private static final String DUMMY_URL = "DUMMY_URL";
   
   private CreateSubscription createSubscription;
   
   private RequestHandler requestHandlerMock;
   private AccountRepository accountRepositoryMock;
   
   @Before
   public void setUp() {
      requestHandlerMock = mock(RequestHandler.class);
      accountRepositoryMock = mock(AccountRepository.class);
      createSubscription = new CreateSubscriptionService(requestHandlerMock, accountRepositoryMock);  
   }
   
   @Test
   public void testOk() {
      createSubscription.createSubscription(DUMMY_URL);
   }
}
