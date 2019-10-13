package com.example.modelpaperapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {



    public static final String DATABASE_NAME = "projectModel.db";
    public static final int DATABASE_VERSION = 1;

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
}

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);

    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + UserProfile.Users.TABLE_NAME + " (" +
                    UserProfile.Users._ID + " INTEGER PRIMARY KEY," +
                    UserProfile.Users.C1 + " TEXT," +
                    UserProfile.Users.C2 + " TEXT," +
                    UserProfile.Users.C3 + " TEXT," +
                    UserProfile.Users.C4 + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + UserProfile.Users.TABLE_NAME;


    public long addInfo(String username,String dob ,String password, String gender)
    {

        SQLiteDatabase db = getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(UserProfile.Users.C1, username);
        values.put(UserProfile.Users.C2, dob);
        values.put(UserProfile.Users.C3, password);
        values.put(UserProfile.Users.C4, gender);


// Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(UserProfile.Users.TABLE_NAME, null, values);

        return newRowId;

    }

    public boolean updateInfo(String username,String dob ,String password, String gender)
    {
        SQLiteDatabase db = getWritableDatabase();

// New value for one column
       // String title = "MyNewTitle";
        ContentValues values = new ContentValues();
        //values.put(UserProfile.Users.C1, username); // NOT USED USERNAME
        values.put(UserProfile.Users.C2, dob);
        values.put(UserProfile.Users.C3, password);
        values.put(UserProfile.Users.C4, gender);


// Which row to update, based on the title
        String selection = UserProfile.Users.C1 + " LIKE ?";
        String[] selectionArgs = { username };

        int count = db.update(
                UserProfile.Users.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        if(count >= 1)
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    public void deleteInfo(String username)
    {
        SQLiteDatabase db = getWritableDatabase();

        // Define 'where' part of query.
        String selection = UserProfile.Users.C1 + " LIKE ?";
// Specify arguments in placeholder order.
        String[] selectionArgs = { username };
// Issue SQL statement.
        int deletedRows = db.delete(UserProfile.Users.TABLE_NAME, selection, selectionArgs);
    }


    public List readAllInfo()
    {
        SQLiteDatabase db = getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                UserProfile.Users.C1,
                UserProfile.Users.C2,
                UserProfile.Users.C3,
                UserProfile.Users.C4,
        };

// Filter results WHERE "title" = 'My Title'
        String selection = UserProfile.Users.C1 + " = ?";
        String[] selectionArgs = { "My Title" };

// How you want the results sorted in the resulting Cursor
        String sortOrder =
                UserProfile.Users.C1 + " DESC";

        Cursor cursor = db.query(
                UserProfile.Users.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all) - select
                null,              // The columns for the WHERE clause - where
                null,          // The values for the WHERE clause - arguments
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List usernames = new ArrayList<>();
        while(cursor.moveToNext()) {
            String user = cursor.getString(
                    cursor.getColumnIndexOrThrow(UserProfile.Users.C1));
            usernames.add(user);
        }
        cursor.close();

        return usernames;
    }

    public List readAllInfo(String username)
    {
        SQLiteDatabase db = getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                UserProfile.Users.C1,
                UserProfile.Users.C2,
                UserProfile.Users.C3,
                UserProfile.Users.C4,
        };

// Filter results WHERE "title" = 'My Title'
        String selection = UserProfile.Users.C1 + " LIKE ?";
        String[] selectionArgs = { username };

// How you want the results sorted in the resulting Cursor
        String sortOrder =
                UserProfile.Users.C1 + " DESC";

        Cursor cursor = db.query(
                UserProfile.Users.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all) - select
                selection,              // The columns for the WHERE clause - where
                selectionArgs,          // The values for the WHERE clause - arguments
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List userInfo = new ArrayList<>();
        while(cursor.moveToNext()) {

            String user = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.C1));
            String dob = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.C2));
            String pass = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.C3));
            String gender = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.C4));

            userInfo.add(user);
            userInfo.add(dob);
            userInfo.add(pass);
            userInfo.add(gender);
        }
        cursor.close();

        return userInfo;
    }







}
