package com.appdirect.user.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appdirect.connection.error.ErrorCodes;
import com.appdirect.connection.response.Response;
import com.appdirect.connection.response.ResponseEntityFactory;
import com.appdirect.model.entity.UserEntity;
import com.appdirect.user.exception.UserException;
import com.appdirect.user.service.AssignUser;
import com.appdirect.user.service.UnassignUser;
import com.google.common.base.Strings;

@RestController
@RequestMapping("/users")
public class UserController {
   
   private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
   private static final String EVENT_URL_IS_NULL = "Event url is null";
   
   private final AssignUser assignUser;
   private final UnassignUser unassignUser;

   @Autowired
   public UserController(AssignUser assignUser, UnassignUser unassignUser) {
      this.assignUser = assignUser;
      this.unassignUser = unassignUser;
   }

   @RequestMapping(path = "/assign", method = {RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
   public ResponseEntity<String> assignUserNotGet(@RequestParam("eventUrl") final String eventUrl, final Model model) {
      return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
   }
   
   @RequestMapping(path = "/assign", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<Response> assignUser(@RequestParam("eventUrl") final String eventUrl, final Model model) {
      
      if(Strings.isNullOrEmpty(eventUrl)) {
         return ResponseEntityFactory.buildResponseEntityFromResponse(false, null, ErrorCodes.FORBIDDEN, EVENT_URL_IS_NULL);
      }

      UserEntity user = null;
      try {
         user = assignUser.assignUser(eventUrl);
      } catch(UserException e) {
         LOGGER.error("Error during user assignment", e);
         return ResponseEntityFactory.buildResponseEntityFromResponse(false, null, e.getErrorCode(), "Error during user assignment");
      }
      return ResponseEntityFactory.buildResponseEntityFromResponse(true, user.getId(), null, "User assigned successfully");
   }
   
   @RequestMapping(path = "/unassign", method = {RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
   public ResponseEntity<String> unassignUserGet(@RequestParam("eventUrl") final String eventUrl, final Model model) {
      return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
   }
   
   @RequestMapping(path = "/unassign", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<Response> unassignUser(@RequestParam("eventUrl") final String eventUrl, final Model model) {
      
      if(Strings.isNullOrEmpty(eventUrl)) {
         return ResponseEntityFactory.buildResponseEntityFromResponse(false, null, ErrorCodes.FORBIDDEN, EVENT_URL_IS_NULL);
      }
      
      UserEntity user = null;
      try {
         user = unassignUser.unassignUser(eventUrl);
      } catch(UserException e) {
         LOGGER.error("Error during user unassignment", e);
         return ResponseEntityFactory.buildResponseEntityFromResponse(false, null, e.getErrorCode(), "Error during user unassignment");
      }
      return ResponseEntityFactory.buildResponseEntityFromResponse(true, user.getId(), null, "User unassigned successfully");
   }
}
