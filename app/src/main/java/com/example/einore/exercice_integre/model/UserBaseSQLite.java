package com.example.einore.exercice_integre.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class UserBaseSQLite extends SQLiteOpenHelper{

    public static final String TABLE_USERS = "table_users";
    public static final String COL_ID = "ID";
    public static final String COL_NAME = "NAME";

    public static final String CREATE_BDD = "CREATE TABLE " + TABLE_USERS + " (" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NAME + " TEXT NOT NULL);";

    public UserBaseSQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super (context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_USERS);
        onCreate(db);
    }
}
