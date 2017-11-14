package pizzarat.cs2340.gatech.edu.ratpatrol;

import org.junit.*;

import pizzarat.cs2340.gatech.edu.structure.Verification;

import static org.junit.Assert.*;
/**
 * Created by Luka on 11/13/2017.
 */

public class IsValidSQLDateTest {

    private String test;
    public static final int TIMEOUT = 300;

    @Test(timeout = TIMEOUT)
    public void testEmpty(){
        test = "";
        assertEquals(Verification.isValidSQLDate((test)),false);
    }

    @Test(timeout = TIMEOUT)
    public void testBadFormatYearLast(){
        test = "09/21/2016";
        assertEquals(Verification.isValidSQLDate((test)),false);
    }

    @Test(timeout = TIMEOUT)
    public void testBadFormatDayMonthSwap(){
        test = "2016/21/09";
        assertEquals(Verification.isValidSQLDate((test)),false);
    }

    @Test(timeout = TIMEOUT)
    public void testBadFormatBadYearLow(){
        test = "0000/09/21";
        assertEquals(Verification.isValidSQLDate((test)),false);
    }

    @Test(timeout = TIMEOUT)
    public void testBadFormatBadYearHigh(){
        test = "9999/09/21";
        assertEquals(Verification.isValidSQLDate((test)),false);
    }

    @Test(timeout = TIMEOUT)
    public void testBadFormatBadYearLetters(){
        test = "2WHY/09/21";
        assertEquals(Verification.isValidSQLDate((test)),false);
    }

    @Test(timeout = TIMEOUT)
    public void testBadFormatBadYearShort(){
        test = "20/09/21";
        assertEquals(Verification.isValidSQLDate((test)),false);
    }

    @Test(timeout = TIMEOUT)
    public void testBadFormatBadYearLong(){
        test = "0100110101100101011011010110010101110011/09/21";
        assertEquals(Verification.isValidSQLDate((test)),false);
    }

    @Test(timeout = TIMEOUT)
    public void testBadFormatBigDay(){
        test = "2016/09/99";
        assertEquals(Verification.isValidSQLDate((test)),false);
    }

    @Test(timeout = TIMEOUT)
    public void testBadFormatZeroDay(){
        test = "2016/09/00";
        assertEquals(Verification.isValidSQLDate((test)),false);
    }

    @Test(timeout = TIMEOUT)
    public void testBadFormatLongDay(){
        test = "2016/09/103";
        assertEquals(Verification.isValidSQLDate((test)),false);
    }

    @Test(timeout = TIMEOUT)
    public void testBadFormatShortDay(){
        test = "2016/09/2";
        assertEquals(Verification.isValidSQLDate((test)),false);
    }

    @Test(timeout = TIMEOUT)
    public void testBadFormatBigMonth(){
        test = "2016/99/21";
        assertEquals(Verification.isValidSQLDate((test)),false);
    }

    @Test(timeout = TIMEOUT)
    public void testBadFormatSmallMonth(){
        test = "2016/2/21";
        assertEquals(Verification.isValidSQLDate((test)),false);
    }

    @Test(timeout = TIMEOUT)
    public void testBadFormatZeroMonth(){
        test = "2016/00/21";
        assertEquals(Verification.isValidSQLDate((test)),false);
    }

    @Test(timeout = TIMEOUT)
    public void testBadFormatLongMonth(){
        test = "2016/21000/21";
        assertEquals(Verification.isValidSQLDate((test)),false);
    }

    @Test(timeout = TIMEOUT)
    public void testBadFormatShortMonth(){
        test = "2016/2/21";
        assertEquals(Verification.isValidSQLDate((test)),false);
    }

    @Test(timeout = TIMEOUT)
    public void testGoodFormat(){
        test = "2016/09/21";
        assertEquals(Verification.isValidSQLDate((test)),true);
    }

    @Test(timeout = TIMEOUT)
    public void testGoodFormat2(){
        test = "2001/09/11";
        assertEquals(Verification.isValidSQLDate((test)),true);
    }

    @Test(timeout = TIMEOUT)
    public void testOldieButGoodie(){
        test = "1997/12/24";
        assertEquals(Verification.isValidSQLDate((test)),true);
    }
}
