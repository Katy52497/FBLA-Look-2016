package com.example.jade.fbla_look;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
// There's a lot of unused import statements , but I  still dont want to delete the lines . Ununsed statement doesn't do any harms anyway.

public class RegisterActivity extends Activity {
    protected EditText mUsername;
    protected EditText mUserEmail;
    protected  EditText mUserPassword;
    protected Button mRegisterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //intialize
        mUsername = (EditText)findViewById(R.id.username_register);
        mUserEmail = (EditText)findViewById(R.id.useremail_register);
        mUserPassword = (EditText)findViewById(R.id.userpassword_register);
        mRegisterButton = (Button)findViewById(R.id.button_register);

        //Listening to event Register Button
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get the username, password and email and convert them to strings.
                String username = mUsername.getText().toString().trim();
                String password = mUserPassword.getText().toString().trim();
                String email = mUserEmail.getText().toString().trim();

                //Store user in parse
                ParseUser user = new ParseUser();
                user.setUsername(username);
                user.setPassword(password);
                user.setEmail(email);
                user.signUpInBackground(new SignUpCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            // Take user to user list page
                            Toast.makeText(RegisterActivity.this,"Welcome!",Toast.LENGTH_LONG).show();
                            //Take User to the homepage
                            Intent takeUserHome = new Intent(RegisterActivity.this, UserList.class);
                            startActivity(takeUserHome);

                        } else {
                            // Sign up didn't succeed. Look at the ParseException
                            // to figure out what went wrong
                            Toast.makeText(RegisterActivity.this,"Honey, you're not doing it right.",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

    }

}
