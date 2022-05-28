package com.example.app;

import android.graphics.Color;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class Fragment1 extends Fragment{
    MainActivity activity;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView= (ViewGroup) inflater.inflate(R.layout.fragment2,container,false);
        Button btn_t1 = rootView.findViewById(R.id.btn_t1);
        Button btn_t2 = rootView.findViewById(R.id.btn_t2);
        Button btn_t3 = rootView.findViewById(R.id.btn_t3);
        Button btn_t4 = rootView.findViewById(R.id.btn_t4);
        LinearLayout content_t1 = rootView.findViewById(R.id.content_t1);
        LinearLayout content_t2 = rootView.findViewById(R.id.content_t2);
        LinearLayout content_t3 = rootView.findViewById(R.id.content_t3);
        LinearLayout content_t4 = rootView.findViewById(R.id.content_t4);
        HorizontalScrollView flag1_view = rootView.findViewById(R.id.flag1_view);
        View content1_bar1 = rootView.findViewById(R.id.flag1_content1_bar1);
        View content1_bar2 = rootView.findViewById(R.id.flag1_content1_bar2);
        View content1_bar3 = rootView.findViewById(R.id.flag1_content1_bar3);
        View content1_bar4 = rootView.findViewById(R.id.flag1_content1_bar4);
        View content2_bar1 = rootView.findViewById(R.id.flag1_content2_bar1);
        View content2_bar2 = rootView.findViewById(R.id.flag1_content2_bar2);
        View content2_bar3 = rootView.findViewById(R.id.flag1_content2_bar3);
        View content2_bar4 = rootView.findViewById(R.id.flag1_content2_bar4);
        View content3_bar1 = rootView.findViewById(R.id.flag1_content3_bar1);
        View content3_bar2 = rootView.findViewById(R.id.flag1_content3_bar2);
        View content3_bar3 = rootView.findViewById(R.id.flag1_content3_bar3);
        View content3_bar4 = rootView.findViewById(R.id.flag1_content3_bar4);
        View content4_bar1 = rootView.findViewById(R.id.flag1_content4_bar1);
        View content4_bar2 = rootView.findViewById(R.id.flag1_content4_bar2);
        View content4_bar3 = rootView.findViewById(R.id.flag1_content4_bar3);
        View content4_bar4 = rootView.findViewById(R.id.flag1_content4_bar4);
        TextView flag1_text_main = rootView.findViewById(R.id.text_main);
        int standard_carbohydrate = 500;
        int standard_fat = 40;
        int standard_protein = 70;
        int standard_kcal = 2400;
        int standard_bar_width = 188;

        flag1_text_main.setMovementMethod(new ScrollingMovementMethod());

        int[][] numdata = {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
        String[] nutdata = {"carbohydrate", "protein", "fat", "kcal"};
        View[][] arrView = {{content1_bar1,content1_bar2,content1_bar3,content1_bar4}, {content2_bar1,content2_bar2,content2_bar3,content2_bar4}, {content3_bar1,content3_bar2,content3_bar3,content3_bar4}, {content4_bar1,content4_bar2,content4_bar3,content4_bar4}};
        String json = null;

        try {
            InputStream is = getResources().getAssets().open("jsons/test.json");
            int fileSize = is.available();

            byte[] buffer = new byte[fileSize];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");

        }
        catch (IOException ex) {ex.printStackTrace();}

        try {
            JSONArray jsonArray = new JSONArray(json);

            for(int i=0; i<numdata.length;i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                int[] inNumdata = numdata[i];
                for(int j=0; j<inNumdata.length;j++){
                    String temp = jsonObject.getString(nutdata[j]);
                    numdata[i][j] = Integer.parseInt(temp);
                }
            }

        } catch (JSONException e) {e.printStackTrace();}

        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                double temp = 0.0;
                switch (j) {
                    case 0:
                        temp = numdata[i][0]/(standard_carbohydrate/3.0)*standard_bar_width;
                        break;
                    case 1:
                        temp = numdata[i][1]/(standard_protein/3.0)*standard_bar_width;
                        break;
                    case 2:
                        temp = numdata[i][2]/(standard_fat/3.0)*standard_bar_width;
                        break;
                    case 3:
                        temp = numdata[i][3]/(standard_kcal/3.0)*standard_bar_width;
                        break;
                    default:
                        break;
                }
                int res = (int) Math.round(temp);
                if (res >= standard_bar_width) {
                    res = standard_bar_width;
                }
                arrView[i][j].getLayoutParams().width = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, res, getResources().getDisplayMetrics());
            }
        }



        btn_t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_t1.setTextColor(Color.parseColor("#000000"));
                btn_t2.setTextColor(Color.parseColor("#666666"));
                btn_t3.setTextColor(Color.parseColor("#666666"));
                btn_t4.setTextColor(Color.parseColor("#666666"));
                flag1_view.smoothScrollTo(content_t1.getLeft()-96,0);
            }
        });
        btn_t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_t1.setTextColor(Color.parseColor("#666666"));
                btn_t2.setTextColor(Color.parseColor("#000000"));
                btn_t3.setTextColor(Color.parseColor("#666666"));
                btn_t4.setTextColor(Color.parseColor("#666666"));
                flag1_view.smoothScrollTo(content_t2.getLeft()-96,0);
            }
        });
        btn_t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_t1.setTextColor(Color.parseColor("#666666"));
                btn_t2.setTextColor(Color.parseColor("#666666"));
                btn_t3.setTextColor(Color.parseColor("#000000"));
                btn_t4.setTextColor(Color.parseColor("#666666"));
                flag1_view.smoothScrollTo(content_t3.getLeft()-96,0);
            }
        });
        btn_t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_t1.setTextColor(Color.parseColor("#666666"));
                btn_t2.setTextColor(Color.parseColor("#666666"));
                btn_t3.setTextColor(Color.parseColor("#666666"));
                btn_t4.setTextColor(Color.parseColor("#000000"));
                flag1_view.smoothScrollTo(content_t4.getLeft()-96,0);
            }
        });
        flag1_view.setOnTouchListener( new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        return rootView;
    }


}

