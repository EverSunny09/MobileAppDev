package com.example.expensemanagementapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class DataBaseExecution extends SQLiteOpenHelper {

    private static String dbName = "ExpenseManagement.db";

    private static final String User_Table = "User";
    private static final String Column_UserId = "UserId";
    private static final String Column_FirstName = "FirstName";
    private static final String Column_LastName = "LastName";
    private static final String Column_Password = "Password";
    private static final String Column_Email = "Email";
    private static final String Column_BaseCurrency = "BaseCurrency";
    private static final String Column_EmployeeId = "EmployeeId";

    public SQLiteDatabase ExpenseManagementDB ;

    public DataBaseExecution(Context context) {
        super(context, dbName, null, 1);
        ExpenseManagementDB= getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createUserTable = "CREATE TABLE " + User_Table + " ( " + Column_UserId + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Column_FirstName + " TEXT, " + Column_LastName + " TEXT, " + Column_Password + " TEXT, " + Column_Email+ " TEXT, " + Column_BaseCurrency + " INTEGER, " + Column_EmployeeId + " TEXT )";
                                 // CREATE TABLE USER_TABLE ( UserId int PRIMARY KEY AUTOINCREMENT, FirstName Text, LastName TEXT, Password TEXT, Email TEXT, BaseCurrency int, employeeId text )

        db.execSQL(createUserTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Boolean checkExistingUser(String employeeId){
        Cursor results = ExpenseManagementDB.rawQuery("Select * from User where LOWER(EmployeeId) = ?",new String[]{employeeId});
        return  results.getCount()>0;
    }

}
