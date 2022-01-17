package com.example.generalapp.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Pengembalian {
    @PrimaryKey
    public int id_pengembalian;

    @ColumnInfo(name = "tanggal_pengembalian")
    public String tanggalPengembalian;
    public String peminjamanId;
}
