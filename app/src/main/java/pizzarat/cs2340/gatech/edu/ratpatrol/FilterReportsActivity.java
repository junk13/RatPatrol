package pizzarat.cs2340.gatech.edu.ratpatrol;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import pizzarat.cs2340.gatech.edu.structure.DateRangeStruct;
import pizzarat.cs2340.gatech.edu.structure.StaticHolder;

/**
 * @author Harrison Banh
 *         This class is a simple acitivity to allow the user to filter the rat reports
 *         in the rat archive and rat map by specifying a String parameter.
 */
public class FilterReportsActivity extends AppCompatActivity {
    private TextView beforeDateTextView;
    private TextView afterDateTextView;
    private Button filterButton;
    private Button cancelFilterButton;
    private View calendar;
    private DatePicker datePicker;
    private TextView dateView;
    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_reports);

        year = 2017;
        month = 10;
        day = 10;

        beforeDateTextView = (TextView) findViewById(R.id.beforeDateTextView);
        afterDateTextView = (TextView) findViewById(R.id.afterDateTextView);
        filterButton = (Button) findViewById(R.id.filterReportsButton);
        cancelFilterButton = (Button) findViewById(R.id.cancelFilterButton);

        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO grab text from TextView and filter reports
                String beforeDate = ((String) beforeDateTextView.getText());
                String afterDate = ((String) afterDateTextView.getText());
                beforeDate = getDate(beforeDate);
                afterDate = getDate(afterDate);
                StaticHolder.dateRange = new DateRangeStruct(beforeDate, afterDate);
                switchBackToSelectionScreen();
            }
        });

        // Filter parameter is discarded and screen switches to the Selection Screen
        cancelFilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beforeDateTextView.setText("");
                afterDateTextView.setText("");
                switchBackToSelectionScreen();
            }
        });
}



    private String getDate(String dateAndTime) {
        String[] date = dateAndTime.split(" ")[0].split("/");
        return date[2] + "/" + date[1] + "/" + date[0];
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
