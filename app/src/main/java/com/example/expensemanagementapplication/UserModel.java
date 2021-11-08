package com.example.expensemanagementapplication;

public class UserModel {

    private int UserId;
    private String FirstName;
    private String LastName;
    private String Password;
    private String Email;
    private int BaseCurrency;
    private String EmployeeId;


    //Constructor

    public UserModel(int userId, String firstName, String lastName, String password, String email, int baseCurrency, String employeeId) {
        UserId = userId;
        FirstName = firstName;
        LastName = lastName;
        Password = password;
        Email = email;
        BaseCurrency = baseCurrency;
        EmployeeId = employeeId;
    }

    //Constructor without parameters

    public UserModel() {
    }

    //toString to print contents of a class object


    @Override
    public String toString() {
        return "UserModel{" +
                "UserId=" + UserId +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", Password='" + Password + '\'' +
                ", Email='" + Email + '\'' +
                ", BaseCurrency=" + BaseCurrency +
                ", EmployeeId='" + EmployeeId + '\'' +
                '}';
    }

    //getters and setters
    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getBaseCurrency() {
        return BaseCurrency;
    }

    public void setBaseCurrency(int baseCurrency) {
        BaseCurrency = baseCurrency;
    }

    public String getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(String employeeId) {
        EmployeeId = employeeId;
    }
}
