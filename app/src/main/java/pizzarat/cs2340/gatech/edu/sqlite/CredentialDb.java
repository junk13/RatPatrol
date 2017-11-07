package pizzarat.cs2340.gatech.edu.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Evie Brown.
 *
 * Represents the SQLiteDatabase of all our users.
 */
class CredentialDb extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    //name of database file
    private static final String DATABASE_NAME = "Cred.db";

    CredentialDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    static String getCredEmailCol() {
        return CredentialDbContract.ContractEntry.CREDENTIAL_TABLE_EMAIL;
    }

    static String getCredHashCol() {
        return CredentialDbContract.ContractEntry.CREDENTIAL_TABLE_HASH;
    }

    static String getPermCol() {
        return CredentialDbContract.ContractEntry.CREDENTIAL_TABLE_PERM;
    }

    static String getTableName() {
        return CredentialDbContract.ContractEntry.TABLE_NAME;
    }

// --Commented out by Inspection START (11/6/2017 1:49 AM):
//    static String getID() {
//        return CredentialDbContract.ContractEntry._ID;
//    }
// --Commented out by Inspection STOP (11/6/2017 1:49 AM)

// --Commented out by Inspection START (11/6/2017 1:49 AM):
//    static void purgeCredDb(SQLiteDatabase db) {
//        db.execSQL(CredentialDbContract.SQL_DELETE_ENTRIES);
//    }
// --Commented out by Inspection STOP (11/6/2017 1:49 AM)

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