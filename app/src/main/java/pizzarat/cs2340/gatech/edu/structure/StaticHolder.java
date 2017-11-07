package pizzarat.cs2340.gatech.edu.structure;

/**
 * @author Luka Derado
 *
 *         Utility class to hold the current report for use in the
 *         DetailedReportActivity and an array of all the reports in the
 *         database.
 */
public class StaticHolder {
    public static ReportStructure report;

    //stores the date range set by the filter. used in map for filtering
    // markers.
    public static DateRangeStruct dateRange;
}
