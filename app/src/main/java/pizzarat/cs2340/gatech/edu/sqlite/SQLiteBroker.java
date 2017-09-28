package pizzarat.cs2340.gatech.edu.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;

import static java.security.spec.MGF1ParameterSpec.SHA1;

/**
 * Created by evieb on 9/28/2017.
 */

public class SQLiteBroker extends AppCompatActivity {
    //Initialize database
    private final CredentialDb creDb = new CredentialDb(this.getApplicationContext());
    //takes in credentials from Db TODO: duplicate exception logging
    public long writeToCreDb(String username, String password) {
        //TODO: make credential checker method to watch for duplicates
        //checkDuplicate(username);

        // Gets the data repository in write mode
        SQLiteDatabase db = creDb.getWritableDatabase();
        ContentValues values = new ContentValues();
        //in email column, place email
        values.put(CredentialDb.getCredEmailCol(), username);
        //in password column, place password TODO: make password messageDigest hashed SHA256
        values.put(CredentialDb.getCredHashCol(), password);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(CredentialDb.getTableName(), null, values);
        return newRowId;
    }


}
