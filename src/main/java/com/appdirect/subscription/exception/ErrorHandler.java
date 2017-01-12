package com.appdirect.subscription.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appdirect.subscription.entity.SubscriptionResponse;

@ControllerAdvice
public class ErrorHandler {

   @ExceptionHandler(Exception.class)
   @ResponseBody
   public ResponseEntity<SubscriptionResponse> handleException(Exception exception) {
      SubscriptionResponse subscriptionResponse = new SubscriptionResponse(false, null, ErrorCodes.UNKNOWN_ERROR, "An unexpected error has occurred");
      return new ResponseEntity<>(subscriptionResponse, HttpStatus.OK);
   }
}
