package com.appdirect.subscription.entity;

import com.google.common.base.Preconditions;

/**
 * <p>Object used to represent what happened after a subscription request.</p>
 * @author quentinbricard
 *
 */
public class SubscriptionResponse {
   
   private String success;
   private String accountIdentifier;
   private String errorCode;
   private String message;
   
   /**
    * <p>Just a shortcut to the regular constructor.</p>
    * @param success <code>true</code> or <code>false</code> which will be passed as a {@link String}
    * @param accountIdentifier unique identifier
    * @param errorCode errorCode if any
    * @param message message (optional)
    */
   public SubscriptionResponse(boolean success, String accountIdentifier, String errorCode, String message) {
      this(Boolean.toString(success).toLowerCase(), accountIdentifier, errorCode, message);
   }
   
   /**
    * <p>Build an response representing a success or a failure.</p>
    * @param success <code>true</code> or <code>false</code> as a {@link String}
    * @param accountIdentifier unique identifier
    * @param errorCode errorCode if any
    * @param message message (optional)
    */
   public SubscriptionResponse(String success, String accountIdentifier, String errorCode, String message) {
      Preconditions.checkNotNull(success, "success field must not be null");
      this.success = success;
      this.accountIdentifier = accountIdentifier;
      this.errorCode = errorCode;
      this.message = message;
   }

   public String getSuccess() {
      return success;
   }

   public String getAccountIdentifier() {
      return accountIdentifier;
   }

   public String getErrorCode() {
      return errorCode;
   }

   public String getMessage() {
      return message;
   }
   
}
