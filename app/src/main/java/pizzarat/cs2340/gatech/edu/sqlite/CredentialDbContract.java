package pizzarat.cs2340.gatech.edu.sqlite;

import android.provider.BaseColumns;

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
        static final String TABLE_NAME = "Credentials";
        static final String CREDENTIAL_TABLE_EMAIL = "title";
        static final String CREDENTIAL_TABLE_HASH = "pass";
        static final String CREDENTIAL_TABLE_PERM = "permission";
    }
    //helpful php
    static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ContractEntry.TABLE_NAME + " (" +
                    ContractEntry._ID + " INTEGER PRIMARY KEY," +
                    ContractEntry.CREDENTIAL_TABLE_EMAIL + " TEXT," +
                    ContractEntry.CREDENTIAL_TABLE_HASH + " TEXT," +
                    ContractEntry.CREDENTIAL_TABLE_PERM + " TEXT)";

    static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ContractEntry.TABLE_NAME;

}