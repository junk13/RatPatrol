package pizzarat.cs2340.gatech.edu.ratpatrol;

import org.junit.Test;

import pizzarat.cs2340.gatech.edu.structure.Verification;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Harrison Banh
 *         Tests to ensure correct validation of data in the Verification class
 */
public class VerificationTest {
    public static final int TIMEOUT = 200;

    @Test(timeout = TIMEOUT)
    public void testIsValidTimeWithInvalidHours() {
        String failTime = "24:00";
        assertFalse(Verification.isValidTime(failTime));

        String impossibleTime = "25:00";
        assertFalse(Verification.isValidTime(impossibleTime));

        String ridiculousTime = "99:00";
        assertFalse(Verification.isValidTime(ridiculousTime));
    }

    @Test(timeout = TIMEOUT)
    public void testIsValidTimeWithTimesMissingTheColon() {
        String invalidTime = "0000";
        assertFalse(Verification.isValidTime(invalidTime));
    }

    @Test(timeout = TIMEOUT)
    public void testIsValidTimeWithAMAndPM() {
        String amTime = "01:00 am";
        String pmTime = "02:00 pm";

        assertFalse(Verification.isValidTime(amTime));
        assertFalse(Verification.isValidTime(pmTime));
    }

    @Test(timeout = TIMEOUT)
    public void testIsValidTimeWithTimesWithoutFourDigits() {
        String missingHour = "1:00";
        assertFalse(Verification.isValidTime(missingHour));

        String missingMinute = "00:1";
        assertFalse(Verification.isValidTime(missingMinute));
    }

    @Test(timeout = TIMEOUT)
    public void testIsValidTimeWithInvalidMinutes() {
        String badMinutes = "01:61";
        assertFalse(Verification.isValidTime(badMinutes));

        String badMinutes2 = "01:99";
        assertFalse(Verification.isValidTime(badMinutes2));
    }

    @Test(timeout = TIMEOUT)
    public void testIsValidTimeWithCorrectTimes() {
        String midnight = "00:00";
        assertTrue(Verification.isValidTime(midnight));

        String latestPossibleTime = "23:59";
        assertTrue(Verification.isValidTime(latestPossibleTime));

        String noon = "12:00";
        assertTrue(Verification.isValidTime(noon));

        String time = "11:01";
        assertTrue(Verification.isValidTime(time));
    }


}