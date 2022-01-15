package com.example.generalapp.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.generalapp.R;
import com.example.generalapp.auth.LoginActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText username,nama_lengkap,password,email,telepon;
    CheckBox melukis,olahraga,membaca,mainGame,jalanJalan,musik;
    RadioGroup jenisKelamin;
    RadioButton radioButton;
    String hobbies = "";
    SharedPreferences sharedPreferences;

    //Membuat Shared Preferences
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME = "username";
    private static final String KEY_FULL_NAME = "nama_lengkap";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_TELEPON = "telepon";
    private static final String KEY_JENIS_KELAMIN = "jenisKelamin";
    private static final String KEY_HOBBIES = "hobbies";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.username);
        nama_lengkap = findViewById(R.id.nama_lengkap);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        telepon = findViewById(R.id.telepon);
        jenisKelamin = findViewById(R.id.jenisKelamin);
        melukis = findViewById(R.id.melukis);
        olahraga = findViewById(R.id.olahraga);
        membaca = findViewById(R.id.membaca);
        musik = findViewById(R.id.musik);
        mainGame = findViewById(R.id.mainGame);
        jalanJalan = findViewById(R.id.jalanJalan);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
    }

    public void registrasi(View view) {

        hobbies = "";
        String user = username.getText().toString();
        String nama = nama_lengkap.getText().toString();
        String pass = password.getText().toString();
        String mail = email.getText().toString();
        String phone = telepon.getText().toString();
        int selectedId = jenisKelamin.getCheckedRadioButtonId();

        if(TextUtils.isEmpty(user)){
            Toast.makeText(this, "Ada yang Kosong", Toast.LENGTH_LONG).show();
            username.setError("Field Tidak Boleh Kosong");

        }else if(TextUtils.isEmpty(nama)){
            Toast.makeText(this, "Ada yang Kosong", Toast.LENGTH_LONG).show();
            nama_lengkap.setError("Field Tidak Boleh Kosong");

        }else if(TextUtils.isEmpty(pass)){
            Toast.makeText(this, "Ada yang Kosong", Toast.LENGTH_LONG).show();
            password.setError("Field Tidak Boleh Kosong");

        }else if(TextUtils.isEmpty(mail)){
            Toast.makeText(this, "Ada yang Kosong", Toast.LENGTH_LONG).show();
            email.setError("Field Tidak Boleh Kosong");

        }else if(TextUtils.isEmpty(phone)){
            Toast.makeText(this, "Ada yang Kosong", Toast.LENGTH_LONG).show();
            telepon.setError("Field Tidak Boleh Kosong");

        }else if(findViewById(selectedId) == null){
            Toast.makeText(this, "Jenis Kelamin Belum Dipilih", Toast.LENGTH_LONG).show();
        }

        else{
            radioButton = findViewById(selectedId);

            if(melukis.isChecked()){
                hobbies += melukis.getText().toString()+" ";
            }
            if(olahraga.isChecked()){
                hobbies += olahraga.getText().toString()+" ";
            }
            if(membaca.isChecked()){
                hobbies += membaca.getText().toString()+" ";
            }
            if(musik.isChecked()){
                hobbies += musik.getText().toString()+" ";
            }
            if(jalanJalan.isChecked()){
                hobbies += jalanJalan.getText().toString()+" ";
            }
            if(mainGame.isChecked()){
                hobbies += mainGame.getText().toString()+" ";
            }

            //Simpan data tadi ke shared preferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(KEY_NAME, username.getText().toString());
            editor.putString(KEY_FULL_NAME, nama_lengkap.getText().toString());
            editor.putString(KEY_PASSWORD, password.getText().toString());
            editor.putString(KEY_EMAIL, email.getText().toString());
            editor.putString(KEY_TELEPON, telepon.getText().toString());
            editor.putString(KEY_JENIS_KELAMIN, radioButton.getText().toString());
            editor.putString(KEY_HOBBIES, hobbies);
            editor.apply();

            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }
    }
}