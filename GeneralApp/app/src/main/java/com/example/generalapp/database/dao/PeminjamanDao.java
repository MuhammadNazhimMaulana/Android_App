package com.example.generalapp.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.generalapp.database.entity.Buku;
import com.example.generalapp.database.entity.Peminjaman;
import com.example.generalapp.database.model.PeminjamanWithBuku;

import java.util.List;

@Dao
public interface PeminjamanDao {

    @Transaction
    @Query("SELECT * FROM peminjaman")
    public List<PeminjamanWithBuku> getPeminjamanWithBuku();

    @Query("INSERT INTO peminjaman (nama_peminjam,bukuId) VALUES(:nama_peminjam, :bukuId)")
    void insertAll(String nama_peminjam, String bukuId);

    @Query("UPDATE peminjaman SET nama_peminjam=:nama_peminjam, bukuId=:bukuId WHERE id_peminjaman=:id_peminjaman")
    void update(int id_peminjaman, String nama_peminjam, String bukuId);

    @Query("SELECT * FROM peminjaman WHERE id_peminjaman=:id_peminjaman")
    Peminjaman get(int id_peminjaman);

    @Delete
    void delete(Peminjaman peminjaman);

    @Query("SELECT id_peminjaman FROM peminjaman WHERE nama_peminjam=:nama_peminjam")
    Peminjaman getNama(String nama_peminjam);

    @Query("SELECT nama_peminjam FROM peminjaman")
    List<String> getAllPeminjam();
}
