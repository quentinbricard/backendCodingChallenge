package com.appdirect.subscription.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import com.appdirect.oauth.RequestSigner;
import com.appdirect.subscription.exception.SubscriptionException;
import com.appdirect.subscription.service.RequestHandler;
import com.google.common.net.HttpHeaders;

/**
 * <p>Component handling a request call.</p>
 * @author quentinbricard
 *
 */
@Component
public class RequestHandlerImpl implements RequestHandler {
   private static final Logger LOGGER = LoggerFactory.getLogger(RequestHandlerImpl.class);
   
   private final RequestSigner requestSigner;
   
   @Autowired
   public RequestHandlerImpl(final RequestSigner requestSigner) {
      this.requestSigner = requestSigner;
   }
   
   /*
    * (non-Javadoc)
    * @see com.appdirect.subscription.service.RequestHandler#executeRequest(java.lang.String, java.lang.String)
    */
   @Override
   public String executeRequest(String action, String url) {
      HttpURLConnection connection = requestSigner.getSignedConnection(url);
      // Always manipulate Json
      connection.setRequestProperty(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
      String responseData = null;
      try {
         connection.connect();
         int code = connection.getResponseCode();
         if(code != HttpStatus.OK.value()) {
            LOGGER.error("Code {} different than {} excepted", code, HttpStatus.OK.value());
            return null;
         }
         // To close automatically the streams
         try(InputStream inputStream = connection.getInputStream();
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            // Actual copy
            FileCopyUtils.copy(inputStream, outputStream);
            responseData = new String(outputStream.toByteArray());
            LOGGER.debug("Response: {}", responseData);
         }
      } catch(IOException e) {
         throw new SubscriptionException(action, "Error connection to URL " + url, e);
      }
      return responseData;
   }

}
