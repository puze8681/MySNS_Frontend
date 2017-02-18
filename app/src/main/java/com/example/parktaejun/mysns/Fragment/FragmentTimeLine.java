package com.example.parktaejun.mysns.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.parktaejun.mysns.Adapter.AdapterChatRoomList;
import com.example.parktaejun.mysns.Adapter.AdapterTimeLine;
import com.example.parktaejun.mysns.ChatInsideActivity;
import com.example.parktaejun.mysns.Data.TimeLine;
import com.example.parktaejun.mysns.Data.User;
import com.example.parktaejun.mysns.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by parktaejun on 2017. 2. 17..
 */

public class FragmentTimeLine extends Fragment {

    private ListView timeline_list;
    private AdapterTimeLine listAdapter;
    private List<TimeLine> items = new ArrayList<>();

    public FragmentTimeLine(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_timeline, container, false);
        timeline_list = (ListView) view.findViewById(R.id.timeline_list);

        items.add(new TimeLine("이름", "제목", "시간", "내용"));

        listAdapter = new AdapterTimeLine(getContext(), items);
        timeline_list.setAdapter(listAdapter);

        timeline_list.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                        String name = items.get(i).getName();
                        Intent chatIntent = new Intent(getActivity(), ChatInsideActivity.class);
                        startActivity(chatIntent);
                    }
                }
        );

        return view;
    }

}
