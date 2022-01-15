package com.example.generalapp.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;
import com.example.generalapp.database.entity.Genre;

import java.util.List;

@Dao
public interface GenreDao {

    @Query("SELECT * FROM genre")
    List<Genre> getAll();

    @Query("SELECT genre_buku FROM genre")
    List<String> getAllGenre();

    @Query("INSERT INTO genre (genre_buku) VALUES(:genre_buku)")
    void insertAll(String genre_buku);

    @Query("UPDATE genre SET genre_buku=:genre_buku WHERE id_genre=:id_genre")
    void update(int id_genre, String genre_buku);

    @Query("SELECT * FROM genre WHERE id_genre=:id_genre")
    Genre get(int id_genre);

    @Query("SELECT id_genre FROM genre WHERE genre_buku=:genre_buku")
    Genre getNama(String genre_buku);

    @Delete
    void delete(Genre genre);

}
