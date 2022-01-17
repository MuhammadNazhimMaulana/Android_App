package com.example.generalapp.database.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.generalapp.database.entity.Buku;
import com.example.generalapp.database.entity.Peminjaman;

import java.util.List;

public class PeminjamanWithBuku {
    @Embedded
    public Peminjaman peminjaman;
    @Relation(
            parentColumn = "bukuId",
            entityColumn = "id_buku"
    )
    public Buku buku;
}
