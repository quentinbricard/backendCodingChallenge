package com.appdirect.subscription.service;

@FunctionalInterface
public interface CreateSubscription {
   public static final String ACTION = "CREATE";
   
   void createSubscription(String eventUrl);
}
