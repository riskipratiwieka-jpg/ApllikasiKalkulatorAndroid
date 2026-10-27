package com.swu.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AudioActivity extends AppCompatActivity {

    Button btnSpotify, btnKembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);

        btnSpotify = findViewById(R.id.btnSpotify);
        btnKembali = findViewById(R.id.btnKembali);

        // Tombol untuk membuka Spotify
        btnSpotify.setOnClickListener(v -> {
            try {
                Intent intent = getPackageManager()
                        .getLaunchIntentForPackage("com.spotify.music");

                if (intent != null) {
                    startActivity(intent);
                } else {
                    // Membuka halaman Spotify jika aplikasi tidak ditemukan
                    Intent browserIntent = new Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://open.spotify.com/")
                    );
                    startActivity(browserIntent);
                }
            } catch (Exception e) {
                Toast.makeText(
                        AudioActivity.this,
                        "Spotify tidak dapat dibuka",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

        // Tombol kembali ke halaman sebelumnya
        btnKembali.setOnClickListener(v -> finish());
    }
}
