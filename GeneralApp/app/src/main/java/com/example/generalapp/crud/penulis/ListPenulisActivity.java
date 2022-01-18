package com.example.generalapp.crud.penulis;

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
import com.example.generalapp.adapter.AdapterPenulis;
import com.example.generalapp.crud.penulis.ListPenulisActivity;
import com.example.generalapp.crud.penulis.TambahPenulisActivity;
import com.example.generalapp.database.entity.Penulis;
import com.example.generalapp.database.AppDatabase;

import java.util.ArrayList;
import java.util.List;

public class ListPenulisActivity extends AppCompatActivity  {
    private RecyclerView recyclerView;
    private Button btn_tambah_penulis, btn_home;
    private AppDatabase database;
    private AdapterPenulis adapterPenulis;
    private List<Penulis> list = new ArrayList<>();
    private AlertDialog.Builder dialog;

    public ListPenulisActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_penulis);

        recyclerView = findViewById(R.id.recycler_view);
        btn_tambah_penulis = findViewById(R.id.btn_tambah_penulis);
        btn_home = findViewById(R.id.btn_home);

        database = AppDatabase.getInstance(getApplicationContext());
        list.clear();
        list.addAll(database.penulisDao().getAll());
        adapterPenulis = new AdapterPenulis(getApplicationContext(), list);
        adapterPenulis.setDialog(new AdapterPenulis.Dialog() {

            @Override
            public void onClick(int position) {
                final CharSequence[] dialogItem = {"Edit", "Hapus"};
                dialog = new AlertDialog.Builder(ListPenulisActivity.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                Intent intent = new Intent(ListPenulisActivity.this,
                                        TambahPenulisActivity.class);
                                intent.putExtra("id_penulis", list.get(position).id_penulis);
                                startActivity(intent);
                                break;
                            case 1:
                                Penulis penulis = list.get(position);
                                database.penulisDao().delete(penulis);
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
        recyclerView.setAdapter(adapterPenulis);

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListPenulisActivity.this, HomeActivity.class));
            }
        });

        btn_tambah_penulis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListPenulisActivity.this, TambahPenulisActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        list.clear();
        list.addAll(database.penulisDao().getAll());
        adapterPenulis.notifyDataSetChanged();
    }

}
