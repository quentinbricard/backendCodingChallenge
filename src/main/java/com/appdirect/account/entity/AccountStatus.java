package com.appdirect.account.entity;

/**
 * <p>Enumeration to represent the status of an account.</p>
 * @author quentinbricard
 *
 */
public enum AccountStatus {

   FREE_TRIAL(1),
   FREE_TRIAL_EXPIRED(2),
   CANCELED(3),
   ACTIVE(4),
   SUSPENDED(5);
   
   private int status;
   
   AccountStatus(int status) {
      this.status = status;
   }
   
   public int getStatus() {
      return status;
   }
   
   /**
    * <p>Parsing method to return the account status associated to the given integer.</p>
    * @param status integer
    * @return a {@link AccountStatus} if found, <code>null</code> otherwise
    */
   public static AccountStatus fromInt(int status) {
      for(AccountStatus account: AccountStatus.values()) {
         if(status == account.status) {
            return account;
         }
      }
      return null;
   }
}
