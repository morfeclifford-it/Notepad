package com.example.testapplication;

import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import android.widget.TextView;
import android.view.LayoutInflater;
import java.util.List;
public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder>{
    private List<String[]> notesList;

    public NotesAdapter(List<String[]> notesList){
        this.notesList = notesList;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_notes_view, parent, false);
        return new NoteViewHolder (view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position){
        String[] mark = notesList.get(position);
        holder.title.setText(mark[0]);
        holder.desc.setText(mark[1]);
        holder.desc.setText(mark[2]);
    }

    @Override
    public int getItemCount(){
        return notesList.size();
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder{
        TextView title, desc, date;
        public NoteViewHolder(@NonNull View listView){
            super(listView);
            title = listView.findViewById(R.id.viewTitle);
            desc = listView.findViewById(R.id.viewContent);
            date = listView.findViewById(R.id.DateView);
        }
    }
}
