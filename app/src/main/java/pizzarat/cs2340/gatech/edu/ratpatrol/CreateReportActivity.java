package pizzarat.cs2340.gatech.edu.ratpatrol;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import pizzarat.cs2340.gatech.edu.sqlite.SQLiteReportBroker;
import pizzarat.cs2340.gatech.edu.structure.ReportStructure;
import pizzarat.cs2340.gatech.edu.structure.Verification;

/**
 * Represents the screen to create a rat sighting report.
 */
public class CreateReportActivity extends AppCompatActivity {
    public SQLiteReportBroker reportBroker = new SQLiteReportBroker();
    private TextView key;
    private TextView date;
    private TextView time;
    private TextView address;
    private TextView city;
    private TextView zipcode;
    private TextView buildingType;

    // TODO grab data, create report, save to DB, generate unique key

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_report);
        String maxKey = Integer.toString(reportBroker.getMaxKey(getBaseContext()) + 1);
        key = (TextView) findViewById(R.id.createKeyView);

        // Generate and set the unique key
        key.setText("Key: " + maxKey);

        date = (TextView) findViewById(R.id.createDateView);
        time = (TextView) findViewById(R.id.createTimeView);
        address = (TextView) findViewById(R.id.createAddressView);
        city = (TextView) findViewById(R.id.createCityView);
        zipcode = (TextView) findViewById(R.id.createZipcodeView);
        buildingType = (TextView) findViewById(R.id.createLocationTextView);

        Button createButton = (Button) findViewById(R.id.createReportButton);
        Button cancelButton = (Button) findViewById(R.id.cancelReportButton);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToNavigationScreenActivity();
            }
        });
    }

    /**
     * Switches back to the selection screen after submission/cancellation.
     */
    public void switchToNavigationScreenActivity() {
        Intent switchToNavigationScreen = new Intent(this, NavigationActivity.class);
        this.startActivity(switchToNavigationScreen);
    }

    /**
     * Creates and adds the user's new report created from the filled out views
     * into the Database.
     *
     * @param v the widget that triggers creation.
     */
    public void addReport(View v) {
        Geocoder geocoder = new Geocoder(getBaseContext());
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocationName(buildingType.getText().toString(), 1);
            if (!Verification.isValidZip(zipcode.getText().toString())) {
                Toast toast = Toast.makeText(this.getApplicationContext(), "Invalid ZipCode" +"\n"
                        + "Required Format: xxxxx", Toast.LENGTH_SHORT);
                toast.show();
            } else if (!Verification.isValidTime(time.getText().toString())) {
                Log.d("isvalid", time.getText().toString());
                Toast toast = Toast.makeText(this.getApplicationContext(), "Invalid Time" +"\n"
                        + "Required Format: hh:mm", Toast.LENGTH_SHORT);
                toast.show();
            } else if (!Verification.isValidDate(date.getText().toString())) {
                Log.d("isvalid", date.getText().toString());
                Toast toast = Toast.makeText(this.getApplicationContext(), "Invalid Date" + "\n"
                        + "Required Format: mm/dd/year", Toast.LENGTH_SHORT);
                toast.show();
            } else if (!Verification.isValidGeneric(address.getText().toString())) { //ADDRESS
                Log.d("isvalid", address.getText().toString());
                Toast toast = Toast.makeText(this.getApplicationContext(), "Must have Address", Toast.LENGTH_SHORT);
                toast.show();
            } else if (!Verification.isValidGeneric(buildingType.getText().toString())) { //LOCATION
                Log.d("isvalid", buildingType.getText().toString());
                Toast toast = Toast.makeText(this.getApplicationContext(), "Must have location", Toast.LENGTH_SHORT);
                toast.show();
            } else if (!Verification.isValidGeneric(city.getText().toString())) { //CITY
                Log.d("isvalid", city.getText().toString());
                Toast toast = Toast.makeText(this.getApplicationContext(), "Must have City", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                String maxKey = Integer.toString(reportBroker.getMaxKey(getBaseContext()) + 1);
                reportBroker.writeToReportDb(
                        new ReportStructure(
                                maxKey,
                                buildingType.getText().toString(),
                                time.getText().toString(),
                                date.getText().toString(),
                                address.getText().toString(),
                                zipcode.getText().toString(),
                                city.getText().toString(),
                                "Not specified",
                                Double.toString(addresses.get(0).getLatitude()),
                                Double.toString(addresses.get(0).getLatitude())
                        ),
                        getBaseContext()
                );
                switchToNavigationScreenActivity();
            }
        } catch (Exception e) {
            Log.d("Cunt", "kill me now");
            key.setError("An unknown error occurred.");
        }
    }
}
