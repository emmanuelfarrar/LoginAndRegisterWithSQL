package com.efarrar.loginandregisterwithsql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="register.db";
    public static final String TABLE_NAME= "user_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "username";
    public static final String COL_3 = "password";
    public static final String TAG = "DB_OPERATION";

    //constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        Log.e(TAG, "DATABASE CREATED/OPENED");

    }

    /**onCreate() execute query to create table in the db with COLs IT as PK, username, password
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user_table (ID INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    /**addUser() <-- Adds the user
     * calls the getWritableDatabase() so we can put data in the db, builds ContentValues
     * then use put() to insert data into the contentValues
     * inserts contentValues into db and then closes db
     * @param user
     * @param password
     */
    public void addUser(String user, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", user);
        contentValues.put("password", password);

        //insert into table and close the table
        db.insert("user_table", null, contentValues);
        db.close();

        // if we made this return a boolean value add return true; at the end.
    }

    /**checkUser() <-- Checks if the user is in the table
     *
     *
     * Query the table to see if the values are already in the table
     * if the values are in the table the count will increase, of count is greater than 0 the user exists. So return TRUE
     * if 0 then the user does not exist.
     * @param username
     * @param password
     * @return
     */
    public boolean checkUser(String username, String password) {
        String[] columns = {COL_1};
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL_2 + "=?" + " and " + COL_3 + "=?";
        String[] selectionArgs = { username, password };
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null,null,null);
        int count = cursor.getCount();
        db.close();

        if (count >0){
            return true;
        }else {
            return false;
        }

    }


}
