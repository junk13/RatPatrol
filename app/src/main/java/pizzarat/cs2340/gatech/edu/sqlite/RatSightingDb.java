package pizzarat.cs2340.gatech.edu.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Evie Brown.
 */

class RatSightingDb extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    //name of database file
    private static final String DATABASE_NAME = "rat.db";

    RatSightingDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    static String getTableName() {
        return RatSightingDbContract.ContractEntry.TABLE_NAME;
    }

    static String getReportTableKeyCol() {
        return RatSightingDbContract.ContractEntry.REPORT_TABLE_KEY;
    }

    static String getReportTableLocationCol() {
        return RatSightingDbContract.ContractEntry.REPORT_TABLE_LOCATION;
    }

    static String getReportTableDateCol() {
        return RatSightingDbContract.ContractEntry.REPORT_TABLE_DATE;
    }

    static String getReportTableTimeCol() {
        return RatSightingDbContract.ContractEntry.REPORT_TABLE_TIME;
    }

    static String getReportTableAddressCol() {
        return RatSightingDbContract.ContractEntry.REPORT_TABLE_ADDRESS;
    }

    static String getReportTableZipcodeCol() {
        return RatSightingDbContract.ContractEntry.REPORT_TABLE_ZIPCODE;
    }

    static String getReportTableCityCol() {
        return RatSightingDbContract.ContractEntry.REPORT_TABLE_CITY;
    }

    static String getReportTableBoroughCol() {
        return RatSightingDbContract.ContractEntry.REPORT_TABLE_BOROUGH;
    }

    static String getReportTableLatitudeCol() {
        return RatSightingDbContract.ContractEntry.REPORT_TABLE_LAT;
    }

    static String getReportTableLongitudeCol() {
        return RatSightingDbContract.ContractEntry.REPORT_TABLE_LON;
    }

// --Commented out by Inspection START (11/6/2017 1:49 AM):
//    static void purgeCredDb(SQLiteDatabase db) {
//        db.execSQL(RatSightingDbContract.SQL_DELETE_ENTRIES);
//    }
// --Commented out by Inspection STOP (11/6/2017 1:49 AM)

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


}