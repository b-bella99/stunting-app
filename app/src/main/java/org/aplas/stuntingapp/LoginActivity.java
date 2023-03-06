package org.aplas.stuntingapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import org.aplas.stuntingapp.database.DBHelper;
import org.aplas.stuntingapp.model.User;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    EditText edtEmail, edtPassword;
    TextView txtRegist;
    boolean user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnLogIn);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        txtRegist = findViewById(R.id.txtRegist);
        DBHelper db = new DBHelper(this);

        txtRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString();
                String pass = edtPassword.getText().toString();
//                Intent i = new Intent(LoginActivity.this, DashboardActivity.class);
//                i.putExtra("Email: ", email.trim());
//                startActivity(i);
//                Cursor cursor = db.getData();
//
//                if (cursor.getCount() == 0) {
//                    Toast.makeText(LoginActivity.this, "Tidak ada akun yang terdaftar", Toast.LENGTH_LONG).show();
//                }
//
//                if (loginCheck(cursor, email, pass)){
//                    Intent i = new Intent(LoginActivity.this, DashboardActivity.class);
//                    i.putExtra("Email", email);
//                    startActivity(i);
//                } else {
//                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
//                    builder.setCancelable(true);
//                    builder.setTitle("Akun salah");
//                    builder.setMessage("Masukkan email dan password yang benar");
//                    builder.show();
//                }
//                db.close();

                if (validate(email, pass)) {
                    if (db.checkUser(email, pass)) {
                        Bundle bundle = getIntent().getExtras();
                        String nama = bundle.getString("Nama");
                        Intent i = new Intent(LoginActivity.this, DashboardActivity.class);
                        i.putExtra("Email", email.trim());
                        i.putExtra("Nama", nama);
                        startActivity(i);
                    } else {
                        Toast.makeText(LoginActivity.this, "Password salah!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    public static boolean loginCheck(Cursor cursor, String email, String pass) {
        while (cursor.moveToNext()){
            if (cursor.getString(0).equals(email)){
                if (cursor.getString(2).equals(pass)){
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    private boolean validate(String email, String pass) {
        boolean valid;
        if (email.isEmpty()) {
            valid = false;
//            Toast.makeText(LoginActivity.this, "Email harus disiis!", Toast.LENGTH_LONG).show();
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
            builder.setCancelable(true);
            builder.setTitle("Email salah");
            builder.setMessage("Masukkan email yang benar!");
            builder.show();
        } else {
            valid = true;
        }

        if (pass.isEmpty()) {
            valid = false;
//            Toast.makeText(LoginActivity.this, "Password harus disiis!", Toast.LENGTH_LONG).show();
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
            builder.setCancelable(true);
            builder.setTitle("Password salah");
            builder.setMessage("Masukkan password yang benar!");
            builder.show();
        } else {
            valid = true;
        }
        return valid;
    }
}