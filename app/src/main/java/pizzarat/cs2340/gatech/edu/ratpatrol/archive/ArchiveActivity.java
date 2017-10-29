package pizzarat.cs2340.gatech.edu.ratpatrol.archive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.SearchView;

import java.util.ArrayList;

import pizzarat.cs2340.gatech.edu.ratpatrol.DetailedReportViewActivity;
import pizzarat.cs2340.gatech.edu.ratpatrol.R;
import pizzarat.cs2340.gatech.edu.sqlite.SQLiteReportBroker;
import pizzarat.cs2340.gatech.edu.structure.ReportStructure;
import pizzarat.cs2340.gatech.edu.structure.StaticHolder;

/**
 * Represents the screen that displays all the rat sightings in the database.
 */
public class ArchiveActivity extends AppCompatActivity {
    public SQLiteReportBroker reportBroker = new SQLiteReportBroker();
    ArrayList<ReportStructure> posts = new ArrayList<>();
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        initRecycler();
        searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                posts = reportBroker.getReportsWithSubstring(query, getBaseContext());
                initRecycler();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length() > 2) {
                    posts = reportBroker.getReportsWithSubstring(newText, getBaseContext());
                    initRecycler();
                }
                return false;
            }
        });

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

    private void setupList() {
        Log.d("hidden","setupList()");
        try {
            //posts = reportBroker.getDateConstrainedReports("10/22/2016","10/23/2016",getBaseContext());
            //posts = reportBroker.getReportsWithSubstring("Vacant",getBaseContext());

            posts = reportBroker.reportArrayList(getBaseContext());


        } catch (Exception e){
            Log.d("hidden", "ERR MSG: " + e.getLocalizedMessage());
        }

    }

    /**
     * Switches from the list view of all the displayed rat reports to the
     * detailed view of the single report when clicked on.
     * @param report the specific report to display in detail.
     */
    public void switchToReportDetails(int report) {
        Intent switchToDetailedReports = new Intent(this, DetailedReportViewActivity.class);
        StaticHolder.report = posts.get(report);
        this.startActivity(switchToDetailedReports);
    }
}


