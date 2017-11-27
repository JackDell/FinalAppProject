package com.example.ahame_000.seg2105;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.DatabaseMetaData;

public class AccountLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_login);
    }
    // takes user to Create New Account page
    public void createAccBttnClick(View view){

        EditText emailTxt = (EditText)findViewById(R.id.Email_EditText_AccountLogin);
        String emailString = emailTxt.getText().toString();

        setContentView(R.layout.activity_create_account);

        EditText emailTxtCreateAcc = (EditText)findViewById(R.id.Email_EditText_CreateAccount);
        emailTxtCreateAcc.setText(emailString);

    }

    public void accLoginBttnClick (View view){

        //extract email from ID
        EditText emailTxt = (EditText)findViewById(R.id.Email_EditText_AccountLogin);
        //convert email txt view to String
        String emailString = emailTxt.getText().toString();

        EditText passwordTxt = (EditText)findViewById(R.id.Password_EditText_CreateAccount);
        String passwordString = passwordTxt.getText().toString();

       DBmangment dManage= DBmangment.getInstance();

        //If password & email verified, go to profiles activity
        if (dManage.verifyAccount(emailString, passwordString)==true){

            //TODO: change activity_main to allProfiles_layout
            setContentView(R.layout.activity_main);

        }
        //If they don't match, swipe all the fields and have "incorrect pssword..." pop up
        else {
            emailTxt.setText("");
            passwordTxt.setText("");
            View incorrectPopUp = findViewById(R.id.incorrectCreds_TextView_AccountLogin);
            incorrectPopUp.setVisibility(View.VISIBLE);

        }

    }
}
