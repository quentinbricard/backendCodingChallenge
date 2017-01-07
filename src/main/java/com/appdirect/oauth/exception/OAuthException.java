package com.appdirect.oauth.exception;

public class OAuthException extends RuntimeException {

   private static final long serialVersionUID = 8840770362206313423L;

   public OAuthException() {
   }
   
   public OAuthException(String message) {
      super(message);
   }
   
   public OAuthException(String message, Throwable cause) {
      super(message, cause);
   }
}
