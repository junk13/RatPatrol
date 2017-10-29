package pizzarat.cs2340.gatech.edu.structure;

import android.content.Context;
import android.util.Log;

import java.util.List;

import pizzarat.cs2340.gatech.edu.ratpatrol.ArchiveActivity;
import pizzarat.cs2340.gatech.edu.sqlite.SQLiteCredBroker;
import pizzarat.cs2340.gatech.edu.sqlite.SQLiteReportBroker;

/**
 * Created by Luka on 10/29/2017.
 */

public class GraphUtilities {
    /**
     *      Organizes a List of Report Structures by their date entries
     *      for each report.
     *
     * @param reports   All the reports one wants to compile into a int[]
     * @return          An array of size 12, where each value represents
     *                  the number of reports within the repective month.
     */
    static public int[] organizeByMonth(List<ReportStructure> reports){
        int[] months = new int[12];
        for (ReportStructure report: reports) {
            String date = report.getDate();
            if (Verification.isValidSQLDate(date)) { //date is yyyy/MM/dd from SQL
                months[Integer.parseInt(date.substring(5, 7)) - 1]++;
            } else {
                Log.e("GRAPH","RatReport "+report.getKey()+ " has invalid date: " + date);
            }
        }
        return months;
    }

    static public int[] organizeByYear(Context c, List<ReportStructure> reports){
        SQLiteReportBroker reportBroker = new SQLiteReportBroker();
        //int[] extremeDates = reportBroker.findExtremeDates(c); //{earliestYear, latestYear}
        //int yearSpan = extremeDates[1] - extremeDates[0];
        int yearSpan = 8;
        int[] years = new int[yearSpan];
        for (ReportStructure report: reports) {
            String date = report.getDate();
            if (Verification.isValidSQLDate(date)) { //date is yyyy/MM/dd from SQL
                years[Integer.parseInt(date.substring(0, 4)) - 2010]++;
            } else {
                Log.e("GRAPH","RatReport "+report.getKey()+ " has invalid date: " + date);
            }
        }
        return years;
    }

    static public int[] organizeByDay(List<ReportStructure> reports){
        int[] months = new int[31];
        for (ReportStructure report: reports) {
            String date = report.getDate();
            if (Verification.isValidSQLDate(date)) { //date is yyyy/MM/dd from SQL
                months[Integer.parseInt(date.substring(8, 10)) - 1]++;
            } else {
                Log.e("GRAPH","RatReport "+report.getKey()+ " has invalid date: " + date);
            }
        }
        return months;
    }

}
