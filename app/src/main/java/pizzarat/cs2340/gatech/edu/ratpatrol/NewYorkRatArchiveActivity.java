package pizzarat.cs2340.gatech.edu.ratpatrol;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pizzarat.cs2340.gatech.edu.sqlite.SQLiteReportBroker;
import pizzarat.cs2340.gatech.edu.structure.ReportStructure;

/**
 * Represents the screen that displays all the rat sightings in New York.
 */
public class NewYorkRatArchiveActivity extends AppCompatActivity {
    // TODO change list to Rat Sightings
    private RecyclerView.LayoutManager layoutManager;
    private List<String> listData = new ArrayList<>();
    private SQLiteReportBroker reportBroker;


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
        reportBroker = new SQLiteReportBroker();
    }

    // TODO change to list of rat sighting objects
    private void setupList() {
        //ArrayList<ReportStructure> rsList = reportBroker.getListOfReports(this.getApplicationContext());

        for(int i = 0; i < 10; i++) {
            listData.add("Click me " +  i);
        }
    }

    public void switchToReportDetails() {
        Intent switchToDetailedReports = new Intent(this, DetailedRatReportViewActivity.class);
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
                /*
                if(isLongClick) {
                    Toast.makeText(context, "Long Click: " + listData.get(position), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, " " + listData.get(position), Toast.LENGTH_SHORT).show();
                }
                */
                    switchToReportDetails();
                }
            });
        }

        @Override
        public int getItemCount() {
            return listData.size();
        }
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public TextView reportDescription;

        private ItemClickListener itemClickListener;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            reportDescription = (TextView) itemView.findViewById(R.id.reportDescription);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

        }

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


