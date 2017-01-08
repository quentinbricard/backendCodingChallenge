package com.appdirect.oauth.impl;

import org.springframework.stereotype.Component;

import com.appdirect.oauth.RequestValidator;
import com.appdirect.oauth.exception.OAuthException;
import com.google.common.base.Strings;

@Component
public class RequestValidatorImpl implements RequestValidator {

   @Override
   public void validateOauthParameters(String oauthConsumerKey, String oauthNonce, String oauthSignature, String oauthSignatureMethod, String oauthTimestamp, String oauthVersion) {
      if(Strings.isNullOrEmpty(oauthSignature)) {
         throw new OAuthException("Request does not contain a signature");
      }
      // TODO
   }

}
