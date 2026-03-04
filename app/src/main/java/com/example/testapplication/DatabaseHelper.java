package com.example.testapplication;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.ContentValues;
import android.content.Context;
import java.util.List;
import java.util.ArrayList;
public class DatabaseHelper extends SQLiteOpenHelper{
    public DatabaseHelper(Context context){
        super(context,"notepad_database", null, 1);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE notes (id INTEGER PRIMARY KEY AUTOINCREMENT, noteTitle TEXT, noteDesc TEXT, date TEXT)");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS notes");
        onCreate(db);
    }

    public boolean addUser(String noteTitle, String noteDesc, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("noteTitle", noteTitle);
        values.put("noteDesc", noteDesc);
        values.put("date", date);
        long result = db.insert("notes", null, values);
        return result != -1;
    }

    public List<String[]> getAllNotes(){
        List<String[]> notesList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT noteTitle, noteDesc, date FROM notes ORDER BY id DESC", null);
        if (cursor.moveToFirst()){
            do{
                String title = cursor.getString(0);
                String desc = cursor.getString(1);
                String date = cursor.getString(2);
                notesList.add(new String[]{title, desc, date});
            } while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return notesList;
    }
}
