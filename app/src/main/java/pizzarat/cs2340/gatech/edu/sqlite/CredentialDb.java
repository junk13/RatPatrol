package pizzarat.cs2340.gatech.edu.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Evie Brown
 */

public class CredentialDb extends SQLiteOpenHelper {

    static final int DATABASE_VERSION = 1;
    //name of database file
    static final String DATABASE_NAME = "localCred.db";

    public CredentialDb(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CredentialDbContract.SQL_CREATE_ENTRIES);
    }

    //clear and regenerate the database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(CredentialDbContract.SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
