package com.example.generalapp.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.generalapp.HomeActivity;
import com.example.generalapp.R;

public class LoginActivity extends AppCompatActivity {
    EditText username_login,password;
    Button btn_login;
    SharedPreferences sharedPreferences;

    // Shared preferences
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_TELEPON = "telepon";
    private static final String KEY_JENIS_KELAMIN = "jenisKelamin";
    private static final String KEY_HOBBIES = "hobbies";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username_login = findViewById(R.id.username_login);
        password = findViewById(R.id.password);
        btn_login = findViewById(R.id.btn_login);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        String user = sharedPreferences.getString(KEY_HOBBIES, null);
    }

    //Menuju Register
    public void register(View view) {
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
    }

    //Menuju Register
    public void login(View view) {
        String user = sharedPreferences.getString(KEY_NAME, null);
        String pass = sharedPreferences.getString(KEY_PASSWORD, null);
        String pengguna = username_login.getText().toString();
        String sandi = password.getText().toString();

        if (pengguna.equals(user) == true && sandi.equals(pass) == true) {

            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        }else
        {
            Toast.makeText(this, "Username Atau Password Salah", Toast.LENGTH_LONG).show();
        }
    }
}