package pizzarat.cs2340.gatech.edu.ratpatrol;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Evie Brown, Harrison B.
 * <p>
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
     * Sets the listener for each report item in the Recycler View.
     *
     * @param itemClickListener
     */
    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    /**
     * Sets the on click functionality of the specified widget.
     *
     * @param v the specified widget
     */
    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition(), false);
    }

    /**
     * Sets the long click functionality fo the specified widget.
     *
     * @param v the specified widget
     * @return true when the widget has been long clicked
     */
    @Override
    public boolean onLongClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition(), true);
        return true;
    }
}