package pizzarat.cs2340.gatech.edu.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pizzarat.cs2340.gatech.edu.structure.ReportStructure;
import pizzarat.cs2340.gatech.edu.structure.StaticHolder;


/**
 * @author Evie Brown
 * A custom broker in SQL to work with our rat sighting reports in our database.
 */
public class SQLiteReportBroker extends AppCompatActivity {
    final int CSV_SIZE = 12219;
    /**
     * Writes rat reports to the database
     * @param rReport report to be saved
     * @param context context from which function is called
     * @return primary key value of new row
     */
    public void writeToReportDb(ReportStructure rReport, Context context) {
        RatSightingDb ratDb = new RatSightingDb(context);

        // Gets the data repository in write mode
        SQLiteDatabase writableDb = ratDb.getWritableDatabase();
        ContentValues values = new ContentValues();


        //in X column, set X
        values.put(RatSightingDb.getReportTableKeyCol(), rReport.getKey());
        values.put(RatSightingDb.getReportTableLocationCol(), rReport.getBuildingType());
        values.put(RatSightingDb.getReportTableDateCol(), rReport.getDate());
        values.put(RatSightingDb.getReportTableTimeCol(), rReport.getTime());
        values.put(RatSightingDb.getReportTableAddressCol(), rReport.getAddress());
        values.put(RatSightingDb.getReportTableZipcodeCol(), rReport.getZipCode());
        values.put(RatSightingDb.getReportTableCityCol(), rReport.getCity());
        values.put(RatSightingDb.getReportTableBoroughCol(), rReport.getBorough());
        values.put(RatSightingDb.getReportTableLatitudeCol(), rReport.getLatitude());
        values.put(RatSightingDb.getReportTableLongitudeCol(), rReport.getLongitude());

        // Insert the new row, returning the primary key value of the new row
        writableDb.insert(RatSightingDb.getTableName(), null, values);

        ratDb.close();

    }
    /**
     * getter for SQLite cursor Report database
     * @param c context from which the function is called
     * @return cursor for which to read database info from
     */
    private Cursor getCursor(Context c) {
        RatSightingDb rDb = new RatSightingDb(c);
        SQLiteDatabase readableDb = rDb.getReadableDatabase();


        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                RatSightingDb.getReportTableKeyCol() + " DESC";

        return readableDb.query(
                RatSightingDb.getTableName(),            // The table to query
                null,                                   // The columns to return
                null,                                   // The columns for the WHERE clause
                null,                                   // The values for the WHERE clause
                null,                                   // don't group the rows
                null,                                   // don't filter by row groups
                sortOrder                               // The sort order
        );

    }


    /**
     * getter for SQLite cursor Report database
     * @param formattedBeforeDate from date constraint
     * @param formattedAfterDate to date constraint
     * @param c context from which the function is called
     * @return cursor for which to read database info from
     */
    private Cursor getDateConstrainedCursor(String formattedBeforeDate, String formattedAfterDate, Context c) {
        RatSightingDb rDb = new RatSightingDb(c);
        SQLiteDatabase readableDb = rDb.getReadableDatabase();


        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                RatSightingDb.getReportTableKeyCol() + " DESC";

        return readableDb.query(
                RatSightingDb.getTableName(),            // The table to query
                null,                                   // The columns to return
                "date BETWEEN ? AND ?",                                   // The columns for the WHERE clause
                new String[] {formattedBeforeDate, formattedAfterDate},                                   // The values for the WHERE clause
                null,                                   // don't group the rows
                null,                                   // don't filter by row groups
                sortOrder                               // The sort order
        );

    }


    /**
     * getter for SQLite substring search cursor Report database
     * @param keystring substring to search for
     * @param c context from which the function is called
     * @return cursor for which to read database info from
     */
    private Cursor getSubstringReportsCursor(String keystring, Context c) {
        RatSightingDb rDb = new RatSightingDb(c);
        SQLiteDatabase readableDb = rDb.getReadableDatabase();


        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                RatSightingDb.getReportTableKeyCol() + " DESC";

        return readableDb.query(
                RatSightingDb.getTableName(),            // The table to query
                null,                                   // The columns to return
                RatSightingDb.getReportTableAddressCol() + " LIKE '%" + keystring + "%'",                                   // The columns for the WHERE clause
                null,                                   // The values for the WHERE clause
                null,                                   // don't group the rows
                null,                                   // don't filter by row groups
                sortOrder                               // The sort order
        );

    }

    /**
     * Gets rat reports created between dates
     * @param context context from which function is called
     * @return rat reports that fit the constraints
     */
    public ArrayList<ReportStructure> getDateConstrainedReports(Context context) {
        if (StaticHolder.dateRange == null)
        {
            return reportArrayList(context);
        }
        String from = StaticHolder.dateRange.getFrom();
        String to = StaticHolder.dateRange.getTo();
        if (from == null || from.isEmpty())
        {
            from = "2014/00/00";
        }
        if (to == null || to.isEmpty())
        {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date();
            to = dateFormat.format(date);
        }

        Cursor cursor = getDateConstrainedCursor(from, to, context);
        //ArrayList to return
        ArrayList<ReportStructure> aList = new ArrayList<>();
        populateList(cursor, aList);
        return aList;
    }

    /**
     * Gets rat reports that contain a substring
     * @param keystring desired substring
     * @param context context from which function is called
     * @return rat reports that fit the constraints
     */
    public ArrayList<ReportStructure> getReportsWithSubstring(String keystring, Context context) {

        Cursor cursor = getSubstringReportsCursor(keystring, context);
        //ArrayList to return
        ArrayList<ReportStructure> aList = new ArrayList<>();
        populateList(cursor, aList);
        return aList;
    }


    /**
     * Gets all rat reports
     * @param context context from which function is called
     * @return all rat reports
     */
    public ArrayList<ReportStructure> reportArrayList(Context context) {
        Cursor cursor = getCursor(context);
        //ArrayList to return
        ArrayList<ReportStructure> aList = new ArrayList<>();
        populateList(cursor, aList);
        return aList;
    }


// --Commented out by Inspection START (11/6/2017 1:49 AM):
//    /**
//     * Gets stringified database contents
//     * @param c context from which function is called
//     * @return string of database contents
//     */
//    public String getDbContent(Context c) throws  Exception {
//        List<String> itemIds = new ArrayList<String>();
//        Cursor cursor = getCursor(c);
//        while(cursor.moveToNext()) {
//            String str = cursor.getString(0);
//            itemIds.add(str);
//        }
//
//        cursor.close();
//        return itemIds.toString();
//
//    }
// --Commented out by Inspection STOP (11/6/2017 1:49 AM)

// --Commented out by Inspection START (11/6/2017 1:49 AM):
//    /**
//     * Checks if database is empty
//     * @param c context from which function is called
//     * @return true if empty
//     */
//    public boolean isEmpty(Context c) {
//        RatSightingDb rDb = new RatSightingDb(c);
//        SQLiteDatabase readableDb = rDb.getReadableDatabase();
//        String count = "SELECT count(*) FROM " + RatSightingDb.getTableName();
//        Cursor mcursor = readableDb.rawQuery(count, null);
//        mcursor.moveToFirst();
//        int icount = mcursor.getInt(0);
//        return !(icount > 0);
//    }
// --Commented out by Inspection STOP (11/6/2017 1:49 AM)

    /**
     * Finds the highest unique key for a rat sighting report. Used to generate
     * the unique key when the user decides to create a new report
     *
     * @param c the specified context
     * @return the current maximum key throughout all rat sighting reports
     */
    public int getMaxKey(Context c) {
        RatSightingDb rDb = new RatSightingDb(c);
        SQLiteDatabase readableDb = rDb.getReadableDatabase();
        String query = "SELECT MAX(" + RatSightingDb.getReportTableKeyCol() + ") FROM " + RatSightingDb.getTableName();
        Cursor mcursor = readableDb.rawQuery(query, null);
        mcursor.moveToFirst();
        return mcursor.getInt(0);
    }

    /**
     * Checks if CSV data completely imported
     * @param c context from which function is called
     * @return true if data imported
     */
    public boolean isPopulated(Context c) {
        RatSightingDb rDb = new RatSightingDb(c);
        SQLiteDatabase readableDb = rDb.getReadableDatabase();
        String count = "SELECT count(*) FROM " + RatSightingDb.getTableName();
        Cursor mcursor = readableDb.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        return (icount >= CSV_SIZE);
    }

    /**
     * Populates the specified list with rat sighting reports with data from
     * the cursor.
     *
     * @param cursor the cursor to grab the data from
     * @param list   the list to be populated
     */
    private void populateList(Cursor cursor, List<ReportStructure> list) {
        cursor.moveToPosition(-1);
        while (cursor.moveToNext()) {
            list.add(new ReportStructure(
                    cursor.getInt(0) + "",     // key
                    cursor.getString(1),            // building type
                    cursor.getString(2),            // date
                    cursor.getString(3),            // time
                    cursor.getString(4),            // address
                    cursor.getString(5),            // zipcode
                    cursor.getString(6),            // city
                    cursor.getString(7),            // borough
                    cursor.getString(8),            // latitude
                    cursor.getString(9)             // longitude

            ));
        }
        cursor.close();
        Log.d("hidden", list.toString());
        Log.d("hidden", "aList.size() = " + list.size());
    }


// --Commented out by Inspection START (11/6/2017 1:49 AM):
//    /**
//     * Finds the max and min dates
//     *
//     * @param c the specified context
//     * @return the max and min dates
//     */
//    public int[] findExtremeDates(Context c) {
//        RatSightingDb rDb = new RatSightingDb(c);
//        SQLiteDatabase readableDb = rDb.getReadableDatabase();
//        String query = "SELECT MAX(" + RatSightingDb.getReportTableDateCol() + "), MIN(" + RatSightingDb.getReportTableDateCol() + ") FROM " + RatSightingDb.getTableName();
//        Cursor mcursor = readableDb.rawQuery(query, null);
//        mcursor.moveToFirst();
//        int maxDate = mcursor.getInt(0);
//        int minDate = mcursor.getInt(1);
//        Log.d("hidden",""+minDate + " | " + maxDate);
//        Log.d("hidden","meow");
//        return new int[] {minDate, maxDate};
//    }
// --Commented out by Inspection STOP (11/6/2017 1:49 AM)
}
