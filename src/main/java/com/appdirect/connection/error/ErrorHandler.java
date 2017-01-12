package com.appdirect.connection.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appdirect.connection.response.Response;

@ControllerAdvice
public class ErrorHandler {

   @ExceptionHandler(Exception.class)
   @ResponseBody
   public ResponseEntity<Response> handleException(Exception exception) {
      Response subscriptionResponse = new Response(false, null, ErrorCodes.UNKNOWN_ERROR, "An unexpected error has occurred");
      return new ResponseEntity<>(subscriptionResponse, HttpStatus.OK);
   }
}
