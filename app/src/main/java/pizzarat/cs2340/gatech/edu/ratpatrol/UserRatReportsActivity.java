package pizzarat.cs2340.gatech.edu.ratpatrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pizzarat.cs2340.gatech.edu.exception.DuplicateReportDbException;
import pizzarat.cs2340.gatech.edu.sqlite.SQLiteReportBroker;
import pizzarat.cs2340.gatech.edu.structure.ReportHolder;
import pizzarat.cs2340.gatech.edu.structure.ReportStructure;

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
        location = (TextView) findViewById(R.id.createLocationTextView);
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
        try {
            if (!isValidZip(zipcode.getText().toString())) {
                Toast toast = Toast.makeText(this.getApplicationContext(), "Invalid ZipCode", Toast.LENGTH_SHORT);
                toast.show();
                //TODO fix the time validation code.
            } else if (!isValidTime(time.getText().toString())) {
                Log.d("isvalid", time.getText().toString());
                Toast toast = Toast.makeText(this.getApplicationContext(), "Invalid Time", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                // TODO check all fields are valid.
                ReportStructure newReport = new ReportStructure(
                        key.getText().toString(),
                        location.getText().toString(),
                        time.getText().toString(),
                        date.getText().toString(),
                        address.getText().toString(),
                        zipcode.getText().toString(),
                        city.getText().toString(),
                        "Manhatten");
                ReportHolder.add(newReport);
                switchToSelectionScreenActivity();
            }
        } catch (Exception e){
            key.setError("An unknown error occurred.");
        }
    }

    private boolean isValidZip(String zip){
        return zip.length() == 5;
    }
    private boolean isValidTime(String str){
        String form = "([0-2][0-9][:][0-5][0-9][ap][m])";
        return str.matches(form);
        //TODO Not empty for feilds.
        //TODO time format is 12 hour.
        //TODO date format is xx/xx/xxxx
    }
}
