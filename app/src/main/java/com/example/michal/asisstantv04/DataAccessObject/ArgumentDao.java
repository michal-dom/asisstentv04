package com.example.michal.asisstantv04.DataAccessObject;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.michal.asisstantv04.Models.Argument;

import java.util.ArrayList;
import java.util.List;

public class ArgumentDao extends DataBaseContentProvider implements IArgumentDao, IArgumentSchema{
    private Cursor cursor;
    private ContentValues initialValues;

    public ArgumentDao(SQLiteDatabase db) {
        super(db);
    }

    @Override
    public Argument fetchArgById(int argId) {
        final String selectionArgs[] = {String.valueOf(argId)};
        final String selection = COLUMN_ARG_ID + " = ?";
        Argument arg = new Argument();
        cursor = super.query(ARGS_TABLE, ARG_COLUMNS, selection, selectionArgs, COLUMN_ARG_ID);
        if (cursor != null){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                arg = cursorToEntity(cursor);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return arg;

    }

    @Override
    public List<Argument> fetchAllArgs() {
        List<Argument> argList = new ArrayList<>();
        cursor = super.query(ARGS_TABLE, ARG_COLUMNS, null, null, COLUMN_ARG_ID);

        if(cursor != null){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                Argument arg = cursorToEntity(cursor);
                argList.add(arg);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return argList;
    }

    @Override
    public int addArgs(Argument arg) {
        setContentValue(arg);
        try {
            return (int) super.insert(ARGS_TABLE, getContentValue());
        }catch (SQLiteConstraintException ex){
            Log.w("Database", ex.getMessage());
            return -1;
        }
    }

    private ContentValues getContentValue() {
        return initialValues;
    }

    private void setContentValue(Argument arg) {
        initialValues = new ContentValues();
        initialValues.put(COLUMN_REQUEST_ID,arg.requestId);
        initialValues.put(COLUMN_ARG,arg.argContent);
        initialValues.put(COLUMN_TYPE, arg.type);
    }

    @Override
    public boolean deleteAllArgs() {
        return false;
    }

    @Override
    protected Argument cursorToEntity(Cursor cursor) {

        Argument arg = new Argument();

        int argIdIndex;
        int requestIdIndex;
        int argContentIndex;
        int typeIndex;

        if(cursor != null){
            if (cursor.getColumnIndex(COLUMN_ARG_ID) != -1){
                argIdIndex = cursor.getColumnIndexOrThrow(COLUMN_ARG_ID);
                arg.argId = cursor.getInt(argIdIndex);
            }
            if (cursor.getColumnIndex(COLUMN_REQUEST_ID) != -1){
                requestIdIndex = cursor.getColumnIndexOrThrow(COLUMN_REQUEST_ID);
                arg.requestId = cursor.getInt(requestIdIndex);
            }
            if (cursor.getColumnIndex(COLUMN_ARG) != -1){
                argContentIndex = cursor.getColumnIndexOrThrow(COLUMN_ARG);
                arg.argContent = cursor.getString(argContentIndex);
            }
            if (cursor.getColumnIndex(COLUMN_TYPE) != -1){
                typeIndex = cursor.getColumnIndexOrThrow(COLUMN_TYPE);
                arg.type = cursor.getString(typeIndex);
            }
        }
        return arg;
    }
}
