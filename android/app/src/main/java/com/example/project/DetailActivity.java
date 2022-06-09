//사용자별 상세정보 액티비티
package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = "DetailActivity";
    private ImageButton btn_back;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();

        String month = intent.getExtras().getString("sel_month");
        String day = intent.getExtras().getString("sel_day");

        btn_back = (ImageButton) findViewById(R.id.detail_btn_back);
        title = (TextView) findViewById(R.id.detail_title);

        title.setText(month + ". " + day + ".");

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
}