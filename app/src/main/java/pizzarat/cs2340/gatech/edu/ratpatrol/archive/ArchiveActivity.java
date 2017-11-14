package pizzarat.cs2340.gatech.edu.ratpatrol.archive;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

import pizzarat.cs2340.gatech.edu.ratpatrol.CreateReportActivity;
import pizzarat.cs2340.gatech.edu.ratpatrol.DetailedReportViewActivity;
import pizzarat.cs2340.gatech.edu.ratpatrol.FilterReportsActivity;
import pizzarat.cs2340.gatech.edu.ratpatrol.MapActivity;
import pizzarat.cs2340.gatech.edu.ratpatrol.R;
import pizzarat.cs2340.gatech.edu.ratpatrol.ReportGraphActivity;
import pizzarat.cs2340.gatech.edu.ratpatrol.WelcomeActivity;
import pizzarat.cs2340.gatech.edu.sqlite.SQLiteReportBroker;
import pizzarat.cs2340.gatech.edu.structure.ReportStructure;
import pizzarat.cs2340.gatech.edu.structure.StaticHolder;

/**
 * Represents the screen that displays all the rat sightings in the database.
 */
public class ArchiveActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private final SQLiteReportBroker reportBroker = new SQLiteReportBroker();
    private ArrayList<ReportStructure> posts = new ArrayList<>();
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Navigation drawer creation
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Rat Archive");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView)
                findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Archive recycler view creation
        posts = reportBroker.reportArrayList(getBaseContext());
        initRecycler();
        SearchView searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                posts = reportBroker.getReportsWithSubstring(query,
                        getBaseContext());
                initRecycler();
                return false;
            }


            /**
             * Recognizes if the user has typed some text to search through the
             * rat report archive. The inputted text must be of length 3 or
             * greater.
             * @param newText user's desired text filter
             * @return true if some text was entered
             */
            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length() <= 2) {
                    return true;
                }

                ArrayList<ReportStructure> temp =
                        reportBroker.getReportsWithSubstring(newText,
                                getBaseContext());
                if (newText.length() > 2 && posts == null) {

                    return false;
                }
                if (newText.length() > 2) {
                    posts = temp;
                    initRecycler();
                }

                return true;
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
            Toast message = Toast.makeText(getApplicationContext(),
                    "You are already on this screen", Toast.LENGTH_LONG);
            message.show();
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
    private void switchBackToWelcomeActivity() {
        Intent switchToWelcomeActivity =
                new Intent(this, WelcomeActivity.class);
        this.startActivity(switchToWelcomeActivity);
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
        Intent switchToMapActivity = new Intent(this, MapActivity.class);
        this.startActivity(switchToMapActivity);
    }

    /**
     * Switches to the FilterReportsActivity.
     */
    private void switchToFilterReportsScreen() {
        Intent switchToFilterReportsActivity =
                new Intent(this, FilterReportsActivity.class);
        this.startActivity(switchToFilterReportsActivity);
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
        Toast message = Toast.makeText(getBaseContext(),
                "To filter/edit graph, use the filter "
                        + "button on the Navigation Screen.", Toast.LENGTH_LONG);
        message.show();
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

    /**
     * initializes the list of rat reports in the rat archive
     */
    private void initRecycler() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        setupList();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerAdapter adapter = new RecyclerAdapter(this, posts, this);
        recyclerView.setAdapter(adapter);
    }

    //populate posts
    private void setupList() {
        Log.d("hidden", "setupList()");
        try {
            if (posts == null) {
                posts = reportBroker.reportArrayList(getBaseContext());
            }
        } catch (Exception e) {
            Log.d("hidden", "ERR MSG: " + e.getLocalizedMessage());
        }

    }

    /**
     * Switches from the list view of all the displayed rat reports to the
     * detailed view of the single report when clicked on.
     *
     * @param report the specific report to display in detail.
     */
    public void switchToReportDetails(int report) {
        Intent switchToDetailedReports =
                new Intent(this, DetailedReportViewActivity.class);
        StaticHolder.report = posts.get(report);
        this.startActivity(switchToDetailedReports);
    }
}


