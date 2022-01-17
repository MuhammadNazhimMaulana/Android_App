package com.example.generalapp.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Denda {
    @PrimaryKey
    public int id_denda;

    @ColumnInfo(name = "total_denda")
    public String totalDenda;
    public String pengembalianId;
}
