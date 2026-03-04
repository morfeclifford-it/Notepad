package com.example.testapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class Notepad extends AppCompatActivity {

    Button addnote;

    RecyclerView recyclerView;

    DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notepad);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerViewTobby);
        dbHelper = new DatabaseHelper(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        addnote = findViewById(R.id.addNotes);
        addnote.setOnClickListener(v -> {
            Intent next = new Intent(Notepad.this, Notes1.class);
            startActivity(next);
        });
    }
    @Override
    protected void onResume(){
        super.onResume();
        List<String[]> notesList = dbHelper.getAllNotes();
        NotesAdapter adapter = new NotesAdapter(notesList);
        recyclerView.setAdapter(adapter);
    }
}