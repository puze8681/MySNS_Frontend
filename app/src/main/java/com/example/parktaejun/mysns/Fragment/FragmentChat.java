package com.example.parktaejun.mysns.Fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.parktaejun.mysns.Adapter.AdapterChatRoomList;
import com.example.parktaejun.mysns.ChatInsideActivity;
import com.example.parktaejun.mysns.Data.User;
import com.example.parktaejun.mysns.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by parktaejun on 2017. 2. 17..
 */

public class FragmentChat extends Fragment {

    private EditText chat_edit;
    private ListView chat_list;
    private AdapterChatRoomList listAdapter;
    private List<User> items = new ArrayList<>();

    public FragmentChat(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        chat_edit = (EditText) view.findViewById(R.id.chat_edit);
        chat_list = (ListView) view.findViewById(R.id.chatroom_list);

        items.add(new User("박태준"));
        items.add(new User("윤영채"));
        items.add(new User("박태준"));
        items.add(new User("윤영채"));
        items.add(new User("박태준"));
        items.add(new User("윤영채"));
        items.add(new User("박태준"));
        items.add(new User("윤영채"));
        items.add(new User("박태준"));
        items.add(new User("윤영채"));
        items.add(new User("박태준"));
        items.add(new User("윤영채"));
        items.add(new User("박태준"));
        items.add(new User("윤영채"));
        items.add(new User("박태준"));
        items.add(new User("윤영채"));
        items.add(new User("박태준"));
        items.add(new User("윤영채"));
        items.add(new User("박태준"));
        items.add(new User("윤영채"));
        items.add(new User("박태준"));
        items.add(new User("윤영채"));

        listAdapter = new AdapterChatRoomList(getContext(), items);
        chat_list.setAdapter(listAdapter);

        chat_list.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                        String name = items.get(i).getName();
                        Intent chatIntent = new Intent(getActivity(), ChatInsideActivity.class);
                        chatIntent.putExtra("chatName", name);
                        startActivity(chatIntent);
                    }
                }
        );

        return view;
    }


}