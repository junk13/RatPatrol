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
 * Created by evieb on 10/4/2017.
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

        // Insert the new row, returning the primary key value of the new row
        long id = writableDb.insert(RatSightingDb.getTableName(), null, values);
        try{
//            Log.d("Cunt", ""+id);
//            writableDb.execSQL("INSERT INTO " + RatSightingDb.getTableName() +
//                    "\nVALUES (12, dsss, addd, daaf, dskaf, sjafds, fdjsa, fdsaj);");
//            Log.d("Cunt", "this is a triumph");
        } catch (Exception e) {
            Log.d("Cunt", e.getLocalizedMessage());
        }
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

    //returns arraylist of all rat reports
    public ArrayList<ReportStructure> reportArrayList(Context context) {
        Cursor cursor = getCursor(context);
        //ArrayList to return
        ArrayList<ReportStructure> aList = new ArrayList<>();
        cursor.moveToPosition(-1);
        while(cursor.moveToNext()) {
            boolean b = cursor.getString(3).equals("admin"); //TODO: .equals?
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
        String count = "SELECT count(*) FROM " + rDb.getTableName();
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

    public ArrayList<ReportStructure> getListOfReports(Context c) {
        return reportArrayList(c);
    }

    public int getMaxKey(Context c) {
        RatSightingDb rDb = new RatSightingDb(c);
        SQLiteDatabase readableDb = rDb.getReadableDatabase();
        String query = "SELECT MAX(" + RatSightingDb.getReportTableKeyCol() + ") FROM " + rDb.getTableName();
        Cursor mcursor = readableDb.rawQuery(query, null);
        mcursor.moveToFirst();
        int maxKey = mcursor.getInt(0);
        Log.d("hidden",""+maxKey);
        Log.d("hidden","meow");
        return maxKey;
    }

}
