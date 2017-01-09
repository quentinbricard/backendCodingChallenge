package com.appdirect.oauth.impl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.appdirect.oauth.RequestSigner;
import com.appdirect.oauth.exception.OAuthException;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

@PropertySource("classpath:oauth.properties")
@Component
public class RequestSignerImpl implements RequestSigner {

   private static final Logger LOGGER = LoggerFactory.getLogger(RequestSignerImpl.class);
   
   @Value("${oauth.consumer.key}")
   private String oauthKey;
   
   @Value("${oauth.consumer.secret}")
   private String oauthSecret;
   
   public HttpURLConnection getSignedConnection(final String requestedUrl) {
      LOGGER.debug("Creating connection for URL {}...", requestedUrl);
      OAuthConsumer consumer = new DefaultOAuthConsumer(oauthKey, oauthSecret);
      URL url = null;
      try {
         url = new URL(requestedUrl);
      } catch(MalformedURLException e) {
         throw new OAuthException("URL is malformed", e);
      }
      HttpURLConnection request = null;
      try {
         request = (HttpURLConnection) url.openConnection();
      } catch(IOException e) {
         throw new OAuthException("Error opening connection", e);
      }
      try {
         consumer.sign(request);
      } catch(OAuthMessageSignerException|OAuthExpectationFailedException e) {
         throw new OAuthException("Error signing connection", e);
      } catch(OAuthCommunicationException e) {
         throw new OAuthException("Communication error signing connection", e);
      }
      LOGGER.debug("Connection created successfully.");
      return request;
   }
}
