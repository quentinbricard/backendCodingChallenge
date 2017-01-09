package com.appdirect.oauth.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.appdirect.oauth.RequestValidator;
import com.appdirect.oauth.exception.OAuthException;
import com.google.common.base.Strings;

@PropertySource("classpath:oauth.properties")
@Component
public class RequestValidatorImpl implements RequestValidator {

   @Value("${oauth.consumer.key}")
   private String oauthKey;

   @Value("${oauth.consumer.secret}")
   private String oauthSecret;

   @Override
   public void validateOauthParameters(String oauthConsumerKey, String oauthNonce, String oauthSignature, String oauthSignatureMethod, String oauthTimestamp,
         String oauthVersion) {
      if(Strings.isNullOrEmpty(oauthSignature)) {
         throw new OAuthException("Request does not contain a OAuth signature");
      }
      if(!oauthKey.equals(oauthConsumerKey)) {
         throw new OAuthException("OAuth key is different than expected");
      }
      // TODO: Use Spring
      
      // Jersey implementation
      // TODO: verify nonces
      // if (!nonces.verify(oauthSignature, oauthTimestamp, oauthNonce)) {
      // throw new OAuthException("");
      // }
//      OAuthSecrets secrets = new OAuthSecrets().consumerSecret(oauthSecret).tokenSecret(oauthSignature);
//      OAuthParameters params = new OAuthParameters().consumerKey(oauthKey).token(oauthSignature).signatureMethod(HMAC_SHA1.NAME).timestamp().nonce().version();
//      OAuthServerRequest oauthRequest = new OAuthServerRequest(request);
//      try {
//         if(!OAuthSignature.verify(oauthRequest, params, secrets)) {
//            throw new OAuthException("OAuth signature is invalid");
//         }
//      } catch(OAuthSignatureException e) {
//         throw new OAuthException("Error during signature validation", e);
//      }
   }
}
