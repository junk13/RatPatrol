package pizzarat.cs2340.gatech.edu.ratpatrol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Represents the screen of to create a rat sighting report.
 */
public class UserRatReportsActivity extends AppCompatActivity {
    private TextView key;
    private TextView date;
    private TextView time;
    private TextView address;
    private TextView city;
    private TextView zipcode;
    private TextView location;
    private Spinner borough;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_rat_reports);

        key = (TextView) findViewById(R.id.createKeyView);
        date = (TextView) findViewById(R.id.createDateView);
        time = (TextView) findViewById(R.id.createTimeView);
        address = (TextView) findViewById(R.id.createAddressView);
        city = (TextView) findViewById(R.id.createCityView);
        zipcode = (TextView) findViewById(R.id.createZipcodeView);
        location = (TextView) findViewById(R.id.createLocationTextView);
        // TODO populate spinner with proper borough strings
        borough = (Spinner) findViewById(R.id.createBoroughSpinner);
    }

    // TODO grab data, create report, save to DB
}
