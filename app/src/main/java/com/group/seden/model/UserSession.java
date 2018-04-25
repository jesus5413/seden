package com.group.seden.model;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * @author  Isaac Buitrago
 *
 * A UserSession stores global information about the user and the system
 * settings that need to be accessed through out the application.
 */
public class UserSession {

    private String Email;   // email of the authenticated user

    private String UniqueID;    // key of the User's node in the Database

    private String UserName;

    private static UserSession instance;   // reference to the session

    private SharedPreferences prefs;        // preferences for the application

    private Context context;                // context of the UserSession

    private boolean hasContext = false;

    /**
     * Constructor should only be called once
     */
    private UserSession(){ }

    /**
     * @return reference to the UserSession
     */
    public static UserSession getInstance()
    {
        if(instance == null)
        {
            instance =  new UserSession();
        }

        return(instance);
    }

    /**
     *
     * @param context of the application of which to register
     */
    public void init(Context context)
    {
        this.context = context;

        prefs = PreferenceManager.getDefaultSharedPreferences(context);

        hasContext = true;
    }

    /**
     *
     * @return true if context passed, false otherwise
     */
    public boolean hasContext()
    {
        return (this.hasContext);
    }

    /**
     * Used to start a session for the user with the given information
     */
    public void start(String Email, String UserName, String UniqueID)
    {
        this.Email = Email;

        this.UserName = UserName;

        this.UniqueID = UniqueID;

    }

    /**
     *
     * Getters and Setters
     */


    public String getEmail()
    {
        return Email;
    }

    public void setEmail(String email)
    {
        Email = email;
    }

    public String getUniqueID()
    {
        return UniqueID;
    }

    public void setUniqueID(String uniqueID)
    {
        UniqueID = uniqueID;
    }

    public String getUserName()
    {
        return UserName;
    }

    public void setUserName(String userName)
    {
        UserName = userName;
    }

    public String toString()
    {
        return (String.format("{Email: %s, Username: %s, UniqueID: %s\n", Email, UserName, UniqueID));
    }

}