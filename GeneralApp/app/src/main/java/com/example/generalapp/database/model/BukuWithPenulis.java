package com.example.generalapp.database.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.generalapp.database.entity.Buku;
import com.example.generalapp.database.entity.Penulis;

public class BukuWithPenulis {
    @Embedded
    public Buku buku;
    @Relation(
            parentColumn = "penulisId",
            entityColumn = "id_penulis"
    )
    public Penulis penulis;
}
