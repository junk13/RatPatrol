package pizzarat.cs2340.gatech.edu.structure;

import pizzarat.cs2340.gatech.edu.sqlite.SQLiteReportBroker;

/**
 * Created by Luka on 10/9/2017.
 */

public class ReportHolder {
    public static ReportStructure data;
    public static SQLiteReportBroker globalBroker = new SQLiteReportBroker();
}

//TODO public void populate(){}
//TODO public void add(){}
//TODO public void remove(){}
//TODO public void clear(){}
/**
 *      PLEASE READ ME
 *      ---------------
 *
 *      Added globalBroker to handle dataBase.
 *      I assume here that the data is not persistant between app
 *      iterations. If not, changes need to be made.
 *
 *      To be added:
 *      populate table.
 *      add elements.
 *      remove elements.
 *      clear elements.
 *
 * */