package com.appdirect.subscription.rest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.appdirect.subscription.service.CancelSubscription;
import com.appdirect.subscription.service.ChangeSubscription;
import com.appdirect.subscription.service.CreateSubscription;

@SpringBootTest
public class SubscriptionControllerTest {
   
   private static final String DUMMY = "dummy";
   private static final String EVENT_URL_PARAM = "eventUrl";
   private static final String SUBSCRIPTION_PATH = "/subscriptions";
   private static final String CREATE_PATH = "/create";
   private static final String CREATE_SUBSCRIPTION_PATH = SUBSCRIPTION_PATH + CREATE_PATH;

   private MockMvc mockMvc;
   private CreateSubscription createSubscriptionMock;
   private CancelSubscription cancelSubscriptionMock;
   private ChangeSubscription changeSubscriptionMock;
   
   @Before
   public void setup() {
      createSubscriptionMock = Mockito.mock(CreateSubscription.class);
      cancelSubscriptionMock = Mockito.mock(CancelSubscription.class);
      changeSubscriptionMock = Mockito.mock(ChangeSubscription.class);
      SubscriptionController subscriptionController = new SubscriptionController(createSubscriptionMock, cancelSubscriptionMock, changeSubscriptionMock);
      mockMvc = MockMvcBuilders.standaloneSetup(subscriptionController).build();
   }
 
   @Test
   public void testPostCreate() throws Exception {
      mockMvc.perform(MockMvcRequestBuilders.post(CREATE_SUBSCRIPTION_PATH).param(EVENT_URL_PARAM, DUMMY))
      .andExpect(MockMvcResultMatchers.status().isMethodNotAllowed());
   }
   
   @Test
   public void testEmptyURL() throws Exception {
      mockMvc.perform(MockMvcRequestBuilders.get(CREATE_SUBSCRIPTION_PATH).param(EVENT_URL_PARAM, ""))
      .andExpect(MockMvcResultMatchers.status().isBadRequest());
   }
   
   @Test
   public void testNoURL() throws Exception {
      mockMvc.perform(MockMvcRequestBuilders.get(CREATE_SUBSCRIPTION_PATH))
      .andExpect(MockMvcResultMatchers.status().isBadRequest());
   }
   
   @Test
   public void testDummyURL() throws Exception {
       mockMvc.perform(MockMvcRequestBuilders.get(CREATE_SUBSCRIPTION_PATH).param(EVENT_URL_PARAM, DUMMY))
               .andExpect(MockMvcResultMatchers.status().isOk());
   }
}
