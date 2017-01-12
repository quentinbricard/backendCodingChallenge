package com.appdirect.subscription.exception;

public class SubscriptionException extends RuntimeException {

   private static final long serialVersionUID = 4601171285162451312L;
   
   private final String action;
   private final String errorCode;
   
   public SubscriptionException(String action, String message, String errorCode) {
      super(message);
      this.action = action;
      this.errorCode = errorCode;
   }
   
   public SubscriptionException(String action, String message, String errorCode, Throwable cause) {
      super(message, cause);
      this.action = action;
      this.errorCode = errorCode;
   }

   public String getAction() {
      return action;
   }

   public String getErrorCode() {
      return errorCode;
   }
}
