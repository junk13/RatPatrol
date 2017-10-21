package pizzarat.cs2340.gatech.edu.ratpatrol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import pizzarat.cs2340.gatech.edu.sqlite.SQLiteReportBroker;
import pizzarat.cs2340.gatech.edu.structure.ReportStructure;
import pizzarat.cs2340.gatech.edu.structure.StaticHolder;

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
    private TextView latitude;
    private TextView longitude;
    private SQLiteReportBroker reportBroker = new SQLiteReportBroker();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_rat_report_view);

        Intent data = this.getIntent();

        // Initializing widgets
        key = (TextView) findViewById(R.id.keyTextView);
        date = (TextView) findViewById(R.id.dateTextView);
        time = (TextView) findViewById(R.id.timeTextView);
        address = (TextView) findViewById(R.id.addressTextView);
        city = (TextView) findViewById(R.id.cityTextView);
        zipcode = (TextView) findViewById(R.id.zipcodeTextView);
        borough = (TextView) findViewById(R.id.boroughTextView);
        location = (TextView) findViewById(R.id.locationTextView);
        latitude = (TextView) findViewById(R.id.latitudeTextView);
        longitude = (TextView) findViewById(R.id.longitudeTextView);

        ReportStructure report = StaticHolder.data;
        key.setText("Key: " + reportBroker.getMaxKey(getBaseContext()));
        date.setText("Date: " + report.getDate());
        time.setText("Time: " + report.getTime());
        address.setText(""+ report.getAddress());
        city.setText("City: "+ report.getCity());
        zipcode.setText("Zip: "+report.getZipCode());
        borough.setText(""+report.getBorough());
        location.setText(""+report.getLocation());
        // latitude.setText("Latitude: " + report.getLatitude());
        // longitude.setText("Longitude: " + report.getLongitude());
    }
}
