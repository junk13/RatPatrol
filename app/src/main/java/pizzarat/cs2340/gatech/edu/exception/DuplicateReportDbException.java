package pizzarat.cs2340.gatech.edu.exception;

/**
 * Created by evieb on 10/4/2017.
 */

public class DuplicateReportDbException extends Exception {
    public DuplicateReportDbException(){
        super("Duplicate report found in database.");
    }
}
