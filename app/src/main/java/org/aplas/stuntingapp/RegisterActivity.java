package org.aplas.stuntingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.aplas.stuntingapp.database.DBHelper;
import org.aplas.stuntingapp.model.User;

public class RegisterActivity extends AppCompatActivity {
    EditText edtNama, edtAlamat, edtTelp, edtEmail, edtPass;
    Button btnRegist;
    TextView txtLogin;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtNama = findViewById(R.id.edtNama);
        edtAlamat = findViewById(R.id.edtAlamat);
        edtTelp = findViewById(R.id.edtTelp);
        edtEmail = findViewById(R.id.edtEmailReg);
        edtPass = findViewById(R.id.edtPassReg);
        btnRegist = findViewById(R.id.btnRegist);
        txtLogin = findViewById(R.id .txtLogin);
        dbHelper = new DBHelper(this);

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        btnRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nama = edtNama.getText().toString().trim();
                String alamat = edtAlamat.getText().toString().trim();
                String telp = edtTelp.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();
                String pass = edtPass.getText().toString().trim();

                if (nama.isEmpty() || alamat.isEmpty() || telp.isEmpty() ||
                 email.isEmpty() || pass.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Data harap diisi!", Toast.LENGTH_LONG).show();
                } else {
                    User user = new User();
                    user.setNama(nama);
                    user.setAlamat(alamat);
                    user.setTelp(telp);
                    user.setEmail(email);
                    user.setPassword(pass);

                    dbHelper.addUser(user);
                    Toast.makeText(getApplicationContext(), "Registrasi berhasil!", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                    i.putExtra("Nama", nama.trim());
                    startActivity(i);
//                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }
            }
        });
    }
}