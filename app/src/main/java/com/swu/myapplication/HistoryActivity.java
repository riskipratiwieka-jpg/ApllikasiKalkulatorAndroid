package com.swu.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HistoryActivity extends AppCompatActivity {
    RecyclerView rvRiwayat;
    RiwayatAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        rvRiwayat = findViewById(R.id.rvRiwayat);
        rvRiwayat.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RiwayatAdapter(MainActivity.dataRiwayat);
        rvRiwayat.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

}
