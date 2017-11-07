package pizzarat.cs2340.gatech.edu.structure;

import android.util.Log;

import java.util.List;

/**
 * @Auhtor Luka Derado
 */

public class GraphUtilities {
    /**
     *      Organizes a List of Report Structures by their date entries
     *      for each report.
     *
     * @param reports   All the reports one wants to compile into a int[]
     * @return          An array of size 12, where each value represents
     *                  the number of reports within the respective month.
     */
    public static int[] organizeByMonth(List<ReportStructure> reports) {
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

// --Commented out by Inspection START (11/6/2017 1:49 AM):
//    /**
//     *      Organizes a list of Rat Reports values by Years
//     * @param c         Context
//     * @param reports   Reports to organize
//     * @return          an array of reports per year.
//     */
//    public static int[] organizeByYear(Context c, List<ReportStructure> reports) {
//        SQLiteReportBroker reportBroker = new SQLiteReportBroker();
//        //int[] extremeDates = reportBroker.findExtremeDates(c); //{earliestYear, latestYear}
//        //int yearSpan = extremeDates[1] - extremeDates[0];
//        int startYear = 2010;
//        DateFormat dateFormat = new SimpleDateFormat("yyyy");
//        int endYear = Integer.parseInt((dateFormat.format(new Date())));
//        Log.d("hidden","startYear = " + startYear + " | endYear = " + endYear);
//        if (StaticHolder.dateRange != null)
//        {
//            String startDate = StaticHolder.dateRange.getFrom();
//            String endDate = StaticHolder.dateRange.getTo();
//            startYear = Integer.parseInt(startDate.substring(0, 4));
//            endYear = Integer.parseInt(endDate.substring(0, 4));
//        }
//        int yearSpan = endYear - startYear + 1;
//        int[] years = new int[yearSpan];
//        for (ReportStructure report: reports) {
//            String date = report.getDate();
//            if (Verification.isValidSQLDate(date)) { //date is yyyy/MM/dd from SQL
//                years[Integer.parseInt(date.substring(0, 4)) - startYear]++;
//            } else {
//                Log.e("GRAPH","RatReport "+report.getKey()+ " has invalid date: " + date);
//            }
//        }
//        return years;
//    }
// --Commented out by Inspection STOP (11/6/2017 1:49 AM)

// --Commented out by Inspection START (11/6/2017 1:49 AM):
//    /**
//     *      Organize Reports by Days within a month.
//     *      Month must be given so that proper array length
//     *      is returned.
//     *
//     * @param reports   Reports to organize.
//     * @param month     Month to organize within.
//     * @return          array of rat reports per day.
//     */
//    public static int[] organizeByDay(List<ReportStructure> reports, int month) {
//        String form;
//        int[] days;
//        //31 days   1,3,5,7,8,10,12
//        if (month == 1 ||
//                month == 3 ||
//                month == 5 ||
//                month == 7 ||
//                month == 8 ||
//                month == 10 ||
//                month == 12) {
//            days = new int[31];
//        }
//        //30 days   4,6,9,11
//        else if (month == 4 ||
//                month == 6 ||
//                month == 9 ||
//                month == 11) {
//            form = "([1-2][0-9][0-9][0-9][/](([0][0-9])|([1][0-2]))[/](([0-2][0-9])|([3][0])))";
//            days = new int[30];
//        }
//        //29 days   2
//        else {
//            days = new int[29];
//        }
//
//        for (ReportStructure report : reports) {
//            String date = report.getDate();
//            if (Verification.isValidSQLDate(date)) { //date is yyyy/MM/dd from SQL
//                days[Integer.parseInt(date.substring(8, 10)) - 1]++;
//            } else {
//                Log.e("GRAPH", "RatReport " + report.getKey() + " has invalid date: " + date);
//            }
//        }
//        return days;
//    }
// --Commented out by Inspection STOP (11/6/2017 1:49 AM)

}
