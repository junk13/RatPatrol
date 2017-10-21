package pizzarat.cs2340.gatech.edu.ratpatrol;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pizzarat.cs2340.gatech.edu.sqlite.SQLiteReportBroker;
import pizzarat.cs2340.gatech.edu.structure.ReportStructure;
import pizzarat.cs2340.gatech.edu.structure.StaticHolder;

/**
 * Represents the screen that displays all the rat sightings in New York.
 */
public class NewYorkRatArchiveActivity extends AppCompatActivity {
    public SQLiteReportBroker reportBroker = new SQLiteReportBroker();
    ArrayList<ReportStructure> posts = new ArrayList<>();
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
        Log.d("hidden","setupList()");
        try {
            //posts = reportBroker.getDateConstrainedReports("10/22/2016","10/23/2016",getBaseContext());
            //posts = reportBroker.getReportsWithSubstring("Vacant",getBaseContext());
            posts = reportBroker.reportArrayList(getBaseContext());
        } catch (Exception e){
            Log.d("hidden", "ERR MSG: " + e.getLocalizedMessage());
        }
        for(int i = 0;  i < posts.size(); i++) {
            String newElement = posts.get(i).getBuildingType();
            listData.add(newElement);
        }
    }

    /**
     * Switches from the list view of all the displayed rat reports to the
     * detailed view of the single report when clicked on.
     * @param report the specific report to display in detail.
     */
    public void switchToReportDetails(int report) {
        Intent switchToDetailedReports = new Intent(this, DetailedRatReportViewActivity.class);
        StaticHolder.data = posts.get(report);
        this.startActivity(switchToDetailedReports);
    }

    /**
     * Private inner class used to set the behind the scenes functionality
     * in the Recycler View. This is necessary to switch to the details view.
     */
    class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
        //TODO change list to rat reports
        private List<String> listData = new ArrayList<>();
        private Context context;

        public RecyclerAdapter(List<String> listData, Context context) {
            this.listData = listData;
            this.context = context;
        }

        @Override
        public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View itemView = inflater.inflate(R.layout.rat_report_item, parent, false);

            return new RecyclerViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(RecyclerViewHolder holder, int position) {
            holder.reportDescription.setText(listData.get(position));

            holder.setItemClickListener(new ItemClickListener() {
                // TODO change this to switch to the details screen
                @Override
                public void onClick(View view, int position, boolean isLongClick) {

                    switchToReportDetails(position);
                }
            });
        }

        @Override
        public int getItemCount() {
            return listData.size();
        }
    }

    /**
     * Custom class created for RecyclerView to show the all the rat reports.
     */
    class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public TextView reportDescription;

        private ItemClickListener itemClickListener;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            reportDescription = (TextView) itemView.findViewById(R.id.reportDescription);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

        }

        /**
         * Sets the listener for each data item in the Recycler View.
         * @param itemClickListener
         */
        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }


        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(), false);
        }

        @Override
        public boolean onLongClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(), true);
            return true;
        }
    }

}


