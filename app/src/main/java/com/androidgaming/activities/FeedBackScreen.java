package com.androidgaming.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.androidgaming.R;
import com.crystal.crystalrangeseekbar.widgets.BubbleThumbSeekbar;
import com.github.channguyen.rsv.RangeSliderView;

public class FeedBackScreen extends AppCompatActivity {


    LinearLayout secondLayout;
    LinearLayout firstLayout;
    BubbleThumbSeekbar seekBar;
    RangeSliderView rangePicker;
    LinearLayout thirdLayout = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back_screen);
        initView();
    }

    private void initView() {
        secondLayout = (LinearLayout) findViewById(R.id.secondLayout);
        firstLayout = (LinearLayout) findViewById(R.id.firstLayout);
        thirdLayout = (LinearLayout) findViewById(R.id.thirdLayout);
        rangePicker = (RangeSliderView) findViewById(R.id.rangePicker);
        rangePicker.setOnSlideListener(listener);
        firstLayout.setVisibility(View.VISIBLE);
        secondLayout.setVisibility(View.GONE);
        thirdLayout.setVisibility(View.GONE);
    }

    final RangeSliderView.OnSlideListener listener = new RangeSliderView.OnSlideListener() {
        @Override
        public void onSlide(int index) {
            switch (index) {
                case 0:
                    firstLayout.setVisibility(View.VISIBLE);
                    secondLayout.setVisibility(View.GONE);
                    thirdLayout.setVisibility(View.GONE);
                    break;
                case 1:

                    firstLayout.setVisibility(View.GONE);
                    secondLayout.setVisibility(View.VISIBLE);
                    thirdLayout.setVisibility(View.GONE);

                    break;


                case 2:


                    firstLayout.setVisibility(View.GONE);
                    secondLayout.setVisibility(View.GONE);
                    thirdLayout.setVisibility(View.VISIBLE);

                    break;
            }


        }
    };

}
