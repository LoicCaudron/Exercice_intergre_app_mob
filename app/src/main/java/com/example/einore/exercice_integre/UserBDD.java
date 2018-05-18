package com.example.einore.exercice_integre;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class UserBDD {

    private static final int VERSION = 1;
    private static final String NOM_BDD = "user.db";

    private static final String TABLE_USERS = "table_users";
    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_NAME = "NAME";
    private static final int NUM_COL_NAME = 1;
    private static final String COL_PIN = "PIN";
    private static final int NUM_COL_PIN = 2;

    private SQLiteDatabase bdd;

    private UserBaseSQLite users;

    public UserBDD(Context context) {
        users = new UserBaseSQLite(context, NOM_BDD, null, VERSION);
    }

    public void openForWrite() {
        bdd = users.getWritableDatabase();
    }

    public void openForRead() {
        bdd = users.getReadableDatabase();
    }

    public void close() {
        bdd.close();
    }

    public SQLiteDatabase getBdd() {
        return bdd;
    }

    public long insertLivre(User user) {
        ContentValues content = new ContentValues();
        content.put(COL_NAME, user.getName());

        return bdd.insert(TABLE_USERS, null, content);
    }

    public int updateLivre(int id, User user) {
        ContentValues content = new ContentValues();
        content.put(COL_NAME, user.getName());

        return bdd.update(TABLE_USERS, content, COL_ID + " = " + id, null);
    }

    public int removeLivre(String name) {
        return bdd.delete(TABLE_USERS, COL_NAME + " = " + name, null);
    }

    public User getUser(String name) {
        Cursor c = bdd.query(TABLE_USERS, new String[] { COL_ID, COL_NAME,
                        COL_PIN }, COL_NAME + " LIKE \"" + name + "\"", null, null,
                null, COL_NAME);
        return cursorToUser(c);
    }

    public User cursorToUser(Cursor c) {
        if (c.getCount() == 0) {
            c.close();
            return null;
        }
        User user = new User();
        user.setId(c.getInt(NUM_COL_ID));
        user.setName(c.getString(NUM_COL_NAME));

        c.close();
        return user;
    }

    public ArrayList<User> getAllUsers() {
        Cursor c = bdd.query(TABLE_USERS, new String[] { COL_ID, COL_NAME,
                COL_PIN }, null, null, null, null, COL_NAME);
        if (c.getCount() == 0) {
            c.close();
            return null;
        }
        ArrayList<User> userList = new ArrayList<User> ();
        while (c.moveToNext()) {
            User user = new User();
            user.setId(c.getInt(NUM_COL_ID));
            user.setName(c.getString(NUM_COL_NAME));

            userList.add(user);
        }
        c.close();
        return userList;
    }
}
