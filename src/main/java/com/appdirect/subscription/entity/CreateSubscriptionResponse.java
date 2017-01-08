package com.appdirect.subscription.entity;

import org.assertj.core.util.Preconditions;

/**
 * <p>Object used to represent what happened after a subscription creation request.</p>
 * @author quentinbricard
 *
 */
public class CreateSubscriptionResponse {
   
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
   public CreateSubscriptionResponse(boolean success, String accountIdentifier, String errorCode, String message) {
      this(Boolean.valueOf(success).toString().toLowerCase(), accountIdentifier, errorCode, message);
   }
   
   /**
    * <p>Just a shortcut to the regular constructor.</p>
    * @param success <code>true</code> or <code>false</code> which will be passed as a {@link String}
    * @param accountIdentifier unique identifier
    * @param errorCode errorCode if any
    * @param message message (optional)
    */
   public CreateSubscriptionResponse(String success, String accountIdentifier, String errorCode, String message) {
      Preconditions.checkNotNull(success, "success field must not be null");
      Preconditions.checkNotNull(accountIdentifier, "accountIdentifier must not be null");
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
