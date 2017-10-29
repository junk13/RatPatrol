package pizzarat.cs2340.gatech.edu.ratpatrol;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.SeekBar;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import pizzarat.cs2340.gatech.edu.sqlite.SQLiteReportBroker;
import pizzarat.cs2340.gatech.edu.structure.DateRangeStruct;
import pizzarat.cs2340.gatech.edu.structure.GraphUtilities;
import pizzarat.cs2340.gatech.edu.structure.ReportStructure;
import pizzarat.cs2340.gatech.edu.structure.StaticHolder;

/**
 * Represents the screen the graphically depicts all the rat reports based on
 * the user's desired filter.
 */
public class ReportGraphActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, OnChartValueSelectedListener {

    protected BarChart mChart;
    public SQLiteReportBroker reportBroker = new SQLiteReportBroker();
    ArrayList<ReportStructure> reports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_graph);

        mChart = (BarChart) findViewById(R.id.ratChart);
        mChart.setOnChartValueSelectedListener(this);

        mChart.setDrawBarShadow(false);
        mChart.setDrawValueAboveBar(true);

        mChart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        mChart.setMaxVisibleValueCount(60);

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
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();


        getYearData(yVals1);
        xLabel = getYearLabels();


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

    private int getSpan()
    {

        reports = reportBroker.getDateConstrainedReports(getBaseContext());
        int span = 31536000;
        if (StaticHolder.dateRange != null)
            span = StaticHolder.dateRange.getDateSpan();
        return span;
    }

    private void setData(ArrayList<BarEntry> yVals1) {

        float start = 1f;



        reports = reportBroker.getDateConstrainedReports(getBaseContext());

        BarDataSet set1;

        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "Rat Reports");
//
//            set1.setDrawIcons(false);
//
//            set1.setColors(ColorTemplate.MATERIAL_COLORS);
//
            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setBarWidth(0.9f);

            mChart.setData(data);
        }
    }

    public void getYearData(ArrayList<BarEntry> yVals) {
        Log.d("hidden", "graphing by year");
        //graph by year
        int[] years = GraphUtilities.organizeByYear(getBaseContext(), reports);
        for (int j = 0; j < years.length; j++) {
            Log.d("hidden","years["+j+"] = " + years[j]);
            //yVals1.add(new BarEntry(j, j, getResources().getDrawable(R.drawable.graphmarker)));
            yVals.add(new BarEntry(j, years[j], getResources().getDrawable(R.drawable.graphmarker)));
        }
    }

    public void getMonthData(ArrayList<BarEntry> yVals) {
        //graph by month
        int[] months = GraphUtilities.organizeByMonth(reports);
        for (int j = 0; j < months.length; j++) {
            //yVals1.add(new BarEntry(j, j, getResources().getDrawable(R.drawable.graphmarker)));
            yVals.add(new BarEntry(j, months[j], getResources().getDrawable(R.drawable.graphmarker)));
        }
    }

    public void getDayData(ArrayList<BarEntry> yVals) {
        //graph by day
        int[] days = GraphUtilities.organizeByMonth(reports);
        for (int j = 0; j < days.length; j++) {
            //yVals1.add(new BarEntry(j, j, getResources().getDrawable(R.drawable.graphmarker)));
            yVals.add(new BarEntry(j, days[j], getResources().getDrawable(R.drawable.graphmarker)));
        }
    }

    public ArrayList<String> getYearLabels() {
        final ArrayList xLabel = new ArrayList<String>();
        String startDate = "2010/01/01";
        String endDate = "2017/01/01";
        if (StaticHolder.dateRange != null)
        {
            startDate = StaticHolder.dateRange.getFrom();
            endDate = StaticHolder.dateRange.getTo();
        }
        for (int i = Integer.parseInt(startDate.substring(0,4)); i < Integer.parseInt(endDate.substring(0,4)); i++)
        {
            xLabel.add(""+i);
        }
        return xLabel;
    }

    public ArrayList<String> getMonthLabels() {
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

    public ArrayList<String> getDayLabels() {
        final ArrayList xLabel = new ArrayList<String>();
        int numOfDays = 31;
        for (int i = 0; i < numOfDays; i++)
        {
            xLabel.add(i);
        }
        return xLabel;
    }

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
