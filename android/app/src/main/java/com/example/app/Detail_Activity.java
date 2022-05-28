package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Detail_Activity extends AppCompatActivity {

    ImageButton btn_back;
    TextView title;

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
            }
        });
    }
}