package Database;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

/**
 * Class to store data in the database
 * @JesusNieto
 */
public class Database {

    private static DatabaseReference mDatabase;
    //private static DatabaseReference userChild;
    public static String sysAdmin;

    /**
     * Stores user info when they create an account. They will have a unique ID which stores in the database as well.
     * @param email
     * @param password
     * @param userName
     * example: database/users/user
     */
    public static void storeUserInDBChild(String email, String password, String userName){
        mDatabase = FirebaseDatabase.getInstance().getReference(); // this points to the main database
        HashMap<String, String> childInfo = new HashMap<String, String>();  // creating hashmap to store data to commit to the users node
        childInfo.put("UserName", userName);
        childInfo.put("Email", email);
        childInfo.put("Password", password);
        String tempuID = mDatabase.push().getKey();
        childInfo.put("UniqueID", tempuID);
        mDatabase.child("users").child(tempuID).setValue(childInfo); // this points to the database, then to the users node and then stores a new node in the users nodes with a unique ID.

    }




}
