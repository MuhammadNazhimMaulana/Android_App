package com.example.generalapp.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.generalapp.database.entity.Peminjaman;
import com.example.generalapp.database.entity.Pengembalian;
import com.example.generalapp.database.model.PengembalianWithPeminjaman;

import java.util.List;

@Dao
public interface PengembalianDao {

    @Transaction
    @Query("SELECT * FROM pengembalian")
    public List<PengembalianWithPeminjaman> getPengembalianWithPeminjaman();

    @Query("INSERT INTO pengembalian (tanggal_pengembalian,peminjamanId) VALUES(:tanggal_pengembalian, :peminjamanId)")
    void insertAll(String tanggal_pengembalian, String peminjamanId);

    @Query("UPDATE pengembalian SET tanggal_pengembalian=:tanggal_pengembalian, peminjamanId=:peminjamanId WHERE id_pengembalian=:id_pengembalian")
    void update(int id_pengembalian, String tanggal_pengembalian, String peminjamanId);

    @Query("SELECT * FROM pengembalian WHERE id_pengembalian=:id_pengembalian")
    Pengembalian get(int id_pengembalian);

    @Delete
    void delete(Pengembalian pengembalian);

    @Query("SELECT id_pengembalian FROM pengembalian WHERE tanggal_pengembalian=:tanggal_pengembalian")
    Pengembalian getTanggal(String tanggal_pengembalian);

    @Query("SELECT tanggal_pengembalian FROM pengembalian")
    List<String> getAllKembali();


}