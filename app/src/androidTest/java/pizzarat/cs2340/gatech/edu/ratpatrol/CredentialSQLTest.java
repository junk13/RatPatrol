package pizzarat.cs2340.gatech.edu.ratpatrol;

import android.support.test.runner.AndroidJUnit4;
import android.support.v7.app.AppCompatActivity;

import org.junit.Test;
import org.junit.runner.RunWith;

import pizzarat.cs2340.gatech.edu.sqlite.SQLiteCredBroker;

import static org.junit.Assert.assertEquals;

/**
 * Created by Evie Brown
 */
@RunWith(AndroidJUnit4.class)
public class CredentialSQLTest extends AppCompatActivity {
    private SQLiteCredBroker broker = new SQLiteCredBroker();

    @Test
    public void CredentialStorageTest() throws Exception {
        broker.writeToCredDb("user@test.test", "pass", true, getApplicationContext());
        assertEquals(true, broker.credMatch("user@test.test", "pass", getApplicationContext()));
    }

}
