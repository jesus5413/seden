package com.group.seden.model;

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

    /**
     * Constructor should only be called once
     */
    public UserSession(){

    }

    /**
     *
     * @return reference to the UserSession
     */
//    public static UserSession getInstance()
//    {
//        if(instance == null)
//        {
//            instance = new UserSession();
//        }
//
//        return(instance);
//    }

    /**
     *
     * Getters and Setters for global information
     */
    public String getEmail()
    {
        return Email;
    }

    public void setEmail(String userEmail)
    {
        this.Email = userEmail;
    }

    public String getUserName()
    {
        return UserName;
    }

    public void setUserName(String username)
    {
        this.UserName = username;
    }

    public String getUniqueID()
    {
        return UniqueID;
    }

    public void setUniqueID(String uniqueId)
    {
        this.UniqueID = uniqueId;
    }

    public String getPassword() { return Password; }

    public void setPassword(String password) { this.Password = password; }
}
