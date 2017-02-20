package com.example.parktaejun.mysns.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.parktaejun.mysns.Adapter.AdapterChatRoomList;
import com.example.parktaejun.mysns.Adapter.AdapterTimeLine;
import com.example.parktaejun.mysns.ChatInsideActivity;
import com.example.parktaejun.mysns.Data.TimeLine;
import com.example.parktaejun.mysns.Data.User;
import com.example.parktaejun.mysns.R;
import com.example.parktaejun.mysns.Server.JSONService;
import com.example.parktaejun.mysns.Server.ServerPoster;
import com.example.parktaejun.mysns.TimeLineInsideActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by parktaejun on 2017. 2. 17..
 */

public class FragmentTimeLine extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private ListView timeline_list;
    private AdapterTimeLine listAdapter;
    public static List<TimeLine> items = new ArrayList<>();
    SwipeRefreshLayout mBaseLayout;

    Retrofit retrofit;

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

        mBaseLayout = (SwipeRefreshLayout)view.findViewById(R.id.mBaseLayout);
        mBaseLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mBaseLayout.setOnRefreshListener(FragmentTimeLine.this);
        mBaseLayout.post(new Runnable() {
            @Override
            public void run() {
                mBaseLayout.setRefreshing(true);

                items.clear();
                loadPost();

                mBaseLayout.setRefreshing(false);
            }
        });

        timeline_list.setAdapter(listAdapter);
        timeline_list.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                        String name = items.get(i).getName();
                        Intent chatIntent = new Intent(getActivity(), TimeLineInsideActivity.class);
                        startActivity(chatIntent);
                    }
                }
        );

        return view;
    }

    public void initList(String writer, String title, String time, String context){


        items.add(new TimeLine(writer, title, time, context));
        listAdapter.notifyDataSetChanged();
    }


    public void loadPost(){
        final JSONService loadPost = retrofit.create(JSONService.class);
        Call<List<ServerPoster>> call = loadPost.loadPost();
        call.enqueue(new Callback<List<ServerPoster>>() {

            @Override
            public void onResponse(Call<List<ServerPoster>> call, Response<List<ServerPoster>> response) {
                if(response.code() == 200){
                    List<ServerPoster> posts = response.body();
                    for(ServerPoster post : posts){
                        String[] return_temp = post.post_time.substring(0,post.post_time.length()-8).split("T");
                        String return_val = return_temp[0] + " " + return_temp[1];
                        initList(post.post_name, post.post_title, return_val, post.post_context);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ServerPoster>> call, Throwable t) {
                Toast.makeText(getContext(), "알수없는 에러에요..", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRefresh() {
        items.clear();
        loadPost();
        mBaseLayout.setRefreshing(false);

    }
}
