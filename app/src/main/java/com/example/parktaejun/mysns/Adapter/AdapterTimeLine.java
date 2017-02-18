package com.example.parktaejun.mysns.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.parktaejun.mysns.Data.TimeLine;
import com.example.parktaejun.mysns.Data.User;
import com.example.parktaejun.mysns.LoginActivity;
import com.example.parktaejun.mysns.R;

import java.util.List;

/**
 * Created by parktaejun on 2017. 2. 17..
 */

public class AdapterTimeLine extends BaseAdapter{
    private Context context;
    private List<TimeLine> items;

    SharedPreferences pref;
    SharedPreferences.Editor edit;

    public AdapterTimeLine(Context context, List<TimeLine> items){
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

        View view = null;
        if(position == 0){
            view = LayoutInflater.from(context).inflate(R.layout.item_timeline_post, null);
        }else {
            view = LayoutInflater.from(context).inflate(R.layout.item_chat_room_profile_view, null);
            TextView post_name = (TextView) view.findViewById(R.id.profile_text);
            post_name.setText(LoginActivity.pref.getString("userName", ""));
        }

        return view;


    }
}
