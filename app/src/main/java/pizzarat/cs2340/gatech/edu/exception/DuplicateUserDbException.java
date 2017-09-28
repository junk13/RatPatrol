package pizzarat.cs2340.gatech.edu.exception;


/**
 * Created by evieb on 9/28/2017.
 */

public class DuplicateUserDbException extends Exception {

        public DuplicateUserDbException(){
            super("Duplicate user in Database found");
        }

}
