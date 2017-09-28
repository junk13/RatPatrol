package pizzarat.cs2340.gatech.edu.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;

import java.util.Map;

import pizzarat.cs2340.gatech.edu.exception.DuplicateUserDbException;

import static java.security.spec.MGF1ParameterSpec.SHA1;

/**
 * Created by evieb on 9/28/2017.
 */

public class SQLiteBroker extends AppCompatActivity {
    //Initialize database
    private final CredentialDb cred = new CredentialDb(this.getApplicationContext());

    //takes in credentials from Db TODO: duplicate exception logging
    public long writeToCreDb(String username, String password) throws DuplicateUserDbException {
        //TODO: make credential checker method to watch for duplicates
        //checkDuplicateUser(username);

        // Gets the data repository in write mode
        SQLiteDatabase db = cred.getWritableDatabase();
        ContentValues values = new ContentValues();
        //in email column, place email
        values.put(CredentialDb.getCredEmailCol(), username);
        //in password column, place password TODO: make password messageDigest hashed SHA256
        values.put(CredentialDb.getCredHashCol(), password);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(CredentialDb.getTableName(), null, values);
        return newRowId;
    }

    /**
     *
     * @param user : String for username
     * @param pass : String for password
     * @return true if user and pass are in database in same ID
     */
    public boolean credMatch(String user, String pass) {

    }

    //returns map of users(key) and credentials(value)
    private Map<String, String> credMap(Cursor cursor) {

    }



    /**
     *
     * @param str : String to look for in the Cred database
     * @return true if duplicate user found, else false
     */
    private boolean checkDuplicateUser(String str) {
        //TODO: finish checkDuplicateUser
        return false;
    }


}
