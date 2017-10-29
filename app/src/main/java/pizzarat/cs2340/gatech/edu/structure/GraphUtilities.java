package pizzarat.cs2340.gatech.edu.structure;

import java.util.List;

/**
 * Created by Luka on 10/29/2017.
 */

public class GraphUtilities {
    public int[] OrganizeByMonth(List<ReportStructure> reports){
        int[] months = new int[12];
        for (ReportStructure report: reports) {
            String date = report.getDate();
            months[Integer.parseInt(date.substring(0,2))]++;
        }
        return months;
    }
}
