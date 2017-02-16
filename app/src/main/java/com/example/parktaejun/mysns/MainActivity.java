package com.example.parktaejun.mysns;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.parktaejun.mysns.Adapter.TabPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager)findViewById(R.id.viewpager);
        tabLayout = (TabLayout)findViewById(R.id.tablayout);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);

        tabLayout.addTab(tabLayout.newTab().setText("게시물!").setTag("time_lime"));
        tabLayout.addTab(tabLayout.newTab().setText("친구.").setTag("chat"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("게시물");

        TabPagerAdapter pagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

                switch (tabLayout.getSelectedTabPosition()){
                    case 0:
                        getSupportActionBar().setTitle("게시물");
                        tab.setText("게시물!");
                        break;
                    case 1:
                        getSupportActionBar().setTitle("채팅");
                        tab.setText("채팅!");
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                switch (tabLayout.getSelectedTabPosition()){
                    case 0:
                        tab.setText("게시물");
                        break;
                    case 1:
                        tab.setText("채팅");
                        break;
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
