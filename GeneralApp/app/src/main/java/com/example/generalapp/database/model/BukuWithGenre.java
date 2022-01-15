package com.example.generalapp.database.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.generalapp.database.entity.Buku;
import com.example.generalapp.database.entity.Genre;

import java.util.List;

public class BukuWithGenre {

    @Embedded
    public Buku buku;
    @Relation(
            parentColumn = "genreId",
            entityColumn = "id_genre"
    )
    public Genre genre;
}
