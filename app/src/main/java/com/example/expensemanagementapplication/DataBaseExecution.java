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
    private static final String User_Table = "user";
    private static final String Trip_Table="trip";
    private static final String Expense_Table="expense";

    //Columns
    private static final String Column_UserId = "user_id";
    private static final String Column_FirstName = "first_name";
    private static final String Column_LastName = "last_name";
    private static final String Column_Password = "password";
    private static final String Column_Email = "email";
    private static final String Column_BaseCurrency = "base_currency";
    private static final String Column_EmployeeId = "employee_id";

    private static final String Column_TripId="trip_id";
    private static final String Column_TripName="trip_name";
    private static final String Column_Destination ="destination";
    private static final String Column_TripStartDate ="trip_start_date";
    private static final String Column_TripEndDate ="trip_end_date";
    private static final String Column_RequireRiskAssess ="require_risk_assessment";
    private static final String Column_Desc ="description";
    private static final String Column_IsActive ="is_active";
    private static final String Column_TypeOfTrip="type_of_trip";
    private static final String Column_OtherType="other_type";
    private static final String Column_TotalCompensated="total_compensated";
    private static final String Column_IsInternationalTrip="is_international_trip";
    private static final String Column_TotalExpense="total_expense";

    private static final String Column_ExpenseId="expense_id";
    private static final String Column_TypeOfExpense = "type_of_expense";
    private static final String Column_AmountOfExpense = "amount_of_expense";
    private static final String Column_TimeOfExpense = "time_of_expense";
    private static final String Column_Comments = "comments";
    private static final String Column_Currency = "currency";

    public SQLiteDatabase ExpenseManagementDB ;

    public DataBaseExecution(Context context) {
        super(context, dbName, null, 3);
        ExpenseManagementDB= getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createUserTable = "CREATE TABLE " + User_Table + " ( " + Column_UserId + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Column_FirstName + " TEXT, " + Column_LastName + " TEXT, " + Column_Password + " TEXT, " + Column_Email+ " TEXT, " + Column_BaseCurrency + " INTEGER, " + Column_EmployeeId + " TEXT )";
                                 // CREATE TABLE USER_TABLE ( UserId int PRIMARY KEY AUTOINCREMENT, FirstName Text, LastName TEXT, Password TEXT, Email TEXT, BaseCurrency int, employeeId text )

        String createTripTable = "CREATE TABLE " + Trip_Table + " ( " + Column_TripId + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Column_UserId + " TEXT, " + Column_TripName + " TEXT, " + Column_Destination + " TEXT, " + Column_TripStartDate + " NUMERIC, " + Column_TripEndDate + " NUMERIC, " + Column_RequireRiskAssess + " INTEGER, " + Column_Desc + " TEXT, " + Column_IsActive + " INTEGER, " + Column_TypeOfTrip + " INTEGER, " + Column_OtherType + " TEXT, " + Column_TotalCompensated + " NUMERIC, " + Column_TotalExpense + " NUMERIC, " + Column_IsInternationalTrip + " INTEGER );";

        String createExpenseTable = "CREATE TABLE " + Expense_Table + " ( " + Column_ExpenseId + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Column_TripId + " INTEGER, " + Column_TypeOfExpense + " INTEGER, " + Column_TimeOfExpense + " NUMERIC, " + Column_AmountOfExpense + " NUMERIC, " + Column_Comments + " TEXT, " + Column_Currency + " INTEGER, " + Column_OtherType +" TEXT );";


        db.execSQL(createUserTable);
        db.execSQL(createTripTable);
        db.execSQL(createExpenseTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+User_Table);
        db.execSQL("DROP TABLE IF EXISTS "+Trip_Table);
        db.execSQL("DROP TABLE IF EXISTS "+Expense_Table);
        onCreate(db);
    }

    public Boolean checkExistingUser(String employeeId){
        Cursor results = ExpenseManagementDB.rawQuery("Select * from user where LOWER(employee_id) = ?",new String[]{employeeId});
        return  results.getCount()>0;
    }

    public Boolean checkExistingUserEmail(String emailId){
        Cursor results = ExpenseManagementDB.rawQuery("Select * from user where LOWER(email) = ?",new String[]{emailId});
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
        Cursor results = ExpenseManagementDB.rawQuery("Select password,user_id from User where LOWER(email) = ?",new String[]{userEmail});
        results.moveToFirst();
        return results.getString(0);
    }

    public Cursor getData(String columnNames,String tableName,String colWhere,String colWhereValue){
        Cursor results = ExpenseManagementDB.rawQuery("Select "+columnNames+" from "+tableName+" where "+colWhere+" = ?",new String[]{colWhereValue});
        return results;
    }

    public boolean addNewExpense(ExpenseModel Expense){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Column_ExpenseId,Expense.getExpenseId());
        cv.put(Column_TripId,Expense.getTripId());
        cv.put(Column_TypeOfExpense,Expense.getTypeOfExpense());
        cv.put(Column_TimeOfExpense,Expense.getExpenseDateTime());
        cv.put(Column_AmountOfExpense,Expense.getExpenseAmount());
        cv.put(Column_Comments,Expense.getComments());
        cv.put(Column_Currency,Expense.getCurrency());
        cv.put(Column_OtherType,Expense.getOtherType());

        long insert = db.insert(Expense_Table,null,cv);
        if(insert == -1)
            return false;
        else
            return true;
    }

}
