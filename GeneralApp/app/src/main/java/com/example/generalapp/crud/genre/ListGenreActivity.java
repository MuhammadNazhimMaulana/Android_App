package com.example.generalapp.crud.genre;

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
import com.example.generalapp.database.entity.Genre;
import com.example.generalapp.database.AppDatabase;
import com.example.generalapp.adapter.AdapterGenre;

import java.util.ArrayList;
import java.util.List;

public class ListGenreActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button btn_tambah_genre, btn_home;
    private AppDatabase database;
    private AdapterGenre adapterGenre;
    private List<Genre> list = new ArrayList<>();
    private AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_genre);

        recyclerView = findViewById(R.id.recycler_view);
        btn_tambah_genre = findViewById(R.id.btn_tambah_genre);
        btn_home = findViewById(R.id.btn_home);

        database = AppDatabase.getInstance(getApplicationContext());
        list.clear();
        list.addAll(database.genreDao().getAll());
        adapterGenre = new AdapterGenre(getApplicationContext(), list);
        adapterGenre.setDialog(new AdapterGenre.Dialog() {

            @Override
            public void onClick(int position) {
                final CharSequence[] dialogItem = {"Edit", "Hapus"};
                dialog = new AlertDialog.Builder(ListGenreActivity.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                Intent intent = new Intent(ListGenreActivity.this,
                                        TambahGenreActivity.class);
                                intent.putExtra("id_genre", list.get(position).id_genre);
                                startActivity(intent);
                                break;
                            case 1:
                                Genre genre = list.get(position);
                                database.genreDao().delete(genre);
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
        recyclerView.setAdapter(adapterGenre);

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListGenreActivity.this, HomeActivity.class));
            }
        });

        btn_tambah_genre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListGenreActivity.this, TambahGenreActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        list.clear();
        list.addAll(database.genreDao().getAll());
        adapterGenre.notifyDataSetChanged();
    }

}