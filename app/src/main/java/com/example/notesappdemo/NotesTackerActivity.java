package com.example.notesappdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.notesappdemo.Model.Notes;
import com.example.notesappdemo.ViewModel.NotesViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NotesTackerActivity extends AppCompatActivity {

    ImageView img_back, img_save;
    EditText edt_title, edt_notes;
    String title,description;
    NotesViewModel notesViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_tacker);

        img_back = findViewById(R.id.img_back);
        img_save = findViewById(R.id.img_save);
        edt_title = findViewById(R.id.edt_title);
        edt_notes = findViewById(R.id.edt_notes);

        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);


        img_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 title = edt_title.getText().toString();
                 description = edt_notes.getText().toString();

                 CreateNotes(title,description);
            }
        });

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void CreateNotes(String title, String description) {

        if (title.trim().isEmpty() && description.trim().isEmpty()) {

            Toast.makeText(NotesTackerActivity.this, "Please add a note!", Toast.LENGTH_SHORT).show();
            return;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MMM YYYY HH:mm a");
        Date date = new Date();


        Notes notes1 = new Notes();
        notes1.setTitle(title);
        notes1.setNotes(description);
        notes1.setDate(dateFormat.format(date));

        notesViewModel.VinsertNote(notes1);

        Toast.makeText(NotesTackerActivity.this, "Notes Created Successfully", Toast.LENGTH_SHORT).show();
        finish();
    }
}