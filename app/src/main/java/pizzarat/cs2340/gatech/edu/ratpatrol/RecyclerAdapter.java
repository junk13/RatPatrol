package pizzarat.cs2340.gatech.edu.ratpatrol;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pizzarat.cs2340.gatech.edu.Model.RatSightingReport;

/**
 * Created by harri on 10/4/2017.
 */


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
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
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(isLongClick) {
                    Toast.makeText(context, "Long Click: " + listData.get(position), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, " " + listData.get(position), Toast.LENGTH_SHORT).show();
                }
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
