//사용자별 상세정보 액티비티
package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = "ProfileActivity";
    private String USER_ID, USER_NAME;
    private int update_status;
    private ImageButton btn_back;
    private AppCompatButton btn_signOut, btn_updateUserData;
    private TextView text_userName, text_userID, text_userAge, text_userGender, text_userHeight, text_userWeight;
    private EditText edit_userAge, edit_userGender, edit_userHeight, edit_userWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        SharedPreferences preferences = getSharedPreferences("user_data", Context.MODE_PRIVATE);
        USER_ID = preferences.getString("user_id","");
        USER_NAME = preferences.getString("user_name","");
        update_status = 0;

        btn_back = (ImageButton) findViewById(R.id.profile_btn_back);
        btn_signOut = (AppCompatButton) findViewById(R.id.profile_btn_signout);
        btn_updateUserData = (AppCompatButton) findViewById(R.id.profile_btn_update_user_data);
        text_userName = (TextView) findViewById(R.id.profile_user_name);
        text_userID = (TextView) findViewById(R.id.profile_user_id);
        text_userAge = (TextView) findViewById(R.id.profile_user_age);
        text_userGender = (TextView) findViewById(R.id.profile_user_gender);
        text_userHeight = (TextView) findViewById(R.id.profile_user_height);
        text_userWeight = (TextView) findViewById(R.id.profile_user_weight);
        text_userWeight = (TextView) findViewById(R.id.profile_user_weight);
        edit_userAge = (EditText) findViewById(R.id.profile_edit_user_age);
        edit_userGender = (EditText) findViewById(R.id.profile_edit_user_gender);
        edit_userHeight = (EditText) findViewById(R.id.profile_edit_user_height);
        edit_userWeight = (EditText) findViewById(R.id.profile_edit_user_weight);

        InterfaceUser interfaces = Client.getRetrofitInstance().create(InterfaceUser.class);
        Call<ModelUser> call = interfaces.getUserData(USER_ID);
        call.enqueue(new Callback<ModelUser>() {
            @Override
            public void onResponse(Call<ModelUser> call, Response<ModelUser> response) {
                ModelUser userData = response.body();
                updateUserText(userData.getUser_id(), userData.getUser_name(), userData.getAge(), userData.getGender(), userData.getHeight(), userData.getWeight(), userData.getBmr());
            }

            @Override
            public void onFailure(Call<ModelUser> call, Throwable t) {
                Log.e(TAG, "onFailure: "+ t.getMessage());
            }
        });

        btn_signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences("user_data", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("user_id", "");
                editor.putString("user_name", "");
                editor.commit();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_updateUserData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp_gender;
                int temp_age, temp_height, temp_weight;
                if (update_status == 0) {
                    btn_updateUserData.setBackgroundResource(R.drawable.shape_btn1);
                    btn_updateUserData.setText("수정 완료");
                    text_userAge.setVisibility(View.GONE);
                    text_userGender.setVisibility(View.GONE);
                    text_userHeight.setVisibility(View.GONE);
                    text_userWeight.setVisibility(View.GONE);
                    edit_userAge.setVisibility(View.VISIBLE);
                    edit_userGender.setVisibility(View.VISIBLE);
                    edit_userHeight.setVisibility(View.VISIBLE);
                    edit_userWeight.setVisibility(View.VISIBLE);
                    update_status = 1;
                } else if (update_status == 1) {
                    btn_updateUserData.setBackgroundResource(R.drawable.shape_btn3);
                    btn_updateUserData.setText("정보 수정");
                    temp_age = Integer.parseInt(edit_userAge.getText().toString());
                    temp_gender = edit_userGender.getText().toString();
                    temp_height = Integer.parseInt(edit_userHeight.getText().toString());
                    temp_weight = Integer.parseInt(edit_userWeight.getText().toString());
                    text_userAge.setVisibility(View.VISIBLE);
                    text_userGender.setVisibility(View.VISIBLE);
                    text_userHeight.setVisibility(View.VISIBLE);
                    text_userWeight.setVisibility(View.VISIBLE);
                    edit_userAge.setVisibility(View.GONE);
                    edit_userGender.setVisibility(View.GONE);
                    edit_userHeight.setVisibility(View.GONE);
                    edit_userWeight.setVisibility(View.GONE);
                    update_status = 0;

                    HashMap<String, Object> postingData = new HashMap<>();
                    postingData.put("user_name", "HyunSu");
                    postingData.put("gender", temp_gender);
                    postingData.put("weight", temp_weight);
                    postingData.put("height", temp_height);
                    postingData.put("age", temp_age);
                    InterfaceUser interfaceUser1 = Client.getRetrofitInstance().create(InterfaceUser.class);
                    interfaceUser1.patchUserData(postingData, USER_ID).enqueue(new Callback<ModelUser>() {
                        @Override
                        public void onResponse(Call<ModelUser> call1, Response<ModelUser> response) {
                            if (response.isSuccessful()) {
                                ModelUser userData = response.body();
                                Log.e(TAG, "PATCH" + "patch 성공");
                                Log.e(TAG, "PATCH" + userData.getUser_id());
                                updateUserText(userData.getUser_id(), userData.getUser_name(), userData.getAge(), userData.getGender(), userData.getHeight(), userData.getWeight(), userData.getBmr());
                            }
                        }

                        @Override
                        public void onFailure(Call<ModelUser> call1, Throwable t) {

                        }
                    });

                }
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.go_up_1, R.anim.go_up_2);
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.go_up_1, R.anim.go_up_2);
        finish();
    }

    public void updateUserText(String id, String name, int age, String gender, int height, int weight, int bmr) {
        text_userName.setText(name);
        text_userID.setText("ID : " + id);
        text_userAge.setText(Integer.toString(age));
        if (gender.equals("man")) {
            text_userGender.setText("남");
        } else if (gender.equals("woman")) {
            text_userGender.setText("여");
        }
        text_userHeight.setText(Integer.toString(height));
        text_userWeight.setText(Integer.toString(weight));
    }
}