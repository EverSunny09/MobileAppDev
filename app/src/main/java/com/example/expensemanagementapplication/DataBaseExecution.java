package com.example.expensemanagementapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class DataBaseExecution extends SQLiteOpenHelper {

    private static final String dbName = "expenseManagementDB";

    private SQLiteDatabase ExpenseManagementDB ;

    public DataBaseExecution(@Nullable Context context) {
        super(context, dbName, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

       /* String createUserTable = "";
        String createUserTable = "";
        String createUserTable = "";
        String createUserTable = "";
        String createUserTable = "";

        db.execSQL(createUserTable);*/

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
