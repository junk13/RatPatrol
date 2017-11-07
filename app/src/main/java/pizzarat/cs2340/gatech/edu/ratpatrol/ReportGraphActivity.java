package pizzarat.cs2340.gatech.edu.ratpatrol;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

import pizzarat.cs2340.gatech.edu.ratpatrol.archive.ArchiveActivity;
import pizzarat.cs2340.gatech.edu.sqlite.SQLiteReportBroker;
import pizzarat.cs2340.gatech.edu.structure.GraphUtilities;
import pizzarat.cs2340.gatech.edu.structure.ReportStructure;

/**
 * Represents the screen the graphically depicts all the rat reports based on
 * the user's desired filter.
 */
public class ReportGraphActivity extends AppCompatActivity
        implements SeekBar.OnSeekBarChangeListener, OnChartValueSelectedListener,
        NavigationView.OnNavigationItemSelectedListener {

    final int MAX_ENTRIES = 60;
    private final SQLiteReportBroker reportBroker = new SQLiteReportBroker();
    private BarChart mChart;
    private ArrayList<ReportStructure> reports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_graph);

        // Navigation drawer creation
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Report Creation");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mChart = (BarChart) findViewById(R.id.ratChart);
        mChart.setOnChartValueSelectedListener(this);

        mChart.setDrawBarShadow(false);
        mChart.setDrawValueAboveBar(true);

        mChart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        mChart.setMaxVisibleValueCount(MAX_ENTRIES);

        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        mChart.setDrawGridBackground(false);


        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f); // only intervals of 1 day
        xAxis.setLabelCount(12);

        //int span = getSpan();
        reports = reportBroker.getDateConstrainedReports(getBaseContext());
        final ArrayList<String> xLabel;
        ArrayList<BarEntry> yVals1 = new ArrayList<>();


        getMonthData(yVals1);
        xLabel = getMonthLabels();

//TODO: not delete this
//        if (span >= 31536000) //bigger than year
//        {
//            getYearData(yVals1);
//            xLabel = getYearLabels();
//
//        } else if (span >= 2678400) //bigger than month
//        {
//            getMonthData(yVals1);
//            xLabel = getMonthLabels();
//        } else {
//            getDayData(yVals1);
//            xLabel = getDayLabels();
//        }

        xAxis.setValueFormatter(new IndexAxisValueFormatter(xLabel));

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setLabelCount(8, false);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setLabelCount(8, false);
        rightAxis.setSpaceTop(15f);
        rightAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        setData(yVals1);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_create_report) {
            switchToCreateReportActivity();
        } else if (id == R.id.nav_rat_archive) {
            switchToArchiveActivity();
        } else if (id == R.id.nav_filter) {
            switchToFilterReportsScreen();
        } else if (id == R.id.nav_sightings_map) {
            switchToMapActivity();
        } else if (id == R.id.nav_report_graphs) {
            Toast message = Toast.makeText(getApplicationContext(),
                    "You are already on this screen", Toast.LENGTH_LONG);
            message.show();
        } else if (id == R.id.nav_logout) {
            logout();
        } else if (id == R.id.nav_share) {
            shareOrSendReport("Share");
        } else if (id == R.id.nav_send) {
            shareOrSendReport("Send");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Switches to the WelcomeActivity from the Navigation Screen.
     */
    private void switchBackToWelcomeActivity() {
        Intent switchToWelcomeActivity = new Intent(this, WelcomeActivity.class);
        this.startActivity(switchToWelcomeActivity);
    }

    /**
     * Switches to the ArchiveActivity from the Navigation Screen.
     */
    private void switchToArchiveActivity() {
        Intent switchToArchiveActivity = new Intent(this, ArchiveActivity.class);
        this.startActivity(switchToArchiveActivity);
    }

    /**
     * Switches to the CreateReportActivity.
     */
    private void switchToCreateReportActivity() {
        Intent switchToCreateReportActivity = new Intent(this, CreateReportActivity.class);
        this.startActivity(switchToCreateReportActivity);
    }

    /**
     * Switches to the MapActivity.
     */
    private void switchToMapActivity() {
        Intent switchToMapActivity = new Intent(this, MapActivity.class);
        this.startActivity(switchToMapActivity);
    }

    /**
     * Switches to the FilterReportsActivity.
     */
    public void switchToFilterReportsScreen() {
        Intent switchToFilterReportsActivity = new Intent(this, FilterReportsActivity.class);
        this.startActivity(switchToFilterReportsActivity);
    }

    /**
     * Closes the Navigation Screen thus "logging out" the user
     */
    private void logout() {
        switchBackToWelcomeActivity();
    }

// --Commented out by Inspection START (11/6/2017 1:48 AM):
//    /**
//     * Switches to the ReportGraphActivity.
//     */
//    public void switchToReportGraphScreen() {
//        Intent switchToReportGraphScreenActivity = new Intent(this, ReportGraphActivity.class);
//        startActivity(switchToReportGraphScreenActivity);
//        Toast.makeText(getBaseContext(), "To filter/edit graph, use the filter "
//                + "button on the Navigation Screen.", Toast.LENGTH_LONG).show();
//    }
// --Commented out by Inspection STOP (11/6/2017 1:48 AM)

    /**
     * Opens a bunch of apps at the bottom of the screen allowing the user to
     * share something about the Rat Patrol app using different apps.
     *
     * @param widget the name of widget clicked
     */
    private void shareOrSendReport(String widget) {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        String shareBody = "Your body here";
        String shareSub = "Your subject here";
        share.putExtra(Intent.EXTRA_SUBJECT, shareBody);
        share.putExtra(Intent.EXTRA_TEXT, shareSub);
        startActivity(Intent.createChooser(share, widget + " using"));

    }

// --Commented out by Inspection START (11/6/2017 1:48 AM):
//    /**
//     * Gets span of time between filter dates
//     * @return span of time
//     */
//    private int getSpan()
//    {
//
//        reports = reportBroker.getDateConstrainedReports(getBaseContext());
//        int span = 31536000;
//        if (StaticHolder.dateRange != null)
//            span = StaticHolder.dateRange.getDateSpan();
//        return span;
//    }
// --Commented out by Inspection STOP (11/6/2017 1:48 AM)

    /**
     * Sets bar chart data
     * @param yVals y values of bar chart
     */
    private void setData(ArrayList<BarEntry> yVals) {




        reports = reportBroker.getDateConstrainedReports(getBaseContext());

        BarDataSet set1;

        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals, "Rat Reports");
//
//            set1.setDrawIcons(false);
//
//            set1.setColors(ColorTemplate.MATERIAL_COLORS);
//
            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setBarWidth(0.9f);

            mChart.setData(data);
        }
    }

// --Commented out by Inspection START (11/6/2017 1:48 AM):
//    /**
//     * Sets y values to number of reports in year
//     * @param yVals y values of bar chart
//     */
//    public void getYearData(ArrayList<BarEntry> yVals) {
//        Log.d("hidden", "graphing by year");
//        //graph by year
//        int[] years = GraphUtilities.organizeByYear(getBaseContext(), reports);
//        for (int j = 0; j < years.length; j++) {
//            Log.d("hidden","years["+j+"] = " + years[j]);
//            //yVals1.add(new BarEntry(j, j, getResources().getDrawable(R.drawable.graphmarker)));
//            yVals.add(new BarEntry(j, years[j], getResources().getDrawable(R.drawable.graphmarker)));
//        }
//    }
// --Commented out by Inspection STOP (11/6/2017 1:48 AM)

    /**
     * Sets y values to number of reports in month
     * @param yVals y values of bar chart
     */
    private void getMonthData(ArrayList<BarEntry> yVals) {
        //graph by month
        int[] months = GraphUtilities.organizeByMonth(reports);
        for (int j = 0; j < months.length; j++) {
            //yVals1.add(new BarEntry(j, j, getResources().getDrawable(R.drawable.graphmarker)));
            yVals.add(new BarEntry(j, months[j], getResources().getDrawable(R.drawable.graphmarker)));
        }
    }

// --Commented out by Inspection START (11/6/2017 1:48 AM):
//    /**
//     * Sets y values to number of reports in day
//     * @param yVals y values of bar chart
//     */
//    public void getDayData(ArrayList<BarEntry> yVals) {
//        //graph by day
//        int[] days = GraphUtilities.organizeByMonth(reports);
//        for (int j = 0; j < days.length; j++) {
//            //yVals1.add(new BarEntry(j, j, getResources().getDrawable(R.drawable.graphmarker)));
//            yVals.add(new BarEntry(j, days[j], getResources().getDrawable(R.drawable.graphmarker)));
//        }
//    }
// --Commented out by Inspection STOP (11/6/2017 1:48 AM)

// --Commented out by Inspection START (11/6/2017 1:48 AM):
//    /**
//     * Gets year labels
//     */
//    public ArrayList<String> getYearLabels() {
//        final ArrayList xLabel = new ArrayList<String>();
//        String startDate = "2010/01/01";
//        String endDate = "2017/01/01";
//        if (StaticHolder.dateRange != null)
//        {
//            startDate = StaticHolder.dateRange.getFrom();
//            endDate = StaticHolder.dateRange.getTo();
//        }
//        for (int i = Integer.parseInt(startDate.substring(0,4)); i < Integer.parseInt(endDate.substring(0,4)); i++)
//        {
//            xLabel.add(""+i);
//        }
//        return xLabel;
//    }
// --Commented out by Inspection STOP (11/6/2017 1:48 AM)

    /**
     * Gets month labels
     */
    private ArrayList<String> getMonthLabels() {
        final ArrayList xLabel = new ArrayList<String>();
        xLabel.add("Jan");
        xLabel.add("Feb");
        xLabel.add("Mar");
        xLabel.add("Apr");
        xLabel.add("May");
        xLabel.add("Jun");
        xLabel.add("Jul");
        xLabel.add("Aug");
        xLabel.add("Sep");
        xLabel.add("Oct");
        xLabel.add("Nov");
        xLabel.add("Dec");
        return xLabel;
    }

// --Commented out by Inspection START (11/6/2017 1:48 AM):
//    /**
//     * Gets day labels
//     */
//    public ArrayList<String> getDayLabels() {
//        final ArrayList xLabel = new ArrayList<String>();
//        int numOfDays = 31;
//        for (int i = 0; i < numOfDays; i++)
//        {
//            xLabel.add(i);
//        }
//        return xLabel;
//    }
// --Commented out by Inspection STOP (11/6/2017 1:48 AM)

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}
