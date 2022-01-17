package com.example.generalapp.database.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.generalapp.database.entity.Buku;
import com.example.generalapp.database.entity.Peminjaman;
import com.example.generalapp.database.entity.Pengembalian;
import com.example.generalapp.database.entity.Denda;

import java.util.List;

public class DendaWithPengembalian {
    @Embedded
    public Denda denda;
    @Relation(
            parentColumn = "pengembalianId",
            entityColumn = "id_pengembalian"
    )
    public Pengembalian pengembalian;
}
