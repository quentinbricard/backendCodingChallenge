package com.appdirect.user.service;

import com.appdirect.model.entity.UserEntity;

public interface AssignUser {
   public static final String ACTION = "ASSIGN";
   
   /**
    * 
    * @param eventUrl
    * @return
    */
   UserEntity assignUser(String eventUrl);
}
