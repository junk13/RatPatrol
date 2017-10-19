package pizzarat.cs2340.gatech.edu.sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import pizzarat.cs2340.gatech.edu.ratpatrol.R;
import pizzarat.cs2340.gatech.edu.ratpatrol.SelectionScreenActivity;

/**
 * @author Harrison Banh
 *         This class is a simple acitivity to allow the user to filter the rat reports
 *         in the rat archive and rat map by specifying a String parameter.
 */
public class FilterReportsActivity extends AppCompatActivity {
    private TextView filterTextView;
    private Button filterButton;
    private Button cancelFilterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_reports);

        filterTextView = (TextView) findViewById(R.id.filterTextView);
        filterButton = (Button) findViewById(R.id.filterReportsButton);
        cancelFilterButton = (Button) findViewById(R.id.cancelFilterButton);


    }

    /**
     * Switches to the selection screen after the filter or cancel button has
     * benn pushed.
     */
    private void switchBackToSelectionScreen() {
        Intent switchToSelectionScreen = new Intent(this, SelectionScreenActivity.class);
        this.startActivity(switchToSelectionScreen);
    }

}
