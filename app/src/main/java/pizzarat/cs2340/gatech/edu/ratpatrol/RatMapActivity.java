package pizzarat.cs2340.gatech.edu.ratpatrol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Represents the screen where the user can pick different fields about a rat
 * sighting report to generate a Google Maps of those reports that fix their
 * specified fields.
 */
public class RatMapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rat_map);
    }
}
