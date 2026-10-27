package com.swu.myapplication;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SensorActivity extends AppCompatActivity
        implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private Sensor gyroscope;

    private TextView tvAccelerometer;
    private TextView tvGyroscope;
    private TextView tvStatus;
    private Button btnKembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        tvAccelerometer = findViewById(R.id.tvAccelerometer);
        tvGyroscope = findViewById(R.id.tvGyroscope);
        tvStatus = findViewById(R.id.tvStatus);
        btnKembali = findViewById(R.id.btnKembali);

        // Mengakses sensor HP
        sensorManager = (SensorManager)
                getSystemService(Context.SENSOR_SERVICE);

        accelerometer = sensorManager.getDefaultSensor(
                Sensor.TYPE_ACCELEROMETER);

        gyroscope = sensorManager.getDefaultSensor(
                Sensor.TYPE_GYROSCOPE);

        // Memeriksa ketersediaan accelerometer
        if (accelerometer == null) {
            tvAccelerometer.setText(
                    "Accelerometer tidak tersedia pada perangkat ini.");
        }

        // Memeriksa ketersediaan gyroscope
        if (gyroscope == null) {
            tvGyroscope.setText(
                    "Gyroscope tidak tersedia pada perangkat ini.");
        }

        if (accelerometer == null && gyroscope == null) {
            tvStatus.setText("Tidak ada sensor yang didukung.");
        } else {
            tvStatus.setText("Sensor siap digunakan");
        }

        // Tombol kembali
        btnKembali.setOnClickListener(v -> finish());
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (accelerometer != null) {
            sensorManager.registerListener(
                    this,
                    accelerometer,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }

        if (gyroscope != null) {
            sensorManager.registerListener(
                    this,
                    gyroscope,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Menghentikan pembacaan sensor saat halaman tidak aktif
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            tvAccelerometer.setText(
                    "Accelerometer\n" +
                            "Sumbu X : " + String.format("%.2f", x) + " m/s²\n" +
                            "Sumbu Y : " + String.format("%.2f", y) + " m/s²\n" +
                            "Sumbu Z : " + String.format("%.2f", z) + " m/s²"
            );
        }

        if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            tvGyroscope.setText(
                    "Gyroscope\n" +
                            "Sumbu X : " + String.format("%.2f", x) + " rad/s\n" +
                            "Sumbu Y : " + String.format("%.2f", y) + " rad/s\n" +
                            "Sumbu Z : " + String.format("%.2f", z) + " rad/s"
            );
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Tidak memerlukan tindakan tambahan
    }
}
