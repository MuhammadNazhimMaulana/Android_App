package com.example.generalapp.crud.denda;

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
import com.example.generalapp.adapter.AdapterDenda;
import com.example.generalapp.crud.denda.ListDendaActivity;
import com.example.generalapp.crud.denda.TambahDendaActivity;
import com.example.generalapp.database.AppDatabase;
import com.example.generalapp.adapter.AdapterDenda;
import com.example.generalapp.database.model.DendaWithPengembalian;

import java.util.ArrayList;
import java.util.List;

public class ListDendaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button btn_tambah_denda, btn_home;
    private AppDatabase database;
    private AdapterDenda adapterDenda;
    private List<DendaWithPengembalian> list = new ArrayList<>();
    private AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_denda);

        recyclerView = findViewById(R.id.recycler_view);
        btn_tambah_denda = findViewById(R.id.btn_tambah_denda);
        btn_home = findViewById(R.id.btn_home);

        database = AppDatabase.getInstance(getApplicationContext());
        list.clear();
        list.addAll(database.dendaDao().getDendaWithPengembalian());
        adapterDenda = new AdapterDenda(getApplicationContext(), list);
        adapterDenda.setDialog(new AdapterDenda.Dialog() {

            @Override
            public void onClick(int position) {
                final CharSequence[] dialogItem = {"Edit", "Hapus"};
                dialog = new AlertDialog.Builder(ListDendaActivity.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                Intent intent = new Intent(ListDendaActivity.this,
                                        TambahDendaActivity.class);
                                intent.putExtra("id_denda", list.get(position).denda.id_denda);
                                startActivity(intent);
                                break;
                            case 1:
                                DendaWithPengembalian denda = list.get(position);
                                database.dendaDao().delete(denda.denda);
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
        recyclerView.setAdapter(adapterDenda);

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListDendaActivity.this, HomeActivity.class));
            }
        });

        btn_tambah_denda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListDendaActivity.this, TambahDendaActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        list.clear();
        list.addAll(database.dendaDao().getDendaWithPengembalian());
        adapterDenda.notifyDataSetChanged();
    }
}