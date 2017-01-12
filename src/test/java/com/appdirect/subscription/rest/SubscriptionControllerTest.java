package com.appdirect.subscription.rest;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.appdirect.model.account.entity.AccountEntity;
import com.appdirect.subscription.service.CancelSubscription;
import com.appdirect.subscription.service.ChangeSubscription;
import com.appdirect.subscription.service.CreateSubscription;

@SpringBootTest
public class SubscriptionControllerTest {
   
   private static final String ID = "id";
   private static final String DUMMY = "dummy";
   private static final String EVENT_URL_PARAM = "eventUrl";
   private static final String SUBSCRIPTION_PATH = "/subscriptions";
   private static final String CREATE_PATH = "/create";
   private static final String CANCEL_PATH = "/cancel";
   private static final String CHANGE_PATH = "/change";
   private static final String CREATE_SUBSCRIPTION_PATH = SUBSCRIPTION_PATH + CREATE_PATH;
   private static final String CANCEL_SUBSCRIPTION_PATH = SUBSCRIPTION_PATH + CANCEL_PATH;
   private static final String CHANGE_SUBSCRIPTION_PATH = SUBSCRIPTION_PATH + CHANGE_PATH;

   private MockMvc mockMvc;
   private CreateSubscription createSubscriptionMock;
   private CancelSubscription cancelSubscriptionMock;
   private ChangeSubscription changeSubscriptionMock;
   
   private AccountEntity account;
   @Before
   public void setup() {
      account = new AccountEntity();
      account.setId(ID);
      createSubscriptionMock = Mockito.mock(CreateSubscription.class);
      when(createSubscriptionMock.createSubscription(DUMMY)).thenReturn(account);
      cancelSubscriptionMock = Mockito.mock(CancelSubscription.class);
      when(cancelSubscriptionMock.cancelSubscription(DUMMY)).thenReturn(account);
      changeSubscriptionMock = Mockito.mock(ChangeSubscription.class);
      when(changeSubscriptionMock.changeSubscription(DUMMY)).thenReturn(account);
      SubscriptionController subscriptionController = new SubscriptionController(createSubscriptionMock, cancelSubscriptionMock, changeSubscriptionMock);
      mockMvc = MockMvcBuilders.standaloneSetup(subscriptionController).build();
   }
 
   @Test
   public void testCreatePost() throws Exception {
      mockMvc.perform(MockMvcRequestBuilders.post(CREATE_SUBSCRIPTION_PATH).param(EVENT_URL_PARAM, DUMMY))
      .andExpect(MockMvcResultMatchers.status().isMethodNotAllowed());
   }
   
   
   @Test
   public void testCreateNoURL() throws Exception {
      mockMvc.perform(MockMvcRequestBuilders.get(CREATE_SUBSCRIPTION_PATH))
      .andExpect(MockMvcResultMatchers.status().isBadRequest());
   }
   
   @Test
   public void testCreateSuccess() throws Exception {
       mockMvc.perform(MockMvcRequestBuilders.get(CREATE_SUBSCRIPTION_PATH).param(EVENT_URL_PARAM, DUMMY))
               .andExpect(MockMvcResultMatchers.status().isOk());
   }
   
   @Test
   public void testCancelPost() throws Exception {
      mockMvc.perform(MockMvcRequestBuilders.post(CANCEL_SUBSCRIPTION_PATH).param(EVENT_URL_PARAM, DUMMY))
      .andExpect(MockMvcResultMatchers.status().isMethodNotAllowed());
   }
   
   
   @Test
   public void testCancelNoURL() throws Exception {
      mockMvc.perform(MockMvcRequestBuilders.get(CANCEL_SUBSCRIPTION_PATH))
      .andExpect(MockMvcResultMatchers.status().isBadRequest());
   }
   
   @Test
   public void testCancelSuccess() throws Exception {
      mockMvc.perform(MockMvcRequestBuilders.get(CANCEL_SUBSCRIPTION_PATH).param(EVENT_URL_PARAM, DUMMY))
      .andExpect(MockMvcResultMatchers.status().isOk());
   }
   
   @Test
   public void testChangePost() throws Exception {
      mockMvc.perform(MockMvcRequestBuilders.post(CHANGE_SUBSCRIPTION_PATH).param(EVENT_URL_PARAM, DUMMY))
      .andExpect(MockMvcResultMatchers.status().isMethodNotAllowed());
   }
   
   
   @Test
   public void testChangeNoURL() throws Exception {
      mockMvc.perform(MockMvcRequestBuilders.get(CHANGE_SUBSCRIPTION_PATH))
      .andExpect(MockMvcResultMatchers.status().isBadRequest());
   }
   
   @Test
   public void testChangeSuccess() throws Exception {
      mockMvc.perform(MockMvcRequestBuilders.get(CHANGE_SUBSCRIPTION_PATH).param(EVENT_URL_PARAM, DUMMY))
      .andExpect(MockMvcResultMatchers.status().isOk());
   }
}
