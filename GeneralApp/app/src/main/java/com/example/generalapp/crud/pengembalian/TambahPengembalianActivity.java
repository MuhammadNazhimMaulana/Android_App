package com.example.generalapp.crud.pengembalian;

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
import com.example.generalapp.database.entity.Pengembalian;

import java.util.ArrayList;
import java.util.List;

public class TambahPengembalianActivity extends AppCompatActivity {

    private EditText editTanggalPengembalian;
    private Spinner peminjaman;
    private Button btn_save;
    private AppDatabase database;
    private List<String> listPeminjaman = new ArrayList<>();
    private int id_pengembalian = 0;
    private boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_pengembalian);

        editTanggalPengembalian = findViewById(R.id.tanggal_pengembalian);

        btn_save = findViewById(R.id.btn_save);
        database = AppDatabase.getInstance(getApplicationContext());

        listPeminjaman.clear();
        listPeminjaman.addAll(database.peminjamanDao().getAllPeminjam());

        peminjaman = findViewById(R.id.peminjamanId);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listPeminjaman);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        peminjaman.setAdapter(spinnerAdapter);

        Intent intent = getIntent();
        id_pengembalian = intent.getIntExtra("id_pengembalian", 0);

        if (id_pengembalian>0){
            isEdit = true;
            Pengembalian pengembalian = database.pengembalianDao().get(id_pengembalian);

            editTanggalPengembalian.setText(pengembalian.tanggalPengembalian);

            Peminjaman namaPeminjam = database.peminjamanDao().getNama(pengembalian.peminjamanId);
            peminjaman.setSelection(namaPeminjam.id_peminjaman - 1);
        }else{
            isEdit = false;
        }

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isEdit){
                    database.pengembalianDao().update(id_pengembalian, editTanggalPengembalian.getText().toString(), peminjaman.getSelectedItem().toString().trim());
                }else{
                    database.pengembalianDao().insertAll(editTanggalPengembalian.getText().toString(), peminjaman.getSelectedItem().toString().trim());
                }
                finish();
            }
        });

    }
}