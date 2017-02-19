package com.example.parktaejun.mysns;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parktaejun.mysns.Data.TimeLine;
import com.example.parktaejun.mysns.Fragment.FragmentTimeLine;
import com.example.parktaejun.mysns.Server.JSONService;
import com.example.parktaejun.mysns.Server.ServerPoster;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TimeLineInsideActivity extends AppCompatActivity {

    TextView profile_name;

    Button image_upload;
    Button post;


    Retrofit retrofit;

    EditText post_title;
    EditText post_context;

    // 현재시간을 msec 으로 구한다.
    long now = System.currentTimeMillis();
    // 현재시간을 date 변수에 저장한다.
    Date date = new Date(now);
    // 시간을 나타냇 포맷을 정한다 ( yyyy/MM/dd 같은 형태로 변형 가능 )
    SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    // nowDate 변수에 값을 저장한다.
    String post_time = sdfNow.format(date);
    String post_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_line_inside);
        profile_name = (TextView)findViewById(R.id.profile_name);
        post = (Button)findViewById(R.id.post);
        image_upload = (Button)findViewById(R.id.image_upload);
        profile_name.setText(LoginActivity.pref.getString("userName", ""));

        post_name = LoginActivity.pref.getString("userName", "");
        post_title = (EditText)findViewById(R.id.post_title);
        post_context = (EditText)findViewById(R.id.post_context);


        retrofit = new Retrofit.Builder().baseUrl("http://nh.applepi.kr").addConverterFactory(GsonConverterFactory.create()).build();
        final JSONService time_line_service = retrofit.create(JSONService.class);

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Call<ServerPoster> call = time_line_service.post(post_name, post_title.getText().toString(), post_time, post_context.getText().toString());
                call.enqueue(new Callback<ServerPoster>() {
                    @Override
                    public void onResponse(Call<ServerPoster> call, Response<ServerPoster> response) {
                        if(response.code() == 200){
                            FragmentTimeLine.items.add(new TimeLine(post_name, post_title.getText().toString(), post_time, post_context.getText().toString()));
                            Toast.makeText(getApplicationContext(), "UPLOAD SUCCESS ... ", Toast.LENGTH_SHORT).show();
                        }else if(response.code() == 400){
                            Toast.makeText(getApplicationContext(), "PARAM MISSING ... ", Toast.LENGTH_SHORT).show();
                        }else if(response.code() == 500){
                            Toast.makeText(getApplicationContext(), "SERVER ERR ... ", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplicationContext(), "UNKNOWN ERR ... ", Toast.LENGTH_SHORT).show();
                        }
                        finish();
                    }
                    @Override
                    public void onFailure(Call<ServerPoster> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "요청 불가 ... ", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
