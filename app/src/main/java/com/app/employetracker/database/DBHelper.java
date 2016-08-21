package com.app.employetracker.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.app.employetracker.utility.Constants;

import org.byteclues.lib.init.Env;

/**
 * Created by Hitesh kumawat on 21-08-2016.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "taskmanager";
    public static final int DB_VERSION = 1;
    public static String createTable = "CREATE TABLE IF NOT EXISTS ";
    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createProjectTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static void createProjectTable(SQLiteDatabase db) {
        db.execSQL(createTable + Constants.TABLE_NAME + "("
                + "id" + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + "lat" + " TEXT,"
                + "lng" + " TEXT,"
                + "time_stamp" + " TEXT);");
    }
}
