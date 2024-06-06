package com.example.notesappdemo.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.notesappdemo.DAO.mainDao;
import com.example.notesappdemo.Database.MyRoomDatabase;
import com.example.notesappdemo.Model.Notes;

import java.util.List;

public class NotesRepository {

    public mainDao notesDao;
    public LiveData<List<Notes>> getAllNotes;

    public NotesRepository(Application application){
        MyRoomDatabase database = MyRoomDatabase.getInstance(application);
        notesDao = database.MaindaoInterface();
        getAllNotes = notesDao.getAll();
    }

    public void insert(Notes notes){
        notesDao.insertNotes(notes);
    }

    public  void delete(int id){
        notesDao.deleteNotes(id);
    }

    public void update(Notes notes){
        notesDao.updateNotes(notes);
    }
}
