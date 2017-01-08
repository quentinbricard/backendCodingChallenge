package com.appdirect.oauth;

@FunctionalInterface
public interface RequestValidator {

   void validateOauthParameters(String oauthConsumerKey, String oauthNonce, String oauthSignature, String oauthSignatureMethod, 
         String oauthTimestamp, String oauthVersion);
}
