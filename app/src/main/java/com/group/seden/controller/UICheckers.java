package com.group.seden.controller;

import com.group.seden.model.UserSession;

public class UICheckers {

    public static boolean createButtonChecker(UserSession user){
        if(user.getUserName() == "sysAdmin"){
            return true;
        }

        return false;

    }

}
