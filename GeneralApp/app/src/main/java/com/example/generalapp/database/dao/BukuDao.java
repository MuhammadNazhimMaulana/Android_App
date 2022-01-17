package com.example.generalapp.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;
import androidx.room.Transaction;
import com.example.generalapp.database.entity.Buku;
import com.example.generalapp.database.entity.Genre;
import com.example.generalapp.database.model.BukuWithGenre;

import java.util.List;

@Dao
public interface BukuDao {

    @Query("SELECT * FROM buku")
    public List<Buku> getAll();

    @Query("SELECT judul_buku FROM buku")
    List<String> getAllBuku();

    @Query("SELECT id_buku FROM buku WHERE judul_buku=:judul_buku")
    Buku getJudul(String judul_buku);

    @Transaction
    @Query("SELECT * FROM buku")
    public List<BukuWithGenre> getBukuWithGenre();

    @Query("INSERT INTO buku (judul_buku,namaPenulis,jumlahHalaman,genreId) VALUES(:judul_buku, :namaPenulis, :jumlahHalaman, :genreId)")
    void insertAll(String judul_buku, String namaPenulis, String jumlahHalaman, String genreId);

    @Query("UPDATE buku SET judul_buku=:judul_buku, namaPenulis=:namaPenulis, jumlahHalaman=:jumlahHalaman, genreId=:genreId WHERE id_buku=:id_buku")
    void update(int id_buku, String judul_buku, String namaPenulis, String jumlahHalaman,  String genreId);

    @Query("SELECT * FROM buku WHERE id_buku=:id_buku")
    Buku get(int id_buku);

    @Delete
    void delete(Buku buku);
}
