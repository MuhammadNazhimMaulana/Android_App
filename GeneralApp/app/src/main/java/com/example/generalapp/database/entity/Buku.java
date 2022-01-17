package com.example.generalapp.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class Buku {

    @PrimaryKey
    public int id_buku;

    @ColumnInfo(name = "judul_buku")
    public String judulBuku;
    public String namaPenulis;
    public String jumlahHalaman;
    public String genreId;
}
