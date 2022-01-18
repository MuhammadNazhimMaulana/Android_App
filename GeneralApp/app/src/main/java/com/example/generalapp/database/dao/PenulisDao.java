package com.example.generalapp.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;
import com.example.generalapp.database.entity.Penulis;

import java.util.List;

@Dao
public interface PenulisDao {

    @Query("SELECT * FROM penulis")
    List<Penulis> getAll();

    @Query("SELECT penulis_buku FROM penulis")
    List<String> getAllPenulis();

    @Query("INSERT INTO penulis (penulis_buku) VALUES(:penulis_buku)")
    void insertAll(String penulis_buku);

    @Query("UPDATE penulis SET penulis_buku=:penulis_buku WHERE id_penulis=:id_penulis")
    void update(int id_penulis, String penulis_buku);

    @Query("SELECT * FROM penulis WHERE id_penulis=:id_penulis")
    Penulis get(int id_penulis);

    @Query("SELECT id_penulis FROM penulis WHERE penulis_buku=:penulis_buku")
    Penulis getNama(String penulis_buku);

    @Delete
    void delete(Penulis penulis);

}
