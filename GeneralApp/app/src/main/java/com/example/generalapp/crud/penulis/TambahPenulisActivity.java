package com.example.generalapp.crud.penulis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.generalapp.R;
import com.example.generalapp.database.AppDatabase;
import com.example.generalapp.database.entity.Penulis;

public class TambahPenulisActivity extends AppCompatActivity {

    private EditText editPenulisBuku;
    private Button btn_save;
    private AppDatabase database;
    private int id_penulis = 0;
    private boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_penulis);

        editPenulisBuku = findViewById(R.id.penulis_buku);
        btn_save = findViewById(R.id.btn_save);
        database = AppDatabase.getInstance(getApplicationContext());
        Intent intent = getIntent();
        id_penulis = intent.getIntExtra("id_penulis", 0);

        if (id_penulis>0){
            isEdit = true;
            Penulis penulis = database.penulisDao().get(id_penulis);
            editPenulisBuku.setText(penulis.penulisBuku);
        }else{
            isEdit = false;
        }

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isEdit){
                    database.penulisDao().update(id_penulis, editPenulisBuku.getText().toString());
                }else{
                    database.penulisDao().insertAll(editPenulisBuku.getText().toString());
                }
                finish();
            }
        });
    }
}
