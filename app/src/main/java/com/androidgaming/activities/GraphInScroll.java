package com.androidgaming.activities;

import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;

import com.androidgaming.R;
import com.androidgaming.graph.MyAxisValueFormatter;
import com.androidgaming.helper.Constants;
import com.androidgaming.helper.CustomPreference;
import com.androidgaming.helper.PreferenceKeys;
import com.androidgaming.helper.ProgressHUD;
import com.androidgaming.utility.HelperMethods;
import com.androidgaming.webservice.ApiFactory;
import com.androidgaming.webservice.WebServiceInterface;
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
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.MPPointF;

import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Manish Patidar on 03-04-2018.
 */
public class GraphInScroll extends AppCompatActivity implements OnChartValueSelectedListener {

    private BarChart groupBarChartThree;
    float barWidth;
    float barSpace;
    float groupSpace;
    protected BarChart mChartOne;
    protected BarChart mChartThree;
    private Typeface mTfRegular;
    private Typeface mTfLight;
    private NestedScrollView nestedScrollView;
    private CardView viewOneInScroll;
    private CardView viewTwoInScroll;
    private CardView viewThreeInScroll;
    private CardView viewFourInScroll;
    private CardView viewFiveInScroll;
    private ProgressHUD progressHUD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_in_scroll);

        progressHUD = ProgressHUD.init(GraphInScroll.this);
        callAPI();
        forTabs();
        forScrollView();
        forOne();
        forThree();
        forFive();

    }

    ArrayList<String> chart1Data;
    ArrayList<String> chart1Categories;


    ArrayList<String> chart2S1Data;
    ArrayList<String> chart2S2Data;
    ArrayList<String> chart2S3Data;
    ArrayList<String> chart2Categories;


    ArrayList<String> chart3Data;
    ArrayList<String> chart3Categories;

    private void callAPI() {
        progressHUD.show();
        WebServiceInterface api = ApiFactory.getRetrofitClientWithHeader().create(WebServiceInterface.class);
        Call<ResponseBody> call = null;
        call = api.dashBoard(CustomPreference.getInstance(GraphInScroll.this).getValue(PreferenceKeys.API_TOKEN));
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject responseJson = new JSONObject(HelperMethods.responseYesSuccessful(response));
                        progressHUD.hide();

                        chart1Data = new ArrayList<>();
                        chart1Categories = new ArrayList<>();

                        for (int i = 0; i < responseJson.getJSONObject("data").getJSONObject("chart1").getJSONArray("data").length(); i++) {

                            chart1Data.add(responseJson.getJSONObject("data").getJSONObject("chart1").getJSONArray("data").getString(i));


                        }

                        for (int i = 0; i < responseJson.getJSONObject("data").getJSONObject("chart1").getJSONArray("categories").length(); i++) {

                            chart1Categories.add(responseJson.getJSONObject("data").getJSONObject("chart1").getJSONArray("categories").getString(i));

                        }


                        chart2S1Data = new ArrayList<>();
                        chart2S2Data = new ArrayList<>();
                        chart2S3Data = new ArrayList<>();

                        for (int i = 0; i < responseJson.getJSONObject("data").getJSONObject("chart2").getJSONObject("data").getJSONArray("s1").length(); i++) {

                            chart2S1Data.add(responseJson.getJSONObject("data").getJSONObject("chart2").getJSONObject("data").getJSONArray("s1").getString(i));


                        }


                        for (int i = 0; i < responseJson.getJSONObject("data").getJSONObject("chart2").getJSONObject("data").getJSONArray("s2").length(); i++) {

                            chart2S2Data.add(responseJson.getJSONObject("data").getJSONObject("chart2").getJSONObject("data").getJSONArray("s2").getString(i));


                        }


                        for (int i = 0; i < responseJson.getJSONObject("data").getJSONObject("chart2").getJSONObject("data").getJSONArray("s3").length(); i++) {

                            chart2S3Data.add(responseJson.getJSONObject("data").getJSONObject("chart2").getJSONObject("data").getJSONArray("s3").getString(i));


                        }



                            for (int i = 0; i < responseJson.getJSONObject("data").getJSONObject("chart2").getJSONArray("categories").length(); i++) {

                                chart2Categories.add(responseJson.getJSONObject("data").getJSONObject("chart2").getJSONArray("categories").getString(i));


                        }



                        chart3Data = new ArrayList<>();
                        chart3Categories = new ArrayList<>();

                        for (int i = 0; i < responseJson.getJSONObject("data").getJSONObject("chart3").getJSONObject("data").getJSONArray("data").length(); i++) {

                            chart3Data.add(responseJson.getJSONObject("data").getJSONObject("chart3").getJSONObject("data").getJSONArray("data").getString(i));


                        }

                        for (int i = 0; i < responseJson.getJSONObject("data").getJSONObject("chart3").getJSONArray("categories").length(); i++) {


                            chart3Categories.add(responseJson.getJSONObject("data").getJSONObject("chart3").getJSONArray("categories").getString(i));

                        }



                        Log.e("TAG", "" + chart1Data.size());
                        Log.e("TAG", "" + chart1Categories.size());

                        Log.e("TAG", "" + chart2S1Data.size());
                        Log.e("TAG", "" + chart2S2Data.size());
                        Log.e("TAG", "" + chart2S3Data.size());


                        Log.e("TAG", "" + chart3Data.size());
                        Log.e("TAG", "" + chart3Categories.size());

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }

    private void forScrollView() {

        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);
        viewOneInScroll = (CardView) findViewById(R.id.viewOneInScroll);
        viewTwoInScroll = (CardView) findViewById(R.id.viewTwoInScroll);
        viewThreeInScroll = (CardView) findViewById(R.id.viewThreeInScroll);
        viewFourInScroll = (CardView) findViewById(R.id.viewFourInScroll);
        viewFiveInScroll = (CardView) findViewById(R.id.viewFiveInScroll);

    }

    private void forTabs() {

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        tabLayout.addTab(tabLayout.newTab().setText(GraphInScroll.this.getResources().getString(R.string.total_feedback)));
        tabLayout.addTab(tabLayout.newTab().setText(GraphInScroll.this.getResources().getString(R.string.latest_feedback)));
        tabLayout.addTab(tabLayout.newTab().setText(GraphInScroll.this.getResources().getString(R.string.top_5_categories)));
        tabLayout.addTab(tabLayout.newTab().setText(GraphInScroll.this.getResources().getString(R.string.host_productivity)));
        tabLayout.addTab(tabLayout.newTab().setText(GraphInScroll.this.getResources().getString(R.string.segment_statistics)));


        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                switch (tab.getPosition()) {

                    case 0:

                        int rel1Pos = viewOneInScroll.getTop() - 25;
                        nestedScrollView.scrollTo(0, rel1Pos);
                        break;

                    case 1:

                        int rel2Pos = viewTwoInScroll.getTop() - 25;
                        nestedScrollView.scrollTo(0, rel2Pos);
                        break;

                    case 2:

                        int rel3Pos = viewThreeInScroll.getTop() - 25;
                        nestedScrollView.scrollTo(0, rel3Pos);
                        break;

                    case 3:

                        int rel4Pos = viewFourInScroll.getTop() - 25;
                        nestedScrollView.scrollTo(0, rel4Pos);
                        break;

                    case 4:

                        int rel5Pos = viewFiveInScroll.getTop() - 25;
                        nestedScrollView.scrollTo(0, rel5Pos);
                        break;
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


    private void forOne() {

        mTfRegular = Typeface.createFromAsset(GraphInScroll.this.getAssets(), "OpenSans-Regular.ttf");
        mTfLight = Typeface.createFromAsset(GraphInScroll.this.getAssets(), "OpenSans-Light.ttf");

        mChartOne = (BarChart) findViewById(R.id.chartOne);
        mChartOne.setOnChartValueSelectedListener(this);


        mChartOne.animateXY(2000, 2000);
        //mChartOne.animateX(3000);

        mChartOne.setDrawBarShadow(false);

        mChartOne.getDescription().setEnabled(false);

        mChartOne.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be drawn
        mChartOne.setMaxVisibleValueCount(60);

        mChartOne.getLegend().setEnabled(false);

        // scaling can now only be done on x- and y-axis separately
        mChartOne.setPinchZoom(false);

        mChartOne.setFitBars(true);

        mChartOne.setDoubleTapToZoomEnabled(false);

        mChartOne.setPinchZoom(false);

        // enable scaling and dragging
        mChartOne.setDragEnabled(false);

        mChartOne.setScaleEnabled(false);

        mChartOne.setDrawGridBackground(false);

        mChartOne.setMotionEventSplittingEnabled(false);

        // mChartOne.setDrawYLabels(false);

        mChartOne.getLegend().setForm(Legend.LegendForm.NONE);

        XAxis xAxis = mChartOne.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(mTfLight);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f); // only intervals of 1 day
        xAxis.setLabelCount(6);
        xAxis.setSpaceMin(0.2f);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{"10", "20", "30", "40", "50"}));


        YAxis leftAxis = mChartOne.getAxisLeft();
        leftAxis.setTypeface(mTfLight);
        leftAxis.setLabelCount(5, false);
        leftAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{"10", "20", "30", "40", "50"}));

        IAxisValueFormatter custom = new MyAxisValueFormatter();
        leftAxis.setValueFormatter(custom);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setDrawZeroLine(false);
        leftAxis.setAxisMinimum(0); // this replaces setStartAtZero(true)


        YAxis rightAxis = mChartOne.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setTypeface(mTfLight);
        rightAxis.setLabelCount(0, false);
        rightAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{"10", "20", "30", "40", "50"}));
        rightAxis.setSpaceTop(0f);
        rightAxis.setDrawZeroLine(false);
        rightAxis.setAxisMinimum(0); // this replaces setStartAtZero(true)


        mChartOne.getAxisRight().removeAllLimitLines();

        mChartOne.getAxisRight().getLimitLines().clear();

        mChartOne.getAxisRight().setDrawAxisLine(false);

        mChartOne.getAxisRight().setDrawTopYLabelEntry(false);

        mChartOne.getAxisRight().setDrawLabels(false);

        mChartOne.setDrawValueAboveBar(false);

//        Legend l = mChartOne.getLegend();
//        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
//        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
//        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
//        l.setDrawInside(false);
//        l.setForm(LegendForm.SQUARE);
//        l.setFormSize(9f);
//        l.setTextSize(11f);
//        l.setXEntrySpace(4f);

        // XYMarkerView mv = new XYMarkerView(this, xAxisFormatter);

        // mv.setChartView(mChartOne); // For bounds control

        //  mChartOne.setMarker(mv); // Set the marker to the chart

        setDataOne(4, 4);

        final RectF mOnValueSelectedRectF = new RectF();
        mChartOne.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                if (e == null)
                    return;

                RectF bounds = mOnValueSelectedRectF;
                mChartOne.getBarBounds((BarEntry) e, bounds);
                MPPointF position = mChartOne.getPosition(e, YAxis.AxisDependency.LEFT);

                Log.i("bounds", bounds.toString());
                Log.i("position", position.toString());

                Log.i("x-index", "low: " + mChartOne.getLowestVisibleX() + ", high: " + mChartOne.getHighestVisibleX());

                MPPointF.recycleInstance(position);
            }

            @Override
            public void onNothingSelected() {

            }
        });


    }

    private void setDataOne(int count, float range) {


        ArrayList<BarEntry> yValues = new ArrayList<BarEntry>();


        yValues.add(new BarEntry(0, 10));
        yValues.add(new BarEntry(1, 20));
        yValues.add(new BarEntry(2, 30));
        yValues.add(new BarEntry(3, 40));
        yValues.add(new BarEntry(4, 50));


        BarDataSet set1;

        if (mChartOne.getData() != null && mChartOne.getData().getDataSetCount() > 0) {

            set1 = (BarDataSet) mChartOne.getData().getDataSetByIndex(0);
            set1.setValues(yValues);
            mChartOne.getData().notifyDataChanged();
            mChartOne.notifyDataSetChanged();


        } else {

            set1 = new BarDataSet(yValues, "");
            set1.setColors(Color.parseColor("#7079C9"));

            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setValueTypeface(mTfLight);
            data.setBarWidth(0.5f);
            data.setDrawValues(false);

            mChartOne.setData(data);


        }
    }


    private void forFive() {


        mTfRegular = Typeface.createFromAsset(GraphInScroll.this.getAssets(), "OpenSans-Regular.ttf");
        mTfLight = Typeface.createFromAsset(GraphInScroll.this.getAssets(), "OpenSans-Light.ttf");

        mChartThree = (BarChart) findViewById(R.id.chartFive);
        mChartThree.setOnChartValueSelectedListener(this);


        mChartThree.animateXY(2000, 2000);
        //mChartOne.animateX(3000);

        mChartThree.setDrawBarShadow(false);

        mChartThree.getDescription().setEnabled(false);

        mChartThree.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be drawn
        mChartThree.setMaxVisibleValueCount(60);

        mChartThree.getLegend().setEnabled(false);

        // scaling can now only be done on x- and y-axis separately
        mChartThree.setPinchZoom(false);

        mChartThree.setFitBars(true);

        mChartThree.setDoubleTapToZoomEnabled(false);

        mChartThree.setPinchZoom(false);

        // enable scaling and dragging
        mChartThree.setDragEnabled(false);

        mChartThree.setScaleEnabled(false);

        mChartThree.setDrawGridBackground(false);

        mChartThree.setMotionEventSplittingEnabled(false);

        // mChartOne.setDrawYLabels(false);

        mChartThree.getLegend().setForm(Legend.LegendForm.NONE);

        XAxis xAxis = mChartThree.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(mTfLight);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f); // only intervals of 1 day
        xAxis.setLabelCount(6);
        xAxis.setSpaceMin(0.2f);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{"10", "20", "30", "40", "50"}));


        YAxis leftAxis = mChartThree.getAxisLeft();
        leftAxis.setTypeface(mTfLight);
        leftAxis.setLabelCount(5, false);
        leftAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{"10", "20", "30", "40", "50"}));

        IAxisValueFormatter custom = new MyAxisValueFormatter();
        leftAxis.setValueFormatter(custom);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setDrawZeroLine(false);
        leftAxis.setAxisMinimum(0); // this replaces setStartAtZero(true)


        YAxis rightAxis = mChartThree.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setTypeface(mTfLight);
        rightAxis.setLabelCount(0, false);
        rightAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{"10", "20", "30", "40", "50"}));
        rightAxis.setSpaceTop(0f);
        rightAxis.setDrawZeroLine(false);
        rightAxis.setAxisMinimum(0); // this replaces setStartAtZero(true)


        mChartThree.getAxisRight().removeAllLimitLines();

        mChartThree.getAxisRight().getLimitLines().clear();

        mChartThree.getAxisRight().setDrawAxisLine(false);

        mChartThree.getAxisRight().setDrawTopYLabelEntry(false);

        mChartThree.getAxisRight().setDrawLabels(false);

        mChartThree.setDrawValueAboveBar(false);

//        Legend l = mChartOne.getLegend();
//        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
//        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
//        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
//        l.setDrawInside(false);
//        l.setForm(LegendForm.SQUARE);
//        l.setFormSize(9f);
//        l.setTextSize(11f);
//        l.setXEntrySpace(4f);

        // XYMarkerView mv = new XYMarkerView(this, xAxisFormatter);

        // mv.setChartView(mChartOne); // For bounds control

        //  mChartOne.setMarker(mv); // Set the marker to the chart

        setDataThree(4, 4);


        final RectF mOnValueSelectedRectF = new RectF();
        mChartThree.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                if (e == null)
                    return;

                RectF bounds = mOnValueSelectedRectF;
                mChartOne.getBarBounds((BarEntry) e, bounds);
                MPPointF position = mChartThree.getPosition(e, YAxis.AxisDependency.LEFT);

                Log.i("bounds", bounds.toString());
                Log.i("position", position.toString());

                Log.i("x-index", "low: " + mChartThree.getLowestVisibleX() + ", high: " + mChartThree.getHighestVisibleX());

                MPPointF.recycleInstance(position);
            }

            @Override
            public void onNothingSelected() {

            }
        });


    }

    private void forThree() {

        barWidth = 0.3f;
        barSpace = 0f;
        groupSpace = 0.4f;

        groupBarChartThree = (BarChart) findViewById(R.id.groupBarChartThree);
        groupBarChartThree.setDescription(null);
        groupBarChartThree.setPinchZoom(false);
        groupBarChartThree.setScaleEnabled(false);
        groupBarChartThree.setDrawBarShadow(false);
        groupBarChartThree.setDrawGridBackground(false);


        //DUMMY DATA

        int groupCount = 6;

        ArrayList xVals = new ArrayList();

        xVals.add("Jan");
        xVals.add("Feb");
        xVals.add("Mar");
        xVals.add("Apr");
        xVals.add("May");
        xVals.add("Jun");

        ArrayList yVals1 = new ArrayList();
        ArrayList yVals2 = new ArrayList();


        yVals1.add(new BarEntry(1, (float) 1));
        yVals2.add(new BarEntry(1, (float) 2));
        yVals1.add(new BarEntry(2, (float) 3));
        yVals2.add(new BarEntry(2, (float) 4));
        yVals1.add(new BarEntry(3, (float) 5));
        yVals2.add(new BarEntry(3, (float) 6));
        yVals1.add(new BarEntry(4, (float) 7));
        yVals2.add(new BarEntry(4, (float) 8));
        yVals1.add(new BarEntry(5, (float) 9));
        yVals2.add(new BarEntry(5, (float) 10));
        yVals1.add(new BarEntry(6, (float) 11));
        yVals2.add(new BarEntry(6, (float) 12));


        //to draw the graph
        BarDataSet set1, set2;
        set1 = new BarDataSet(yVals1, "A");
        set1.setColor(Color.RED);
        set2 = new BarDataSet(yVals2, "B");
        set2.setColor(Color.BLUE);
        BarData data = new BarData(set1, set2);
        data.setValueFormatter(new LargeValueFormatter());
        groupBarChartThree.setData(data);
        groupBarChartThree.getBarData().setBarWidth(barWidth);
        groupBarChartThree.getXAxis().setAxisMinimum(0);
        groupBarChartThree.getXAxis().setAxisMaximum(0 + groupBarChartThree.getBarData().getGroupWidth(groupSpace, barSpace) * groupCount);
        groupBarChartThree.groupBars(0, groupSpace, barSpace);
        groupBarChartThree.getData().setHighlightEnabled(false);
        groupBarChartThree.invalidate();


        //Draw the indicator

        Legend l = groupBarChartThree.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(true);
        l.setYOffset(20f);
        l.setXOffset(0f);
        l.setYEntrySpace(0f);
        l.setTextSize(8f);

        //X-axis
        XAxis xAxis = groupBarChartThree.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setCenterAxisLabels(true);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisMaximum(6);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xVals));
//Y-axis
        groupBarChartThree.getAxisRight().setEnabled(false);
        YAxis leftAxis = groupBarChartThree.getAxisLeft();
        leftAxis.setValueFormatter(new LargeValueFormatter());
        leftAxis.setDrawGridLines(true);
        leftAxis.setSpaceTop(35f);
        leftAxis.setAxisMinimum(0f);
    }

    private void setDataThree(int count, float range) {


        ArrayList<BarEntry> yValues = new ArrayList<BarEntry>();


        yValues.add(new BarEntry(0, 10));
        yValues.add(new BarEntry(1, 20));
        yValues.add(new BarEntry(2, 30));
        yValues.add(new BarEntry(3, 40));
        yValues.add(new BarEntry(4, 50));


        BarDataSet set1;

        if (mChartThree.getData() != null && mChartThree.getData().getDataSetCount() > 0) {

            set1 = (BarDataSet) mChartThree.getData().getDataSetByIndex(0);
            set1.setValues(yValues);
            mChartThree.getData().notifyDataChanged();
            mChartThree.notifyDataSetChanged();


        } else {

            set1 = new BarDataSet(yValues, "");
            set1.setColors(Color.parseColor("#7079C9"));

            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setValueTypeface(mTfLight);
            data.setBarWidth(0.5f);
            data.setDrawValues(false);

            mChartThree.setData(data);


            final RectF mOnValueSelectedRectF = new RectF();
            mChartThree.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
                @Override
                public void onValueSelected(Entry e, Highlight h) {
                    if (e == null)
                        return;

                    RectF bounds = mOnValueSelectedRectF;
                    mChartOne.getBarBounds((BarEntry) e, bounds);
                    MPPointF position = mChartThree.getPosition(e, YAxis.AxisDependency.LEFT);

                    Log.i("bounds", bounds.toString());
                    Log.i("position", position.toString());

                    Log.i("x-index", "low: " + mChartThree.getLowestVisibleX() + ", high: " + mChartThree.getHighestVisibleX());

                    MPPointF.recycleInstance(position);
                }

                @Override
                public void onNothingSelected() {

                }
            });


        }
    }


    @Override
    public void onValueSelected(Entry e, Highlight h) {


    }

    @Override
    public void onNothingSelected() {

    }


}
