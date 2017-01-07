package com.appdirect.subscription.exception;

public class SubscriptionException extends RuntimeException {

   private static final long serialVersionUID = 4601171285162451312L;
   
   private final String action;
   
   public SubscriptionException(String action, String message) {
      super(message);
      this.action = action;
   }
   
   public SubscriptionException(String action, String message, Throwable cause) {
      super(message, cause);
      this.action = action;
   }

   public String getAction() {
      return action;
   }
}
