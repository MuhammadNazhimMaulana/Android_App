package com.example.generalapp.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.generalapp.database.entity.Denda;
import com.example.generalapp.database.model.DendaWithPengembalian;

import java.util.List;

@Dao
public interface DendaDao {

    @Transaction
    @Query("SELECT * FROM denda")
    public List<DendaWithPengembalian> getDendaWithPengembalian();

    @Query("INSERT INTO denda (total_denda,pengembalianId) VALUES(:total_denda, :pengembalianId)")
    void insertAll(String total_denda, String pengembalianId);

    @Query("UPDATE denda SET total_denda=:total_denda, pengembalianId=:pengembalianId WHERE id_denda=:id_denda")
    void update(int id_denda, String total_denda, String pengembalianId);

    @Query("SELECT * FROM denda WHERE id_denda=:id_denda")
    Denda get(int id_denda);

    @Delete
    void delete(Denda denda);


}
