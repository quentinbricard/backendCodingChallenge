package com.appdirect.subscription.service;

@FunctionalInterface
public interface RequestHandler {

   /**
    * <p>Performs a signed request and returns the result as a {@link String}.</p>
    * @param action requested action (in case of error)
    * @param url the given URL
    * @return a {@link String}
    */
   String executeRequest(String action, String url);
}
