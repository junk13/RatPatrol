package pizzarat.cs2340.gatech.edu.ratpatrol;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pizzarat.cs2340.gatech.edu.exception.DuplicateReportDbException;
import java.util.List;

import pizzarat.cs2340.gatech.edu.sqlite.SQLiteReportBroker;
import pizzarat.cs2340.gatech.edu.structure.ReportStructure;

/**
 * Represents the screen of to create a rat sighting report.
 */
public class CreateRatReportActivity extends AppCompatActivity {
    public SQLiteReportBroker reportBroker = new SQLiteReportBroker();
    private TextView key;
    private TextView date;
    private TextView time;
    private TextView address;
    private TextView city;
    private TextView zipcode;
    private TextView buildingType;
    private Spinner borough;
    private Button createButton;
    private Button cancelButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_rat_reports);

        key = (TextView) findViewById(R.id.createKeyView);
        // Generate and set the unique key
        key.setText("Key: " +
                Integer.toString(reportBroker.getMaxKey(getBaseContext()) + 1));

        date = (TextView) findViewById(R.id.createDateView);
        time = (TextView) findViewById(R.id.createTimeView);
        address = (TextView) findViewById(R.id.createAddressView);
        city = (TextView) findViewById(R.id.createCityView);
        zipcode = (TextView) findViewById(R.id.createZipcodeView);
        buildingType = (TextView) findViewById(R.id.createLocationTextView);
        // TODO populate spinner with proper borough strings
        //borough = (Spinner) findViewById(R.id.createBoroughSpinner);

        createButton = (Button) findViewById(R.id.createReportButton);
        cancelButton = (Button) findViewById(R.id.cancelReportButton);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToSelectionScreenActivity();
            }
        });
    }

    // TODO grab data, create report, save to DB, generate unique key

    /**
     *      Switches back to the selection screen after submission/cancellation.
     */
    public void switchToSelectionScreenActivity() {
        Intent switchToSelectionScreen = new Intent(this, SelectionScreenActivity.class);
        this.startActivity(switchToSelectionScreen);
    }

    /**
     *      Attempt to add new data to the List and leave.
     *
     *      Failuire to add new data will result in no action
     *      for the time being.
     */
    public void addReport(View v) {
        Geocoder geocoder = new Geocoder(getBaseContext());
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocationName(buildingType.getText().toString(), 1);
            if (!isValidZip(zipcode.getText().toString())) {
                Toast toast = Toast.makeText(this.getApplicationContext(), "Invalid ZipCode", Toast.LENGTH_SHORT);
                toast.show();
            } else if (!isValidTime(time.getText().toString())) {
                Log.d("isvalid", time.getText().toString());
                Toast toast = Toast.makeText(this.getApplicationContext(), "Invalid Time", Toast.LENGTH_SHORT);
                toast.show();
            } else if (!isValidDate(date.getText().toString())){
                Log.d("isvalid", date.getText().toString());
                Toast toast = Toast.makeText(this.getApplicationContext(), "Invalid Date", Toast.LENGTH_SHORT);
                toast.show();
            } else if(!isValidGeneric(address.getText().toString())){ //ADDRESS
                Log.d("isvalid", address.getText().toString());
                Toast toast = Toast.makeText(this.getApplicationContext(), "Must have Address", Toast.LENGTH_SHORT);
                toast.show();
            } else if(!isValidGeneric(buildingType.getText().toString())){ //LOCATION
                Log.d("isvalid", buildingType.getText().toString());
                Toast toast = Toast.makeText(this.getApplicationContext(), "Must have location", Toast.LENGTH_SHORT);
                toast.show();
            } else if(!isValidGeneric(city.getText().toString())){ //CITY
                Log.d("isvalid", city.getText().toString());
                Toast toast = Toast.makeText(this.getApplicationContext(), "Must have City", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                reportBroker.writeToReportDb(
                        new ReportStructure(
                                key.getText().toString(),
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
                switchToSelectionScreenActivity();
            }
        } catch (Exception e) {
            key.setError("An unknown error occurred.");
        }
    }

    private boolean isValidZip(String zip){
        return zip.length() == 5;
    }
    private boolean isValidTime(String str){
        String form = "((([0][0-9])|([1][0-2]))[:][0-5][0-9][ap][m])";
        return str.matches(form);
    }
    private boolean isValidDate(String str){
        String form = "((([0][\\d])|[1][0-2])[/](([0-2][\\d])|[3][0-1])[/][2][0][\\d][\\d])";
        return str.matches(form);
        //TODO date format accepts 0-31 for all months. Could be modified for each month.
    }
    private boolean isValidGeneric(String str){
        return !str.equals("");
    }
}
