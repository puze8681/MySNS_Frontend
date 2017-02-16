package com.example.parktaejun.mysns;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.parktaejun.mysns.Data.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    EditText user_id;
    EditText user_pw;

    Button login_btn;
    Button register_btn;

    ProgressDialog progress_dialog;

    Retrofit retrofit;

    SharedPreferences pref;
    SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user_id = (EditText) findViewById(R.id.user_id);
        user_pw = (EditText) findViewById(R.id.user_pw);

        login_btn = (Button) findViewById(R.id.login_btn);
        register_btn = (Button) findViewById(R.id.register_btn);

        retrofit = new Retrofit.Builder().baseUrl("http://nh.applepi.kr").addConverterFactory(GsonConverterFactory.create()).build();
        final JSONService service = retrofit.create(JSONService.class);

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress_dialog = new ProgressDialog(LoginActivity.this);
                progress_dialog.setMessage("로그인 중입니다 ... ");
                progress_dialog.show();

                Call<User> call = service.login(user_id.getText().toString(), user_pw.getText().toString());
                call.enqueue(new Callback<User>() {
                                 @Override
                                 public void onResponse(Call<User> call, Response<User> response) {
                                     if(response.code() == 200){
                                         User user = response.body();
                                         Log.d("user name", "user name : " + user.user_name);

                                         if(user != null){
                                             Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);
                                             savePreference(user.user_name);
                                             startActivity(loginIntent);
                                             finish();
                                         }
                                     }
                                     else {
                                         progress_dialog.dismiss();
                                         Toast.makeText(getApplicationContext(), "알 수 없는 오류 ... ", Toast.LENGTH_SHORT).show();
                                     }
                                 }

                                 @Override
                                 public void onFailure(Call<User> call, Throwable t) {
                                     progress_dialog.dismiss();
                                     Toast.makeText(getApplicationContext(), "요청 불가 ... ", Toast.LENGTH_SHORT).show();
                                 }
                             });
            }
        });
    }

    public void getPreference(){
        pref = getSharedPreferences("pref", 0);
        pref.getString("user_name", "");
    }

    public void savePreference(String user_name){
        pref = getSharedPreferences("pref", 0);
        edit = pref.edit();
        edit.putString("user_name", user_name);
        edit.commit();
    }

    public void removePreference(String user_name){
        pref = getSharedPreferences("pref", 0);
        edit = pref.edit();
        edit.remove("user_name");
        edit.commit();
    }
}
