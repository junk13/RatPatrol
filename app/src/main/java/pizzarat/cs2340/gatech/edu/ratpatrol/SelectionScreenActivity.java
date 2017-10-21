package pizzarat.cs2340.gatech.edu.ratpatrol;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import pizzarat.cs2340.gatech.edu.exception.DuplicateReportDbException;
import pizzarat.cs2340.gatech.edu.sqlite.SQLiteReportBroker;
import pizzarat.cs2340.gatech.edu.structure.ReportStructure;


/**
 * This is the selection screen after logging in which allow the
 * user to switch to different screens with different functions
 * using the displayed buttons.
 *
 * @author Harrison Banh
 */
public class SelectionScreenActivity extends AppCompatActivity {
    private Button logoutButton;
    private Button ratArchiveButton;
    private Button createReport;
    private Button ratMapButton;
    private Button filterScreenButton;
    private BackgroundDataTask bdTask = null;
    private SQLiteReportBroker reportBroker = new SQLiteReportBroker();
    private boolean csvLoaded = false;

    /**
     * Creates the SelectionScreenActivity
     * @param savedInstanceState the bundle from the last activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_screen);

        // Set logout button function
        logoutButton = (Button) findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               logout();
            }
        });

        // Set Rat Archive button function
        ratArchiveButton = (Button) findViewById(R.id.ratArchiveButton);
        ratArchiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (csvLoaded) {
                    switchToNYRatArchiveActivity();
                }
                else {
                    Toast.makeText(getBaseContext(), "Waiting for CSV data to load in!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Sets create a report button function
        createReport = (Button) findViewById(R.id.createReportButton);
        createReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToUserRatReportsActivity();
            }
        });

        // Sets the Rat Map button function
        ratMapButton = (Button) findViewById(R.id.ratMapButton);
        ratMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToRatMapActivity();
            }
        });

        // Sets the Filter reports button function
        filterScreenButton = (Button) findViewById(R.id.filterScreenButton);
        filterScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //switch to Filter Reports Activity
                switchToFilterReportsScreen();
            }
        });

        Log.d("hidden",""+reportBroker.getMaxKey(getBaseContext()));
        if (!reportBroker.isPopulated(this.getBaseContext())){
            Log.d("hidden","it's not populated, boi");
            //Get csv data
            bdTask = new BackgroundDataTask();
            bdTask.execute();
        }
        else {
            Log.d("hidden","it's populated, boi");
            csvLoaded = true;
        }
    }

    /**
     * Switches to the LoginActivity from the SelectionScreenActivity.
     */
    public void switchBackToLoginActivity() {
        Intent startSelectionScreenActivity = new Intent(SelectionScreenActivity.this, LoginActivity.class);
        SelectionScreenActivity.this.startActivity(startSelectionScreenActivity);
    }

    /**
     * Switches to the WelcomeActivity from the SelectionScreenActivity.
     */
    public void switchBackToWelcomeActivity() {
        Intent switchToWelcomeActivity = new Intent(SelectionScreenActivity.this, Welcome.class);
        SelectionScreenActivity.this.startActivity(switchToWelcomeActivity);
    }

    /**
     * Switches to the RatArchiveActivity from the SelectionScreenActivity.
     */
    public void switchToNYRatArchiveActivity() {
        Intent switchToNYRatArchiveActivity = new Intent(this, RatArchiveActivity.class);
        this.startActivity(switchToNYRatArchiveActivity);
    }

    /**
     * Switches to the CreateRatReportActivity.
     */
    public void switchToUserRatReportsActivity() {
        Intent switchToUserRatReportsActivity = new Intent(this, CreateRatReportActivity.class);
        this.startActivity(switchToUserRatReportsActivity);
    }

    /**
     * Switches to the RatMapActivity.
     */
    public void switchToRatMapActivity() {
        Intent switchToRatMapActivity = new Intent(this, RatMapActivity.class);
        this.startActivity(switchToRatMapActivity);
    }

    /**
     * Switches to the FilterReportsActivity
     */
    public void switchToFilterReportsScreen() {
        Intent switchToFilterReportsActivity = new Intent(this, FilterReportsActivity.class);
        this.startActivity(switchToFilterReportsActivity);
    }

    /**
     * Closes the SelectionScreenActivity thus "logging out" the user
     */
    public void logout() {
       switchBackToWelcomeActivity();
    }

    /**
     * Read in offline rat data from csv
     */
    private void readRatData()  {
        String csvFile = "raw/ratsightings.csv";
        InputStream inputStream;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        Log.d("hidden","in function...");
        try {
            Log.d("hidden","trying to read file...");
            inputStream = getResources().openRawResource(R.raw.ratsightings);
            br = new BufferedReader(new InputStreamReader(inputStream));
            line = br.readLine();
            Geocoder geocoder = new Geocoder(getBaseContext());
            List<Address> addresses = null;
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] ratSighting = line.split(cvsSplitBy);

                String key = ratSighting[0];
                String buildingType = ratSighting[7];
                String date = getDate(ratSighting[1]);
                String time = getTime(ratSighting[1]);
                String address = ratSighting[9];
                String zip = ratSighting[8];
                String city = ratSighting[16];
                String borough = ratSighting[23];
                String latitude = "";
                String longitude = "";
                try {
                    latitude = ratSighting[49];
                    longitude = ratSighting[50];
                } catch (ArrayIndexOutOfBoundsException e) {
                    Log.d("Cunt", "caught array index out of bounds");
                }
                if (latitude.isEmpty() || longitude.isEmpty()) {
                    try {
                        addresses = geocoder.getFromLocationName(address + " " + zip + " " + city + " " + borough, 1);
                        latitude = Double.toString(addresses.get(0).getLatitude());
                        longitude = Double.toString(addresses.get(0).getLongitude());
                    } catch (Exception e) {
                        Log.d("Cunt", "caught location error");
                    }

                }
                ReportStructure rsr = new ReportStructure(key, buildingType, date,
                        time, address, zip, city, borough, latitude, longitude);

                reportBroker.writeToReportDb(rsr,this.getApplicationContext());
            }

        } catch (FileNotFoundException e) {
            Log.d("hidden","FILE NOT FOUND");
            e.printStackTrace();
        } catch (DuplicateReportDbException e) {
            Log.d("hidden",e.getLocalizedMessage());
        } catch (IOException e) {
            Log.d("hidden","IOEXCEPTION");
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private String getDate(String dateAndTime) {
        String[] date = dateAndTime.split(" ")[0].split("/");
        return date[2] + "/" + date[1] + "/" + date[0];
    }

    private String getTime(String dateAndTime) {
        String[] time = dateAndTime.substring(dateAndTime.indexOf(" ")+1).split(":| ");
        if (time[3].equals("PM"))
        {
            time[0] = ""+(Integer.parseInt(time[0])+12);
        }
        return time[0] + ":" + time[1] + ":" + time[2];
    }


    public class BackgroundDataTask extends AsyncTask<Context, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Context... contexts) {
            readRatData();
            return true;
        }

        protected void onPostExecute(final Boolean success) {
            bdTask = null;
            //showProgress(false);

            if (success) {
                Log.d("hidden","hi");
                Toast.makeText(getBaseContext(), "You can now view and create rat reports", Toast.LENGTH_SHORT).show();
                csvLoaded = true;
            } else {
                Toast.makeText(getBaseContext(), "There was an error loading in the rat data!", Toast.LENGTH_SHORT).show();
            }
        }

    }

}
