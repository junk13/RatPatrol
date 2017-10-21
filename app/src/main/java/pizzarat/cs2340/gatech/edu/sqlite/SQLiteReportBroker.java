package pizzarat.cs2340.gatech.edu.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import pizzarat.cs2340.gatech.edu.exception.DuplicateReportDbException;
import pizzarat.cs2340.gatech.edu.structure.ReportStructure;


/**
 * Created by Evie Brown
 */

public class SQLiteReportBroker extends AppCompatActivity { //TODO: duplicate exception logging
    public long writeToReportDb(ReportStructure rReport, Context context) throws DuplicateReportDbException {
        RatSightingDb ratDb = new RatSightingDb(context);
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
        values.put(RatSightingDb.getReportTableLatitudeCol(), rReport.getLatitude());
        values.put(RatSightingDb.getReportTableLongitudeCol(), rReport.getLongitude());

        // Insert the new row, returning the primary key value of the new row
        long id = writableDb.insert(RatSightingDb.getTableName(), null, values);

        ratDb.close();

        return id;
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
     * getter for SQLite cursor Report database
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
                RatSightingDb.getReportTableLocationCol() + " LIKE '%" + keystring + "%'",                                   // The columns for the WHERE clause
                null,                                   // The values for the WHERE clause
                null,                                   // don't group the rows
                null,                                   // don't filter by row groups
                sortOrder                               // The sort order
        );

    }

    //returns arraylist of all rat reports
    public ArrayList<ReportStructure> getDateConstrainedReports(String beforeDate, String afterDate, Context context) {
        Cursor cursor = getDateConstrainedCursor(getDate(beforeDate),getDate(afterDate),context);
        //ArrayList to return
        ArrayList<ReportStructure> aList = new ArrayList<>();
        cursor.moveToPosition(-1);
        while(cursor.moveToNext()) {
            aList.add(new ReportStructure(
                    cursor.getInt(0)+"",       //key
                    cursor.getString(1),    //location
                    cursor.getString(2),    //date
                    cursor.getString(3),       //time
                    cursor.getString(4),    //address
                    cursor.getString(5),       //zipcode
                    cursor.getString(6),    //city
                    cursor.getString(7)     //borough

            ));
        }
        cursor.close();
        return aList;
    }

    //returns arraylist of all rat reports
    public ArrayList<ReportStructure> getReportsWithSubstring(String keystring, Context context) {
        Cursor cursor = getSubstringReportsCursor(keystring, context);
        //ArrayList to return
        ArrayList<ReportStructure> aList = new ArrayList<>();
        cursor.moveToPosition(-1);
        while(cursor.moveToNext()) {
            aList.add(new ReportStructure(
                    cursor.getInt(0)+"",       //key
                    cursor.getString(1),    //location
                    cursor.getString(2),    //date
                    cursor.getString(3),       //time
                    cursor.getString(4),    //address
                    cursor.getString(5),       //zipcode
                    cursor.getString(6),    //city
                    cursor.getString(7)     //borough

            ));
        }
        cursor.close();
        Log.d("hidden",aList.toString());
        Log.d("hidden","aList.size() = " + aList.size());
        return aList;
    }

    //TODO
    //returns arraylist of all rat reports
    public ArrayList<ReportStructure> getReportsWithSubstringSpecified(String keystring, Context context) {
        String[] sl = keystring.split(":"); //{column to search, substring}
        //if sl[0] not a valid column, do location search on sl[1] (or search whole thing, get 0 results)
        //else, search in that specific column


        //TODO: combine methods
        //Cursor cursor = getSubstringReportsCursor(sl[0],sl[1], context);
        Cursor cursor = getSubstringReportsCursor(keystring, context);
        //ArrayList to return
        ArrayList<ReportStructure> aList = new ArrayList<>();
        cursor.moveToPosition(-1);
        while(cursor.moveToNext()) {
            aList.add(new ReportStructure(
                    cursor.getInt(0)+"",       //key
                    cursor.getString(1),    //location
                    cursor.getString(2),    //date
                    cursor.getString(3),       //time
                    cursor.getString(4),    //address
                    cursor.getString(5),       //zipcode
                    cursor.getString(6),    //city
                    cursor.getString(7)     //borough

            ));
        }
        cursor.close();
        Log.d("hidden",aList.toString());
        Log.d("hidden","aList.size() = " + aList.size());
        return aList;
    }

    //returns arraylist of all rat reports
    public ArrayList<ReportStructure> reportArrayList(Context context) {
        Cursor cursor = getCursor(context);
        //ArrayList to return
        ArrayList<ReportStructure> aList = new ArrayList<>();
        cursor.moveToPosition(-1);
        while(cursor.moveToNext()) {
            aList.add(new ReportStructure(
                    cursor.getInt(0)+"",       //key
                    cursor.getString(1),    //location
                    cursor.getString(2),    //date
                    cursor.getString(3),       //time
                    cursor.getString(4),    //address
                    cursor.getString(5),       //zipcode
                    cursor.getString(6),    //city
                    cursor.getString(7)     //borough

            ));
        }
        cursor.close();
        Log.d("hidden",aList.toString());
        Log.d("hidden","aList.size() = " + aList.size());
        return aList;
    }

    public String getDbContent(Context c) throws  Exception {
        List<String> itemIds = new ArrayList<String>();
        Cursor cursor = getCursor(c);
        while(cursor.moveToNext()) {
            //long itemId = cursor.getLong(
            //        cursor.getColumnIndexOrThrow(CredentialDb.getID()));
            String str = cursor.getString(0);
            itemIds.add(str);
        }

        cursor.close();
        return itemIds.toString();

    }


    public boolean isEmpty(Context c) {
        RatSightingDb rDb = new RatSightingDb(c);
        SQLiteDatabase readableDb = rDb.getReadableDatabase();
        String count = "SELECT count(*) FROM " + RatSightingDb.getTableName();
        Cursor mcursor = readableDb.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        return !(icount > 0);
    }

    public ArrayList<ReportStructure> dummy(){
        ArrayList<ReportStructure> dummies = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            ReportStructure next =(new ReportStructure(
                    ""+i,       //key
                    "Place"+i,    //location
                    "Today",    //date
                    "0",       //time
                    "Here",    //address
                    "00000",       //zipcode
                    "England",    //city
                    "Mahatten"));   //borough)
            dummies.add(next);
        }
        return dummies;
    }

    public int getMaxKey(Context c) {
        RatSightingDb rDb = new RatSightingDb(c);
        SQLiteDatabase readableDb = rDb.getReadableDatabase();
        String query = "SELECT MAX(" + RatSightingDb.getReportTableKeyCol() + ") FROM " + RatSightingDb.getTableName();
        Cursor mcursor = readableDb.rawQuery(query, null);
        mcursor.moveToFirst();
        int maxKey = mcursor.getInt(0);
        Log.d("hidden",""+maxKey);
        Log.d("hidden","meow");
        return maxKey;
    }

    public boolean isPopulated(Context c) {
        RatSightingDb rDb = new RatSightingDb(c);
        SQLiteDatabase readableDb = rDb.getReadableDatabase();
        String count = "SELECT count(*) FROM " + RatSightingDb.getTableName();
        Cursor mcursor = readableDb.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        return (icount >= 12219);
    }

    //TODO: Move to utility class
    private String getDate(String s) {
        String[] date = s.split("/");
        return date[2] + "/" + date[1] + "/" + date[0];
    }

    /**
     * Populates the specified list with rat sighting reports with data from
     * the cursor
     *
     * @param cursor the cursor to grab the data from
     * @param list   the list to be populated
     */
    private void populateList(Cursor cursor, List<ReportStructure> list) {
        cursor.moveToPosition(-1);
        while (cursor.moveToNext()) {
            list.add(new ReportStructure(
                    cursor.getInt(0) + "",    //key
                    cursor.getString(1),    //location
                    cursor.getString(2),    //date
                    cursor.getString(3),    //time
                    cursor.getString(4),    //address
                    cursor.getString(5),    //zipcode
                    cursor.getString(6),    //city
                    cursor.getString(7)     //borough

            ));
        }
        cursor.close();
        Log.d("hidden", list.toString());
        Log.d("hidden", "aList.size() = " + list.size());
    }

}
