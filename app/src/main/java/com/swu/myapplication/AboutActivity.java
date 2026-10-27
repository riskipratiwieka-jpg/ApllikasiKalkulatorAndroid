package com.swu.myapplication;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_about);

        Button btnKembali = findViewById(R.id.btnKembali);

        btnKembali.setOnClickListener(v -> {
            finish();
        });
    }
}