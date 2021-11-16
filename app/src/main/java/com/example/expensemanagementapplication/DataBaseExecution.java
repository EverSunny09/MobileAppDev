package com.example.expensemanagementapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.Locale;


public class DataBaseExecution extends SQLiteOpenHelper {
    //DBName
    private static String dbName = "ExpenseManagement.db";

    //TableNames
    private static final String User_Table = "User";
    private static final String Trip_Table="Trip";

    //Columns
    private static final String Column_UserId = "UserId";
    private static final String Column_FirstName = "FirstName";
    private static final String Column_LastName = "LastName";
    private static final String Column_Password = "Password";
    private static final String Column_Email = "Email";
    private static final String Column_BaseCurrency = "BaseCurrency";
    private static final String Column_EmployeeId = "EmployeeId";

    private static final String Column_TripId="TripId";
    private static final String Column_TripName="TripName";
    private static final String Column_Destination ="Destination";
    private static final String Column_TripStartDate ="TripStartDate";
    private static final String Column_TripEndDate ="TripEndDate";
    private static final String Column_RequireRiskAssess ="RequireRiskAssessment";
    private static final String Column_Desc ="Description";
    private static final String Column_IsActive ="IsActive";
    private static final String Column_TypeOfTrip="TypeOfTrip";
    private static final String Column_OtherType="OtherType";
    private static final String Column_TotalCompensated="TotalCompensated";
    private static final String Column_IsInternationalTrip="IsInternationalTrip";
    private static final String Column_TotalExpense="TotalExpense";

    public SQLiteDatabase ExpenseManagementDB ;

    public DataBaseExecution(Context context) {
        super(context, dbName, null, 2);
        ExpenseManagementDB= getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createUserTable = "CREATE TABLE " + User_Table + " ( " + Column_UserId + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Column_FirstName + " TEXT, " + Column_LastName + " TEXT, " + Column_Password + " TEXT, " + Column_Email+ " TEXT, " + Column_BaseCurrency + " INTEGER, " + Column_EmployeeId + " TEXT )";
                                 // CREATE TABLE USER_TABLE ( UserId int PRIMARY KEY AUTOINCREMENT, FirstName Text, LastName TEXT, Password TEXT, Email TEXT, BaseCurrency int, employeeId text )

        String createTripTable = "CREATE TABLE " + Trip_Table + " ( " + Column_TripId + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Column_UserId + " TEXT, " + Column_TripName + " TEXT, " + Column_Destination + " TEXT, " + Column_TripStartDate + " NUMERIC, " + Column_TripEndDate + " NUMERIC, " + Column_RequireRiskAssess + " INTEGER, " + Column_Desc + " TEXT, " + Column_IsActive + " INTEGER, " + Column_TypeOfTrip + " INTEGER, " + Column_OtherType + " TEXT, " + Column_TotalCompensated + " NUMERIC, " + Column_TotalExpense + " NUMERIC, " + Column_IsInternationalTrip + " INTEGER );";


        db.execSQL(createUserTable);
        db.execSQL(createTripTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+User_Table);
        db.execSQL("DROP TABLE IF EXISTS "+Trip_Table);
        onCreate(db);
    }

    public Boolean checkExistingUser(String employeeId){
        Cursor results = ExpenseManagementDB.rawQuery("Select * from User where LOWER(EmployeeId) = ?",new String[]{employeeId});
        return  results.getCount()>0;
    }

    public Boolean checkExistingUserEmail(String emailId){
        Cursor results = ExpenseManagementDB.rawQuery("Select * from User where LOWER(Email) = ?",new String[]{emailId});
        return  results.getCount()>0;
    }

    public boolean addNewUser(UserModel User){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Column_FirstName,User.getFirstName());
        cv.put(Column_LastName,User.getLastName());
        cv.put(Column_Password,User.getPassword());
        cv.put(Column_Email,User.getEmail());
        cv.put(Column_BaseCurrency,User.getBaseCurrency());
        cv.put(Column_EmployeeId,User.getEmployeeId());
        long insert = db.insert(User_Table,null,cv);
        if(insert == -1)
            return false;
        else
            return true;
    }

    public String getPwdFromDB (String userEmail){
        Cursor results = ExpenseManagementDB.rawQuery("Select Password,UserId from User where LOWER(Email) = ?",new String[]{userEmail});
        results.moveToFirst();
        return results.getString(0);
    }

    public Cursor getData(String columnNames,String tableName,String colWhere,String colWhereValue){
        Cursor results = ExpenseManagementDB.rawQuery("Select "+columnNames+" from "+tableName+" where "+colWhere+" = ?",new String[]{colWhereValue});
        return results;
    }

}
