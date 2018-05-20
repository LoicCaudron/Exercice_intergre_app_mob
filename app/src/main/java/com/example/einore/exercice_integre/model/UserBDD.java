package com.example.einore.exercice_integre.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class UserBDD {    //classe DAO

    private static final int VERSION = 1;
    private static final String NOM_BDD = "user.db";

    private static final String TABLE_USERS = "table_users";
    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_NAME = "NAME";
    private static final int NUM_COL_NAME = 1;
    private static final String COL_PIN = "PIN";
    private static final int NUM_COL_PIN = 2;
    private static final String COL_T_MIN = "T_MIN";
    private static final int NUM_COL_T_MIN = 3;
    private static final String COL_T_MAX = "T_MAX";
    private static final int NUM_COL_T_MAX = 4;
    private static final String COL_HUMIDITY_MIN = "HUMIDITY_MIN";
    private static final int NUM_COL_HUMIDITY_MIN = 5;
    private static final String COL_HUMIDITY_MAX = "HUMIDITY_MAX";
    private static final int NUM_COL_HUMIDITY_MAX = 6;
    private static final String COL_BATTERY_MIN = "BATTERY_MIN";
    private static final int NUM_COL_BATTERY_MIN = 7;
    private static final String COL_BATTERY_MAX = "BATTERY_MAX";
    private static final int NUM_COL_BATTERY_MAX = 8;


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

    public long insertUser(User user) {
        ContentValues content = new ContentValues();
        content.put(COL_NAME, user.getName());

        return bdd.insert(TABLE_USERS, null, content);
    }

    public int updateUser(int id, User user) {
        ContentValues content = new ContentValues();
        content.put(COL_T_MIN, user.getT_min());
        content.put(COL_T_MAX, user.getT_max());
        content.put(COL_HUMIDITY_MIN, user.getHumidity_min());
        content.put(COL_HUMIDITY_MAX, user.getHumidity_max());
        content.put(COL_BATTERY_MIN, user.getBattery_min());
        content.put(COL_BATTERY_MAX, user.getBattery_max());

        return bdd.update(TABLE_USERS, content, COL_ID + " = " + id, null);
    }

    public int removeUser(String name) {                                                            //sûrement à changer  !!!!!!!
        return bdd.delete(TABLE_USERS, COL_NAME + " = " + name, null);
    }


    public User getUser(int id) {
        Cursor c = bdd.query(TABLE_USERS,
                new String[] { COL_ID, COL_NAME, COL_PIN, COL_T_MIN, COL_T_MAX, COL_HUMIDITY_MIN, COL_HUMIDITY_MAX, COL_BATTERY_MIN, COL_BATTERY_MAX},
                COL_ID + " = " + id,
                null,
                null,
                null,
                null);
        return cursorToUser(c);
    }

    public User getUser(String name, int pin) {
        Cursor c = bdd.query(TABLE_USERS, new String[] { COL_ID, COL_NAME,
                        COL_PIN }, COL_NAME + " LIKE \"" + name + "\" AND " + COL_PIN + "LIKE " + pin, null, null,
                null, COL_NAME);
        return cursorToUser(c);
    }

    public User cursorToUser(Cursor c) {
        if (c.getCount() == 0) {
            c.close();
            return null;
        }
        User user = new User("Loic", 1234);
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
            User user = new User("Loic", 1234);
            user.setId(c.getInt(NUM_COL_ID));
            user.setName(c.getString(NUM_COL_NAME));

            userList.add(user);
        }
        c.close();
        return userList;
    }
}
