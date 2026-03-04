package com.example.testapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Notes1 extends AppCompatActivity {

    ImageButton backNote1;
    ImageButton doneButton;
    DatabaseHelper dbHelper;
    TextView titleText, contentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notes1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        doneButton = findViewById(R.id.doneButton);
        titleText = findViewById(R.id.titleText);
        contentText = findViewById(R.id.content);
        dbHelper = new DatabaseHelper(this);

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleText.getText().toString().trim();
                String desc = contentText.getText().toString().trim();
                String date = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(new Date());

                if(title.isEmpty()){
                    Toast.makeText(Notes1.this, "Please input a title", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean saved = dbHelper.addUser(title, desc, date);

                if(saved){
                    Toast.makeText(Notes1.this, "The note had successfully saved", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(Notes1.this, "The note hadn't been saved", Toast.LENGTH_SHORT).show();
                }
            }
        });


        backNote1 = findViewById(R.id.back);
        backNote1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent back = new Intent(Notes1.this, Notepad.class);
                        startActivity(back);
                    }
                }
        );
    }
}