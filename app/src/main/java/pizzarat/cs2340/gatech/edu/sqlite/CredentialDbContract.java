package pizzarat.cs2340.gatech.edu.sqlite;

import android.provider.BaseColumns;
import android.view.ViewDebug;

/**
 * Created by Evie Brown
 */

final class CredentialDbContract {
    //avoid accidental instantiation
    private CredentialDbContract() {
    }

    /**
     * package-private {@link ContractEntry} defines Strings for use in Credential management
     * implement android.provider.BaseColumns for _id compatibility (i.e. cursor)
     */
    static class ContractEntry implements BaseColumns {
        //fields for database info
        static final String TABLE_NAME = "table1";
        static final String COLUMN_NAME_TITLE = "title";
        static final String COLUMN_NAME_SUBTITLE = "subtitle";
    }
    //helpful php
    static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ContractEntry.TABLE_NAME + " (" +
                    ContractEntry._ID + " INTEGER PRIMARY KEY," +
                    ContractEntry.COLUMN_NAME_TITLE + " TEXT," +
                    ContractEntry.COLUMN_NAME_SUBTITLE + " TEXT)";

    static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ContractEntry.TABLE_NAME;

}
