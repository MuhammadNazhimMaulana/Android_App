package com.example.generalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.generalapp.crud.buku.ListBukuActivity;
import com.example.generalapp.crud.denda.ListDendaActivity;
import com.example.generalapp.crud.genre.ListGenreActivity;
import com.example.generalapp.crud.peminjaman.ListPeminjamanActivity;
import com.example.generalapp.crud.pengembalian.ListPengembalianActivity;

public class HomeActivity extends AppCompatActivity {
    TextView user;
    SharedPreferences sharedPreferences;

    // Shared preferences
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        user = findViewById(R.id.user);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);

        //Getting value
        String pengguna = sharedPreferences.getString(KEY_NAME, null);

        //Set value
        user.setText(pengguna);
    }

    //Menuju Genre
    public void genre(View view) {
        Intent intent = new Intent(getApplicationContext(), ListGenreActivity.class);
        startActivity(intent);
    }

    //Menuju Buku
    public void buku(View view) {
        Intent intent = new Intent(getApplicationContext(), ListBukuActivity.class);
        startActivity(intent);
    }

    //Menuju Peminjaman
    public void pinjam(View view) {
        Intent intent = new Intent(getApplicationContext(), ListPeminjamanActivity.class);
        startActivity(intent);
    }

    //Menuju Pengembalian
    public void kembali(View view) {
        Intent intent = new Intent(getApplicationContext(), ListPengembalianActivity.class);
        startActivity(intent);
    }

    //Menuju Denda
    public void denda(View view) {
        Intent intent = new Intent(getApplicationContext(), ListDendaActivity.class);
        startActivity(intent);
    }
}