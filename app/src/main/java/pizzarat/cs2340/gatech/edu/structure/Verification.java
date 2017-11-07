package pizzarat.cs2340.gatech.edu.structure;

/**
 * @author Luka Derado
 *
 *         A utility class created to verify dates of rat reports to be
 *         efficiently parsed through in filters.
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
     * SQL stores dates in the format: YYYY/MM/DD,
     * So, we need to verify it slightly differently.
     *
     * @param str the user's date
     * @return true if the date is valid.
     */
    public static boolean isValidSQLDate(String str) {
        String form = "([1-2][0-9][0-9][0-9][/](([0][0-9])|([1][0-2]))[/](([0-2][0-9])|([3][0-1])))";
        if (str.matches(form)) {
            String strMonth = str.substring(5, 7);
            //31 days   1,3,5,7,8,10,12
            if (strMonth.equals("01") ||
                    strMonth.equals("03") ||
                    strMonth.equals("05") ||
                    strMonth.equals("07") ||
                    strMonth.equals("08") ||
                    strMonth.equals("10") ||
                    strMonth.equals("12")) {
                return str.matches(form);
            }
            //30 days   4,6,9,11
            else if (strMonth.equals("04") ||
                    strMonth.equals("06") ||
                    strMonth.equals("09") ||
                    strMonth.equals("11")) {
                form = "([1-2][0-9][0-9][0-9][/](([0][0-9])|([1][0-2]))[/](([0-2][0-9])|([3][0])))";
                return str.matches(form);
            }
            //29 days   2
            else {
                form = "([1-2][0-9][0-9][0-9][/](([0][0-9])|([1][0-2]))[/]([0-2][0-9]))";
                return str.matches(form);
            }

        } else {
            return false;
        }
    }

    /**
     * Determines if the the user's specified zipcode is legitimate
     *
     * @param zip the user's zipcode
     * @return true if the zipcode is of valid length
     */
    public static boolean isValidZip(String zip) {
        return zip.length() == 5;
    }

    /**
     * Determines if the user's specified type is legitimate using 24 hour
     * formatted time.
     *
     * @param str the user's specified time
     * @return true if the time is valid
     */
    public static boolean isValidTime(String str) {
        String form = "((([0-1][0-9])|([2][0-4]))[:][0-5][0-9])";
        return str.matches(form);
    }

    /**
     * Generic validation method for all other String based fields.
     *
     * @param str the user's specified input
     * @return true if not empty
     */
    public static boolean isValidGeneric(String str) {
        return !str.equals("");
    }
}
