package com.example.ahame_000.seg2105;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class CreateAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
    }

    // takes user to Create New Account page
    public void createAccBttnClick(View view){

        EditText emailTxt = (EditText)findViewById(R.id.Email_EditText_AccountLogin);
        String emailString = emailTxt.getText().toString();

        setContentView(R.layout.activity_create_account);

        EditText emailTxtCreateAcc = (EditText)findViewById(R.id.Email_EditText_CreateAccount);
        emailTxtCreateAcc.setText(emailString);

    }

    //creates a new DBmanager ( which creates a new account ) 
    public void doneCreateNewAccBttnClick(View view) {

        EditText emailTxt = (EditText) findViewById(R.id.Email_EditText_CreateAccount);
        String emailString = emailTxt.getText().toString();

        EditText passwordTxt = (EditText) findViewById(R.id.Password_EditText_CreateAccount);
        String passwordString = emailTxt.getText().toString();

        EditText confirmPasswordTxt = (EditText) findViewById(R.id.ConfirmPassword_EditText_CreateAccount);
        String confirmPasswordString = emailTxt.getText().toString();


        if (passwordConfirm(passwordString, confirmPasswordString) == true) {
            DBmangment newAcc = new DBmangment(emailString, passwordString);
            setContentView(R.layout.activity_account_login);

        }
    }


    //confirm the users 2 passwords are the same, returns true if they are
    public boolean passwordConfirm( String pass1, String pass2){

        boolean confirmed = false;

        View incorrectPopUp = findViewById(R.id.passNotSame_TextView_CreateAccount);

        if(pass1 != pass2){

            incorrectPopUp.setVisibility(View.VISIBLE);
        }
        else {
            incorrectPopUp.setVisibility(View.INVISIBLE);
            confirmed = true;
        }
        return confirmed;

    }
}
