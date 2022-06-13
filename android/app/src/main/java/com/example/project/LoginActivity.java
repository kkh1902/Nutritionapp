//로그인 액티비티
package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private Intent intent;
    private AppCompatButton btn_signin, btn_signup;
    private EditText input_id;
    private InterfaceUser interfaceUser;
    private String USER_ID, USER_NAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_signin = (AppCompatButton) findViewById(R.id.login_btn_signin);
        btn_signup = (AppCompatButton) findViewById(R.id.login_btn_signup);
        input_id = (EditText) findViewById(R.id.login_input_id);

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                USER_ID = input_id.getText().toString();
                interfaceUser = Client.getRetrofitInstance().create(InterfaceUser.class);
                Call<ModelUser> call = interfaceUser.getUserData(USER_ID);
                call.enqueue(new Callback<ModelUser>() {
                    @Override
                    public void onResponse(Call<ModelUser> call, Response<ModelUser> response) {
                        if (response != null) {
                            ModelUser userData = response.body();
                            USER_NAME = userData.getUser_name();
                            SharedPreferences preferences = getSharedPreferences("user_data", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("user_id", USER_ID);
                            editor.putString("user_name", USER_NAME);
                            editor.commit();
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"아이디가 존재하지 않습니다.\n다시 시도하세요.",Toast.LENGTH_SHORT);
                        }
                    }

                    @Override
                    public void onFailure(Call<ModelUser> call, Throwable t) {
                        Log.e(TAG, "onFailure: "+ t.getMessage());
                        Toast.makeText(getApplicationContext(),"서버와의 연결이 원활하지 않습니다.\n잠시후 다시 시도하세요.",Toast.LENGTH_SHORT);
                    }
                });

                intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.go_down_1, R.anim.go_down_2);
                finish();
            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

    }
    @Override
    public void onBackPressed() {
        finish();
    }
}
