package com.appdirect.subscription.service;

import com.appdirect.account.entity.AccountEntity;

@FunctionalInterface
public interface CreateSubscription {
   public static final String ACTION = "CREATE";
   
   /**
    * <p>Creates a subscription with a given URL:
    * <ol>
    * <li>Performs a HTTP call to this URL in order to get details.</li>
    * <li>Creates and stores an account associated to the retrieved information.</li>
    * </ol> 
    * </p>
    * @param eventUrl
    * @return the created {@link AccountEntity}
    */
   AccountEntity createSubscription(String eventUrl);
}
