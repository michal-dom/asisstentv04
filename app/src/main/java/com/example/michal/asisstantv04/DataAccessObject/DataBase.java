package com.example.michal.asisstantv04.DataAccessObject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.SQLException;

public class DataBase {
    private static final String TAG = "VoiceAssistantDatabase";
    private static final String DATABASE_NAME = "voice_assistant_database.db";
    private DatabaseHelper mDbHelper;
    // Increment DB Version on any schema change
    private static final int DATABASE_VERSION = 11;
    private final Context mContext;
    public static RequestDao mRequestDao;
    public static ArgumentDao mArgDao;
    public static SQLiteDatabase mDb;

    public DataBase open() throws SQLException {
        mDbHelper = new DatabaseHelper(mContext);
        mDb = mDbHelper.getWritableDatabase();

        mRequestDao = new RequestDao(mDb);
        mArgDao = new ArgumentDao(mDb);

        return this;
    }

    public void close() {
        mDbHelper.close();
    }

    public DataBase(Context context) {
        this.mContext = context;
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(IRequestSchema.REQUEST_TABLE_CREATE);
            db.execSQL(IArgumentSchema.ARG_TABLE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion,
                              int newVersion) {
            Log.w(TAG, "Upgrading database from version "
                    + oldVersion + " to "
                    + newVersion + " which destroys all old data");

            db.execSQL("DROP TABLE IF EXISTS "
                    + IRequestSchema.REQUEST_TABLE);
            db.execSQL("DROP TABLE IF EXISTS "
                    + IArgumentSchema.ARGS_TABLE);
            onCreate(db);

        }
    }
}
