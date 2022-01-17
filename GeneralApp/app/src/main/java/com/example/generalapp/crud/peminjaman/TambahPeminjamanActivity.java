package com.example.generalapp.crud.peminjaman;

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
import com.example.generalapp.database.entity.Peminjaman;
import com.example.generalapp.database.entity.Buku;
import com.example.generalapp.database.entity.Genre;

import java.util.ArrayList;
import java.util.List;

public class TambahPeminjamanActivity extends AppCompatActivity {

    private EditText editNamaPeminjam;
    private Spinner buku;
    private Button btn_save;
    private AppDatabase database;
    private List<String> listBuku = new ArrayList<>();
    private int id_peminjaman = 0;
    private boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_peminjaman);

        editNamaPeminjam = findViewById(R.id.nama_peminjam);

        btn_save = findViewById(R.id.btn_save);
        database = AppDatabase.getInstance(getApplicationContext());

        listBuku.clear();
        listBuku.addAll(database.bukuDao().getAllBuku());

        buku = findViewById(R.id.bukuId);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listBuku);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        buku.setAdapter(spinnerAdapter);

        Intent intent = getIntent();
        id_peminjaman = intent.getIntExtra("id_peminjaman", 0);

        if (id_peminjaman>0){
            isEdit = true;
            Peminjaman peminjaman = database.peminjamanDao().get(id_peminjaman);

            editNamaPeminjam.setText(peminjaman.namaPeminjam);

            Buku judulBuku = database.bukuDao().getJudul(peminjaman.bukuId);
            buku.setSelection(judulBuku.id_buku - 1);
        }else{
            isEdit = false;
        }

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isEdit){
                    database.peminjamanDao().update(id_peminjaman, editNamaPeminjam.getText().toString(), buku.getSelectedItem().toString().trim());
                }else{
                    database.peminjamanDao().insertAll(editNamaPeminjam.getText().toString(), buku.getSelectedItem().toString().trim());
                }
                finish();
            }
        });

    }
}