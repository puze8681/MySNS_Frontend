package com.example.parktaejun.mysns;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.parktaejun.mysns.Server.JSONService;
import com.example.parktaejun.mysns.Server.ServerUser;

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

    public static SharedPreferences pref;
    public static SharedPreferences.Editor edit;

    Boolean login_check = false;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getAutoLogin();
        if(pref.getBoolean("login_check", login_check)){
            Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(loginIntent);
            login_check = true;
            saveAutoLogin(login_check);
            Toast toast = Toast.makeText(LoginActivity.this, "자동 로그인 ...", Toast.LENGTH_SHORT );
            toast.show();
            finish();
        }

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

                Call<ServerUser> call = service.login(user_id.getText().toString(), user_pw.getText().toString());
                call.enqueue(new Callback<ServerUser>() {
                                 @Override
                                 public void onResponse(Call<ServerUser> call, Response<ServerUser> response) {
                                     if(response.code() == 200){
                                         ServerUser user = response.body();
                                         String user_name = user.user_name;
                                         if(user != null){
                                             Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);
                                             startActivity(loginIntent);
                                             login_check = true;

                                             saveAutoLogin(login_check);
                                             savePreference(user_name);
                                             finish();
                                         }
                                     } else if(response.code() == 400){
                                         progress_dialog.dismiss();
                                         Toast.makeText(getApplicationContext(), "아이디 혹은 비밀번호가 옳지 않습니다 ... ", Toast.LENGTH_SHORT).show();
                                     } else{
                                         progress_dialog.dismiss();
                                         Toast.makeText(getApplicationContext(), "UNKNOWN ERR ... ", Toast.LENGTH_SHORT).show();
                                     }
                                 }

                                 @Override
                                 public void onFailure(Call<ServerUser> call, Throwable t) {
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

    public void getAutoLogin(){
        pref = getSharedPreferences("pref", 0);
        pref.getBoolean("login_check", false);
    }

    public void saveAutoLogin(Boolean login_check){
        pref = getSharedPreferences("pref", 0);
        edit = pref.edit();
        edit.putBoolean("login_check", login_check);
        edit.commit();
    }

    public void removeAutoLogin(Boolean login_check){
        pref = getSharedPreferences("pref", 0);
        edit = pref.edit();
        edit.remove("login_check");
        edit.commit();
    }
}
