package pizzarat.cs2340.gatech.edu.exception;


/**
 * @Author Evie Brown
 */

public class DuplicateUserDbException extends Exception {

        public DuplicateUserDbException(){
            super("Duplicate user in Database found");
        }

}
