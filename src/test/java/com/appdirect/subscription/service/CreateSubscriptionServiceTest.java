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

import com.appdirect.connection.error.ErrorCodes;
import com.appdirect.connection.request.RequestHandler;
import com.appdirect.connection.response.json.Company;
import com.appdirect.connection.response.json.Order;
import com.appdirect.connection.response.json.Payload;
import com.appdirect.connection.response.json.Details;
import com.appdirect.model.entity.AccountEntity;
import com.appdirect.model.entity.AccountStatus;
import com.appdirect.model.repository.AccountRepository;
import com.appdirect.subscription.exception.SubscriptionException;
import com.appdirect.subscription.service.impl.CreateSubscriptionService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(PowerMockRunner.class)
@PrepareForTest(CreateSubscriptionService.class)
public class CreateSubscriptionServiceTest {

   private static final String COMPANY_NAME = "company";
   private static final String DUMMY_URL = "DUMMY_URL";
   
   private CreateSubscription createSubscription;
   
   private RequestHandler requestHandlerMock;
   private AccountRepository accountRepositoryMock;
   
   @Mock
   private ObjectMapper mapperMock;
   
   private Details detailsSubscription;
   
   @Before
   public void setUp() throws Exception {
      requestHandlerMock = mock(RequestHandler.class);
      accountRepositoryMock = mock(AccountRepository.class);
      whenNew(ObjectMapper.class).withNoArguments().thenReturn(mapperMock);
      createSubscription = new CreateSubscriptionService(requestHandlerMock, accountRepositoryMock);
      
      detailsSubscription = new Details();
      Payload payload = new Payload();
      Company company = new Company();
      company.setName(COMPANY_NAME);
      Order order = new Order();
      payload.setOrder(order);
      payload.setCompany(company);
      detailsSubscription.setPayload(payload);
   }
   
   @Test
   public void testCreationSuccessful() throws JsonParseException, JsonMappingException, IOException {
      // Declare response as a String so the right signature is found
      String response = null;
      when(mapperMock.readValue(response, Details.class)).thenReturn(detailsSubscription);
      AccountEntity account = createSubscription.createSubscription(DUMMY_URL);
      assertNotNull(account);
      // Since repository is not actually called
      assertNull(account.getId());
      assertEquals(COMPANY_NAME, account.getName());
      assertEquals(AccountStatus.FREE_TRIAL.getStatus(), account.getStatus());
   }
   
   @SuppressWarnings("unchecked")
   @Test
   public void testErrorJSon() throws JsonParseException, JsonMappingException, IOException {
      // Declare response as a String so the right signature is found
      String response = null;
      when(mapperMock.readValue(response, Details.class)).thenThrow(IOException.class);
      try {
         createSubscription.createSubscription(DUMMY_URL);
         fail("An exception should have occured");
      } catch(SubscriptionException e) {
         assertEquals(CreateSubscription.ACTION, e.getAction());
         assertEquals(ErrorCodes.INVALID_RESPONSE, e.getErrorCode());
      }
   }
}
