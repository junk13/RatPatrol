package pizzarat.cs2340.gatech.edu.ratpatrol;

import android.content.Intent;
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

import pizzarat.cs2340.gatech.edu.ratpatrol.archive.ArchiveActivity;
import pizzarat.cs2340.gatech.edu.structure.DateRangeStruct;
import pizzarat.cs2340.gatech.edu.structure.StaticHolder;

/**
 * This class is a simple activity to allow the user to filter the rat reports
 * in the rat archive and rat map by specifying a String parameter.
 *
 * @author Harrison Banh
 */
public class FilterReportsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private TextView beforeDateTextView;
    private TextView afterDateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_reports);

        // Navigation drawer creation
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Report Filter");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView =
                (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        beforeDateTextView = (TextView) findViewById(R.id.beforeDateTextView);
        afterDateTextView = (TextView) findViewById(R.id.afterDateTextView);
        Button filterButton = (Button) findViewById(R.id.filterReportsButton);
        Button cancelFilterButton = (Button) findViewById(R.id.cancelFilterButton);

        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String beforeDate =
                            (beforeDateTextView.getText().toString());
                    String afterDate = (afterDateTextView.getText().toString());
                    Log.d("hidden", beforeDate + " | " + afterDate);
                    StaticHolder.dateRange =
                            new DateRangeStruct(getDate(beforeDate),
                                    getDate(afterDate));
                } catch (Exception e) {
                    Log.d("hidden", e.getLocalizedMessage());
                }
                switchBackToNavigationScreenActivity();
            }
        });

        // Filter parameter is discarded and screen switches to the Nav screen
        cancelFilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beforeDateTextView.setText("");
                afterDateTextView.setText("");
                switchBackToNavigationScreenActivity();
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
            switchToCreateReportActivity();
        } else if (id == R.id.nav_rat_archive) {
            switchToArchiveActivity();
        } else if (id == R.id.nav_filter) {
            Toast message = Toast.makeText(getApplicationContext(),
                    "You are already on this screen", Toast.LENGTH_LONG);
            message.show();
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
    private void switchBackToWelcomeActivity() {
        Intent switchToWelcomeActivity =
                new Intent(this, WelcomeActivity.class);
        this.startActivity(switchToWelcomeActivity);
    }

    /**
     * Switches to the ArchiveActivity from the Navigation Screen.
     */
    private void switchToArchiveActivity() {
        Intent switchToArchiveActivity =
                new Intent(this, ArchiveActivity.class);
        this.startActivity(switchToArchiveActivity);
    }

    /**
     * Switches to the CreateReportActivity.
     */
    private void switchToCreateReportActivity() {
        Intent switchToCreateReportActivity =
                new Intent(this, CreateReportActivity.class);
        this.startActivity(switchToCreateReportActivity);
    }

    /**
     * Switches to the MapActivity.
     */
    private void switchToMapActivity() {
        Intent switchToMapActivity =
                new Intent(this, MapActivity.class);
        this.startActivity(switchToMapActivity);
    }

    /**
     * Closes the Navigation Screen thus "logging out" the user
     */
    private void logout() {
        switchBackToWelcomeActivity();
    }

    /**
     * Switches to the ReportGraphActivity.
     */
    private void switchToReportGraphScreen() {
        Intent switchToReportGraphScreenActivity =
                new Intent(this, ReportGraphActivity.class);
        startActivity(switchToReportGraphScreenActivity);
        Toast.makeText(getBaseContext(), "To filter/edit graph, use the " +
                        "filter button on the Navigation Screen.",
                Toast.LENGTH_LONG).show();
    }

    /**
     * Opens a bunch of apps at the bottom of the screen allowing the user to
     * share something about the Rat Patrol app using different apps.
     *
     * @param widget the name of widget clicked
     */
    private void shareOrSendReport(String widget) {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        String shareBody = "Your body here";
        String shareSub = "Your subject here";
        share.putExtra(Intent.EXTRA_SUBJECT, shareBody);
        share.putExtra(Intent.EXTRA_TEXT, shareSub);
        startActivity(Intent.createChooser(share, widget + " using"));

    }

    private String getDate(String dateAndTime) {
        String[] date = dateAndTime.split(" ")[0].split("/");
        return date[2] + "/" + date[0] + "/" + date[1];
    }

    /**
     * Switches to the navigation screen after the filter or cancel button has
     * benn pushed.
     */
    private void switchBackToNavigationScreenActivity() {
        Intent switchToNavigationScreen =
                new Intent(this, NavigationActivity.class);
        this.startActivity(switchToNavigationScreen);
    }

}
