package com.appdirect.user.exception;

public class UserException extends RuntimeException {

   private static final long serialVersionUID = -2230498638980913489L;
   
   private final String action;
   private final String errorCode;
   
   public UserException(String action, String message, String errorCode) {
      super(message);
      this.action = action;
      this.errorCode = errorCode;
   }
   
   public UserException(String action, String message, String errorCode, Throwable cause) {
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
