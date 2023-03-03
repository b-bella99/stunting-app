package org.aplas.stuntingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class DashboardActivity extends AppCompatActivity {
    Button btnProfil;
    ImageButton btnInfoStunting, btnNutrition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

//        btnLogout = findViewById(R.id.btnAnak);
        btnProfil = findViewById(R.id.btnProfil);
        btnInfoStunting = findViewById(R.id.btnInfoStunting);
        btnNutrition = findViewById(R.id.btnNutrition);

        btnProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AccountActivity.class));
            }
        });

        btnInfoStunting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), InfoStuntingActivity.class));
            }
        });

        btnNutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), NutritionActivity.class));
            }
        });
    }
}