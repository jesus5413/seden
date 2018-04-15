package com.group.seden.model;

/**
 * @author  Isaac Buitrago
 *
 * A UserSession stores global information about the user and the system
 * settings that need to be accessed through out the application.
 */
public class UserSession {

    private String userEmail;   // email of the authenticated user

    private String username;    // username of the authenticated user

    private String uniqueId;    // key of the User's node in the Database

    private static UserSession instance;   // reference to the session

    /**
     * Constructor should only be called once
     */
    private UserSession(){}

    /**
     *
     * @return reference to the UserSession
     */
    public static UserSession getInstance()
    {
        if(instance == null)
        {
            instance = new UserSession();
        }

        return(instance);
    }

    /**
     *
     * Getters and Setters for global information
     */
    public String getUserEmail()
    {
        return userEmail;
    }

    public void setUserEmail(String userEmail)
    {
        this.userEmail = userEmail;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getUniqueId()
    {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId)
    {
        this.uniqueId = uniqueId;
    }
}
