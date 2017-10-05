package pizzarat.cs2340.gatech.edu.ratpatrol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the screen that displays all the rat sightings in New York.
 */
public class NewYorkRatArchiveActivity extends AppCompatActivity {
    // TODO change list to Rat Sightings
    private RecyclerView.LayoutManager layoutManager;
    private List<String> listData = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_york_rat_archive);

        setupList();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerAdapter adapter = new RecyclerAdapter(listData, this);
        recyclerView.setAdapter(adapter);
    }

    // TODO change to list of rat sighting objects
    private void setupList() {
        for(int i = 1; i < 10; i++) {
            listData.add("Click me " +  i);
        }
    }
}
