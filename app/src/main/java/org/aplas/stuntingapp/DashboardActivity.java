package org.aplas.stuntingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity {
    TextView txtEmail, txtNama;
    Button btnProfil;
    ImageButton btnTracing, btnInfoStunting, btnNutrition, btnFoodHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

//        btnLogout = findViewById(R.id.btnAnak);
        txtNama = findViewById(R.id.txtNamaDash);
        txtEmail = findViewById(R.id.txtEmailDash);
        btnProfil = findViewById(R.id.btnProfil);
        btnTracing = findViewById(R.id.btnTracing);
        btnInfoStunting = findViewById(R.id.btnInfoStunting);
        btnNutrition = findViewById(R.id.btnNutrition);
        btnFoodHelp = findViewById(R.id.btnFoodHelp);

        Bundle bundle = getIntent().getExtras();
        String email = bundle.getString("Email");
        String nama = bundle.getString("Nama");
        txtEmail.setText(email);
        txtNama.setText(nama);


        btnProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AccountActivity.class));
            }
        });

        btnTracing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), TracingActivity.class));
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

        btnFoodHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), FoodHelpActivity.class));
            }
        });
    }
}