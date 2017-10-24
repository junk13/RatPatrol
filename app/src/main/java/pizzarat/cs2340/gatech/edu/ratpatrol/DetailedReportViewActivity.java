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
public class DetailedReportViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_report_view);

        Intent data = this.getIntent();

        // Initializing widgets
        TextView key = (TextView) findViewById(R.id.keyTextView);
        TextView date = (TextView) findViewById(R.id.dateTextView);
        TextView time = (TextView) findViewById(R.id.timeTextView);
        TextView address = (TextView) findViewById(R.id.addressTextView);
        TextView city = (TextView) findViewById(R.id.cityTextView);
        TextView zipcode = (TextView) findViewById(R.id.zipcodeTextView);
        TextView borough = (TextView) findViewById(R.id.boroughTextView);
        TextView buildingType = (TextView) findViewById(R.id.locationTextView);
        TextView latitude = (TextView) findViewById(R.id.latitudeTextView);
        TextView longitude = (TextView) findViewById(R.id.longitudeTextView);

        // Grabbing the specified report and filling the widgets with its information
        ReportStructure report = StaticHolder.data;

        // Set key
        String keyText = getString(R.string.key_prompt) + report.getKey();
        key.setText(keyText);

        // Set date
        String dateText = getString(R.string.date_prompt) + report.getDate();
        date.setText(dateText);

        // Set time
        String timeText = getString(R.string.time_prompt) + report.getTime();
        time.setText(timeText);

        // Set address
        address.setText(report.getAddress());

        // Set city
        String cityText = getString(R.string.city_prompt) + report.getCity();
        city.setText(cityText);

        // Set zipcode
        String zipcodeText = getString(R.string.zipcode_prompt) + report.getZipCode();
        zipcode.setText(zipcodeText);

        // Set borough
        String boroughText = getString(R.string.borough_prompt) + report.getBorough();
        borough.setText(boroughText);

        // Set building type
        String buildingTypeText = getString(R.string.building_type_prompt) + report.getBuildingType();
        buildingType.setText(buildingTypeText);

        // Set latitude
        String latitudeText = getString(R.string.latitude_prompt) + report.getLatitude();
        latitude.setText(latitudeText);

        // Set longitude
        String longitudeText = getString(R.string.longitude_prompt) + report.getLongitude();
        longitude.setText(longitudeText);
    }
}
