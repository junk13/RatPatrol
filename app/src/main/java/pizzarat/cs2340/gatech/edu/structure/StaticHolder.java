package pizzarat.cs2340.gatech.edu.structure;

import java.util.ArrayList;

/**
 * Created by Luka on 10/9/2017.
 */

/**
 * Utility class to hold the current report for use in the
 * DetailedReportActivity and an array of all the reports in the database.
 */
public class StaticHolder {
    public static ReportStructure report;
    public static ArrayList<ReportStructure> userReports = new ArrayList<>();

    //stores the date range set by the filter. used in map for filtering markers.
    public static DateRangeStruct dateRange;

    public static void add(ReportStructure report) {
        userReports.add(report);
    }

}
