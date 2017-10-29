package pizzarat.cs2340.gatech.edu.structure;

import java.util.List;

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
    public int[] organizeByMonth(List<ReportStructure> reports){
        int[] months = new int[12];
        for (ReportStructure report: reports) {
            String date = report.getDate();
            months[Integer.parseInt(date.substring(0,2))-1]++;
        }
        return months;
    }
}
