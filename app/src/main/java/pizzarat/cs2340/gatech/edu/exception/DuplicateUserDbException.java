package pizzarat.cs2340.gatech.edu.exception;


/**
 * @author Evie Brown
 */

public class DuplicateUserDbException extends Exception {
    /**
     * Creates a DuplicateUserDBException with the stock error message
     */
    public DuplicateUserDbException() {
        super("Duplicate user in Database found");
    }

}
