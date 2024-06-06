package com.example.notesappdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SearchView;

import com.example.notesappdemo.Adapter.NotesAdapter;
import com.example.notesappdemo.Model.Notes;
import com.example.notesappdemo.ViewModel.NotesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab_add;
    NotesViewModel notesViewModel;

    RecyclerView recyclerViewNotes;
    NotesAdapter notesAdapter;
List<Notes> allNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab_add = findViewById(R.id.fab_add);

        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);
        recyclerViewNotes = findViewById(R.id.recyclerNotes);



        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), NotesTackerActivity.class);
                startActivity(i);
            }
        });

        notesViewModel.getAllNotes.observe(this, notes -> {
            recyclerViewNotes.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayout.VERTICAL));
            notesAdapter = new NotesAdapter(MainActivity.this,notes);
            allNotes = notes;
            recyclerViewNotes.setAdapter(notesAdapter);
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_notes, menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);

        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search Notes Here...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                NotesFiltered(newText);
                return true;
            }
        });
        return true;
    }

    private void NotesFiltered(String newText) {
        ArrayList<Notes> filteredList = new ArrayList<>();

        for (Notes singleNote : allNotes) {
            if (singleNote.getTitle().toLowerCase().contains(newText.toLowerCase())
                    || singleNote.getNotes().toLowerCase().contains(newText.toLowerCase())) {
                filteredList.add(singleNote);
            }
        }
        this.notesAdapter.searchNotes(filteredList);

    }
    }
