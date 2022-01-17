package com.example.generalapp.crud.denda;

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
import com.example.generalapp.database.entity.Denda;

import java.util.ArrayList;
import java.util.List;

public class TambahDendaActivity extends AppCompatActivity {

    private EditText editTotalDenda;
    private Spinner pengembalian;
    private Button btn_save;
    private AppDatabase database;
    private List<String> listDenda = new ArrayList<>();
    private int id_denda = 0;
    private boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_denda);

        editTotalDenda = findViewById(R.id.total_denda);

        btn_save = findViewById(R.id.btn_save);
        database = AppDatabase.getInstance(getApplicationContext());

        listDenda.clear();
        listDenda.addAll(database.pengembalianDao().getAllKembali());

        pengembalian = findViewById(R.id.pengembalianId);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listDenda);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pengembalian.setAdapter(spinnerAdapter);

        Intent intent = getIntent();
        id_denda = intent.getIntExtra("id_denda", 0);

        if (id_denda>0){
            isEdit = true;
            Denda denda = database.dendaDao().get(id_denda);

            editTotalDenda.setText(denda.totalDenda);

            Pengembalian tanggalPengembalian = database.pengembalianDao().getTanggal(denda.pengembalianId);
            pengembalian.setSelection(tanggalPengembalian.id_pengembalian - 1);
        }else{
            isEdit = false;
        }

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isEdit){
                    database.dendaDao().update(id_denda, editTotalDenda.getText().toString(), pengembalian.getSelectedItem().toString().trim());
                }else{
                    database.dendaDao().insertAll(editTotalDenda.getText().toString(), pengembalian.getSelectedItem().toString().trim());
                }
                finish();
            }
        });

    }
}