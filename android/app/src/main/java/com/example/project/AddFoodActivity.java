// 음식 추가 액티비티
package com.example.project;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddFoodActivity extends AppCompatActivity {

    private static final String TAG = "AddFoodActivity";
    private String month, day, date;
    private ArrayList<ModelFood> arrayList;
    private Intent intent;
    private AdapterListFood arrayAdapter;
    private InterfaceFood interfaces;
    private InputMethodManager imm;
    private AppCompatButton btn_search;
    private EditText select;
    private ImageButton btn_back;
    private ListView addlist;
    private RelativeLayout progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfood);

        intent = getIntent();

        month = intent.getExtras().getString("sel_month");
        day = intent.getExtras().getString("sel_day");
        date = month + day;

        btn_back = (ImageButton) findViewById(R.id.addfood_btn_back);
        btn_search = (AppCompatButton) findViewById(R.id.addfood_btn_search);
        select = (EditText) findViewById(R.id.addfood_search);
        addlist = (ListView) findViewById(R.id.addfood_list_addlist);
        progress = (RelativeLayout) findViewById(R.id.addfood_progress);
        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        arrayList = new ArrayList<ModelFood>();
        arrayAdapter = new AdapterListFood(this, arrayList);
        interfaces = Client.getRetrofitInstance().create(InterfaceFood.class);

        addlist.setAdapter(arrayAdapter);

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select.setCursorVisible(true);
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.go_up_1, R.anim.go_up_2);
                finish();
            }
        });

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp_selected = select.getText().toString();
                arrayList.clear();
                select.setCursorVisible(false);
                imm.hideSoftInputFromWindow(select.getWindowToken(), 0);
                progress.setVisibility(View.VISIBLE);
                Call<ArrayList<ModelFood>> call = interfaces.getFoodData(temp_selected);
                call.enqueue(new Callback<ArrayList<ModelFood>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ModelFood>> call, Response<ArrayList<ModelFood>> response) {
                        ArrayList<ModelFood> items = response.body();

                        for (int i=0; i < items.toArray().length; i++) {
//                            arrayList.add();
                        }
                        arrayAdapter.notifyDataSetChanged();
                        progress.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Call<ArrayList<ModelFood>> call, Throwable t) {
                        Log.e(TAG, "onFailure: " + t.getMessage());
                        progress.setVisibility(View.GONE);
                    }
                });
            }
        });

        addlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ModelFood sel_food = (ModelFood) arrayAdapter.getItem(i);
                intent = new Intent(getApplicationContext(), ModalActivity.class);
                intent.putExtra("sel_food_name", sel_food.getFood_name());
                intent.putExtra("date", date);
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
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
}
