package pizzarat.cs2340.gatech.edu.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import pizzarat.cs2340.gatech.edu.exception.DuplicateUserDbException;


/**
 * @author Evie Brown.
 */

public class SQLiteBroker extends AppCompatActivity {
    //Initialize database
    private final CredentialDb cred = new CredentialDb(this.getApplicationContext());

    //takes in credentials from Db TODO: duplicate exception logging
    public long writeToDb(String username, String password, boolean isAdmin) throws DuplicateUserDbException {
        //throw DuplicateUserDbException if username is already used
        if (containsDuplicateUser(username))
            throw new DuplicateUserDbException();
        // Gets the data repository in write mode
        SQLiteDatabase db = cred.getWritableDatabase();
        ContentValues values = new ContentValues();
        //in email column, place email
        values.put(CredentialDb.getCredEmailCol(), username);
        //in password column, place password TODO: make password messageDigest hashed SHA256
        values.put(CredentialDb.getCredHashCol(), password);
        //in permissions column, set user type
        String perm = isAdmin ? "admin" : "user";
        values.put(CredentialDb.getPermCol(), perm);

        // Insert the new row, returning the primary key value of the new row
        return db.insert(CredentialDb.getTableName(), null, values);
    }

    /**
     * getter for SQLite cursor
     * @return cursor for which to read database info from
     */
    public Cursor getCursor() {
        SQLiteDatabase sr = cred.getReadableDatabase();


        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                CredentialDb.getCredHashCol() + " DESC";

        return sr.query(
                CredentialDb.getTableName(),            // The table to query
                null,                                   // The columns to return
                null,                                   // The columns for the WHERE clause
                null,                                   // The values for the WHERE clause
                null,                                   // don't group the rows
                null,                                   // don't filter by row groups
                sortOrder                               // The sort order
        );

    }


    /**
     * @param user : String for username
     * @param pass : String for password
     * @return true if user and pass are in database in same ID
     */
    public boolean credMatch(String user, String pass) {
        return true;
    }

    //returns map of users(key) and credentials(value)
    private ArrayList<CredentialStructure> credArrayList(Cursor cursor) {
        //ArrayList to return
        ArrayList aList = new ArrayList<CredentialStructure>();
        cursor.moveToPosition(-1);
        //cycle through cursor and add columns to ArrayList
        while(cursor.moveToNext()) {
            boolean b = cursor.getString(3) == "admin";
            aList.add(new CredentialStructure(
                    cursor.getString(0),    //id
                    cursor.getString(1),    //Username
                    cursor.getString(2),    //Password
                    b                       //isAdmin
                    ));
        }
        return aList;
    }


    /**
     * @param str : String to look for in the Cred database
     * @return true if duplicate user found, else false
     */
    private boolean containsDuplicateUser(String str) {
        ArrayList<CredentialStructure> aList = credArrayList(getCursor());
        for (int i = 0; i < aList.size(); i++)
            if (aList.get(i).sameUser(new CredentialStructure(str)))
                return true;
        return false;
    }

}
