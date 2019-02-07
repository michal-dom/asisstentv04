package com.example.michal.asisstantv04.DataAccessObject;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.michal.asisstantv04.Models.Request;

import java.util.ArrayList;
import java.util.List;

public class RequestDao extends DataBaseContentProvider implements IRequestDao, IRequestSchema {
    private Cursor cursor;
    private ContentValues initialValues;

    public RequestDao(SQLiteDatabase db) {
        super(db);
    }

    @Override
    public Request fetchRequestById(int requestId) {
        final String selectionArgs[] = {String.valueOf(requestId)};
        final String selection = COLUMN_ID + " = ?";
        Request request = new Request();
        cursor = super.query(REQUEST_TABLE, REQUEST_COLUMNS, selection, selectionArgs, COLUMN_ID);
        if (cursor != null){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                request = cursorToEntity(cursor);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return request;
    }

    @Override
    public List<Request> fetchAllRequests() {
        List<Request> requestList = new ArrayList<Request>();
        cursor = super.query(REQUEST_TABLE, REQUEST_COLUMNS, null, null, COLUMN_ID);

        if(cursor != null){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                Request request = cursorToEntity(cursor);
                requestList.add(request);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return requestList;
    }

    @Override
    public int addRequest(Request request) {

        setContentValue(request);
        try {
            return (int) super.insert(REQUEST_TABLE, getContentValue());
        }catch (SQLiteConstraintException ex){
            Log.w("Database", ex.getMessage());
            return -1;
        }
    }

    @Override
    public boolean deleteAllRequests() {
        return false;
    }

    @Override
    protected Request cursorToEntity(Cursor cursor) {
        Request request = new Request();

        int idIndex;
        int textIndex;
        int actionIndex;

        if(cursor != null){
            if (cursor.getColumnIndex(COLUMN_ID) != -1){
                idIndex = cursor.getColumnIndexOrThrow(COLUMN_ID);
                request.setRequestId(cursor.getInt(idIndex));
            }
            if (cursor.getColumnIndex(COLUMN_TEXT) != -1){
                textIndex = cursor.getColumnIndexOrThrow(COLUMN_TEXT);
                request.setText(cursor.getString(textIndex));
            }
            if (cursor.getColumnIndex(COLUMN_ACTION) != -1){
                actionIndex = cursor.getColumnIndexOrThrow(COLUMN_ACTION);
                request.setAction(cursor.getString(actionIndex));
            }
        }
        return request;
    }

    private ContentValues getContentValue() {
        return initialValues;
    }

    private void setContentValue(Request request) {
        initialValues = new ContentValues();
        initialValues.put(COLUMN_TEXT,request.getText());
        initialValues.put(COLUMN_ACTION,request.getAction());
    }
}
