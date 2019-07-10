package com.example.android.viewpager.Data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

public class Provider extends ContentProvider {
    private static final int NEWS = 100;
    private static final int NEWS_ID = 101;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static final String LOG_TAG = "";

    static {
        sUriMatcher.addURI(Contract.Entry.CONTENT_AUTHORITY, Contract.Entry.PATH_NEWS, NEWS);
        sUriMatcher.addURI(Contract.Entry.CONTENT_AUTHORITY, Contract.Entry.PATH_NEWS + "/#", NEWS_ID);
    }

    private DbHelper mDbHelper;
    @Override
    public boolean onCreate() {
        mDbHelper = new DbHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor;

        SQLiteDatabase mSQLiteDatabase = mDbHelper.getReadableDatabase();

        int match = sUriMatcher.match(uri);

        switch (match) {
            case NEWS:
                cursor = mSQLiteDatabase.query(Contract.Entry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, null);
                break;
            case NEWS_ID:
                selection = Contract.Entry.COLUMN_PAPER + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };

                cursor = mSQLiteDatabase.query(Contract.Entry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, null);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int match = sUriMatcher.match(uri);
        switch (match) {
            case NEWS:

                return insertNews(uri, values);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }
    private Uri insertNews(Uri uri, ContentValues values) {
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        // Insert the new pet with the given values
        long id = database.insert(Contract.Entry.TABLE_NAME, null, values);
        // If the ID is -1, then the insertion failed. Log an error and return null.
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        int rowDeleted;
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case NEWS:
                // Delete all rows that match the selection and selection args

                rowDeleted = database.delete(Contract.Entry.TABLE_NAME, selection, selectionArgs);
                break;
            case NEWS_ID:
                // Delete a single row given by the ID in the URI
                selection = Contract.Entry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                //getContext().getContentResolver().notifyChange(uri, null);
                rowDeleted = database.delete(Contract.Entry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }
        if(rowDeleted!=0){
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowDeleted;
    }

    @Override
    public int update(Uri uri,ContentValues values,String selection, String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case NEWS:
                return updateNews(uri, values, selection, selectionArgs);
            case NEWS_ID:
                selection = Contract.Entry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updateNews(uri, values, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }
    }
    private int updateNews(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        // Returns the number of database rows affected by the update statement
        getContext().getContentResolver().notifyChange(uri, null);
        return database.update(Contract.Entry.TABLE_NAME, values, selection, selectionArgs);
    }
}
