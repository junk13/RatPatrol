package pizzarat.cs2340.gatech.edu.structure;

/**
 * @author Luka Derado
 *
 *         Utility class to hold the current report for use in the
 *         DetailedReportActivity and an array of all the reports in the database.
 */
public class StaticHolder {
    public static ReportStructure report;
    // --Commented out by Inspection (11/6/2017 1:51 AM):public static ArrayList<ReportStructure> userReports = new ArrayList<>();

    //stores the date range set by the filter. used in map for filtering markers.
    public static DateRangeStruct dateRange;

// --Commented out by Inspection START (11/6/2017 1:49 AM):
//    public static void add(ReportStructure report) {
//        userReports.add(report);
//    }
// --Commented out by Inspection STOP (11/6/2017 1:49 AM)

}
