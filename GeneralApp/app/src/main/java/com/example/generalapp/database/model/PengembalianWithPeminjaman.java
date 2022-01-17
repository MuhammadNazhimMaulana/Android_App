package com.example.generalapp.database.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.generalapp.database.entity.Buku;
import com.example.generalapp.database.entity.Peminjaman;
import com.example.generalapp.database.entity.Pengembalian;

import java.util.List;

public class PengembalianWithPeminjaman {
    @Embedded
    public Pengembalian pengembalian;
    @Relation(
            parentColumn = "peminjamanId",
            entityColumn = "id_peminjaman"
    )
    public Peminjaman peminjaman;
}

