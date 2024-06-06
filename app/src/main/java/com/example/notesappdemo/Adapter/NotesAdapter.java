package com.example.notesappdemo.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesappdemo.MainActivity;
import com.example.notesappdemo.Model.Notes;
import com.example.notesappdemo.R;
import com.example.notesappdemo.UpdateNoteActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder>{
    MainActivity mainActivity;
    List<Notes> notesList;
    List<Notes> allSearchNotes;
    public NotesAdapter(MainActivity mainActivity, List<Notes> notes) {
        this.mainActivity = mainActivity;
        this.notesList = notes;
        allSearchNotes = new ArrayList<>(notesList);
    }



    public void searchNotes(List<Notes> filteredNotes)
    {
        this.notesList = filteredNotes;
        notifyDataSetChanged();

    }
    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mainActivity).inflate(R.layout.notes_list_layout, parent, false);
        NotesViewHolder viewHolder = new NotesViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        Notes note = notesList.get(position);
        holder.tv_title.setText(note.getTitle());
        holder.tv_notes.setText(note.getNotes());
        holder.tv_date.setText(note.getDate());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mainActivity, UpdateNoteActivity.class);

                intent.putExtra("note_id",note.getID());
                intent.putExtra("note_title",note.getTitle());
                intent.putExtra("note_description",note.getNotes());

                mainActivity.startActivity(intent);

            }
        });

        int color_code = getRandomColor();
        int color = ContextCompat.getColor(holder.itemView.getContext(),color_code);
        holder.notes_container.setCardBackgroundColor(color);

    }

    private int getRandomColor() {
        List<Integer> colorCode = new ArrayList<>();
        colorCode.add(R.color.LUMBIABLUE);
        colorCode.add(R.color.SAGE);
        colorCode.add(R.color.BLOSSOM);
        colorCode.add(R.color.LAVENDER);
        colorCode.add(R.color.GOLDENROD);
        colorCode.add(R.color.LIGHTPITCH);
        colorCode.add(R.color.pink);
        colorCode.add(R.color.light_pink);
        colorCode.add(R.color.MELON);
        colorCode.add(R.color.light_purple);
        colorCode.add(R.color.deep_cyan);
        colorCode.add(R.color.light_blue);
        colorCode.add(R.color.blue);
        colorCode.add(R.color.deep_cyan);
        colorCode.add(R.color.teal);
        colorCode.add(R.color.TANG);

        Random random = new Random();
        int random_color = random.nextInt(colorCode.size());
        return colorCode.get(random_color);

    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    class NotesViewHolder extends RecyclerView.ViewHolder {

        CardView notes_container;
        TextView tv_title, tv_notes, tv_date;
        ImageView img_pin;
     public NotesViewHolder(@NonNull View itemView) {
         super(itemView);

         notes_container = itemView.findViewById(R.id.notes_container);
         tv_title = itemView.findViewById(R.id.tv_title);
         tv_notes = itemView.findViewById(R.id.tv_notes);

         tv_date = itemView.findViewById(R.id.tv_date);
         img_pin = itemView.findViewById(R.id.img_pin);

     }
 }
}
