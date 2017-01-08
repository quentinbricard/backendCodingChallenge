package com.appdirect.subscription.service;

import com.appdirect.oauth.account.entity.Account;

@FunctionalInterface
public interface CancelSubscription {
   public static final String ACTION = "CANCEL";
   
   /**
    * <p>Cancels a subscription with a given URL:
    * <ol>
    * <li>Performs a HTTP call to this URL in order to get details.</li>
    * <li>Cancel and save the account associated to the retrieved information.</li>
    * </ol> 
    * </p>
    * @param eventUrl
    * @return
    */
   Account cancelSubscription(String eventUrl);
}
