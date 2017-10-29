package pizzarat.cs2340.gatech.edu.structure;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Luka on 10/24/2017.
 */

public class Verification {

    /**
     * Determines if the user's specified date is legitimate using the
     * following format mm/dd/year
     *
     * @param str the user's date
     * @return true if the date is valid
     */
//    public static boolean isValidDate(String str) {
//        try {
//            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
//            df.setLenient(false);
//            df.parse(str);
//            return true;
//        } catch (ParseException e) {
//            return false;
//        }
//    }
    public static boolean isValidDate(String str) {
        String form = "((([0][0-9])|([1][0-2]))[/](([0-2][0-9])|([3][0-1]))[/][1-2][0-9][0-9][0-9])";
        return str.matches(form);
    }

    /**
     * Determines if the the user's specified zipcode is legitimate
     *
     * @param zip the user's zipcode
     * @return true if the zipcode is of valid length
     */
    public static boolean isValidZip(String zip){
        return zip.length() == 5;
    }

    /**
     * Determines if the user's specified type is legitimate using 24 hour
     * formatted time.
     * @param str the user's specified time
     * @return true if the time is valid
     */
    public static boolean isValidTime(String str){
        String form = "((([0-1][0-9])|([2][0-4]))[:][0-5][0-9])";
        return str.matches(form);
    }

    /**
     * Generic validation method for all other String based fields.
     * @param str the user's specified input
     * @return true if not empty
     */
    public static boolean isValidGeneric(String str){
        return !str.equals("");
    }
}
