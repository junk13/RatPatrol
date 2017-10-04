package pizzarat.cs2340.gatech.edu.ratpatrol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * @author Harrison Banh
 * Represents the detailed view of one of the archived rat reports in New York
 * City.
 */
public class DetailedRatReportViewActivity extends AppCompatActivity {
    private TextView key;
    private TextView date;
    private TextView time;
    private TextView address;
    private TextView city;
    private TextView zipcode;
    private TextView borough;
    private TextView location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_rat_report_view);

        // Initializing widgets
        key = (TextView) findViewById(R.id.keyTextView);
        date = (TextView) findViewById(R.id.dateTextView);
        time = (TextView) findViewById(R.id.timeTextView);
        address = (TextView) findViewById(R.id.addressTextView);
        city = (TextView) findViewById(R.id.cityTextView);
        zipcode = (TextView) findViewById(R.id.zipcodeTextView);
        borough = (TextView) findViewById(R.id.boroughTextView);
        location = (TextView) findViewById(R.id.locationTextView);

        // TODO: Set the text of the widgets to the selected Rat Report in NY Archive
    }
}
