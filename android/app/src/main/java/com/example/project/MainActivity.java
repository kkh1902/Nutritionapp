package com.example.project;

import static java.lang.Math.min;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.widget.NestedScrollView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    //메인페이지
    private static final String TAG = "MainActivity";
    private String USER_ID, USER_NAME;
    private String sel_year, sel_month, sel_day, sel_mealtime, min_nutr;
    private ArrayList<ModelRecord> arrayList;
    private ArrayList<ModelRecommend> arrayListRecommend;
    private View barKcal, barTan, barDan, barJi, barSugar, barSalt, time_sel1, time_sel2, time_sel3;
    private InterfaceRecord interfaces;
    private InterfaceRecommend interfaceRecommend;
    private InterfaceUser interfaceUser;
    private InterfaceRecord interfaceRecord;
    private Intent intent;
    private AppCompatButton btn_detail, btn_addfood, btn_listfood, btn_time_morning, btn_time_lunch, btn_time_dinner;
    private CalendarView calendarView;
    private TextView content1_title;
    private ScrollView scrollView;

    //메인-디테일 페이지
    private AppCompatButton btn_recommend;
    private RelativeLayout wrap_recommend, wrap_recommend_detail;
    private int bnt_recommend_status = 0;
    private TextView text_user_kcal, text_user_tan, text_user_dan,text_user_ji, text_rec_food1, text_rec_food2, text_rec_food3;
    private ImageView status_user_kcal, status_user_tan, status_user_dan, status_user_ji;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SimpleDateFormat formatYear = new SimpleDateFormat("yyyy");
        SimpleDateFormat formatMonth = new SimpleDateFormat("MM");
        SimpleDateFormat formatDay = new SimpleDateFormat("dd");

        SharedPreferences preferences = getSharedPreferences("user_data", Context.MODE_PRIVATE);
        USER_ID = preferences.getString("user_id","");
        USER_NAME = preferences.getString("user_name","");

        //레이아웃 정의(메인)
        scrollView = (ScrollView) findViewById(R.id.main_scrollview);
        btn_addfood = (AppCompatButton) findViewById(R.id.main_btn_addfood);
        btn_listfood = (AppCompatButton) findViewById(R.id.main_btn_listfood);
        btn_detail = (AppCompatButton) findViewById(R.id.main_btn_profile);
        calendarView = (CalendarView) findViewById(R.id.main_calendarview);
        btn_time_morning = (AppCompatButton) findViewById(R.id.btn_time_breakfast);
        btn_time_lunch = (AppCompatButton) findViewById(R.id.btn_time_lunch);
        btn_time_dinner = (AppCompatButton) findViewById(R.id.btn_time_dinner);
        content1_title = (TextView) findViewById(R.id.main_title_content1);
        barKcal = (View) findViewById(R.id.main_bar_kcal);
        barTan = (View) findViewById(R.id.main_bar_tan);
        barDan = (View) findViewById(R.id.main_bar_dan);
        barJi = (View) findViewById(R.id.main_bar_ji);
        barSugar = (View) findViewById(R.id.main_bar_sugar);
        barSalt = (View) findViewById(R.id.main_bar_salt);
        time_sel1 = (View) findViewById(R.id.btn_time_breakfast_sel);
        time_sel2 = (View) findViewById(R.id.btn_time_lunch_sel);
        time_sel3 = (View) findViewById(R.id.btn_time_dinner_sel);

        //레이아웃 정의(메인-디테일)
        btn_recommend = (AppCompatButton) findViewById(R.id.detail_btn_recommend_detail);
        wrap_recommend = (RelativeLayout) findViewById(R.id.detail_wrap_recommend);
        wrap_recommend_detail = (RelativeLayout) findViewById(R.id.detail_wrap_recommend_detail);
        text_user_kcal = (TextView)  findViewById(R.id.detail_user_kcal);
        text_user_tan = (TextView)  findViewById(R.id.detail_user_tan);
        text_user_dan = (TextView)  findViewById(R.id.detail_user_dan);
        text_user_ji = (TextView)  findViewById(R.id.detail_user_ji);
        text_rec_food1 = (TextView) findViewById(R.id.detail_recommend_food1);
        text_rec_food2 = (TextView) findViewById(R.id.detail_recommend_food2);
        text_rec_food3 = (TextView) findViewById(R.id.detail_recommend_food3);
        status_user_kcal = (ImageView) findViewById(R.id.detail_user_kcal_status);
        status_user_tan = (ImageView) findViewById(R.id.detail_user_tan_status);
        status_user_dan = (ImageView) findViewById(R.id.detail_user_dan_status);
        status_user_ji = (ImageView) findViewById(R.id.detail_user_ji_status);

        content1_title.setText(USER_NAME + "님이 섭취하신 영양소입니다.");
        sel_year = formatYear.format(calendarView.getDate());
        sel_month = formatMonth.format(calendarView.getDate());
        sel_day = formatDay.format(calendarView.getDate());
        sel_mealtime = "아침";

        barKcal.getLayoutParams().height = 16;
        barTan.getLayoutParams().height = 16;
        barDan.getLayoutParams().height = 16;
        barJi.getLayoutParams().height = 16;
        barSugar.getLayoutParams().height = 16;
        barSalt.getLayoutParams().height = 16;

        arrayList = new ArrayList<ModelRecord>();
        arrayListRecommend = new ArrayList<ModelRecommend>();
        interfaces = Client.getRetrofitInstance().create(InterfaceRecord.class);
        interfaceRecord = Client.getRetrofitInstance().create(InterfaceRecord.class);
        interfaceRecommend = Client.getRetrofitInstance().create(InterfaceRecommend.class);
        interfaceUser = Client.getRetrofitInstance().create(InterfaceUser.class);

        Call<ArrayList<ModelRecord>> callRecord = interfaceRecord.getRecordData(USER_ID);
        callRecord.enqueue(new Callback<ArrayList<ModelRecord>>() {
            @Override
            public void onResponse(Call<ArrayList<ModelRecord>> call, Response<ArrayList<ModelRecord>> response) {
                arrayList.clear();
                Double sum_kcal = 0.0, sum_tan = 0.0, sum_dan = 0.0, sum_ji = 0.0;
                int temp_status_kcal = 0, temp_status_tan = 0, temp_status_dan = 0, temp_status_ji = 0;
                ArrayList<ModelRecord> records = response.body();
                if (records != null) {
                    for (int i=0; i < records.toArray().length; i++) {
                        if (records.get(i).getDate().substring(0,10).equals(sel_year+"-"+sel_month+"-"+sel_day)) {
                            sum_kcal = sum_kcal + Double.parseDouble(records.get(i).getKcal());
                            sum_tan = sum_tan + Double.parseDouble(records.get(i).getTan());
                            sum_dan = sum_dan + Double.parseDouble(records.get(i).getDan());
                            sum_ji = sum_ji + Double.parseDouble(records.get(i).getFat());
                        }
                    }
                    text_user_kcal.setText(String.format("%.1f",sum_kcal) + " Kcal");
                    text_user_tan.setText(String.format("%.1f",sum_tan) + " g");
                    text_user_dan.setText(String.format("%.1f",sum_dan) + " g");
                    text_user_ji.setText(String.format("%.1f",sum_ji) + " g");
                    if (temp_status_kcal == 0) {
                        status_user_kcal.setImageResource(R.drawable.img_good);
                    } else {
                        status_user_kcal.setImageResource(R.drawable.img_warning);
                    }
                    if (temp_status_tan == 0) {
                        status_user_tan.setImageResource(R.drawable.img_good);
                    } else {
                        status_user_tan.setImageResource(R.drawable.img_warning);
                    }
                    if (temp_status_dan == 0) {
                        status_user_dan.setImageResource(R.drawable.img_good);
                    } else {
                        status_user_dan.setImageResource(R.drawable.img_warning);
                    }
                    if (temp_status_ji == 0) {
                        status_user_ji.setImageResource(R.drawable.img_good);
                    } else {
                        status_user_ji.setImageResource(R.drawable.img_warning);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ModelRecord>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                text_user_kcal.setText("error");
                text_user_tan.setText("error");
                text_user_dan.setText("error");
                text_user_ji.setText("error");
                status_user_kcal.setImageResource(R.drawable.img_get_error);
                status_user_tan.setImageResource(R.drawable.img_get_error);
                status_user_dan.setImageResource(R.drawable.img_get_error);
                status_user_ji.setImageResource(R.drawable.img_get_error);
            }
        });

        Call<ArrayList<ModelRecommend>> callRecommend = interfaceRecommend.getRecommendFood();
        callRecommend.enqueue(new Callback<ArrayList<ModelRecommend>>() {
            @Override
            public void onResponse(Call<ArrayList<ModelRecommend>> call, Response<ArrayList<ModelRecommend>> response) {
                Double sum_kcal = 0.0, sum_tan = 0.0, sum_dan = 0.0, sum_ji = 0.0;
                int temp_status_kcal = 0, temp_status_tan = 0, temp_status_dan = 0, temp_status_ji = 0;
                ArrayList<ModelRecommend> recommends = response.body();
//                text_rec_food1.setText(recommends.get(0).getFood_name());
//                text_rec_food2.setText(recommends.get(1).getFood_name());
//                text_rec_food3.setText(recommends.get(2).getFood_name());
                text_rec_food1.setText("곰탕");
                text_rec_food2.setText("연어샐러드");
                text_rec_food3.setText("생선찜");
            }

            @Override
            public void onFailure(Call<ArrayList<ModelRecommend>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                text_rec_food1.setText("error");
                text_rec_food2.setText("error");
                text_rec_food3.setText("error");
            }
        });

        updateBarData(sel_mealtime);

        //캘린더 선택 날짜
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                sel_year = String.format("%04d", year);
                sel_month = String.format("%02d", month+1);
                sel_day = String.format("%02d", day);
                arrayList.clear();
                Animation visible = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.visible_v);
                Animation invisible = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.invisible_v);
                time_sel1.setVisibility(View.VISIBLE);
                time_sel2.setVisibility(View.INVISIBLE);
                time_sel3.setVisibility(View.INVISIBLE);
                time_sel1.setAnimation(visible);
                if (sel_mealtime.equals("점심")) {
                    time_sel2.setAnimation(invisible);
                }
                if (sel_mealtime.equals("저녁")) {
                    time_sel3.setAnimation((invisible));
                }
                sel_mealtime = "아침";
                updateBarData(sel_mealtime);

                Call<ArrayList<ModelRecord>> call = interfaceRecord.getRecordData(USER_ID);
                call.enqueue(new Callback<ArrayList<ModelRecord>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ModelRecord>> lcal, Response<ArrayList<ModelRecord>> response) {
                        arrayList.clear();
                        Double sum_kcal = 0.0, sum_tan = 0.0, sum_dan = 0.0, sum_ji = 0.0;
                        int temp_status_kcal = 0, temp_status_tan = 0, temp_status_dan = 0, temp_status_ji = 0;
                        ArrayList<ModelRecord> records = response.body();
                        for (int i=0; i < records.toArray().length; i++) {
                            if (records.get(i).getDate().substring(0,10).equals(sel_year+"-"+sel_month+"-"+sel_day)) {
                                sum_kcal = sum_kcal + Double.parseDouble(records.get(i).getKcal());
                                sum_tan = sum_tan + Double.parseDouble(records.get(i).getTan());
                                sum_dan = sum_dan + Double.parseDouble(records.get(i).getDan());
                                sum_ji = sum_ji + Double.parseDouble(records.get(i).getFat());
                            }
                        }
                        text_user_kcal.setText(String.format("%.1f",sum_kcal) + " Kcal");
                        text_user_tan.setText(String.format("%.1f",sum_tan) + " g");
                        text_user_dan.setText(String.format("%.1f",sum_dan) + " g");
                        text_user_ji.setText(String.format("%.1f",sum_ji) + " g");
                        if (temp_status_kcal == 0) {
                            status_user_kcal.setImageResource(R.drawable.img_good);
                        } else {
                            status_user_kcal.setImageResource(R.drawable.img_warning);
                        }
                        if (temp_status_tan == 0) {
                            status_user_tan.setImageResource(R.drawable.img_good);
                        } else {
                            status_user_tan.setImageResource(R.drawable.img_warning);
                        }
                        if (temp_status_dan == 0) {
                            status_user_dan.setImageResource(R.drawable.img_good);
                        } else {
                            status_user_dan.setImageResource(R.drawable.img_warning);
                        }
                        if (temp_status_ji == 0) {
                            status_user_ji.setImageResource(R.drawable.img_good);
                        } else {
                            status_user_ji.setImageResource(R.drawable.img_warning);
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<ModelRecord>> call, Throwable t) {
                        Log.e(TAG, "onFailure: " + t.getMessage());
                        text_user_kcal.setText("error");
                        text_user_tan.setText("error");
                        text_user_dan.setText("error");
                        text_user_ji.setText("error");
                        status_user_kcal.setImageResource(R.drawable.img_get_error);
                        status_user_tan.setImageResource(R.drawable.img_get_error);
                        status_user_dan.setImageResource(R.drawable.img_get_error);
                        status_user_ji.setImageResource(R.drawable.img_get_error);
                    }
                });
            }
        });
        updateBarData(sel_mealtime);

        btn_time_morning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation visible = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.visible_v);
                Animation invisible = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.invisible_v);
                time_sel1.setVisibility(View.VISIBLE);
                time_sel2.setVisibility(View.INVISIBLE);
                time_sel3.setVisibility(View.INVISIBLE);
                time_sel1.setAnimation(visible);
                if (sel_mealtime.equals("점심")) {
                    time_sel2.setAnimation(invisible);
                }
                if (sel_mealtime.equals("저녁")) {
                    time_sel3.setAnimation((invisible));
                }
                sel_mealtime = "아침";
                updateBarData(sel_mealtime);
            }
        });
        btn_time_lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation visible = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.visible_v);
                Animation invisible = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.invisible_v);
                time_sel1.setVisibility(View.INVISIBLE);
                time_sel2.setVisibility(View.VISIBLE);
                time_sel3.setVisibility(View.INVISIBLE);
                time_sel1.setAnimation(visible);
                if (sel_mealtime.equals("아침")) {
                    time_sel1.setAnimation(invisible);
                }
                if (sel_mealtime.equals("저녁")) {
                    time_sel3.setAnimation((invisible));
                }
                sel_mealtime = "점심";
                updateBarData(sel_mealtime);
            }
        });
        btn_time_dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation visible = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.visible_v);
                Animation invisible = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.invisible_v);
                time_sel1.setVisibility(View.INVISIBLE);
                time_sel2.setVisibility(View.INVISIBLE);
                time_sel3.setVisibility(View.VISIBLE);
                time_sel3.setAnimation(visible);
                if (sel_mealtime.equals("아침")) {
                    time_sel1.setAnimation(invisible);
                }
                if (sel_mealtime.equals("점심")) {
                    time_sel2.setAnimation((invisible));
                }
                sel_mealtime = "저녁";
                updateBarData(sel_mealtime);
            }
        });

        //음식 추가 페이지 전환
        btn_addfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), AddFoodActivity.class);
                intent.putExtra("sel_date", sel_year + "-" + sel_month + "-" + sel_day);
                startActivity(intent);
                overridePendingTransition(R.anim.go_down_1, R.anim.go_down_2);
            }
        });
        //음식 목록 페이지 전환
        btn_listfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), ListFoodActivity.class);
                intent.putExtra("sel_date", sel_year + "-" + sel_month + "-" + sel_day);
                Log.e(TAG, "mealtime" +sel_mealtime);
                intent.putExtra("sel_mealtime", sel_mealtime);
                startActivity(intent);
                overridePendingTransition(R.anim.go_down_1, R.anim.go_down_2);
            }
        });
        //상세 보기 페이지 전환
        btn_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), ProfileActivity.class);
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
                btn_detail.setBackgroundResource(R.drawable.img_profile_activity_sel);
                return false;
            }
        });
        btn_recommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bnt_recommend_status == 0) {
                    Animation visible = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.visible);
                    btn_recommend.setText("숨기기");
                    wrap_recommend_detail.setVisibility(View.VISIBLE);
                    wrap_recommend_detail.setAnimation(visible);

                    bnt_recommend_status = 1;
                } else {
                    btn_recommend.setText("더보기");
                    final Handler handler = new Handler();
                    Animation invisible = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.invisible);
                    wrap_recommend_detail.setVisibility(View.INVISIBLE);
                    wrap_recommend_detail.setAnimation(invisible);
//                    wrap_recommend_detail.setVisibility(View.GONE);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            wrap_recommend_detail.setVisibility(View.GONE);
                        }
                    }, 300);

                    bnt_recommend_status = 0;
                }
            }
        });

    }
    private void updateBarData(String mealtime) {
        arrayList.clear();
        Call<ArrayList<ModelRecord>> call = interfaceRecord.getRecordData(USER_ID);
        call.enqueue(new Callback<ArrayList<ModelRecord>>() {
            @Override
            public void onResponse(Call<ArrayList<ModelRecord>> call, Response<ArrayList<ModelRecord>> response) {
                arrayList.clear();
                ArrayList<ModelRecord> foods = response.body();
                double tempKcal = 0.0, tempTan = 0.0, tempDan = 0.0, tempJi = 0.0, tempSugar = 0.0, tempSalt = 0.0;
                double avgKcal = 2000.0/3.0, avgTan = 292.0/3.0, avgDan = 71.0/3.0, avgJi = 49.0/3.0, avgSugar = 60.0/3.0, avgSalt = 3668.0/3.0; //임시 평균값
                for (int i = 0; i < foods.toArray().length; i++) {
                    String temp_meal_time = foods.get(i).getMeal_time();
                    String temp_date = foods.get(i).getDate().substring(0,10);
                    if (temp_date.equals(sel_year + "-" + sel_month + "-" + sel_day)) {
                        if (temp_meal_time.equals(sel_mealtime)) {
                            if (foods.get(i).getKcal().equals("N/A")) {tempKcal = tempKcal + 0.0;} else { tempKcal = tempKcal + Double.parseDouble(foods.get(i).getKcal());}
                            if (foods.get(i).getTan().equals("N/A")) {tempTan = tempTan + 0.0; } else { tempTan = tempTan + Double.parseDouble(foods.get(i).getTan());}
                            if (foods.get(i).getDan().equals("N/A")) {tempDan = tempDan + 0.0; } else { tempDan = tempDan + Double.parseDouble(foods.get(i).getDan());}
                            if (foods.get(i).getFat().equals("N/A")) {tempJi = tempJi + 0.0; } else { tempJi = tempJi + Double.parseDouble(foods.get(i).getFat());}
                            if (foods.get(i).getSugar().equals("N/A")) {tempSugar = tempSugar + 0.0; } else { tempSugar = tempSugar + Double.parseDouble(foods.get(i).getSugar());}
                            if (foods.get(i).getSalt().equals("N/A")) {tempSalt = tempSalt + 0.0; } else { tempSalt = tempSalt + Double.parseDouble(foods.get(i).getSalt());}
                            Log.e(TAG, "temp : " + tempKcal +"/"+ tempTan +"/"+ tempDan +"/"+ tempJi +"/"+ tempSugar +"/"+ tempSalt);
                        }
                    }
                    else {min_nutr = "{empty}";}
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
    private void calc_lackNutrition(double tan, double dan, double ji) {

    }
}