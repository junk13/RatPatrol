package pizzarat.cs2340.gatech.edu.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import pizzarat.cs2340.gatech.edu.exception.DuplicateUserDbException;


/**
 * @author Evie Brown.
 */

public class SQLiteCredBroker extends AppCompatActivity {
    //takes in credentials from Db TODO: duplicate exception logging
    public long writeToCredDb(String username, String password, boolean isAdmin, Context context) throws DuplicateUserDbException {
        final CredentialDb cred = new CredentialDb(context);
        //throw DuplicateUserDbException if username is already used
        if (containsDuplicateUser(username, context))
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
    public Cursor getCursor(Context c) {
        CredentialDb cred = new CredentialDb(c);
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
    public boolean credMatch(String user, String pass, Context c) {
        CredentialStructure cs = fetchCredentialStructureByUser(user, c);
        if (cs == null) {
            System.out.println("444444444444");
            return false;

        }
        System.out.println(cs.getPass());
        System.out.println(pass);
        return cs.getPass().equals(pass);
    }

    /**
     * @param userStr username to check
     * @param c app context
     * @return true if user is admin, else false
     */
    public boolean isUserAdmin(String userStr, Context c) {
        return fetchCredentialStructureByUser(userStr, c).getAdmin();
    }

    //returns map of users(key) and credentials(value)
    private ArrayList<CredentialStructure> credArrayList(Cursor cursor) {
        //ArrayList to return
        ArrayList<CredentialStructure> aList = new ArrayList<CredentialStructure>();
        cursor.moveToPosition(-1);
        //cycle through cursor and add columns to ArrayList
        while(cursor.moveToNext()) {
            boolean b = cursor.getString(3).equals("admin"); //TODO: .equals?
            aList.add(new CredentialStructure(
                    cursor.getString(0),    //id
                    cursor.getString(1),    //Username
                    cursor.getString(2),    //Password
                    b                       //isAdmin
                    ));
        }
        return aList;
    }
    //return database in string
    public String getDbContent(Context c) throws  Exception {
        List<String> itemIds = new ArrayList<String>();
        Cursor cursor = getCursor(c);
        while(cursor.moveToNext()) {
            //long itemId = cursor.getLong(
            //        cursor.getColumnIndexOrThrow(CredentialDb.getID()));
            String str = cursor.getString(0);
            itemIds.add(str);
        }
        cursor.moveToPosition(-1);
        while(cursor.moveToNext()) {
            //long itemId = cursor.getLong(
            //        cursor.getColumnIndexOrThrow(CredentialDb.getID()));
            String str = cursor.getString(1);
            itemIds.add(str);
        }
        cursor.moveToPosition(-1);
        while(cursor.moveToNext()) {
            //long itemId = cursor.getLong(
            //        cursor.getColumnIndexOrThrow(CredentialDb.getID()));
            String str = cursor.getString(2);
            itemIds.add(str);
        }
        cursor.close();
        return itemIds.toString();

    }

    /**
     *  @return CredentialStructure containing a matching username
     *  @param userStr : the username to look for
     */

    private CredentialStructure fetchCredentialStructureByUser(String userStr, Context c) {
        ArrayList<CredentialStructure> aList = credArrayList(getCursor(c));
        for (int i = 0; i < aList.size(); i++) {
            System.out.println(aList.get(i));
            if (aList.get(i).getUser().equals(userStr)){
                System.out.println("66666666666666666");
                return aList.get(i);
            }

        }
        return null;
    }

    /**
     * @param str : String to look for in the Cred database
     * @return true if duplicate user found, else false
     */
    private boolean containsDuplicateUser(String str, Context c) {
        return fetchCredentialStructureByUser(str, c) != null;
    }

}
