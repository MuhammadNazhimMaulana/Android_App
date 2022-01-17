package com.example.generalapp.crud.pengembalian;

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
import com.example.generalapp.adapter.AdapterPengembalian;
import com.example.generalapp.crud.pengembalian.ListPengembalianActivity;
import com.example.generalapp.crud.pengembalian.TambahPengembalianActivity;
import com.example.generalapp.database.AppDatabase;
import com.example.generalapp.adapter.AdapterPengembalian;
import com.example.generalapp.database.model.PengembalianWithPeminjaman;

import java.util.ArrayList;
import java.util.List;

public class ListPengembalianActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button btn_tambah_pengembalian, btn_home;
    private AppDatabase database;
    private AdapterPengembalian adapterPengembalian;
    private List<PengembalianWithPeminjaman> list = new ArrayList<>();
    private AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pengembalian);

        recyclerView = findViewById(R.id.recycler_view);
        btn_tambah_pengembalian = findViewById(R.id.btn_tambah_pengembalian);
        btn_home = findViewById(R.id.btn_home);

        database = AppDatabase.getInstance(getApplicationContext());
        list.clear();
        list.addAll(database.pengembalianDao().getPengembalianWithPeminjaman());
        adapterPengembalian = new AdapterPengembalian(getApplicationContext(), list);
        adapterPengembalian.setDialog(new AdapterPengembalian.Dialog() {

            @Override
            public void onClick(int position) {
                final CharSequence[] dialogItem = {"Edit", "Hapus"};
                dialog = new AlertDialog.Builder(ListPengembalianActivity.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                Intent intent = new Intent(ListPengembalianActivity.this,
                                        TambahPengembalianActivity.class);
                                intent.putExtra("id_pengembalian", list.get(position).pengembalian.id_pengembalian);
                                startActivity(intent);
                                break;
                            case 1:
                                PengembalianWithPeminjaman pengembalian = list.get(position);
                                database.pengembalianDao().delete(pengembalian.pengembalian);
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
        recyclerView.setAdapter(adapterPengembalian);

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListPengembalianActivity.this, HomeActivity.class));
            }
        });

        btn_tambah_pengembalian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListPengembalianActivity.this, TambahPengembalianActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        list.clear();
        list.addAll(database.pengembalianDao().getPengembalianWithPeminjaman());
        adapterPengembalian.notifyDataSetChanged();
    }
}