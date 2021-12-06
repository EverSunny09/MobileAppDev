package com.example.expensemanagementapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Locale;


public class DataBaseExecution extends SQLiteOpenHelper {
    //DBName
    private static String dbName = "ExpenseManagement.db";

    //TableNames
    private static final String User_Table = "user";
    private static final String Trip_Table="trip";
    private static final String Expense_Table="expense";
    private static final String Exchange_Detail_Table = "exchange_detail";

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
    private static final String Column_TotalCompensated="total_compensation";
    private static final String Column_IsInternationalTrip="is_international_trip";
    private static final String Column_TotalExpense="total_expense";

    private static final String Column_ExpenseId="expense_id";
    private static final String Column_TypeOfExpense = "type_of_expense";
    private static final String Column_AmountOfExpense = "amount_of_expense";
    private static final String Column_TimeOfExpense = "time_of_expense";
    private static final String Column_Comments = "comments";
    private static final String Column_Currency = "currency";

    private static final String Column_ExchangeId="exchange_id";
    private static final String Column_Type="type"; //inbound or outbound
    private static final String Column_Data="data";


    public SQLiteDatabase ExpenseManagementDB ;

    public DataBaseExecution(Context context) {
        super(context, dbName, null, 8);
        ExpenseManagementDB= getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createUserTable = "CREATE TABLE " + User_Table + " ( " + Column_UserId + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Column_FirstName + " TEXT, " + Column_LastName + " TEXT, " + Column_Password + " TEXT, " + Column_Email+ " TEXT, " + Column_BaseCurrency + " INTEGER, " + Column_EmployeeId + " TEXT )";
                                 // CREATE TABLE USER_TABLE ( UserId int PRIMARY KEY AUTOINCREMENT, FirstName Text, LastName TEXT, Password TEXT, Email TEXT, BaseCurrency int, employeeId text )

        String createTripTable = "CREATE TABLE " + Trip_Table + " ( " + Column_TripId + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Column_UserId + " TEXT, " + Column_TripName + " TEXT, " + Column_Destination + " TEXT, " + Column_TripStartDate + " NUMERIC, " + Column_TripEndDate + " NUMERIC, " + Column_RequireRiskAssess + " INTEGER, " + Column_Desc + " TEXT, " + Column_IsActive + " INTEGER, " + Column_TypeOfTrip + " TEXT, " + Column_OtherType + " TEXT, " + Column_TotalCompensated + " TEXT, " + Column_TotalExpense + " TEXT, " + Column_IsInternationalTrip + " INTEGER );";

        String createExpenseTable = "CREATE TABLE " + Expense_Table + " ( " + Column_ExpenseId + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Column_TripId + " INTEGER, " + Column_TypeOfExpense + " INTEGER, " + Column_TimeOfExpense + " NUMERIC, " + Column_AmountOfExpense + " NUMERIC, " + Column_Comments + " TEXT, " + Column_Currency + " INTEGER, " + Column_OtherType +" TEXT );";

        String createExchangeDetailTable = "CREATE TABLE "+Exchange_Detail_Table + " ( " +Column_ExchangeId + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Column_UserId + " INTEGER, "+ Column_Type + " TEXT, "+ Column_Data + "TEXT);";


        db.execSQL(createUserTable);
        db.execSQL(createTripTable);
        db.execSQL(createExpenseTable);
        db.execSQL(createExchangeDetailTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+User_Table);
        db.execSQL("DROP TABLE IF EXISTS "+Trip_Table);
        db.execSQL("DROP TABLE IF EXISTS "+Expense_Table);
        db.execSQL("DROP TABLE IF EXISTS "+Exchange_Detail_Table);
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

    public long addNewUser(UserModel User){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Column_FirstName,User.getFirstName());
        cv.put(Column_LastName,User.getLastName());
        cv.put(Column_Password,User.getPassword());
        cv.put(Column_Email,User.getEmail());
        cv.put(Column_BaseCurrency,User.getBaseCurrency());
        cv.put(Column_EmployeeId,User.getEmployeeId());
        return db.insert(User_Table,null,cv);
        /*if(insert == -1)
            return false;
        else
            return true;*/
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

    public boolean addNewTrip(TripModel Trip){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Column_UserId,Trip.getUserId());
        cv.put(Column_TripName,Trip.getTripName());
        cv.put(Column_Destination,Trip.getDestination());
        cv.put(Column_TripStartDate,Trip.getTripStartDate());
        cv.put(Column_TripEndDate,Trip.getTripEndDate());
        cv.put(Column_RequireRiskAssess,Trip.getRequireRiskAssessment());
        cv.put(Column_OtherType,Trip.getOtherType());
        cv.put(Column_Desc,Trip.getDescription());
        cv.put(Column_IsActive,Trip.getIsActive());
        cv.put(Column_TypeOfTrip,Trip.getTypeOfTrip());
        cv.put(Column_TotalCompensated,Trip.getTotalCompensated());
        cv.put(Column_TotalExpense,Trip.getTotalExpense());
        cv.put(Column_IsInternationalTrip,Trip.getIsInternationalTrip());


        long insert = db.insert(Trip_Table,null,cv);
        if(insert == -1)
            return false;
        else
            return true;
    }

    public boolean updateTrip(TripModel Trip){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Column_UserId,Trip.getUserId());
        cv.put(Column_TripName,Trip.getTripName());
        cv.put(Column_Destination,Trip.getDestination());
        cv.put(Column_TripStartDate,Trip.getTripStartDate());
        cv.put(Column_TripEndDate,Trip.getTripEndDate());
        cv.put(Column_RequireRiskAssess,Trip.getRequireRiskAssessment());
        cv.put(Column_OtherType,Trip.getOtherType());
        cv.put(Column_Desc,Trip.getDescription());
        cv.put(Column_IsActive,Trip.getIsActive());
        cv.put(Column_TypeOfTrip,Trip.getTypeOfTrip());
        cv.put(Column_TotalCompensated,Trip.getTotalCompensated());
        cv.put(Column_TotalExpense,Trip.getTotalExpense());
        cv.put(Column_IsInternationalTrip,Trip.getIsInternationalTrip());

        int rows = db.update(Trip_Table, cv, "trip_id = ?", new String[]{ String.valueOf(Trip.getTripId())});
        return rows > 0;
    }


    public Cursor getAllTripsDetails(ArrayList<Integer> tripIds){
        String query = "SELECT trip_id, trip_name, destination, trip_start_date, trip_end_date, total_expense, total_compensation, is_international_trip, require_risk_assessment FROM trip"
                + " WHERE trip_id IN (" + makePlaceholders(tripIds.size()) + ")";
        Cursor cursor = ExpenseManagementDB.rawQuery(query, getStringFromInt(tripIds));
        return cursor;
    }

    public Cursor getAllExpenseDetails(ArrayList<Integer> tripIds){
        String query = "SELECT trip_id, expense_id, amount_of_expense, type_of_expense FROM expense"
                + " WHERE trip_id IN (" + makePlaceholders(tripIds.size()) + ")";
        Cursor cursor = ExpenseManagementDB.rawQuery(query, getStringFromInt(tripIds));
        return cursor;
    }

    public boolean deleteTrip(String tripId){
        deleteExpense(tripId);
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Trip_Table, Column_TripId + "=" + tripId, null) > 0;
    }
    public boolean deleteExpense(String tripId){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Expense_Table, Column_TripId + "=" + tripId, null) > 0;
    }

    String[] getStringFromInt(ArrayList<Integer> Ids){
        String[] strArray = new String[Ids.size()];
        for (int i = 0; i < Ids.size(); i++) {
            strArray[i] = String.valueOf(Ids.get(i));
        }
        return strArray;
    }


    String makePlaceholders(int len) {

        if (len < 1) {
            // It will lead to an invalid query anyway ..
            throw new RuntimeException("No placeholders");
        } else {
            StringBuilder sb = new StringBuilder(len * 2 - 1);
            sb.append("?");
            for (int i = 1; i < len; i++) {
                sb.append(",?");
            }
            return sb.toString();
        }
    }



}
