package pizzarat.cs2340.gatech.edu.ratpatrol;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import pizzarat.cs2340.gatech.edu.structure.DateRangeStruct;
import pizzarat.cs2340.gatech.edu.structure.StaticHolder;

/**
 * @author Harrison Banh
 *         This class is a simple acitivity to allow the user to filter the rat reports
 *         in the rat archive and rat map by specifying a String parameter.
 */
public class FilterReportsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private TextView beforeDateTextView;
    private TextView afterDateTextView;
    private Button filterButton;
    private Button cancelFilterButton;
    private View calendar;
    private DatePicker datePicker;
    private TextView dateView;
    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_reports);

        year = 2017;
        month = 10;
        day = 10;

        beforeDateTextView = (TextView) findViewById(R.id.beforeDateTextView);
        afterDateTextView = (TextView) findViewById(R.id.afterDateTextView);
        filterButton = (Button) findViewById(R.id.filterReportsButton);
        cancelFilterButton = (Button) findViewById(R.id.cancelFilterButton);

        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO grab text from TextView and filter reports
                try {
                    String beforeDate = (beforeDateTextView.getText().toString());
                    String afterDate = (afterDateTextView.getText().toString());
                    Log.d("hidden", beforeDate + " | " + afterDate);
                    StaticHolder.dateRange = new DateRangeStruct(getDate(beforeDate), getDate(afterDate));
                } catch (Exception e) {
                    Log.d("hidden", e.getLocalizedMessage());
                }
                switchBackToNavigationScreenActivity();
            }
        });

        // Filter parameter is discarded and screen switches to the Selection Screen
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

    private String getDate(String dateAndTime) {
        String[] date = dateAndTime.split(" ")[0].split("/");
        return date[2] + "/" + date[0] + "/" + date[1];
    }

    /**
     * Switches to the navigation screen after the filter or cancel button has
     * benn pushed.
     */
    private void switchBackToNavigationScreenActivity() {
        Intent switchToNavigationScreen = new Intent(this, NavigationActivity.class);
        this.startActivity(switchToNavigationScreen);
    }

}
