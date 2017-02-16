package com.example.parktaejun.mysns.Adapter;

/**
 * Created by parktaejun on 2017. 2. 17..
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.parktaejun.mysns.Fragment.FragmentChatting;
import com.example.parktaejun.mysns.Fragment.FragmentTimeLine;


/**
 * Created by parktaejun on 2017. 2. 10..
 */

public class TabPagerAdapter extends FragmentStatePagerAdapter {

    private int tabCount;

    public TabPagerAdapter(FragmentManager fm, int tabCount){
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position){

        switch (position){
            case 0:
                FragmentTimeLine fragmentTimeLine = new FragmentTimeLine();
                return fragmentTimeLine;
            case 1:
                FragmentChatting fragmentChatting = new FragmentChatting();
                return fragmentChatting;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}