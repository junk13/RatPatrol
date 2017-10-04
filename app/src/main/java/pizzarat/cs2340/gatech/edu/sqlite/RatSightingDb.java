package pizzarat.cs2340.gatech.edu.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Evie Brown.
 */

class RatSightingDb extends SQLiteOpenHelper {

    static final int DATABASE_VERSION = 1;
    //name of database file
    static final String DATABASE_NAME = "RatReport.db";

    RatSightingDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(RatSightingDbContract.SQL_CREATE_ENTRIES);
    }

    //clear and regenerate the database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(RatSightingDbContract.SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    static void purgeCredDb(SQLiteDatabase db) {
        db.execSQL(RatSightingDbContract.SQL_DELETE_ENTRIES);
    }
}