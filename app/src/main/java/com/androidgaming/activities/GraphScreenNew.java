package com.androidgaming.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import com.androidgaming.R;
import com.androidgaming.adapter.PagerAdapter;

public class GraphScreenNew extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_graph_screen_new);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        tabLayout.addTab(tabLayout.newTab().setText(GraphScreenNew.this.getResources().getString(R.string.total_feedback)));
        tabLayout.addTab(tabLayout.newTab().setText(GraphScreenNew.this.getResources().getString(R.string.latest_feedback)));
        tabLayout.addTab(tabLayout.newTab().setText(GraphScreenNew.this.getResources().getString(R.string.top_5_categories)));
        tabLayout.addTab(tabLayout.newTab().setText(GraphScreenNew.this.getResources().getString(R.string.host_productivity)));
        tabLayout.addTab(tabLayout.newTab().setText(GraphScreenNew.this.getResources().getString(R.string.segment_statistics)));


        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
