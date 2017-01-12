package com.appdirect.user.service;

import com.appdirect.model.entity.UserEntity;

@FunctionalInterface
public interface UnassignUser {
   public static final String ACTION = "UNASSIGN";
   
   /**
    * 
    * @param eventUrl
    * @return
    */
   UserEntity unassignUser(String eventUrl);
}
