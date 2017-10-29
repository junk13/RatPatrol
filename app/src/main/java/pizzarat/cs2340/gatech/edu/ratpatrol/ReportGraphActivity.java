package pizzarat.cs2340.gatech.edu.ratpatrol;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import pizzarat.cs2340.gatech.edu.sqlite.SQLiteReportBroker;
import pizzarat.cs2340.gatech.edu.structure.ReportStructure;

/**
 * Represents the screen the graphically depicts all the rat reports based on
 * the user's desired filter.
 */
public class ReportGraphActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, OnChartValueSelectedListener {

    protected BarChart mChart;
    public SQLiteReportBroker reportBroker = new SQLiteReportBroker();
    ArrayList<ReportStructure> reports;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
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
        xAxis.setLabelCount(7);
        xAxis.setTextColor(Color.BLACK);

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

        setData(12, 50);

    }



    private void setData(int count, float range) {

        float start = 1f;
        reports = reportBroker.reportArrayList(getBaseContext());
        int[] months = new int[12];

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

        for (int i = (int) start; i < 12 && i < reports.size(); i++) {
            float mult = (range + 1);
            float val = (float) (Math.random() * mult);
            ReportStructure rs = reports.get(i);
            String[] reportDate = rs.getDate().split("/");
            String year = reportDate[0];
            String month = reportDate[1];
            String day = reportDate[2];

            months[Integer.parseInt(month)-1]++;
        }

        for (int j = 0; j < 12; j++)
        {
            //yVals1.add(new BarEntry(j, j, getResources().getDrawable(R.drawable.graphmarker)));
            yVals1.add(new BarEntry(j, months[j], getResources().getDrawable(R.drawable.graphmarker)));
        }

        BarDataSet set1;

        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "The year 2017");

            set1.setDrawIcons(false);

            set1.setColors(ColorTemplate.MATERIAL_COLORS);

            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setBarWidth(0.9f);

            mChart.setData(data);
        }
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
