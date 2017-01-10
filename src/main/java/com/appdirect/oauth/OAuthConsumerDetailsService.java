package com.appdirect.oauth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth.common.signature.SharedConsumerSecretImpl;
import org.springframework.security.oauth.provider.BaseConsumerDetails;
import org.springframework.security.oauth.provider.ConsumerDetails;
import org.springframework.security.oauth.provider.ConsumerDetailsService;
import org.springframework.stereotype.Component;

import com.appdirect.oauth.exception.OAuthException;

@Component
public class OAuthConsumerDetailsService implements ConsumerDetailsService {
   private final static Logger LOGGER = LoggerFactory.getLogger(OAuthConsumerDetailsService.class);

   @Value("${oauth.consumer.key}")
   private String oauthKey;

   @Value("${oauth.consumer.secret}")
   private String oauthSecret;

   @Override
   public ConsumerDetails loadConsumerByConsumerKey(String consumerKey) {
      if(!oauthKey.equals(consumerKey)) {
         throw new OAuthException("Signature validation failed");
      }
      BaseConsumerDetails baseConsumerDetails;
      baseConsumerDetails = new BaseConsumerDetails();
      baseConsumerDetails.setConsumerKey(consumerKey);
      baseConsumerDetails.setSignatureSecret(new SharedConsumerSecretImpl(oauthSecret));
      // baseConsumerDetails.setConsumerName("Sample");
      baseConsumerDetails.setRequiredToObtainAuthenticatedToken(false);
      baseConsumerDetails.getAuthorities().add(new SimpleGrantedAuthority("ROLE_OAUTH"));
      LOGGER.info("OAuth signature validated successfully");
      return baseConsumerDetails;
   }

}