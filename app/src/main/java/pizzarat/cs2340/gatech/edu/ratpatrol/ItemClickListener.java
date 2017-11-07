package pizzarat.cs2340.gatech.edu.ratpatrol;

import android.view.View;

/**
 * Created by Harrison Banh on 10/4/2017.
 * Interface for RecyclerView click listener.
 */
public interface ItemClickListener {
    /**
     * Creates the onclick logic for each item within the RecyclerView
     *
     * @param view        the Recycler View containing this item
     * @param position    the item clicked within the RecyclerView
     * @param isLongClick determines if the item was long clicked or not
     */
    void onClick(View view, int position, boolean isLongClick);
}
