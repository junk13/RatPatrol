package pizzarat.cs2340.gatech.edu.structure;

import java.util.ArrayList;

/**
 * Created by Luka on 10/9/2017.
 */

/**
 *      This method holds local custom reports.
 *      This prevents persistence, which we do not want for now.
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

/**
 *      PLEASE READ ME
 *      ---------------
 *
 *      Added globalBroker to handle dataBase.
 *      I assume here that the report is not persistant between app
 *      iterations. If not, changes need to be made.
 *
 *      To be added:
 *      populate table.
 *      add elements.
 *      remove elements.
 *      clear elements.
 *
 * */