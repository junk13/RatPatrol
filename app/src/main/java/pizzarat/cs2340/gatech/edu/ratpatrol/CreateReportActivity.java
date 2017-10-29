package pizzarat.cs2340.gatech.edu.ratpatrol;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
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
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import pizzarat.cs2340.gatech.edu.sqlite.SQLiteReportBroker;
import pizzarat.cs2340.gatech.edu.structure.ReportStructure;
import pizzarat.cs2340.gatech.edu.structure.Verification;

/**
 * Represents the screen to create a rat sighting report.
 */
public class CreateReportActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
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

        // Navigation drawer creation
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Report Creation");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Actual report creation code
        String maxKey = Integer.toString(reportBroker.getMaxKey(getBaseContext()) + 1);
        key = (TextView) findViewById(R.id.createKeyView);

        // Generate and set the unique key
        key.setText("Key: " + maxKey);

        date = (TextView) findViewById(R.id.createDateView);
        time = (TextView) findViewById(R.id.createTimeView);

        Date curDate = new Date();
        SimpleDateFormat militaryTimeFormat = new SimpleDateFormat("kk:mm");

        //Todo: UtilityClass.militaryTimeFormat.format(curDate)
        String currentDateTimeString = militaryTimeFormat.format(curDate);
        time.setText(currentDateTimeString); //set current time as default text

        address = (TextView) findViewById(R.id.createAddressView);
        city = (TextView) findViewById(R.id.createCityView);
        zipcode = (TextView) findViewById(R.id.createZipcodeView);
        buildingType = (TextView) findViewById(R.id.createLocationTextView);

        Button cancelButton = (Button) findViewById(R.id.cancelReportButton);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToNavigationScreenActivity();
            }
        });
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
            Toast message = Toast.makeText(getApplicationContext(),
                    "You are already on this screen", Toast.LENGTH_LONG);
            message.show();
        } else if (id == R.id.nav_rat_archive) {
            switchToArchiveActivity();
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
     * Switches back to the selection screen after submission/cancellation.
     */
    public void switchToNavigationScreenActivity() {
        Intent navScreen = new Intent(this, NavigationActivity.class);
        this.startActivity(navScreen);
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
                Toast toast = Toast.makeText(this.getApplicationContext(), "Invalid ZipCode" + "\n"
                        + "Required Format: xxxxx", Toast.LENGTH_SHORT);
                toast.show();
            } else if (!Verification.isValidTime(time.getText().toString())) {
                Log.d("isvalid", time.getText().toString());
                Toast toast = Toast.makeText(this.getApplicationContext(), "Invalid Time" + "\n"
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
                                getDate(date.getText().toString()),
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

    private String getDate(String dateAndTime) {
        String[] date = dateAndTime.split(" ")[0].split("/");
        return date[2] + "/" + date[0] + "/" + date[1];
    }
}
