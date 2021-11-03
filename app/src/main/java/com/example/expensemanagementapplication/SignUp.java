package com.example.expensemanagementapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class SignUp extends AppCompatActivity {

    private EditText firstName,lastName,employeeId;
    private DataBaseExecution db;
    String fName,lName,empId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        firstName= (EditText)findViewById(R.id.firstName);
        lastName= (EditText)findViewById(R.id.lastName);
        employeeId= (EditText) findViewById(R.id.employeeId);
        db=new DataBaseExecution(this);
    }

    public void nextButtonClick(View view){

        fName = firstName.getText().toString().trim();
        lName = lastName.getText().toString().trim();
        empId = employeeId.getText().toString().trim();

        if(fName.length()>0 && lName.length()>0 && empId.length()>0){
            boolean userExists = checkExistingUser(empId);
            if(userExists){
               raiseToast("Entered User already Exists");
            }
            else{
                moveToSignUp1();
            }
        }
        else{
            raiseToast("Enter details");
        }
    }

    private void moveToSignUp1(){
        Intent signUp1 = new Intent(this,SignUp1.class);
        signUp1.putExtra(SignUp1.firstName,fName);
        signUp1.putExtra(SignUp1.lastName,lName);
        signUp1.putExtra(SignUp1.employeeId,empId);
        startActivity(signUp1);
    }

    private boolean checkExistingUser(String employeeId){
        return db.checkExistingUser(employeeId.toLowerCase());
    }

    private void raiseToast(String toastMsg){
        Toast alertText = Toast.makeText(getApplicationContext(), toastMsg, Toast.LENGTH_SHORT);
        alertText.show();
    }
}