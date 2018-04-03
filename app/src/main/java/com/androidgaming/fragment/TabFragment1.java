package com.androidgaming.fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidgaming.R;
import com.androidgaming.graph.MyAxisValueFormatter;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
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
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;

/**
 * Created by Manish Patidar on 23-03-2018.
 */

public class TabFragment1 extends Fragment implements OnChartValueSelectedListener {

    protected BarChart mChart;
    private View viewForTheScreen;
    private Typeface mTfRegular;
    private Typeface mTfLight;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        viewForTheScreen = inflater.inflate(R.layout.tab_fragment_1, container, false);

        mTfRegular = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Regular.ttf");
        mTfLight = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Light.ttf");

        mChart = (BarChart) viewForTheScreen.findViewById(R.id.chart1);
        mChart.setOnChartValueSelectedListener(this);


        mChart.animateXY(2000, 2000);
        //mChart.animateX(3000);

        mChart.setDrawBarShadow(false);

        mChart.getDescription().setEnabled(false);

        mChart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be drawn
        mChart.setMaxVisibleValueCount(60);

        mChart.getLegend().setEnabled(false);

        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        mChart.setFitBars(true);

        mChart.setDoubleTapToZoomEnabled(false);

        mChart.setPinchZoom(false);

        // enable scaling and dragging
        mChart.setDragEnabled(false);

        mChart.setScaleEnabled(false);

        mChart.setDrawGridBackground(false);

        mChart.setMotionEventSplittingEnabled(false);

        // mChart.setDrawYLabels(false);

        mChart.getLegend().setForm(Legend.LegendForm.NONE);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(mTfLight);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f); // only intervals of 1 day
        xAxis.setLabelCount(6);
        xAxis.setSpaceMin(0.2f);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{"10","20","30","40","50"}));


        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setTypeface(mTfLight);
        leftAxis.setLabelCount(5, false);
        leftAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{"10","20","30","40","50"}));

        IAxisValueFormatter custom = new MyAxisValueFormatter();
        leftAxis.setValueFormatter(custom);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setDrawZeroLine(false);
        leftAxis.setAxisMinimum(0); // this replaces setStartAtZero(true)


        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setTypeface(mTfLight);
        rightAxis.setLabelCount(0, false);
        rightAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{"10","20","30","40","50"}));
        rightAxis.setSpaceTop(0f);
        rightAxis.setDrawZeroLine(false);
        rightAxis.setAxisMinimum(0); // this replaces setStartAtZero(true)


        mChart.getAxisRight().removeAllLimitLines();

        mChart.getAxisRight().getLimitLines().clear();

        mChart.getAxisRight().setDrawAxisLine(false);

        mChart.getAxisRight().setDrawTopYLabelEntry(false);

        mChart.getAxisRight().setDrawLabels(false);

        mChart.setDrawValueAboveBar(false);

//        Legend l = mChart.getLegend();
//        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
//        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
//        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
//        l.setDrawInside(false);
//        l.setForm(LegendForm.SQUARE);
//        l.setFormSize(9f);
//        l.setTextSize(11f);
//        l.setXEntrySpace(4f);

        // XYMarkerView mv = new XYMarkerView(this, xAxisFormatter);

        // mv.setChartView(mChart); // For bounds control

        //  mChart.setMarker(mv); // Set the marker to the chart

        setData(4, 4);

        return  viewForTheScreen;
    }









    private void setData(int count, float range) {


        ArrayList<BarEntry> yValues = new ArrayList<BarEntry>();


        yValues.add(new BarEntry(0, 10));
        yValues.add(new BarEntry(1, 20));
        yValues.add(new BarEntry(2, 30));
        yValues.add(new BarEntry(3, 40));
        yValues.add(new BarEntry(4, 50));



        BarDataSet set1;

        if (mChart.getData() != null && mChart.getData().getDataSetCount() > 0) {

            set1 = (BarDataSet) mChart.getData().getDataSetByIndex(0);
            set1.setValues(yValues);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();


        } else {

            set1 = new BarDataSet(yValues, "");
            set1.setColors(Color.parseColor("#7079C9"));

            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setValueTypeface(mTfLight);
            data.setBarWidth(0.9f);
            data.setDrawValues(false);

            mChart.setData(data);


        }
    }


    protected RectF mOnValueSelectedRectF = new RectF();

    @SuppressLint("NewApi")
    @Override
    public void onValueSelected(Entry e, Highlight h) {

        if (e == null)
            return;

        RectF bounds = mOnValueSelectedRectF;
        mChart.getBarBounds((BarEntry) e, bounds);
        MPPointF position = mChart.getPosition(e, YAxis.AxisDependency.LEFT);

        Log.i("bounds", bounds.toString());
        Log.i("position", position.toString());

        Log.i("x-index", "low: " + mChart.getLowestVisibleX() + ", high: " + mChart.getHighestVisibleX());

        MPPointF.recycleInstance(position);
    }

    @Override
    public void onNothingSelected() {
    }
}
