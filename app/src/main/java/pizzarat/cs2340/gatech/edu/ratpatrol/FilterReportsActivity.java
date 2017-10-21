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

/**
 * @author Harrison Banh
 *         This class is a simple acitivity to allow the user to filter the rat reports
 *         in the rat archive and rat map by specifying a String parameter.
 */
public class FilterReportsActivity extends AppCompatActivity {
    private TextView filterTextView;
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


//        calendar = (CalendarView) findViewById(R.id.calendarView);
//        int[] dates = {0,0}; //{beforedate,afterdate}
//        calendar = (CalendarView) findViewById(R.id.calendarView); // get the reference of CalendarView
//        public void onSelectedDayChange(CalendarView calendar, int arg1, int arg2, int arg3) {
//            int d = arg1 + arg2 + arg3;
//            if (d > dates[0]) {
//                dates[1] = d;
//            }
//            else {
//                int temp = dates[0];
//                dates[0] = d;
//                dates[1] = temp;
//            }
//        }

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
