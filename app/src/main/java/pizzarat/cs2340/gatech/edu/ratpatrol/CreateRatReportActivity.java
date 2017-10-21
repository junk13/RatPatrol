package pizzarat.cs2340.gatech.edu.ratpatrol;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

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
    public void addReport(View v){
        Geocoder geocoder = new Geocoder(getBaseContext());
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocationName(buildingType.getText().toString(), 1);
            reportBroker.writeToReportDb(new ReportStructure(
                    key.getText().toString(),
                    buildingType.getText().toString(),
                    time.getText().toString(),
                    date.getText().toString(),
                    address.getText().toString(),
                    zipcode.getText().toString(),
                    city.getText().toString(),
                            "Manhatten",
                            Double.toString(addresses.get(0).getLatitude()),
                            Double.toString(addresses.get(0).getLatitude())
                    ),
                    getBaseContext()
            );
            switchToSelectionScreenActivity();
        } catch(Exception e){
            key.setError("An unknown error occurred.");
        }
    }

