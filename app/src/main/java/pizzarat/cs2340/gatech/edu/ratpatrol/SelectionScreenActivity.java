package pizzarat.cs2340.gatech.edu.ratpatrol;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
    private View logoutButton;
    private View ratArchiveButton;
    private View userReportsButton;
    private View ratMapButton;
    private BackgroundDataTask bdTask = null;
    private SQLiteReportBroker reportBroker = new SQLiteReportBroker();

    /**
     * Creates the SelectionScreenActivity
     * @param savedInstanceState the bundle from the last activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_screen);

        // Logout Function
        logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               logout();
            }
        });

        // New York Rat Archive
        ratArchiveButton = findViewById(R.id.ratArchiveButton);
        ratArchiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToNYRatArchiveActivity();
            }
        });

        // User Reports Activity
        userReportsButton = findViewById(R.id.createReportButton);
        userReportsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToUserRatReportsActivity();
            }
        });

        // Rat Map Activity
        ratMapButton = findViewById(R.id.ratMapButton);
        ratMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToRatMapActivity();
            }
        });




        bdTask = new BackgroundDataTask();
        bdTask.execute();
//        String dbContent;
//        try {
//            dbContent = reportBroker.getDbContent(this.getApplicationContext());
//            Log.d("hidden",dbContent);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        //addDummyToSQL();
        //Log.d("Cunt",": " + reportBroker.isEmpty(this.getApplicationContext()));

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
     * Switches to the NewYorkRatArchiveActivity from the SelectionScreenActivity.
     */
    public void switchToNYRatArchiveActivity() {
        Intent switchToNYRatArchiveActivity = new Intent(this, NewYorkRatArchiveActivity.class);
        this.startActivity(switchToNYRatArchiveActivity);
    }

    /**
     * Switches to the UserRatReportsActivity.
     */
    public void switchToUserRatReportsActivity() {
        Intent switchToUserRatReportsActivity = new Intent(this, UserRatReportsActivity.class);
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
     * Closes the SelectionScreenActivity thus "logging out" the user
     */
    // Make change later
    public void logout() {
       finish();
    }


    private void addDummyToSQL() {
        ReportStructure rsrTest;
        try {
            rsrTest = new ReportStructure("432", "my house", "10/10/2000", "12:00:00 AM", "101 Cool Dude Rd", "30309", "New York", "little one");
            Log.d("hidden",rsrTest.getBorough());
            reportBroker.writeToReportDb(rsrTest, this.getBaseContext());
            Log.d("hidden",rsrTest.getDate());
        } catch (DuplicateReportDbException e) {
            Log.d("hidden",e.getLocalizedMessage());
        }
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

        ReportStructure rsrTest;
        try {
            rsrTest = new ReportStructure("32", "my house", "10/10/2000", "12:00:00 AM", "101 Cool Dude Rd", "30309", "New York", "little one");
            reportBroker.writeToReportDb(rsrTest, this.getApplicationContext());
            Log.d("hidden",rsrTest.getDate());
        } catch (DuplicateReportDbException e) {
            Log.d("hidden",e.getLocalizedMessage());
        }

        try {
            Log.d("hidden","trying to read file...");
            inputStream = getResources().openRawResource(R.raw.ratsightings);
            br = new BufferedReader(new InputStreamReader(inputStream));
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] ratSighting = line.split(cvsSplitBy);

                String key = ratSighting[0];
                String location = ratSighting[7];
                String date = getDate(ratSighting[1]);
                String time = getTime(ratSighting[1]);
                String address = ratSighting[9];
                String zip = ratSighting[8];
                String city = ratSighting[16];
                String borough = ratSighting[23];
                ReportStructure rsr = new ReportStructure(key,location,date,time,address,zip,city,borough);

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
        String[] time = dateAndTime.substring(dateAndTime.indexOf(" ")).split("[:/]+");
        if (time[3].equals("PM"))
        {
            time[0] = ""+(Integer.parseInt(time[0])+12);
        }
        return time[1] + ":" + time[1];
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
                Log.d("Cunt","hi");
                Toast.makeText(getBaseContext(), "gud job", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getBaseContext(), "die", Toast.LENGTH_SHORT).show();
            }
        }

    }

}
