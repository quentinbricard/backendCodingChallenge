package com.appdirect.subscription.service;

import com.appdirect.oauth.account.entity.Account;

@FunctionalInterface
public interface ChangeSubscription {
   public static final String ACTION = "CHANGE";
   
   /**
    * <p>Changes a subscription with a given URL:
    * <ol>
    * <li>Performs a HTTP call to this URL in order to get details.</li>
    * <li>Changes and stores an account associated to the retrieved information.</li>
    * </ol> 
    * </p>
    * @param eventUrl
    * @return the changed {@link Account}
    */
   Account changeSubscription(String eventUrl);
}
