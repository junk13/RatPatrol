package pizzarat.cs2340.gatech.edu.ratpatrol.archive;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import pizzarat.cs2340.gatech.edu.ratpatrol.ItemClickListener;
import pizzarat.cs2340.gatech.edu.ratpatrol.R;
import pizzarat.cs2340.gatech.edu.structure.ReportStructure;

/**
 * Private inner class used to set the behind the scenes functionality
 * in the Recycler View. This is necessary to switch to the details view.
 */
class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    private final ArchiveActivity archiveActivity;
    // --Commented out by Inspection (11/6/2017 1:49 AM):private final Context context;
    private List<ReportStructure> listData = new ArrayList<>();

    public RecyclerAdapter(ArchiveActivity archiveActivity, List<ReportStructure> listData, Context context) {
        this.archiveActivity = archiveActivity;
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
        holder.reportDescription.setText(listData.get(position).getAddress());
        //switches to report details
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {

                archiveActivity.switchToReportDetails(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
