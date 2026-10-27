package com.swu.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        Button btnKalkulator = findViewById(R.id.btnMenuKalkulator);
        Button btnTentang = findViewById(R.id.btnTentangPembuat);
        Button btnRiwayat = findViewById(R.id.btnMenuRiwayat);

        // Intent navigasi kirim data
        btnKalkulator.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            startActivity(intent);
        });

        // Intent btn tentang
        btnTentang.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, AboutActivity.class);
            startActivity(intent);
        });


        btnRiwayat.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, HistoryActivity.class);
            startActivity(intent);
        });

    }
}