package com.example.notesappdemo.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.notesappdemo.DAO.mainDao;
import com.example.notesappdemo.Model.Notes;

@Database(entities = {Notes.class},version = 1,exportSchema = false)
public abstract class MyRoomDatabase extends RoomDatabase {

    public abstract mainDao MaindaoInterface();

    private static MyRoomDatabase databseinstance;
    private static final String DB_NAME = "NoteApp";

    public static MyRoomDatabase getInstance(Context context) {
        if (databseinstance == null) {
            databseinstance = Room.databaseBuilder(context.getApplicationContext(),
                            MyRoomDatabase.class, DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return databseinstance;
    }

}
