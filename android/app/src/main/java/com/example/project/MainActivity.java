package com.example.project;

import static java.lang.Math.min;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private String USER_ID;
    private String sel_year, sel_month, sel_day, sel_mealtime, min_nutr;
    private ArrayList<ModelRecord> arrayList;
    private View barKcal, barTan, barDan, barJi, barSugar, barSalt;
    private InterfaceRecord interfaces;
    private Intent intent;
    private AppCompatButton btn_detail, btn_addfood, btn_listfood, btn_time_morning, btn_time_lunch, btn_time_dinner;
    private CalendarView calendarView;
    private TextView content1_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SimpleDateFormat formatYear = new SimpleDateFormat("yyyy");
        SimpleDateFormat formatMonth = new SimpleDateFormat("MM");
        SimpleDateFormat formatDay = new SimpleDateFormat("dd");

        SharedPreferences preferences = getSharedPreferences("user_id", Context.MODE_PRIVATE);
        USER_ID = preferences.getString("user_id","");

        //레이아웃 정의
        btn_addfood = (AppCompatButton) findViewById(R.id.main_btn_addfood);
        btn_listfood = (AppCompatButton) findViewById(R.id.main_btn_listfood);
        btn_detail = (AppCompatButton) findViewById(R.id.main_btn_detail);
        calendarView = (CalendarView) findViewById(R.id.main_calendarview);
        btn_time_morning = (AppCompatButton) findViewById(R.id.btn_time_morning);
        btn_time_lunch = (AppCompatButton) findViewById(R.id.btn_time_lunch);
        btn_time_dinner = (AppCompatButton) findViewById(R.id.btn_time_dinner);
        content1_title = (TextView) findViewById(R.id.main_title_content1);
        barKcal = (View) findViewById(R.id.main_bar_kcal);
        barTan = (View) findViewById(R.id.main_bar_tan);
        barDan = (View) findViewById(R.id.main_bar_dan);
        barJi = (View) findViewById(R.id.main_bar_ji);
        barSugar = (View) findViewById(R.id.main_bar_sugar);
        barSalt = (View) findViewById(R.id.main_bar_salt);

        content1_title.setText(USER_ID + "님이 섬취하신 영양소입니다.");
        sel_year = formatYear.format(calendarView.getDate());
        sel_month = formatMonth.format(calendarView.getDate());
        sel_day = formatDay.format(calendarView.getDate());
        sel_mealtime = "breakfast";

        barKcal.getLayoutParams().height = 0;
        barTan.getLayoutParams().height = 0;
        barDan.getLayoutParams().height = 0;
        barJi.getLayoutParams().height = 0;
        barSugar.getLayoutParams().height = 0;
        barSalt.getLayoutParams().height = 0;

        arrayList = new ArrayList<ModelRecord>();
        interfaces = ClientRecord.getRetrofitInstance().create(InterfaceRecord.class);
        updateBarData(sel_mealtime);

        //캘린더 선택 날짜
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                sel_year = Integer.toString(year); //yyyy
                sel_month = Integer.toString(month); //MM
                sel_day = Integer.toString(day); //dd
            }
        });

        btn_time_morning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_time_morning.setBackgroundResource(R.drawable.shape_btn_time_selector_sel);
                btn_time_lunch.setBackgroundResource(R.drawable.shape_btn_time_selector);
                btn_time_dinner.setBackgroundResource(R.drawable.shape_btn_time_selector);
                sel_mealtime = "breakfast";
                updateBarData(sel_mealtime);
            }
        });
        btn_time_lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_time_morning.setBackgroundResource(R.drawable.shape_btn_time_selector);
                btn_time_lunch.setBackgroundResource(R.drawable.shape_btn_time_selector_sel);
                btn_time_dinner.setBackgroundResource(R.drawable.shape_btn_time_selector);
                sel_mealtime = "lunch";
                updateBarData(sel_mealtime);
            }
        });
        btn_time_dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_time_morning.setBackgroundResource(R.drawable.shape_btn_time_selector);
                btn_time_lunch.setBackgroundResource(R.drawable.shape_btn_time_selector);
                btn_time_dinner.setBackgroundResource(R.drawable.shape_btn_time_selector_sel);
                sel_mealtime = "dinner";
                updateBarData(sel_mealtime);
            }
        });

        //음식 추가 페이지 전환
        btn_addfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), AddFoodActivity.class);
                intent.putExtra("sel_month",sel_month);
                intent.putExtra("sel_day",sel_day);
                startActivity(intent);
                overridePendingTransition(R.anim.go_down_1, R.anim.go_down_2);
            }
        });
        //음식 목록 페이지 전환
        btn_listfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), ListFoodActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.go_down_1, R.anim.go_down_2);
            }
        });
        //상세 보기 페이지 전환
        btn_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                intent = new Intent(getApplicationContext(), DetailActivity.class);
//                intent.putExtra("sel_month",sel_month);
//                intent.putExtra("sel_day",sel_day);
//                startActivity(intent);
//                overridePendingTransition(R.anim.go_down_1, R.anim.go_down_2);
                intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.go_down_1, R.anim.go_down_2);
            }
        });
        btn_addfood.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                btn_addfood.setBackgroundResource(R.drawable.img_addfood_activity_sel);
                return false;
            }
        });
        btn_listfood.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                btn_listfood.setBackgroundResource(R.drawable.img_listfood_activity_sel);
                return false;
            }
        });
        btn_detail.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                btn_detail.setBackgroundResource(R.drawable.img_detail_activity_sel);
                return false;
            }
        });

    }
    private void updateBarData(String mealtime) {
        arrayList.clear();
        Call<ArrayList<ModelRecord>> call = interfaces.getRecordData(USER_ID);
        call.enqueue(new Callback<ArrayList<ModelRecord>>() {
            @Override
            public void onResponse(Call<ArrayList<ModelRecord>> call, Response<ArrayList<ModelRecord>> response) {
                ArrayList<ModelRecord> foods = response.body();
                double tempKcal = 0.0, tempTan = 0.0, tempDan = 0.0, tempJi = 0.0, tempSugar = 0.0, tempSalt = 0.0;
                double avgKcal = 600.0, avgTan = 900.0, avgDan = 900.0, avgJi = 900.0, avgSugar = 0.0, avgSalt = 0.0; //임시 평균값
                for (int i = 0; i < foods.toArray().length; i++) {
                    String temp_meal_time = foods.get(i).getMeal_time();
                    if (temp_meal_time.equals(sel_mealtime)) {
                        tempKcal = tempKcal + Double.parseDouble(foods.get(i).getKcal());
                        tempTan = tempTan + Double.parseDouble(foods.get(i).getTan());
                        tempDan = tempDan + Double.parseDouble(foods.get(i).getProtein());
                        tempJi = tempJi + Double.parseDouble(foods.get(i).getFat());
                        tempSugar = tempSugar + Double.parseDouble(foods.get(i).getSugar());
                        tempSalt = tempSalt + Double.parseDouble(foods.get(i).getSalt());
                        Log.e(TAG, "Kcal" + tempDan);
                    } else {
                        Log.e(TAG, "No Data");
                        min_nutr = "{empty}";
                    }
                }
                barKcal.getLayoutParams().height = (int)Math.round(116 * (tempKcal / avgKcal));
                barTan.getLayoutParams().height = (int)Math.round(116 * (tempTan / avgTan));
                barDan.getLayoutParams().height = (int)Math.round(116 * (tempDan / avgDan));
                barJi.getLayoutParams().height = (int)Math.round(116 * (tempJi / avgJi));
                barSugar.getLayoutParams().height = (int)Math.round(116 * (tempSugar / avgSugar));
                barSalt.getLayoutParams().height = (int)Math.round(116 * (tempSalt / avgSalt));
                barKcal.requestLayout();
                barTan.requestLayout();
                barDan.requestLayout();
                barJi.requestLayout();
                barSugar.requestLayout();
                barSalt.requestLayout();
            }
            @Override
            public void onFailure(Call<ArrayList<ModelRecord>> call, Throwable t) {
                barKcal.getLayoutParams().height = 1;
                barTan.getLayoutParams().height = 1;
                barDan.getLayoutParams().height = 1;
                barJi.getLayoutParams().height = 1;
                barSugar.getLayoutParams().height = 1;
                barSalt.getLayoutParams().height = 1;
                barKcal.requestLayout();
                barTan.requestLayout();
                barDan.requestLayout();
                barJi.requestLayout();
                barSugar.requestLayout();
                barSalt.requestLayout();
                min_nutr = "{error}";
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}