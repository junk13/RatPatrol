package pizzarat.cs2340.gatech.edu.ratpatrol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import pizzarat.cs2340.gatech.edu.structure.ReportStructure;
import pizzarat.cs2340.gatech.edu.structure.StaticHolder;

/**
 * @author Harrison Banh
 * Represents the detailed view of one of the archived rat reports in New York
 * City.
 */
public class DetailedRatReportViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_rat_report_view);

        Intent data = this.getIntent();

        // Initializing widgets
        TextView key = (TextView) findViewById(R.id.keyTextView);
        TextView date = (TextView) findViewById(R.id.dateTextView);
        TextView time = (TextView) findViewById(R.id.timeTextView);
        TextView address = (TextView) findViewById(R.id.addressTextView);
        TextView city = (TextView) findViewById(R.id.cityTextView);
        TextView zipcode = (TextView) findViewById(R.id.zipcodeTextView);
        TextView borough = (TextView) findViewById(R.id.boroughTextView);
        TextView location = (TextView) findViewById(R.id.locationTextView);
        TextView latitude = (TextView) findViewById(R.id.latitudeTextView);
        TextView longitude = (TextView) findViewById(R.id.longitudeTextView);

        // Grabbing the specified report and filling the widgets with its information
        ReportStructure report = StaticHolder.data;
        key.setText("Key: " + report.getKey());
        date.setText("Date: " + report.getDate());
        time.setText("Time: " + report.getTime());
        address.setText(""+ report.getAddress());
        city.setText("City: " + report.getCity());
        zipcode.setText("Zip: " + report.getZipCode());
        borough.setText("" + report.getBorough());
        location.setText("" + report.getBuildingType());
        latitude.setText("Latitude: " + report.getLatitude());
        longitude.setText("Longitude: " + report.getLongitude());
    }
}
