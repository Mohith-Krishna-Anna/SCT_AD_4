package com.example.qrcodescanner;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.qrcodescanner.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    private TextView tvScanResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvScanResult = findViewById(R.id.tvScanResult);
        Button btnScan = findViewById(R.id.btnScan);
        Button btnGenerate = findViewById(R.id.btnGenerate);

        btnScan.setOnClickListener(view -> {
            IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
            integrator.setPrompt("Scan a QR code");
            integrator.setOrientationLocked(false);
            integrator.initiateScan();
        });

        btnGenerate.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, QRCodeGeneratorActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                tvScanResult.setText("Result: " + result.getContents());
            } else {
                tvScanResult.setText("Cancelled");
            }
        }
    }
}