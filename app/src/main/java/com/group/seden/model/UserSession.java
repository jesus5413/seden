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

    private String Password;    // username of the authenticated user

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
     *
     * Getters and Setters for global information
     */
    public String getEmail()
    {
        Email = prefs.getString("Email", null);

        return  (Email);
    }

    public void setEmail(String userEmail)
    {
        prefs.edit().putString("Email", userEmail).commit();

        this.Email = userEmail;
    }

    public String getUserName()
    {
        UserName = prefs.getString("Email", null);

        return(UserName);
    }

    public void setUserName(String username)
    {
        prefs.edit().putString("Username", username).commit();

        this.UserName = username;
    }

    public String getUniqueID()
    {
        UniqueID = prefs.getString("Email", null);

        return (UniqueID);
    }

    public void setUniqueID(String uniqueId)
    {
        prefs.edit().putString("UniqueID", uniqueId).commit();

        this.UniqueID = uniqueId;
    }

}