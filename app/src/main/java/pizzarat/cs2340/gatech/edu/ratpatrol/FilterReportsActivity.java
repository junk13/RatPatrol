package pizzarat.cs2340.gatech.edu.ratpatrol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO grab text from TextView and filter reports
                String filter = (String) filterTextView.getText();
                switchBackToSelectionScreen();
            }
        });

        // Filter parameter is discarded and screen switches to the Selection Screen
        cancelFilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterTextView.setText("");
                switchBackToSelectionScreen();
            }
        });
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
