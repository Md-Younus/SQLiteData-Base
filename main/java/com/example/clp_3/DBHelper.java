package com.example.clp_3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super( context, "Userdate.bd", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL( "create Table Userdetails (name VARCAR, contact VARCAR, dob VARCAR)" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {

        DB.execSQL( "drop Table if exists Userdetails" );

    }

    public Boolean insetuserdata(String name, String contact, String dob){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put( "name", name );
        contentValues.put( "contact", contact );
        contentValues.put( "dob", dob );
        long result = DB.insert( "Userdetails",null,contentValues );
        if(result==-1){
            return  false;
        }
        else {
            return true;
        }
    }

    public Boolean updateuserdata(String name, String contact, String dob){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put( "contact", contact );
        contentValues.put( "dob", dob );
        Cursor cursor=DB.rawQuery( "Select * from Userdetails where name = ?", new String[]{name} );
        if(cursor.getCount()>0) {

            long result = DB.update( "Userdetails", contentValues, "name=?", new String[] {name} );
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else {
                return false;
            }
        }

    public Boolean deleteuserdata(String name){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor=DB.rawQuery( "Select * from Userdetails where name = ?", new String[]{name} );
        if(cursor.getCount()>0) {

            long result = DB.delete( "Userdetails",  "name=?", new String[]{name} );
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else {
                return false;
            }
        }
    public Cursor getdata(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor=DB.rawQuery( "Select * from Userdetails", null );
        return cursor;
    }
}

