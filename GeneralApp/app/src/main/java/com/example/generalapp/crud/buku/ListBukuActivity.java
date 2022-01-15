package com.example.generalapp.crud.buku;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.generalapp.HomeActivity;
import com.example.generalapp.R;
import com.example.generalapp.database.entity.Buku;
import com.example.generalapp.database.AppDatabase;
import com.example.generalapp.adapter.AdapterBuku;

import java.util.ArrayList;
import java.util.List;

public class ListBukuActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button btn_tambah_buku, btn_home;
    private AppDatabase database;
    private AdapterBuku adapterBuku;
    private List<Buku> list = new ArrayList<>();
    private AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_buku);

        recyclerView = findViewById(R.id.recycler_view);
        btn_tambah_buku = findViewById(R.id.btn_tambah_buku);
        btn_home = findViewById(R.id.btn_home);

        database = AppDatabase.getInstance(getApplicationContext());
        list.clear();
        list.addAll(database.bukuDao().getAll());
        adapterBuku = new AdapterBuku(getApplicationContext(), list);
        adapterBuku.setDialog(new AdapterBuku.Dialog() {

            @Override
            public void onClick(int position) {
                final CharSequence[] dialogItem = {"Edit", "Hapus"};
                dialog = new AlertDialog.Builder(ListBukuActivity.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                Intent intent = new Intent(ListBukuActivity.this,
                                        TambahBukuActivity.class);
                                intent.putExtra("id_buku", list.get(position).id_buku);
                                startActivity(intent);
                                break;
                            case 1:
                                Buku buku = list.get(position);
                                database.bukuDao().delete(buku);
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
        recyclerView.setAdapter(adapterBuku);

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListBukuActivity.this, HomeActivity.class));
            }
        });

        btn_tambah_buku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListBukuActivity.this, TambahBukuActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        list.clear();
        list.addAll(database.bukuDao().getAll());
        adapterBuku.notifyDataSetChanged();
    }
}