package com.swu.myapplication;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class ImageActivity extends AppCompatActivity {
    ImageView imageLogo;
    Button buttonKembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        // Menghubungkan komponen
        imageLogo = findViewById(R.id.imageView);
        buttonKembali = findViewById(R.id.btnKembali);
        // Menampilkan gambar logo_swu
        imageLogo.setImageResource(R.drawable.logo_swu);
        // Tombol kembali ke halaman sebelumnya
        buttonKembali.setOnClickListener(v -> finish());
    }
}
