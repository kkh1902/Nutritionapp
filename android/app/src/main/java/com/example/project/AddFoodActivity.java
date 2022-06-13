// 음식 추가 액티비티
package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddFoodActivity extends AppCompatActivity {

    private static final String TAG = "AddFoodActivity";
    private String sel_date;
    private ArrayList<ModelFood> arrayList;
    private Intent intent;
    private AdapterListFood arrayAdapter;
    private InterfaceFood interfaces;
    private InputMethodManager imm;
    private AppCompatButton btn_search;
    private EditText select;
    private ImageButton btn_back;
    private ImageView illust;
    private ListView addlist;
    private RelativeLayout progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfood);

        intent = getIntent();

        sel_date = intent.getExtras().getString("sel_date");

        btn_back = (ImageButton) findViewById(R.id.addfood_btn_back);
        btn_search = (AppCompatButton) findViewById(R.id.addfood_btn_search);
        select = (EditText) findViewById(R.id.addfood_search);
        addlist = (ListView) findViewById(R.id.addfood_list_addlist);
        progress = (RelativeLayout) findViewById(R.id.addfood_progress);
        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        illust = (ImageView) findViewById(R.id.addfood_illist);

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
                HashMap<String, Object> postingData = new HashMap<>();
                postingData.put("food_name", temp_selected);
                progress.setVisibility(View.VISIBLE);
                illust.setVisibility(View.INVISIBLE);
                Call<ArrayList<ModelFood>> call = interfaces.postFoodData(postingData, "food");
                interfaces.postFoodData(postingData,"food").enqueue(new Callback<ArrayList<ModelFood>>() {
                    @Override
                    public void onResponse(Call<ArrayList<ModelFood>> call, Response<ArrayList<ModelFood>> response) {
                        if(response.isSuccessful()){
                            ArrayList<ModelFood> items = response.body();
                            for (int i=0; i < items.toArray().length; i++) {
                                arrayList.add(items.get(i));
                                arrayAdapter.notifyDataSetChanged();
                                progress.setVisibility(View.GONE);
                            }
                        }
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
                intent.putExtra("sel_food_kcal", sel_food.getKcal());
                intent.putExtra("sel_food_tan", sel_food.getTan());
                intent.putExtra("sel_food_dan", sel_food.getDan());
                intent.putExtra("sel_food_fat", sel_food.getFat());
                intent.putExtra("sel_food_sugar", sel_food.getSugar());
                intent.putExtra("sel_food_salt", sel_food.getSalt());
                intent.putExtra("sel_date", sel_date);
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
