package pizzarat.cs2340.gatech.edu.sqlite;


/**
 * @author Evie Brown.
 */

final class RatSightingDbContract {


    //helpful php
    static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ContractEntry.TABLE_NAME + " (" +
                    ContractEntry.REPORT_TABLE_KEY + " INTEGER PRIMARY KEY," +
                    ContractEntry.REPORT_TABLE_BUILDING + " TEXT," +
                    ContractEntry.REPORT_TABLE_DATE + " TEXT," +
                    ContractEntry.REPORT_TABLE_TIME + " TEXT," +
                    ContractEntry.REPORT_TABLE_ADDRESS + " TEXT," +
                    ContractEntry.REPORT_TABLE_ZIPCODE + " TEXT," +
                    ContractEntry.REPORT_TABLE_CITY + " TEXT," +
                    ContractEntry.REPORT_TABLE_BOROUGH + " TEXT," +
                    ContractEntry.REPORT_TABLE_LAT + " TEXT," +
                    ContractEntry.REPORT_TABLE_LON + " TEXT)";
    static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ContractEntry.TABLE_NAME;

    private RatSightingDbContract() {
    }

    /**
     * package-private {@link ContractEntry} defines Strings for use in
     * Credential management implement android.provider.BaseColumns for
     * _id compatibility (i.e. cursor)
     */
    static class ContractEntry {
        //fields for database info
        static final String TABLE_NAME = "Reports";
        static final String REPORT_TABLE_KEY = "key";
        static final String REPORT_TABLE_BUILDING = "building";
        static final String REPORT_TABLE_DATE = "date";
        static final String REPORT_TABLE_TIME = "time";
        static final String REPORT_TABLE_ADDRESS = "address";
        static final String REPORT_TABLE_ZIPCODE = "zipcode";
        static final String REPORT_TABLE_CITY = "city";
        static final String REPORT_TABLE_BOROUGH = "borough";
        static final String REPORT_TABLE_LAT = "latitude";
        static final String REPORT_TABLE_LON = "longitude";


    }

}

