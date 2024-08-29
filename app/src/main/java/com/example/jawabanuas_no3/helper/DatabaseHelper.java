package com.example.jawabanuas_no3.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    static final String DATABASE_NAME = "dataMahasiswa";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_TABLE = "CREATE TABLE users (id INTEGER PRIMARY KEY autoincrement, name TEXT NOT NULL, nim TEXT NOT NULL, ipk REAL NOT NULL, matkul TEXT NOT NULL)";
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS users");
        onCreate(sqLiteDatabase);
    }

    public ArrayList<HashMap<String, String>> getAll() {
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        String QUERY = "SELECT * FROM users";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(QUERY, null);

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("id", cursor.getString(0));
                map.put("name", cursor.getString(1));
                map.put("nim", cursor.getString(2));
                map.put("ipk", String.valueOf(cursor.getFloat(3)));
                map.put("matkul", cursor.getString(4));
                list.add(map);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public void insert(String name, String nim, String ipk, String matkul) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("nim", nim);
        values.put("ipk", Float.valueOf(ipk));
        values.put("matkul", matkul);
        database.insert("users", null, values);
    }

    public void update(int id, String name, String nim, String ipk, String matkul) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("nim", nim);
        values.put("ipk", Float.valueOf(ipk));
        values.put("matkul", matkul);
        database.update("users", values, "id = ?", new String[]{String.valueOf(id)});
    }

    public void delete(int id) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete("users", "id = ?", new String[]{String.valueOf(id)});
    }
}
