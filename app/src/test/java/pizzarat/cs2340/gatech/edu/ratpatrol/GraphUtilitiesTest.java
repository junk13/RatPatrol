package pizzarat.cs2340.gatech.edu.ratpatrol;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

import pizzarat.cs2340.gatech.edu.structure.GraphUtilities;

/**
 * Created by Andrew Young on 11/13/17.
 */


public class GraphUtilitiesTest {
    GraphUtilities gu;

    @Before
    public void setUp() throws Exception {
        gu = new GraphUtilities();
    }

    /**
     * Test method for {@link pizzarat.cs2340.gatech.edu.structure.GraphUtilities#getTime(String)}.
     */
    @Test
    public void testGetTime() {
        Assert.assertEquals("Wrong time conversion", "13:00", gu.getTime("12/25/2017 1:00 PM"));
        Assert.assertEquals("Wrong time conversion", "7:00", gu.getTime("10/31/2011 7:00 AM"));
        Assert.assertEquals("Wrong time conversion", "0:00", gu.getTime("01/01/2000 12:00 AM"));
        Assert.assertEquals("Wrong time conversion", "23:59", gu.getTime("11/11/1111 11:59 PM"));
    }
}