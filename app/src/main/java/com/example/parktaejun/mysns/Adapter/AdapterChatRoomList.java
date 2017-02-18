package com.example.parktaejun.mysns.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.parktaejun.mysns.Data.User;
import com.example.parktaejun.mysns.LoginActivity;
import com.example.parktaejun.mysns.R;

import java.util.List;

/**
 * Created by parktaejun on 2017. 2. 17..
 */

public class AdapterChatRoomList extends BaseAdapter {

    private Context context;
    private List<User> items;

    SharedPreferences pref;
    SharedPreferences.Editor edit;

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

        View view = null;
        if(position == 0){
            view = LayoutInflater.from(context).inflate(R.layout.item_search_chat, null);
        }else if(position == 1 || position == 3){
            view = LayoutInflater.from(context).inflate(R.layout.text, null);
            TextView text = (TextView)view.findViewById(R.id.text_text);

            String _text = position == 1 ? "내 프로필" : "친구";
            text.setText(_text);
        }else if(position == 2){
            view = LayoutInflater.from(context).inflate(R.layout.item_chat_room_profile_view, null);

            ImageView profileImage = (ImageView) view.findViewById(R.id.profile_image);
            TextView profileText = (TextView) view.findViewById(R.id.profile_text);

            profileImage.setImageResource(R.mipmap.ic_launcher);
            profileText.setText(LoginActivity.pref.getString("userName", ""));
        }else {
            view = LayoutInflater.from(context).inflate(R.layout.item_chat_room_profile_view, null);
            TextView name = (TextView) view.findViewById(R.id.profile_text);
            name.setText(items.get(position).getName());
        }

        return view;


    }

}