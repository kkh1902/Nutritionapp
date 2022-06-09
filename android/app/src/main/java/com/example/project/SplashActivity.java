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
import android.text.Spannable;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
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
import java.util.Date;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private String USER_ID;
    private TextView title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferences preferences = getSharedPreferences("user_id", Context.MODE_PRIVATE);
        USER_ID = preferences.getString("user_id","");
        Log.e(TAG, "userid /" + USER_ID + "/");
        if (USER_ID.equals("")) {
            moveMain(1,true);
        } else {
            moveMain(1, false);
        }
    }

    private void moveMain(int sec, boolean check_first) {
        new Handler().postDelayed(new Runnable() {

            Intent intent;

            @Override
            public void run() {
                if (check_first == true) {
                    intent = new Intent(getApplicationContext(), LoginActivity.class);
                } else {
                    intent = new Intent(getApplicationContext(), MainActivity.class);
                }
                startActivity(intent);
                overridePendingTransition(R.anim.go_down_1, R.anim.go_down_2);
                finish();
            }
        }, 1000 * sec);
    }
}