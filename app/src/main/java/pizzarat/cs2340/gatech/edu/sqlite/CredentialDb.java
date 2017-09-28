package pizzarat.cs2340.gatech.edu.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Evie Brown
 */

public class CredentialDb extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    //name of database file
    public static final String DATABASE_NAME = "localCred.db";

    public CredentialDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
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
    public static String getCredEmailCol() {
        return CredentialDbContract.ContractEntry.CREDENTIAL_TABLE_EMAIL;
    }
    public static String getCredHashCol() {
        return CredentialDbContract.ContractEntry.CREDENTIAL_TABLE_HASH;
    }
    public static String getTableName() {
        return CredentialDbContract.ContractEntry.TABLE_NAME;
    }
    public static String getID() {
        return CredentialDbContract.ContractEntry._ID;
    }
    public static void purgeCredDb(SQLiteDatabase db) {
        db.execSQL(CredentialDbContract.SQL_DELETE_ENTRIES);
    }
}