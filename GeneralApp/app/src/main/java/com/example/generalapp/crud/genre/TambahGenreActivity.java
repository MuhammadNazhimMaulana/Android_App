package com.example.generalapp.crud.genre;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.generalapp.R;
import com.example.generalapp.database.AppDatabase;
import com.example.generalapp.database.entity.Genre;

public class TambahGenreActivity extends AppCompatActivity {

    private EditText editGenreBuku;
    private Button btn_save;
    private AppDatabase database;
    private int id_genre = 0;
    private boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_genre);

        editGenreBuku = findViewById(R.id.genre_buku);
        btn_save = findViewById(R.id.btn_save);
        database = AppDatabase.getInstance(getApplicationContext());
        Intent intent = getIntent();
        id_genre = intent.getIntExtra("id_genre", 0);

        if (id_genre>0){
            isEdit = true;
            Genre genre = database.genreDao().get(id_genre);
            editGenreBuku.setText(genre.genreBuku);
        }else{
            isEdit = false;
        }

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isEdit){
                    database.genreDao().update(id_genre, editGenreBuku.getText().toString());
                }else{
                    database.genreDao().insertAll(editGenreBuku.getText().toString());
                }
                finish();
            }
        });
    }
}