package pizzarat.cs2340.gatech.edu.ratpatrol;

import android.view.View;

/**
 * Created by Harrison Banh on 10/4/2017.
 * Interface for RecyclerView click listener.
 */

public interface ItemClickListener {
    void onClick(View view, int position, boolean isLongClick);
}
