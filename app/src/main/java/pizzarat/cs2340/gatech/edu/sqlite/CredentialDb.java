package pizzarat.cs2340.gatech.edu.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Evie Brown.
 */

class CredentialDb extends SQLiteOpenHelper {

    static final int DATABASE_VERSION = 1;
    //name of database file
    static final String DATABASE_NAME = "Cred.db";

    CredentialDb(Context context) {
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
    static String getID() {
        return CredentialDbContract.ContractEntry._ID;
    }
    static void purgeCredDb(SQLiteDatabase db) {
        db.execSQL(CredentialDbContract.SQL_DELETE_ENTRIES);
    }
}