package com.example.parktaejun.mysns.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by parktaejun on 2017. 2. 17..
 */

public class FragmentTimeLine extends Fragment {

    public FragmentTimeLine(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_timeline, container, false);
        return view;
    }

}