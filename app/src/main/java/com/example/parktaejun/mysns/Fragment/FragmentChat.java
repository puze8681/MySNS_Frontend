package com.example.parktaejun.mysns.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.parktaejun.mysns.R;

/**
 * Created by parktaejun on 2017. 2. 17..
 */

public class FragmentChat extends Fragment {

    public FragmentChat(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        return view;
    }
}
