package pizzarat.cs2340.gatech.edu.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;

import pizzarat.cs2340.gatech.edu.Model.RatSightingReport;
import pizzarat.cs2340.gatech.edu.exception.DuplicateReportDbException;


/**
 * Created by evieb on 10/4/2017.
 */

public class SQLiteReportBroker extends AppCompatActivity { //TODO: duplicate exception logging
    public long writeToDb(RatSightingReport rReport, Context context) throws DuplicateReportDbException {
        final CredentialDb ratDb = new CredentialDb(context);
        //throw DuplicateReportDbException if report already exists
//        if (containsDuplicateReport(username, context))
//            throw new DuplicateReportDbException();
        // Gets the data repository in write mode
        SQLiteDatabase writeableDb = ratDb.getWritableDatabase();
        ContentValues values = new ContentValues();
        //in email column, place email

        // Insert the new row, returning the primary key value of the new row
        return writeableDb.insert(CredentialDb.getTableName(), null, values);
    }
}
