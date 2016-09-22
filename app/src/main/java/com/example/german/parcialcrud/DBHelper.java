package com.example.german.parcialcrud;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by German on 21/09/2016.
 */

public class DBHelper extends SQLiteOpenHelper {

    String tabla = "CREATE TABLE PERSONAS (ID INTEGER PRIMARY KEY, NOMBRE TEXT, APELLIDO TEXT, EDAD TEXT)";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tabla);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE PERSONAS");
        db.execSQL(tabla);

    }
}
