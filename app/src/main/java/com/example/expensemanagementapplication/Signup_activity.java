package com.example.expensemanagementapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signup_activity extends AppCompatActivity {

    EditText loginText, passwordText;
    Button btnLlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        loginText= (EditText) findViewById(R.id.login);
        passwordText = (EditText) findViewById(R.id.password);

        btnLlogin  =(Button) findViewById(R.id.buttonLogin);
        btnLlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=loginText.getText().toString();
                String password= passwordText.getText().toString();

                if(username.equals(username) && (password.equals("123456")))
                {
                    Toast.makeText(Signup_activity.this,"Welcome",Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent (getApplicationContext(),NewActivity.class);
startActivity(intent);



                }else

                    Toast.makeText( Signup_activity.this,"Invalid creditintials",Toast.LENGTH_SHORT).show();
            }
        });

    }
}