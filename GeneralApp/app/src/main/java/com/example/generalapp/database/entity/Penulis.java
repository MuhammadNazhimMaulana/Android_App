package com.example.generalapp.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Penulis {

    @PrimaryKey
    public int id_penulis;

    @ColumnInfo(name = "penulis_buku")
    public String penulisBuku;
}
