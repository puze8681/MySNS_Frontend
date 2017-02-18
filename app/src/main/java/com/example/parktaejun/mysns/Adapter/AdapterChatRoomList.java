package com.example.parktaejun.mysns.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.parktaejun.mysns.Data.User;
import com.example.parktaejun.mysns.R;

import java.util.List;

/**
 * Created by parktaejun on 2017. 2. 17..
 */

public class AdapterChatRoomList extends BaseAdapter {

    private Context context;
    private List<User> items;

    public AdapterChatRoomList(Context context, List<User> items){
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.chat, null);
        LinearLayout container = (LinearLayout) view.findViewById(R.id.container);

        if(position == 0){
            view = LayoutInflater.from(context).inflate(R.layout.item_search_chat, null);
        }else {
            view = LayoutInflater.from(context).inflate(R.layout.item_chat_room_view, null);
            TextView name = (TextView) view.findViewById(R.id.chatting_text);
            name.setText(items.get(position).getName());
        }

        return view;
    }


}