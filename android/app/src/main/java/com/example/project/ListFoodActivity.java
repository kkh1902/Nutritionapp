//사용자별 추가된 음식목록 액티비티
package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListFoodActivity extends AppCompatActivity {

    private static final String TAG = "ListFoodActivity";
    private String USER_ID, sel_date, sel_mealtime;
    private ArrayList<ModelRecord> arrayList;
    private Intent intent;
    private AdapterListRecord arrayAdapter;
    private InterfaceRecord interfaces;
    private AppCompatButton btn_breakfast, btn_lunch, btn_dinner;
    private ImageButton btn_back;
    private ListView foodlist;
    private RelativeLayout wrap_empty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listfood);
        btn_back = (ImageButton) findViewById(R.id.listfood_btn_back);
        btn_breakfast = (AppCompatButton) findViewById(R.id.listfood_btn_breakfast);
        btn_lunch = (AppCompatButton) findViewById(R.id.listfood_btn_lunch);
        btn_dinner = (AppCompatButton) findViewById(R.id.listfood_btn_dinner);
        foodlist = (ListView) findViewById(R.id.listood_list_getlist);
        wrap_empty = (RelativeLayout) findViewById(R.id.listfood_wrap_empty);

        SharedPreferences preferences = getSharedPreferences("user_data", Context.MODE_PRIVATE);
        USER_ID = preferences.getString("user_id","");

        intent = getIntent();
        sel_date = intent.getExtras().getString("sel_date");
        sel_mealtime = intent.getExtras().getString("sel_mealtime");
        Log.e(TAG, "mealtime"+sel_mealtime);

        if (sel_mealtime.equals("아침")) {
            selBtnGroup(btn_breakfast,btn_lunch,btn_dinner,0);
        } else if (sel_mealtime.equals("점심")) {
            selBtnGroup(btn_breakfast,btn_lunch,btn_dinner,1);
        } else if (sel_mealtime.equals("저녁")) {
            selBtnGroup(btn_breakfast,btn_lunch,btn_dinner,2);
        }

        arrayList = new ArrayList<ModelRecord>();
        arrayAdapter = new AdapterListRecord(this, arrayList);
        interfaces = Client.getRetrofitInstance().create(InterfaceRecord.class);
        foodlist.setAdapter(arrayAdapter);

        getFoodList(sel_mealtime);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.go_up_1, R.anim.go_up_2);
                finish();
            }
        });
        btn_breakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selBtnGroup(btn_breakfast,btn_lunch,btn_dinner,0);
                getFoodList("아침");
            }
        });
        btn_lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selBtnGroup(btn_breakfast,btn_lunch,btn_dinner,1);
                getFoodList("점심");
            }
        });
        btn_dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selBtnGroup(btn_breakfast,btn_lunch,btn_dinner,2);
                getFoodList("저녁");
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

    private void selBtnGroup(AppCompatButton left, AppCompatButton middle, AppCompatButton right, int val) {
        left.setBackgroundResource(R.drawable.shape_group_btn_left);
        middle.setBackgroundResource(R.drawable.shape_group_btn_middle);
        right.setBackgroundResource(R.drawable.shape_group_btn_right);
        left.setTextColor(Color.parseColor("#FF8b9db0"));
        middle.setTextColor(Color.parseColor("#FF8b9db0"));
        right.setTextColor(Color.parseColor("#FF8b9db0"));
        if (val == 0) {
            left.setBackgroundResource(R.drawable.shape_group_btn_left_sel);
            left.setTextColor(Color.parseColor("#FF12283e"));
        }
        if (val == 1) {
            middle.setBackgroundResource(R.drawable.shape_group_btn_middle_sel);
            middle.setTextColor(Color.parseColor("#FF12283e"));
        }
        if (val == 2) {
            right.setBackgroundResource(R.drawable.shape_group_btn_right_sel);
            right.setTextColor(Color.parseColor("#FF12283e"));
        }
    }
    private void getFoodList(String mealTime) {
        arrayList.clear();
        Call<ArrayList<ModelRecord>> call = interfaces.getRecordData(USER_ID);
        call.enqueue(new Callback<ArrayList<ModelRecord>>() {
            @Override
            public void onResponse(Call<ArrayList<ModelRecord>> lcal, Response<ArrayList<ModelRecord>> response) {
                ArrayList<ModelRecord> foods = response.body();
                for (int i=0; i < foods.toArray().length; i++) {
                    String temp_date = foods.get(i).getDate().substring(0,10);
                    String temp_meal_time = foods.get(i).getMeal_time();
                    if (temp_date.equals(sel_date)) {
                        if (temp_meal_time.equals(mealTime)) {
                            arrayList.add(foods.get(i));
                            wrap_empty.setVisibility(View.GONE);
                        }
                    }
                    else {
                        wrap_empty.setVisibility(View.VISIBLE);
                    }
                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<ModelRecord>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}
