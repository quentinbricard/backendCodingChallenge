package com.appdirect.connection.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public final class ResponseEntityFactory {
   
   private ResponseEntityFactory() {} 
   
   /**
    * <p>Constructs a {@link Response} from the given parameters 
    * and returns a {@link ResponseEntity} with status {@link HttpStatus.OK}</p>
    * @param success
    * @param accountIdentifier
    * @param errorCode
    * @param message
    * @return
    */
   public static ResponseEntity<Response> buildResponseEntityFromResponse(boolean success, 
         String accountIdentifier, String errorCode, String message) {
      Response response = new Response(success, accountIdentifier, errorCode, message);
      return new ResponseEntity<>(response, HttpStatus.OK);
   }

}
