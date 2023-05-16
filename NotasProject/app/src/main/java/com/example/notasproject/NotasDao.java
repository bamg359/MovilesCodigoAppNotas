package com.example.notasproject;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NotasDao {


    @Insert
    void insert(Notas notas);

    @Update
    void update(Notas notas);

    @Delete
    void delete(Notas notas);

    @Query("SELECT * FROM notas_table ORDER BY id ASC")
    LiveData<List<Notas>> getAllNotas();


}
