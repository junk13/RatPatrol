package pizzarat.cs2340.gatech.edu.ratpatrol;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

        DatePicker dp = new DatePicker(getBaseContext());
        dp.init(2017, 10, 10, DatePicker.OnDateChangedListener);
    }


//    public void popupwindow() {
//        try {
//            // We need to get the instance of the LayoutInflater
//
//            LayoutInflater inflater = (LayoutInflater) FilterReportsActivity.this
//                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//            View layout = inflater.inflate(R.layout.popupcalendar,
//                    (ViewGroup) findViewById(R.id.popcal));
//
//            pwindo = new PopupWindow(layout, 1200, 1500, true);
//            pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);
//
//            calendar = (CalendarView) layout.findViewById(R.id.calendar);
//
//            // sets whether to show the week number.
//            calendar.setShowWeekNumber(false);
//
//            // sets the first day of week according to Calendar.
//            // here we set Monday as the first day of the Calendar
//            calendar.setFirstDayOfWeek(2);
//
//            //The background color for the selected week.
//            calendar.setSelectedWeekBackgroundColor(getResources().getColor(R.color.green));
//
//            //sets the color for the dates of an unfocused month.
//            calendar.setUnfocusedMonthDateColor(getResources().getColor(R.color.transparent));
//
//            //sets the color for the separator line between weeks.
//            calendar.setWeekSeparatorLineColor(getResources().getColor(R.color.transparent));
//
//            //sets the color for the vertical bar shown at the beginning and at the end of the selected date.
//            calendar.setSelectedDateVerticalBar(R.color.darkgreen);
//
//            //sets the listener to be notified upon selected date change.
//            calendar.setOnDateChangeListener(new OnDateChangeListener() {
//                //show the selected date as a toast
//                @Override
//                public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
//                    Toast.makeText(getApplicationContext(), day + "/" + month + "/" + year, Toast.LENGTH_LONG).show();
//                }
//            });
//
//            btnSubmit = (Button) layout.findViewById(R.id.button2);
//            //btnSubmit.setOnClickListener(submit_button_click_listener);
//
//            btnClosePopup = (Button) layout.findViewById(R.id.button1);
//            btnClosePopup.setOnClickListener(cancel_button_click_listener);}
//
//    } catch (Exception e) {
//        e.printStackTrace();
//    }
//}

    /**
     * Switches to the selection screen after the filter or cancel button has
     * benn pushed.
     */
    private void switchBackToSelectionScreen() {
        Intent switchToSelectionScreen = new Intent(this, SelectionScreenActivity.class);
        this.startActivity(switchToSelectionScreen);
    }

}
