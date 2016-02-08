package com.example.jade.fbla_look;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class MainActivity extends Activity {
    protected EditText mUsername;
    protected EditText mPassword;
    protected Button mLoginBtn;
    protected Button mCreateAccountBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //intialize
        mUsername = (EditText)findViewById(R.id.usernameLoginTextbox);
        mPassword = (EditText)findViewById(R.id.passwordLoginTextbox);
        mLoginBtn = (Button)findViewById(R.id.loginBtn);
        mCreateAccountBtn =(Button)findViewById(R.id.createAccountbtnLogin);

        //Listen to create account button click
        mCreateAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //take user to register
                Intent takeUserToRegister = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(takeUserToRegister);
            }

        });

        //listen to when the mLogin button is clicked
        mLoginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //get the user inputs , convert to string
                String username = mUsername.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                //Login the user using parse sdk
                ParseUser.logInInBackground(username, password, new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user != null) {
                            //Sucess. User Logged in
                            Toast.makeText(MainActivity.this,"Welcome Back!!!",Toast.LENGTH_LONG).show();

                            //Take User to the homepage
                            Intent takeUserHome = new Intent(MainActivity.this, UserList.class);
                            startActivity(takeUserHome);

                        } else {
                            Toast.makeText(MainActivity.this,"Can't open the door, do you have the right key?",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
