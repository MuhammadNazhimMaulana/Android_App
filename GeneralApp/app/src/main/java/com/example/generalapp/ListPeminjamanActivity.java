package com.example.generalapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.generalapp.database.AppDatabase;
import com.example.generalapp.adapter.AdapterPeminjaman;
import com.example.generalapp.database.model.PeminjamanWithBuku;

import java.util.ArrayList;
import java.util.List;

public class ListPeminjamanActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button btn_tambah_peminjaman, btn_home;
    private AppDatabase database;
    private AdapterPeminjaman adapterPeminjaman;
    private List<PeminjamanWithBuku> list = new ArrayList<>();
    private AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_peminjaman);

        recyclerView = findViewById(R.id.recycler_view);
        btn_tambah_peminjaman = findViewById(R.id.btn_tambah_peminjaman);
        btn_home = findViewById(R.id.btn_home);

        database = AppDatabase.getInstance(getApplicationContext());
        list.clear();
        list.addAll(database.peminjamanDao().getPeminjamanWithBuku());
        adapterPeminjaman = new AdapterPeminjaman(getApplicationContext(), list);
        adapterPeminjaman.setDialog(new AdapterPeminjaman.Dialog() {

            @Override
            public void onClick(int position) {
                final CharSequence[] dialogItem = {"Edit", "Hapus"};
                dialog = new AlertDialog.Builder(ListPeminjamanActivity.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                Intent intent = new Intent(ListPeminjamanActivity.this,
                                        TambahPeminjamanActivity.class);
                                intent.putExtra("id_peminjaman", list.get(position).peminjaman.id_peminjaman);
                                startActivity(intent);
                                break;
                            case 1:
                                PeminjamanWithBuku peminjaman = list.get(position);
                                database.peminjamanDao().delete(peminjaman.peminjaman);
                                onStart();
                                break;
                        }
                    }
                });
                dialog.show();
            }
        });


        RecyclerView.LayoutManager layoutManager = new
                LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterPeminjaman);

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListPeminjamanActivity.this, HomeActivity.class));
            }
        });

        btn_tambah_peminjaman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListPeminjamanActivity.this, TambahPeminjamanActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        list.clear();
        list.addAll(database.peminjamanDao().getPeminjamanWithBuku());
        adapterPeminjaman.notifyDataSetChanged();
    }
}