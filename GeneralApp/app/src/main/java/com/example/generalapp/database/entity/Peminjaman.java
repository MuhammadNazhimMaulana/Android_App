package com.example.generalapp.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Peminjaman {

    @PrimaryKey
    public int id_peminjaman;

    @ColumnInfo(name = "nama_peminjam")
    public String namaPeminjam;
    public String bukuId;
}
