package org.aplas.stuntingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class NutritionActivity extends AppCompatActivity {
    TextView txtBayi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);

        txtBayi = findViewById(R.id.txtNutriBayi);

        txtBayi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DetailNutritionActivity.class));
            }
        });
    }
}