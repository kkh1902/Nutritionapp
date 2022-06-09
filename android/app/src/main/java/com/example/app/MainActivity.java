package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    String sel_month, sel_day, sel_time, file_path;
    int sel_time_n;
    HorizontalScrollView scroll_month, scroll_day;
    ImageButton btn_profile;
    AppCompatButton btn_detail;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        long mNow;
        Date mDate;
        SimpleDateFormat formatMonth = new SimpleDateFormat("MM");
        SimpleDateFormat formatDay = new SimpleDateFormat("dd");
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        sel_time_n = 0;
        file_path = "jsons/test.json";
        int[] standard = {500, 40, 70, 2400};
        String json = null;
        String[] nutdata = {"carbohydrate", "protein", "fat", "kcal"};
//        System.out.println("월" + formatMonth.format(mDate));
//        System.out.println("일" + formatDay.format(mDate));

        //버튼들을 담을 배열 정의
        Button[] month = new Button[12];
        Button[] day = new Button[31];
        Button[] time = new Button[4];
        View[] arrView = new View[4];
        //레이아웃 정의
        scroll_month = (HorizontalScrollView) findViewById(R.id.frag1_wrap_month);
        scroll_day = (HorizontalScrollView) findViewById(R.id.frag1_wrap_day);
        btn_profile = (ImageButton) findViewById(R.id.frag1_btn_profile);
        btn_detail = (AppCompatButton) findViewById(R.id.frag1_btn_detail);
        //폰트 정의
        Typeface font_b = Typeface.createFromAsset(getAssets(), "font/font_b.ttf");
        Typeface font_r = Typeface.createFromAsset(getAssets(), "font/font_r.ttf");
        Typeface font_ul = Typeface.createFromAsset(getAssets(), "font/font_ul.ttf");

        try {
            InputStream is = getResources().getAssets().open(file_path);
            int fileSize = is.available();
            byte[] buffer = new byte[fileSize];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }
        catch (IOException ex) {ex.printStackTrace();}

        //날짜(월) 버튼 정의
        for (int i = 0; i < month.length; i++) {
            String buttonId = "frag1_btn_month" + (i + 1);
            month[i] = findViewById(getResources().getIdentifier(buttonId, "id", getPackageName()));
        }
        //날짜(알) 버튼 정의
        for (int i = 0; i < day.length; i++) {
            String buttonId = "frag1_btn_day" + (i + 1);
            day[i] = findViewById(getResources().getIdentifier(buttonId, "id", getPackageName()));
        }
        //아침, 점심, 저녁, 기타 버튼 정의
        for (int i = 0; i < time.length; i++) {
            String buttonId = "frag1_btn_time" + (i + 1);
            time[i] = findViewById(getResources().getIdentifier(buttonId, "id", getPackageName()));
        }
        //각 영양소별 바 정의
        for (int i = 0; i < arrView.length; i++) {
            String viewId = "frag1_attribute" + (i + 1) + "_data";
            arrView[i] = findViewById(getResources().getIdentifier(viewId, "id", getPackageName()));
        }

        //날짜(월) 버튼 이벤트 추가
        for (Button buttonId : month) {
            buttonId.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button result = findViewById(view.getId());
                    int idx = 0;
                    for (int i = 0; i < month.length; i++) {
                        month[i].setTextColor(Color.parseColor("#FFbbbbbb"));
                        month[i].setTypeface(font_ul);
                        if (month[i] == result) {
                            idx = i;
                        }
                    }
                    result.setTextColor(Color.BLACK);
                    result.setTypeface(font_r);
                    sel_month = (String) result.getText();
                    scroll_month.smoothScrollTo(240 * idx, 0);
                }
            });
        }
        //날짜(일) 버튼 이벤트 추가
        for (Button buttonId : day) {
            buttonId.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button result = findViewById(view.getId());
                    int idx = 0;
                    for (int i = 0; i < day.length; i++) {
                        day[i].setBackgroundResource(R.color.invisible);
                        if (day[i] == result) {
                            idx = i;
                        }
                    }
                    result.setBackgroundResource(R.drawable.selected_day);
                    sel_day = (String) result.getText();
                    scroll_day.smoothScrollTo(144 * idx, 0);
                }
            });
        }
        //시간 버튼 이벤트 추가
        for (Button buttonId : time) {
            buttonId.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button result = findViewById(view.getId());
                    String data = null;
                    int idx = 0;
                    for (int i = 0; i < time.length; i++) {
                        time[i].setTextColor(Color.parseColor("#FFeeeeee"));
                        time[i].setTypeface(font_ul);
                        if (time[i] == result) {
                            idx = i;
                        }
                    }
                    result.setTextColor(Color.WHITE);
                    result.setTypeface(font_b);
                    sel_time = (String) result.getText();
                    sel_time_n = idx;
                    try {
                        InputStream is = getResources().getAssets().open(file_path);
                        int fileSize = is.available();
                        byte[] buffer = new byte[fileSize];
                        is.read(buffer);
                        is.close();
                        data = new String(buffer, "UTF-8");
                    }
                    catch (IOException ex) {ex.printStackTrace();}
                    try {
                        JSONArray jsonArray = new JSONArray(data);
                        JSONObject jsonObject=jsonArray.getJSONObject(sel_time_n);
                        for(int i=0; i<arrView.length;i++){
                            int temp = Integer.parseInt(jsonObject.getString(nutdata[i]));
                            arrView[i].getLayoutParams().width = Integer.parseInt(String.valueOf(Math.round(temp/(standard[i]/3.0)*476)));
                        }

                    } catch (JSONException e) {e.printStackTrace();}
                }
            });
        }

        //선택된 버튼 기본값 설정
        sel_month = (String) month[Integer.parseInt(formatMonth.format(mDate))+1].getText();
        sel_day = (String) day[Integer.parseInt(formatDay.format(mDate))+1].getText();
        for (int i = 0; i < month.length; i++) {
            month[i].setTextColor(Color.parseColor("#FFbbbbbb"));
            month[i].setTypeface(font_ul);
        }
        month[Integer.parseInt(formatMonth.format(mDate))+1].setTextColor(Color.BLACK);
        month[Integer.parseInt(formatMonth.format(mDate))+1].setTypeface(font_r);
        for (int i = 0; i < day.length; i++) {
            day[i].setBackgroundResource(R.color.invisible);
        }
        day[Integer.parseInt(formatDay.format(mDate))+1].setBackgroundResource(R.drawable.selected_day);
        //스크롤 뷰 기본 위치 설정
        Handler setScrollPosition = new Handler();
        setScrollPosition.postDelayed(new Runnable() {
            @Override
            public void run() {
                scroll_month.smoothScrollTo(240 * (Integer.parseInt(formatMonth.format(mDate))+1), 0);
                scroll_day.smoothScrollTo(144 * (Integer.parseInt(formatDay.format(mDate))+1), 0);
            }
        }, 300);

        try {
            JSONArray jsonArray = new JSONArray(json);
            JSONObject jsonObject=jsonArray.getJSONObject(sel_time_n);
            for(int i=0; i<arrView.length;i++){
                int temp = Integer.parseInt(jsonObject.getString(nutdata[i]));
                arrView[i].getLayoutParams().width = Integer.parseInt(String.valueOf(Math.round(temp/(standard[i]/3.0)*476)));
            }

        } catch (JSONException e) {e.printStackTrace();}



        //프로필 페이지 전환
        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.go_down_1, R.anim.go_down_2);
            }
        });
        //상세 보기 페이지 전환
        btn_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), Detail_Activity.class);
                intent.putExtra("sel_month",sel_month);
                intent.putExtra("sel_day",sel_day);
                startActivity(intent);
                overridePendingTransition(R.anim.go_down_1, R.anim.go_down_2);
//                Toast.makeText(MainActivity.this, "test", Toast.LENGTH_SHORT).show();
            }
        });
    }
}