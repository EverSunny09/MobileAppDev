package com.example.expensemanagementapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    private EditText firstName;
    private EditText lastName;
    private EditText employeeId;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        firstName= (EditText)findViewById(R.id.firstName);
        lastName= (EditText)findViewById(R.id.lastName);
        employeeId= (EditText) findViewById(R.id.employeeId);

    }

    public void nextButtonClick(View view){

        String fName = firstName.getText().toString();
        String lName = lastName.getText().toString();
        String empId = employeeId.getText().toString();

        if(!fName.isEmpty() && !lName.isEmpty() && !empId.isEmpty()){
            Intent signUp1 = new Intent(this,SignUp1.class);
            signUp1.putExtra(SignUp1.firstName,fName);
            signUp1.putExtra(SignUp1.lastName,lName);
            signUp1.putExtra(SignUp1.employeeId,empId);
            startActivity(signUp1);
        }

        else{
            Toast alertText = Toast.makeText(getApplicationContext(), "Enter details", Toast.LENGTH_SHORT);
            alertText.show();
        }
    }
}