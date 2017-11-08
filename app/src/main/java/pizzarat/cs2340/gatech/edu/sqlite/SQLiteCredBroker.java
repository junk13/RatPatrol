package pizzarat.cs2340.gatech.edu.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import pizzarat.cs2340.gatech.edu.exception.DuplicateUserDbException;
import pizzarat.cs2340.gatech.edu.structure.CredentialStructure;


/**
 * @author Evie Brown
 *         A custom broker in SQL to work with our credential database.
 */
public class SQLiteCredBroker extends AppCompatActivity {

    /**
     * Writes the specific CredentialStructure into the SQL database
     *
     * @param username the username of credential
     * @param password the password of the credential
     * @param isAdmin  whether or not the credential has admin privileges
     * @param context  the current date
     * @throws DuplicateUserDbException when trying to save another user with
     * the same username
     */
    public void writeToCredDb(String username, String password,
                              boolean isAdmin, Context context)
            throws DuplicateUserDbException {
        final CredentialDb cred = new CredentialDb(context);
        // Throw DuplicateUserDbException if username is already used
        if (containsDuplicateUser(username, context))
            throw new DuplicateUserDbException();
        // Gets the report repository in write mode
        SQLiteDatabase db = cred.getWritableDatabase();
        ContentValues values = new ContentValues();
        // In email column, place email
        values.put(CredentialDb.getCredEmailCol(), username);
        // In password column, place password
        values.put(CredentialDb.getCredHashCol(), password);
        // In permissions column, set user type
        String perm = isAdmin ? "admin" : "user";
        values.put(CredentialDb.getPermCol(), perm);

        // Insert the new row, returning the primary key value of the new row
        long value = db.insert(CredentialDb.getTableName(), null, values);
        db.close();
        return value;
    }

    /**
     * Getter for SQLite cursor
     *
     * @param c context of the app
     * @return cursor for which to read database info from
     */
    private Cursor getCursor(Context c) {
        CredentialDb cred = new CredentialDb(c);
        SQLiteDatabase sr = cred.getReadableDatabase();

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                CredentialDb.getID() + " DESC";

        return sr.query(
                CredentialDb.getTableName(),        // The table to query
                null,                       // The columns to return
                null,                       // The columns for the WHERE clause
                null,                    // The values for the WHERE clause
                null,                       // don't group the rows
                null,                        // don't filter by row groups
                sortOrder                          // The sort order
        );

    }

    /**
     * Determines if the specified user in the database.
     *
     * @param user : String for username
     * @param pass : String for password
     *      @param c : the phone data
     * @return true if user and pass are in database in same ID
     */
    public boolean credMatch(String user, String pass, Context c) {
        CredentialStructure cs = fetchCredentialStructureByUser(user, c);
        if (cs == null) {
            return false;

        }
        return cs.getPass().equals(pass);
    }

// --Commented out by Inspection START (11/6/2017 1:49 AM):
//    /**
//     * Determines if the user has admin privileges
//     *
//     * @param userStr username to check
//     * @param c app context
//     * @return true if user is admin, else false
//     */
//    public boolean isUserAdmin(String userStr, Context c) {
//        return fetchCredentialStructureByUser(userStr, c).getAdmin();
//    }
// --Commented out by Inspection STOP (11/6/2017 1:49 AM)

    /**
     * Returns a list of all the users in the database
     *
     * @param cursor the specified sql cursor
     * @return a list of all the credentials in the database
     */
    private ArrayList<CredentialStructure> credArrayList(Cursor cursor) {
        // ArrayList to return
        ArrayList<CredentialStructure> aList = new ArrayList<>();
        cursor.moveToPosition(-1);
        // Cycle through cursor and add columns to ArrayList
        while (cursor.moveToNext()) {
            boolean b = cursor.getString(3).equals("admin");
            aList.add(new CredentialStructure(
                    cursor.getString(0),    //id
                    cursor.getString(1),    //Username
                    cursor.getString(2),    //Password
                    b                                  //isAdmin
            ));
        }
        return aList;
    }

    //return database in string
    public String getDbContent(Context c) throws  Exception {
//        List<String> itemIds = new ArrayList<String>();
//        Cursor cursor = getCursor(c);
//        while(cursor.moveToNext()) {
//            //long itemId = cursor.getLong(
//            //        cursor.getColumnIndexOrThrow(CredentialDb.getID()));
//            String str = cursor.getString(0);
//            itemIds.add(str);
//        }
//        cursor.moveToPosition(-1);
//        while(cursor.moveToNext()) {
//            //long itemId = cursor.getLong(
//            //        cursor.getColumnIndexOrThrow(CredentialDb.getID()));
//            String str = cursor.getString(1);
//            itemIds.add(str);
//        }
//        cursor.moveToPosition(-1);
//        while(cursor.moveToNext()) {
//            //long itemId = cursor.getLong(
//            //        cursor.getColumnIndexOrThrow(CredentialDb.getID()));
//            String str = cursor.getString(2);
//            itemIds.add(str);
//        }
//        cursor.close();
//        return itemIds.toString();
        return "This method has been removed for security purposes.";
    }

    /**
     * Returns the information about specified user
     *
     * @param userStr : the username to look for
     * @return CredentialStructure containing a matching username
     */
    private CredentialStructure fetchCredentialStructureByUser(String userStr,
                                                               Context c) {
        ArrayList<CredentialStructure> aList = credArrayList(getCursor(c));
        for (int i = 0; i < aList.size(); i++) {
            System.out.println(aList.get(i));
            if (aList.get(i).getUser().equals(userStr)) {
                return aList.get(i);
            }

        }
        return null;
    }

    /**
     * Determines if another user in the database contains the same user name
     *
     * @param str : String to look for in the Cred database
     * @return true if duplicate user found, else false
     */
    private boolean containsDuplicateUser(String str, Context c) {
        return fetchCredentialStructureByUser(str, c) != null;
    }
}
