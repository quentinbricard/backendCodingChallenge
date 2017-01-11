package com.appdirect.oauth;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.oauth.provider.ConsumerDetails;
import org.springframework.security.oauth.provider.ConsumerDetailsService;
import org.springframework.security.oauth.provider.filter.OAuthProviderProcessingFilter;
import org.springframework.test.util.ReflectionTestUtils;

import com.appdirect.oauth.exception.OAuthException;

/**
 * <p>Filter implemented by Spring {@link OAuthProviderProcessingFilter} doesn't need to be tested.</p>
 * <p>This class is just about {@link ConsumerDetails} construction testing.</p>
 * @author quentinbricard
 *
 */
public class OAuthConsumerDetailsServiceTest {

   private static final String CONSUMER_KEY = "key";
   
   private ConsumerDetailsService consumerDetailsService;
   
   @Before
   public void setUp() {
      consumerDetailsService = new OAuthConsumerDetailsService();
      ReflectionTestUtils.setField(consumerDetailsService, "oauthKey", CONSUMER_KEY);
   }
   
   @Test
   public void nullKeyTest() {
      try {
         consumerDetailsService.loadConsumerByConsumerKey(null);
         fail("An exception should have occured");
      } catch(OAuthException e) {
         // Ok
      }
   }
   
   @Test
   public void differentKeyTest() {
      try {
         consumerDetailsService.loadConsumerByConsumerKey("abc");
         fail("An exception should have occured");
      } catch(OAuthException e) {
         // Ok
      }
   }
   
   @Test
   public void keyTest() {
      try {
         ConsumerDetails consumerDetails = consumerDetailsService.loadConsumerByConsumerKey(CONSUMER_KEY);
         assertNotNull(consumerDetails);
         assertEquals(CONSUMER_KEY, consumerDetails.getConsumerKey());
         assertNotNull(consumerDetails.getSignatureSecret());
      } catch(OAuthException e) {
         fail("No exception should have occured");
      }
   }
 }
