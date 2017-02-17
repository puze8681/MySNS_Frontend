package com.example.parktaejun.mysns;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class RegisterActivity extends AppCompatActivity {
    EditText user_id;
    EditText user_pw;
    EditText user_name;

    Button register_btn;

    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        user_id = (EditText)findViewById(R.id.user_id);
        user_pw = (EditText)findViewById(R.id.user_pw);
        user_name = (EditText)findViewById(R.id.user_name);

        register_btn = (Button)findViewById(R.id.register_btn);

        retrofit = new Retrofit.Builder().baseUrl("http://nh.applepi.kr").addConverterFactory(GsonConverterFactory.create()).build();
        final JSONService service = retrofit.create(JSONService.class);

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<User> call = service.register(user_id.getText().toString(), user_pw.getText().toString(), user_name.getText().toString());
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.code() == 200){
                            User user = response.body();
                            if(user != null){
                                Toast.makeText(getApplicationContext(), "회원가입이 완료되었습니다 ... ", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }else if(response.code() == 400){
                            Toast.makeText(getApplicationContext(), "PARAM MISSING ... ", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "UNKNOWN ERR ... ", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "요청을 전송할 수 없습니다 ... ", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
            }
        });
    }
}
