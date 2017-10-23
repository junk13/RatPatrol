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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import pizzarat.cs2340.gatech.edu.sqlite.SQLiteReportBroker;
import pizzarat.cs2340.gatech.edu.structure.ReportStructure;

/**
 * Represents the screen to create a rat sighting report.
 */
public class CreateRatReportActivity extends AppCompatActivity {
    final static String DATE_FORMAT = "MM/dd/yyyy";
    public SQLiteReportBroker reportBroker = new SQLiteReportBroker();
    private TextView key;
    private TextView date;
    private TextView time;
    private TextView address;
    private TextView city;
    private TextView zipcode;
    private TextView buildingType;

    /**
     * Determines if the user's specified date is legitimate using the
     * following format mm/dd/year
     *
     * @param str the user's date
     * @return true if the date is valid
     */
    public static boolean isValidDate(String str) {
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            df.parse(str);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    // TODO grab data, create report, save to DB, generate unique key

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_rat_reports);
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
                switchToSelectionScreenActivity();
            }
        });
    }

    /**
     * Switches back to the selection screen after submission/cancellation.
     */
    public void switchToSelectionScreenActivity() {
        Intent switchToSelectionScreen = new Intent(this, SelectionScreenActivity.class);
        this.startActivity(switchToSelectionScreen);
    }

    /**
     * Creates and adds the user's new report created from the filled out views
     * into the Database.
     * @param v the widget that triggers creation.
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
                Toast toast = Toast.makeText(this.getApplicationContext(), "Invalid Date" + "\n"
                        + "Required Format: mm/dd/year", Toast.LENGTH_SHORT);
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
                switchToSelectionScreenActivity();
            }
        } catch (Exception e) {
            Log.d("Cunt", "kill me now");
            key.setError("An unknown error occurred.");
        }
    }

    /**
     * Determines if the the user's specified zipcode is legitimate
     *
     * @param zip the user's zipcode
     * @return true if the zipcode is of valid length
     */
    private boolean isValidZip(String zip){
        return zip.length() == 5;
    }

    /**
     * Determines if the user's specified type is legitimate using 24 hour
     * formatted time.
     * @param str the user's specified time
     * @return true if the time is valid
     */
    private boolean isValidTime(String str){
        String form = "((([0][0-9])|([1][0-2]))[:][0-5][0-9])";
        return str.matches(form);
    }

    /**
     * Generic validation method for all other String based fields.
     * @param str the user's specified input
     * @return true if not empty
     */
    private boolean isValidGeneric(String str){
        return !str.equals("");
    }
}
