package com.example.notesappdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notesappdemo.Model.Notes;
import com.example.notesappdemo.ViewModel.NotesViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateNoteActivity extends AppCompatActivity {
    ImageView img_back,img_update,img_cancel;
    EditText upTitle,upNote;
    String upnote_title,upnote_description;
    int upnote_id;
    NotesViewModel notesViewModel;
    Notes notes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);

        upTitle = findViewById(R.id.upTitle);
        upNote = findViewById(R.id.upNotes);
        img_back = findViewById(R.id.img_back);
        img_update = findViewById(R.id.img_update);
        img_cancel = findViewById(R.id.img_cancel);

        notes = new Notes();

        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);


        Intent intent = getIntent();
        upnote_id = intent.getIntExtra("note_id",0);
        upnote_title = intent.getStringExtra("note_title");
        upnote_description = intent.getStringExtra("note_description");

        upTitle.setText(upnote_title);
        upNote.setText(upnote_description);


        img_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = upTitle.getText().toString();
                String description = upNote.getText().toString();

                UpdateNotes(title,description);
            }
        });

        img_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void UpdateNotes(String title, String description) {


        if (title.trim().isEmpty() && description.trim().isEmpty()) {

            Toast.makeText(UpdateNoteActivity.this, "Please add a note!", Toast.LENGTH_SHORT).show();
            return;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MMM YYYY HH:mm a");
        Date date = new Date();


        Notes updateNote = new Notes();
        updateNote.setID(upnote_id);
        updateNote.setTitle(title);
        updateNote.setNotes(description);
        updateNote.setDate(dateFormat.format(date));

        notesViewModel.VupdateNote(updateNote);

        Toast.makeText(UpdateNoteActivity.this, "Notes Updated Successfully", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.delete){
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(UpdateNoteActivity.this,R.style.BottomSheetStyle);
            View view = LayoutInflater.from(UpdateNoteActivity.this)
                    .inflate(R.layout.delete_bottom_sheet,(LinearLayout) findViewById(R.id.bottomSheet));

            bottomSheetDialog.setContentView(view);

            TextView tvyes,tvno;

            tvyes = view.findViewById(R.id.btnYes);
            tvno = view.findViewById(R.id.btnNo);

            tvno.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bottomSheetDialog.dismiss();
                }
            });

            tvyes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    notesViewModel.VdeleteNote(upnote_id);
                    finish();
                }
            });

            bottomSheetDialog.show();
        }

        return true;
    }
}