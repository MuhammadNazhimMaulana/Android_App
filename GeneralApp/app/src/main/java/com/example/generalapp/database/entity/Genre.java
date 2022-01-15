package com.example.generalapp.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Genre {

    @PrimaryKey
    public int id_genre;

    @ColumnInfo(name = "genre_buku")
    public String genreBuku;
}
