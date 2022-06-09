//음식 추가 액티비티 리스트 아이템 눌렀을 때, 팝업되는 모달 액티비티
package com.example.project;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModalActivity extends Activity {

    private static final String TAG = "ModalActivity";
    private String USER_ID;
    private String sel_food_name, sel_food_service_wt, sel_food_kcal, sel_food_tan, sel_food_dan, sel_food_ji, date;
    private Intent intent;
    private ArrayList<ModelRecord> arrayList;
    private AppCompatButton btn_add_breakfast, btn_add_lunch, btn_add_dinner, btn_add_false;
    private TextView add_foodtext;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.modal_addfood);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        intent = getIntent();
        sel_food_name = intent.getExtras().getString("sel_food_name");
        sel_food_service_wt = intent.getExtras().getString("sel_food_service_wt");
        sel_food_kcal = intent.getExtras().getString("sel_food_kcal");
        sel_food_tan = intent.getExtras().getString("sel_food_tan");
        sel_food_dan = intent.getExtras().getString("sel_food_dan");
        sel_food_ji = intent.getExtras().getString("sel_food_ji");
        date = intent.getExtras().getString("date");

        add_foodtext = findViewById(R.id.modal_addfood_text);
        btn_add_breakfast = findViewById(R.id.modal_addfood_btn_true_breakfast);
        btn_add_lunch = findViewById(R.id.modal_addfood_btn_true_lunch);
        btn_add_dinner = findViewById(R.id.modal_addfood_btn_true_dinner);
        btn_add_false = findViewById(R.id.modal_addfood_btn_false);
        add_foodtext.setText(sel_food_name+"을(를)\n추가하시겠습니까?");

        SharedPreferences preferences = getSharedPreferences("user_id", Context.MODE_PRIVATE);
        USER_ID = preferences.getString("user_id","");

        btn_add_breakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModalActivity.this.CheckList();
//                HashMap<String, Object> postingData = new HashMap<>();
//                postingData.put("record_id", 0);
//                postingData.put("user_id", "USER_ID");
//                postingData.put("date", date);
//                postingData.put("food", sel_food_name);
//                postingData.put("meal_time", "breakfast");
//                postingData.put("tan", sel_food_tan);
//                postingData.put("protein", sel_food_dan);
//                postingData.put("fat", sel_food_ji);
//                postingData.put("sugar", "00.0");
//                postingData.put("salt", "00.0");
//                postingData.put("kcal", sel_food_kcal);
//                InterfaceRecord methods = ClientRecord.getRetrofitInstance().create(InterfaceRecord.class);
//                methods.postData(postingData, "500").enqueue(new Callback<ModelRecord>() {
//                    @Override
//                    public void onResponse(Call<ModelRecord> call, Response<ModelRecord> response) {
//                        if(response.isSuccessful()){
//                            ModelRecord modelRecord = response.body();
//                            Log.e(TAG, "POST" + "post 성공 / 아침");
//                            Log.e(TAG, "POST" + modelRecord.getUser_id());
//                        }
//                    }
//                    @Override
//                    public void onFailure(Call<ModelRecord> call, Throwable t) {
//                        Log.e(TAG, "POST" + "post 실패 / 아침");
//                    }
//                });
            }
        });
        btn_add_lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModalActivity.this.CheckList();
//                HashMap<String, Object> postingData = new HashMap<>();
//                postingData.put("record_id", "100");
//                postingData.put("user_id", "USER_ID");
//                postingData.put("date", date);
//                postingData.put("food", sel_food_name);
//                postingData.put("meal_time", "lunch");
//                postingData.put("tan", sel_food_tan);
//                postingData.put("protein", sel_food_dan);
//                postingData.put("fat", sel_food_ji);
//                postingData.put("sugar", "00.0");
//                postingData.put("salt", "00.0");
//                postingData.put("kcal", sel_food_kcal);
//                InterfaceRecord methods = Client.getRetrofitInstance().create(InterfaceRecord.class);
//                methods.postData(postingData, "500").enqueue(new Callback<ModelRecord>() {
//                    @Override
//                    public void onResponse(Call<ModelRecord> call, Response<ModelRecord> response) {
//                        if(response.isSuccessful()){
//                            ModelRecord modelRecord = response.body();
//                            Log.e(TAG, "POST" + "post 성공 / 점심");
//                            Log.e(TAG, "POST" + modelRecord.getUser_id());
//                        }
//                    }
//                    @Override
//                    public void onFailure(Call<ModelRecord> call, Throwable t) {
//                        Log.e(TAG, "POST" + "post 실패 / 점심");
//                    }
//                });
            }
        });
        btn_add_dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModalActivity.this.CheckList();
//                HashMap<String, Object> postingData = new HashMap<>();
//                postingData.put("record_id", "100");
//                postingData.put("user_id", "USER_ID");
//                postingData.put("date", date);
//                postingData.put("food", sel_food_name);
//                postingData.put("meal_time", "breakfast");
//                postingData.put("tan", sel_food_tan);
//                postingData.put("protein", sel_food_dan);
//                postingData.put("fat", sel_food_ji);
//                postingData.put("sugar", "00.0");
//                postingData.put("salt", "00.0");
//                postingData.put("kcal", sel_food_kcal);
//                InterfaceRecord methods = Client.getRetrofitInstance().create(InterfaceRecord.class);
//                methods.postData(postingData, "500").enqueue(new Callback<ModelRecord>() {
//                    @Override
//                    public void onResponse(Call<ModelRecord> call, Response<ModelRecord> response) {
//                        if(response.isSuccessful()){
//                            ModelRecord modelRecord = response.body();
//                            Log.e(TAG, "POST" + "post 성공 / 저녁");
//                            Log.e(TAG, "POST" + modelRecord.getUser_id());
//                        }
//                    }
//                    @Override
//                    public void onFailure(Call<ModelRecord> call, Throwable t) {
//                        Log.e(TAG, "POST" + "post 실패 / 저녁");
//                    }
//                });
            }
        });
        btn_add_false.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }

    public void CheckList() {
        setContentView(R.layout.modal_checkfood);
        Button btn_go_listfood = (Button) findViewById(R.id.modal_checkfood_btn_true);
        Button btn_go_addfood = (Button) findViewById(R.id.modal_checkfood_btn_false);

        btn_go_listfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListFoodActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.go_down_1, R.anim.fadeout);
            }
        });
        btn_go_addfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        });

    }
}