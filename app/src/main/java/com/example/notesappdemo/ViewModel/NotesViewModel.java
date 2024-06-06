package com.example.notesappdemo.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.notesappdemo.Model.Notes;
import com.example.notesappdemo.Repository.NotesRepository;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {

    public NotesRepository notesRepository;
    public LiveData<List<Notes>> getAllNotes;
    public NotesViewModel(@NonNull Application application) {
        super(application);


        notesRepository   = new NotesRepository(application);
        getAllNotes = notesRepository.getAllNotes;

    }


    public void VinsertNote(Notes notes){
        notesRepository.insert(notes);
    }

    public void VdeleteNote(int id){
        notesRepository.delete(id);
    }

    public void VupdateNote(Notes notes){
        notesRepository.update(notes);
    }

}
