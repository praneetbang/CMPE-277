package com.example.chatgpt.DbContext;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DBHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "ChatGPT_V2.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_AUDIT_PROMPT = "Audit_Prompt";
    public static final String COLUMN_SEQUENCE_NUMBER = "Sequence_Number";
    public static final String COLUMN_DATE_TIME = "Date_and_Time";
    public static final String COLUMN_PROMPT = "Prompt";

    public static final String TABLE_RESPONSES = "Responses";
    public static final String COLUMN_RESPONSE_SEQUENCE_NUMBER = "Sequence_Number";
    public static final String COLUMN_RESPONSE_DATE_TIME = "Date_and_Time";
    public static final String COLUMN_RESPONSE = "Response";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_AUDIT_PROMPT + " (" +
                COLUMN_SEQUENCE_NUMBER + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_DATE_TIME + " DATETIME DEFAULT CURRENT_TIMESTAMP," +
                COLUMN_PROMPT + " TEXT CHECK(LENGTH(" + COLUMN_PROMPT + ") <= 1024))");

        // Create Responses table
        db.execSQL("CREATE TABLE " + TABLE_RESPONSES + " (" +
                COLUMN_SEQUENCE_NUMBER + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_RESPONSE_DATE_TIME + " DATETIME DEFAULT CURRENT_TIMESTAMP," +
                COLUMN_RESPONSE + " TEXT CHECK(LENGTH(" + COLUMN_RESPONSE + ") <= 4096))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



}
