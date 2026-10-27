package com.swu.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.DownloadManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.provider.Settings;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Deklarasi variabel
    EditText editNum1, editNum2;
    Button buttonPlus, buttonMin, buttonMulti, buttonDiv;
    Button buttonCalc, buttonClear;
    TextView textResult;

    String operatorTerpilih = "";

    // Urutan lima angka terakhir NIM: 04081
    private final int[] urutanNIM = {0, 4, 0, 8, 1};
    private int langkahFitur = 0;

    // Menyimpan riwayat perhitungan
    public static ArrayList<Riwayat> dataRiwayat = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main), (v, insets) -> {
                    Insets systemBars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars());

                    v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                    );

                    return insets;
                });

        // Inisialisasi komponen
        editNum1 = findViewById(R.id.editNum1);
        editNum2 = findViewById(R.id.editNum2);

        buttonPlus = findViewById(R.id.buttonPlus);
        buttonMin = findViewById(R.id.buttonMin);
        buttonMulti = findViewById(R.id.buttonMulti);
        buttonDiv = findViewById(R.id.buttonDiv);
        buttonCalc = findViewById(R.id.buttonCalc);
        buttonClear = findViewById(R.id.buttonClear);

        textResult = findViewById(R.id.textResult);

        // Tombol penjumlahan
        buttonPlus.setOnClickListener(v -> {
            operatorTerpilih = "+";
            Toast.makeText(this, "Operator + dipilih",
                    Toast.LENGTH_SHORT).show();
        });

        // Tombol pengurangan
        buttonMin.setOnClickListener(v -> {
            operatorTerpilih = "-";
            Toast.makeText(this, "Operator - dipilih",
                    Toast.LENGTH_SHORT).show();
        });

        // Tombol perkalian
        buttonMulti.setOnClickListener(v -> {
            operatorTerpilih = "x";
            Toast.makeText(this, "Operator x dipilih",
                    Toast.LENGTH_SHORT).show();
        });

        // Tombol pembagian
        buttonDiv.setOnClickListener(v -> {
            operatorTerpilih = ":";
            Toast.makeText(this, "Operator : dipilih",
                    Toast.LENGTH_SHORT).show();
        });

        // Tombol hitung
        buttonCalc.setOnClickListener(v -> hitungHasil());

        // Tombol hapus
        buttonClear.setOnClickListener(v -> {
            editNum1.setText("");
            editNum2.setText("");
            textResult.setText("");
            operatorTerpilih = "";
            editNum1.requestFocus();
        });
    }

    private void hitungHasil() {
        String input1 = editNum1.getText().toString().trim();
        String input2 = editNum2.getText().toString().trim();

        // Validasi input
        if (input1.isEmpty() || input2.isEmpty()) {
            Toast.makeText(this,
                    "Mohon isi kedua angka terlebih dahulu",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (operatorTerpilih.isEmpty()) {
            Toast.makeText(this,
                    "Mohon pilih operator terlebih dahulu",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double angka1 = Double.parseDouble(input1);
            double angka2 = Double.parseDouble(input2);
            double hasil;

            // Proses perhitungan
            switch (operatorTerpilih) {
                case "+":
                    hasil = angka1 + angka2;
                    break;

                case "-":
                    hasil = angka1 - angka2;
                    break;

                case "x":
                    hasil = angka1 * angka2;
                    break;

                case ":":
                    if (angka2 == 0) {
                        Toast.makeText(this,
                                "Angka tidak bisa dibagi nol",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }
                    hasil = angka1 / angka2;
                    break;

                default:
                    return;
            }

            // Membuat teks perhitungan
            String teksPerhitungan =
                    input1 + " " + operatorTerpilih + " " + input2;

            String hasilString = String.valueOf(hasil);

            // Menampilkan hasil
            textResult.setText(hasilString);

            // Menyimpan riwayat
            dataRiwayat.add(
                    new Riwayat(teksPerhitungan, hasilString)
            );

            Toast.makeText(this,
                    "Jumlah riwayat: " + dataRiwayat.size(),
                    Toast.LENGTH_SHORT).show();

            // Menjalankan fitur berdasarkan urutan NIM
            jalankanFiturNIM(hasil);

        } catch (NumberFormatException e) {
            Toast.makeText(this,
                    "Masukkan angka yang valid",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void jalankanFiturNIM(double hasil) {
        if (langkahFitur >= urutanNIM.length) {return;}
        int angkaTarget = urutanNIM[langkahFitur];

        // Hanya menjalankan fitur jika hasil sesuai urutan NIM
        if (hasil != angkaTarget) {return;}
        int fiturSekarang = langkahFitur;
        langkahFitur++;

        switch (fiturSekarang) {
            case 0:
                // Angka 0 pertama: membuka kamera
                Intent kamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                if (kamera.resolveActivity(getPackageManager()) != null) {startActivity(kamera);
                } else {Toast.makeText(this, "Kamera tidak tersedia", Toast.LENGTH_SHORT).show();}
                break;

            case 1:
                // Angka 4: memutar audio
                startActivity(new Intent(MainActivity.this, AudioActivity.class));
                break;

            case 2:
                // Angka 0 kedua: membuka gambar
                startActivity(new Intent(MainActivity.this, ImageActivity.class));
                break;

            case 3:
                // Angka 8: mengambil lokasi dan membuka WhatsApp
                kirimLokasiKeWa();
                break;

            case 4:
                // Angka 1: membuka sensor
                startActivity(new Intent(MainActivity.this, SensorActivity.class));
                break;
        }
    }

    // Kirim ke WA
    private void kirimLokasiKeWa() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                    this,
                    new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                    },
                    100
            );
            return;
        }

        LocationManager locationManager =
                (LocationManager) getSystemService(LOCATION_SERVICE);

        try {
            Location lokasi = null;

            if (locationManager.isProviderEnabled(
                    LocationManager.GPS_PROVIDER)) {
                lokasi = locationManager.getLastKnownLocation(
                        LocationManager.GPS_PROVIDER);
            }

            if (lokasi == null) {
                lokasi = locationManager.getLastKnownLocation(
                        LocationManager.NETWORK_PROVIDER);
            }

            if (lokasi == null) {
                Toast.makeText(this,
                        "Lokasi belum tersedia. Aktifkan GPS dan coba lagi.",
                        Toast.LENGTH_LONG).show();

                startActivity(new Intent(
                        Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                return;
            }

            String latitude = String.valueOf(lokasi.getLatitude());
            String longitude = String.valueOf(lokasi.getLongitude());
            String mapsLink = "https://maps.google.com/?q=" + latitude + "," + longitude;

            String pesan = "Halo Pak Aryo, ini lokasi saya saat ini: " + mapsLink;

            String urlWa = "https://api.whatsapp.com/send?phone=6285830112275" + "&text=" + Uri.encode(pesan);


            Intent intentWa = new Intent(Intent.ACTION_VIEW, Uri.parse(urlWa));

            startActivity(intentWa);
        } catch (SecurityException e) {
            Toast.makeText(this,
                    "Izin lokasi belum diberikan.",
                    Toast.LENGTH_SHORT).show();
        }
    }

}
