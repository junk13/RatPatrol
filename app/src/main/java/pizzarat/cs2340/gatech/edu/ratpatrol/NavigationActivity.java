package pizzarat.cs2340.gatech.edu.ratpatrol;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
 * This is our navigation screen to move between all the other activities within
 * the Rat Patrol application.
 *
 * @author Harrison Banh
 */
public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private BackgroundDataTask bdTask = null;
    private SQLiteReportBroker reportBroker = new SQLiteReportBroker();
    private boolean csvLoaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Main Menu");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_create_report) {
            switchToCreateReportActivity();
        } else if (id == R.id.nav_rat_archive) {
            if (csvLoaded) {
                switchToArchiveActivity();
            }
            else {
                Toast.makeText(getBaseContext(), "Waiting for CSV data to load in!", Toast.LENGTH_SHORT).show();
            }
        } else if (id == R.id.nav_filter) {
            switchToFilterReportsScreen();
        } else if (id == R.id.nav_sightings_map) {
            switchToMapActivity();
        } else if (id == R.id.nav_report_graphs) {
            switchToReportGraphScreen();
        } else if (id == R.id.nav_logout) {
            logout();
        } else if (id == R.id.nav_share) {
            shareOrSendReport("Share");
        } else if (id == R.id.nav_send) {
            shareOrSendReport("Send");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Switches to the WelcomeActivity from the Navigation Screen.
     */
    public void switchBackToWelcomeActivity() {
        Intent switchToWelcomeActivity = new Intent(this, WelcomeActivity.class);
        this.startActivity(switchToWelcomeActivity);
    }

    /**
     * Switches to the ArchiveActivity from the Navigation Screen.
     */
    public void switchToArchiveActivity() {
        Intent switchToArchiveActivity = new Intent(this, ArchiveActivity.class);
        this.startActivity(switchToArchiveActivity);
    }

    /**
     * Switches to the CreateReportActivity.
     */
    public void switchToCreateReportActivity() {
        Intent switchToCreateReportActivity = new Intent(this, CreateReportActivity.class);
        this.startActivity(switchToCreateReportActivity);
    }

    /**
     * Switches to the MapActivity.
     */
    public void switchToMapActivity() {
        Intent switchToMapActivity = new Intent(this, MapActivity.class);
        this.startActivity(switchToMapActivity);
    }

    /**
     * Switches to the FilterReportsActivity.
     */
    public void switchToFilterReportsScreen() {
        Intent switchToFilterReportsActivity = new Intent(this, FilterReportsActivity.class);
        this.startActivity(switchToFilterReportsActivity);
    }

    /**
     * Closes the Navigation Screen thus "logging out" the user
     */
    public void logout() {
        switchBackToWelcomeActivity();
    }

    /**
     * Switches to the ReportGraphActivity.
     */
    public void switchToReportGraphScreen() {
        Intent switchToReportGraphScreenActivity = new Intent(this, ReportGraphActivity.class);
        startActivity(switchToReportGraphScreenActivity);
        Toast.makeText(getBaseContext(), "To filter/edit graph, use the filter "
                + "button on the Navigation Screen.", Toast.LENGTH_LONG).show();
    }

    /**
     * Opens a bunch of apps at the bottom of the screen allowing the user to
     * share something about the Rat Patrol app using different apps.
     *
     * @param widget the name of widget clicked
     */
    public void shareOrSendReport(String widget) {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        String shareBody = "Your body here";
        String shareSub = "Your subject here";
        share.putExtra(Intent.EXTRA_SUBJECT, shareBody);
        share.putExtra(Intent.EXTRA_TEXT, shareSub);
        startActivity(Intent.createChooser(share, widget + " using"));

    }

    /**
     * Read in offline rat report from csv
     */
    private void readRatData() {
        String csvFile = "raw/ratsightings.csv";
        InputStream inputStream;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        Log.d("hidden", "in function...");
        try {
            Log.d("hidden", "trying to read file...");
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
                    Log.d("hidden", "caught array index out of bounds");
                }
                if (latitude.isEmpty() || longitude.isEmpty()) {
                    try {
                        addresses = geocoder.getFromLocationName(address + " " + zip + " " + city + " " + borough, 1);
                        latitude = Double.toString(addresses.get(0).getLatitude());
                        longitude = Double.toString(addresses.get(0).getLongitude());
                    } catch (Exception e) {
                        Log.d("hidden", "caught location error");
                    }

                }
                ReportStructure rsr = new ReportStructure(key, buildingType, date,
                        time, address, zip, city, borough, latitude, longitude);

                reportBroker.writeToReportDb(rsr, this.getApplicationContext());
            }

        } catch (FileNotFoundException e) {
            Log.d("hidden", "FILE NOT FOUND");
            e.printStackTrace();
        } catch (DuplicateReportDbException e) {
            Log.d("hidden", e.getLocalizedMessage());
        } catch (IOException e) {
            Log.d("hidden", "IOEXCEPTION");
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
        return date[2] + "/" + date[0] + "/" + date[1];
    }

    private String getTime(String dateAndTime) {
        String[] time = dateAndTime.substring(dateAndTime.indexOf(" ") + 1).split(":| ");
        if (time[3].equals("PM")) {
            time[0] = "" + (Integer.parseInt(time[0]) + 12);
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
                Log.d("hidden", "hi");
                Toast.makeText(getBaseContext(), "You can now view and create rat reports", Toast.LENGTH_SHORT).show();
                csvLoaded = true;
            } else {
                Toast.makeText(getBaseContext(), "There was an error loading in the rat report!", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
