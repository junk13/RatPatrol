package pizzarat.cs2340.gatech.edu.structure;

import android.util.Log;

import java.util.List;

/**
 * @author Luka Derado
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

            //date is yyyy/MM/dd from SQL
            if (Verification.isValidSQLDate(date)) {
                months[Integer.parseInt(date.substring(5, 7)) - 1]++;
            } else {
                Log.e("GRAPH", "RatReport " + report.getKey()
                        + " has invalid date: " + date);
            }
        }
        return months;
    }

    public String getTime(String dateAndTime) {
        String[] time = dateAndTime.substring(dateAndTime.indexOf(" ") + 1)
                .split(":| ");
        if ("PM".equals(time[2])) {
            time[0] = "" + (Integer.parseInt(time[0]) + 12);
        } else if ("12".equals(time[0])) {
            time[0] = "0";
        }
        return time[0] + ":" + time[1];
    }
}
