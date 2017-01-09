package com.appdirect.oauth;

import org.junit.Before;

import com.appdirect.oauth.impl.RequestValidatorImpl;

public class RequestValidatorTest {

   private static final String SECRET = "SiD4R16G97KBwjFb";
   private static final String OAUTH_KEY = "backend-coding-challenge-146507";
   private static final String VALID_SIGNATURE = "Syk8hSX93UcaaV/+CZz03FsS/3A=";
   private static final String OAUTH_NONCE = "9048933445469945486";
   private static final String OAUTH_SIGNATURE_METHOD = "HMAC-SHA1";
   private static final String OAUTH_TIMESTAMP = "HMAC-SHA1";
   private static final String OAUTH_VERSION = "1.0";
   
   private RequestValidator requestValidator;
   
   @Before
   public void setUp() {
      requestValidator = new RequestValidatorImpl(); 
   }
   
   public void testOk() {
//      HmacUtils.hmacSha1(OAUTH_KEY.getBytes(), );
//      requestValidator.validateOauthParameters(KEY, null, VALID_SIGNATURE, null, null, null);  
   }
}
