package com.example.notesappdemo.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.notesappdemo.Model.Notes;

import java.util.List;

@Dao
public interface mainDao {

    @Query("SELECT * FROM notes")
    LiveData<List<Notes>> getAll();

    @Insert
    void insertNotes(Notes notes);

    @Query("DELETE FROM notes WHERE id=:id")
    void deleteNotes(int id);


    @Update
    void updateNotes(Notes notes);

}

