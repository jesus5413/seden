package com.group.seden.Database;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.group.seden.model.UserSession;

/**
 * Manages the registration token for the client app instance
 * @author Isaac Buitrago
 */
public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {

    private static String token;    // registration token for the user

    /**
     * Used to get the updated token and store it in the
     * Users node on the database
     */
    @Override
    public void onTokenRefresh()
    {
        //Store InstanceID token and update it in the database
        token = FirebaseInstanceId.getInstance().getToken();
        Database.updateUserTokenInDB(token);
    }

    public static String getToken()
    {
        if(token == null)
        {
            token = FirebaseInstanceId.getInstance().getToken();
        }
        return(token);
    }



}
