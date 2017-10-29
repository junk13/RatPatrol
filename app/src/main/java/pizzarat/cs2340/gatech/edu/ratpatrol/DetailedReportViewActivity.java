package pizzarat.cs2340.gatech.edu.ratpatrol;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import pizzarat.cs2340.gatech.edu.ratpatrol.archive.ArchiveActivity;
import pizzarat.cs2340.gatech.edu.structure.ReportStructure;
import pizzarat.cs2340.gatech.edu.structure.StaticHolder;

/**
 * @author Harrison Banh
 *         Represents the detailed view of one of the archived rat reports in New York
 *         City.
 */
public class DetailedReportViewActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_report_view);

        // Navigation drawer creation
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Report Details");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Intent data = this.getIntent();

        // Initializing widgets
        TextView key = (TextView) findViewById(R.id.keyTextView);
        TextView date = (TextView) findViewById(R.id.dateTextView);
        TextView time = (TextView) findViewById(R.id.timeTextView);
        TextView address = (TextView) findViewById(R.id.addressTextView);
        TextView city = (TextView) findViewById(R.id.cityTextView);
        TextView zipcode = (TextView) findViewById(R.id.zipcodeTextView);
        TextView borough = (TextView) findViewById(R.id.boroughTextView);
        TextView buildingType = (TextView) findViewById(R.id.buildingTypeTextView);
        TextView latitude = (TextView) findViewById(R.id.latitudeTextView);
        TextView longitude = (TextView) findViewById(R.id.longitudeTextView);

        // Grabbing the specified report and filling the widgets with its information
        ReportStructure report = StaticHolder.report;

        // Set key
        String keyText = getString(R.string.key_prompt) + " " + report.getKey();
        key.setText(keyText);

        // Set date
        String dateText = getString(R.string.date_prompt) + " " + report.getDate();
        date.setText(dateText);

        // Set time
        String timeText = getString(R.string.time_prompt) + " " + report.getTime();
        time.setText(timeText);

        // Set address
        String addressText = getString(R.string.address_prompt) + " " + report.getAddress();
        address.setText(addressText);

        // Set city
        String cityText = getString(R.string.city_prompt) + " " + report.getCity();
        city.setText(cityText);

        // Set zipcode
        String zipcodeText = getString(R.string.zipcode_prompt) + " " + report.getZipCode();
        zipcode.setText(zipcodeText);

        // Set borough
        String boroughText = getString(R.string.borough_prompt) + " " + report.getBorough();
        borough.setText(boroughText);

        // Set building type
        String buildingTypeText = getString(R.string.building_type_prompt) + " " + report.getBuildingType();
        buildingType.setText(buildingTypeText);

        // Set latitude
        String latitudeText = getString(R.string.latitude_prompt) + " " + report.getLatitude();
        latitude.setText(latitudeText);

        // Set longitude
        String longitudeText = getString(R.string.longitude_prompt) + " " + report.getLongitude();
        longitude.setText(longitudeText);
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
}
