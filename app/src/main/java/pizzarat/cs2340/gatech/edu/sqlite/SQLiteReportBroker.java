package pizzarat.cs2340.gatech.edu.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import pizzarat.cs2340.gatech.edu.exception.DuplicateReportDbException;
import pizzarat.cs2340.gatech.edu.structure.ReportStructure;


/**
 * Created by evieb on 10/4/2017.
 */

public class SQLiteReportBroker extends AppCompatActivity { //TODO: duplicate exception logging
    public long writeToReportDb(ReportStructure rReport, Context context) throws DuplicateReportDbException {
        final CredentialDb ratDb = new CredentialDb(context);
        //throw DuplicateReportDbException if report already exists
//        if (containsDuplicateReport(username, context))
//            throw new DuplicateReportDbException();
        // Gets the data repository in write mode
        SQLiteDatabase writableDb = ratDb.getWritableDatabase();
        ContentValues values = new ContentValues();
        //in X column, set X
        values.put(RatSightingDb.getReportTableKeyCol(), rReport.getKey());
        values.put(RatSightingDb.getReportTableLocationCol(), rReport.getLocation());
        values.put(RatSightingDb.getReportTableDateCol(), rReport.getDate());
        values.put(RatSightingDb.getReportTableTimeCol(), rReport.getTime());
        values.put(RatSightingDb.getReportTableAddressCol(), rReport.getAddress());
        values.put(RatSightingDb.getReportTableZipcodeCol(), rReport.getZipCode());
        values.put(RatSightingDb.getReportTableCityCol(), rReport.getCity());
        values.put(RatSightingDb.getReportTableBoroughCol(), rReport.getBorough());

        // Insert the new row, returning the primary key value of the new row
        return writableDb.insert(RatSightingDb.getTableName(), null, values);
    }
    /**
     * getter for SQLite cursor Report database
     * @return cursor for which to read database info from
     */
    private Cursor getCursor(Context c) {
        RatSightingDb rDb = new RatSightingDb(c);
        SQLiteDatabase readableDb = rDb.getReadableDatabase();


        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                CredentialDb.getCredHashCol() + " DESC";

        return readableDb.query(
                CredentialDb.getTableName(),            // The table to query
                null,                                   // The columns to return
                null,                                   // The columns for the WHERE clause
                null,                                   // The values for the WHERE clause
                null,                                   // don't group the rows
                null,                                   // don't filter by row groups
                sortOrder                               // The sort order
        );

    }

    //returns arraylist of all rat reports
    private ArrayList<ReportStructure> reportArrayList(Cursor cursor) {
        //ArrayList to return
        ArrayList<ReportStructure> aList = new ArrayList<>();
        cursor.moveToPosition(-1);
        while(cursor.moveToNext()) {
            boolean b = cursor.getString(3).equals("admin"); //TODO: .equals?
            aList.add(new ReportStructure(
                    cursor.getInt(0),       //key
                    cursor.getString(1),    //location
                    cursor.getString(2),    //date
                    cursor.getString(3),       //time
                    cursor.getString(4),    //address
                    cursor.getString(5),       //zipcode
                    cursor.getString(6),    //city
                    cursor.getString(7)     //borough

            ));
        }
        return aList;
    }
    
    public ArrayList<ReportStructure> getListOfReports(Context c) {
        return reportArrayList(getCursor(c));
    }

}
