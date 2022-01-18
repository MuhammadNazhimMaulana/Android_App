package com.example.generalapp.crud.buku;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.generalapp.R;
import com.example.generalapp.database.AppDatabase;
import com.example.generalapp.database.entity.Buku;
import com.example.generalapp.database.entity.Genre;
import com.example.generalapp.database.entity.Penulis;

import java.util.ArrayList;
import java.util.List;

public class TambahBukuActivity extends AppCompatActivity {

    private EditText editJudulBuku, editJumlahHalaman;
    private Spinner genre, penulis;
    private Button btn_save;
    private AppDatabase database;
    private List<String> listGenre = new ArrayList<>(), listPenulis = new ArrayList<>();
    private int id_buku = 0;
    private boolean isEdit = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_buku);

        editJudulBuku = findViewById(R.id.judul_buku);
        editJumlahHalaman = findViewById(R.id.jumlah_halaman);

        btn_save = findViewById(R.id.btn_save);
        database = AppDatabase.getInstance(getApplicationContext());

        listPenulis.clear();
        listPenulis.addAll(database.penulisDao().getAllPenulis());

        penulis = findViewById(R.id.penulisId);
        ArrayAdapter<String> spinnerAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listPenulis);
        spinnerAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        penulis.setAdapter(spinnerAdapter1);

        listGenre.clear();
        listGenre.addAll(database.genreDao().getAllGenre());

        genre = findViewById(R.id.genreId);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listGenre);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genre.setAdapter(spinnerAdapter);

        Intent intent = getIntent();
        id_buku = intent.getIntExtra("id_buku", 0);

        if (id_buku>0){
            isEdit = true;
            Buku buku = database.bukuDao().get(id_buku);

            editJudulBuku.setText(buku.judulBuku);

            Penulis penulisBuku = database.penulisDao().getNama(buku.penulisId);
            penulis.setSelection(penulisBuku.id_penulis - 1);

            editJumlahHalaman.setText(buku.jumlahHalaman);

            Genre genreBuku = database.genreDao().getNama(buku.genreId);
            genre.setSelection(genreBuku.id_genre - 1);
        }else{
            isEdit = false;
        }

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isEdit){
                    database.bukuDao().update(id_buku, editJudulBuku.getText().toString(), penulis.getSelectedItem().toString().trim(), editJumlahHalaman.getText().toString(), genre.getSelectedItem().toString().trim());
                }else{
                    database.bukuDao().insertAll(editJudulBuku.getText().toString(), penulis.getSelectedItem().toString().trim(), editJumlahHalaman.getText().toString(), genre.getSelectedItem().toString().trim());
                }
                finish();
            }
        });

    }
}