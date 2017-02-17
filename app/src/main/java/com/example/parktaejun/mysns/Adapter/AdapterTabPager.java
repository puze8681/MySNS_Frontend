package com.example.parktaejun.mysns.Adapter;

/**
 * Created by parktaejun on 2017. 2. 17..
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.parktaejun.mysns.Fragment.FragmentChat;
import com.example.parktaejun.mysns.Fragment.FragmentTimeLine;


/**
 * Created by parktaejun on 2017. 2. 10..
 */

public class AdapterTabPager extends FragmentStatePagerAdapter {

    private int tabCount;

    public AdapterTabPager(FragmentManager fm, int tabCount){
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
                FragmentChat fragmentChat = new FragmentChat();
                return fragmentChat;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}