package com.example.ahame_000.seg2105;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;

import com.example.ahame_000.seg2105.databasing.DatabaseHelper;
import com.example.ahame_000.seg2105.databasing.DatabaseManager;

import java.sql.DatabaseMetaData;

public class AccountLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_login);
    }
    // takes user to Create New Account page
    public void createAccBttnClick(View view){

        EditText emailTxt = findViewById(R.id.Email_EditText_AccountLogin);
        String emailString = emailTxt.getText().toString();

        setContentView(R.layout.activity_create_account);

        EditText emailTxtCreateAcc = findViewById(R.id.Email_EditText_CreateAccount);
        emailTxtCreateAcc.setText(emailString);

        Intent intent = new Intent(this,CreateAccount.class);
        startActivity(intent);

    }

    public void accLoginBttnClick (View view){




        // Getting the email inputted
        EditText emailTxt = findViewById(R.id.Email_EditText_AccountLogin);
        String emailString = emailTxt.getText().toString();

        EditText passwordTxt = findViewById(R.id.Password_EditText_CreateAccount);
        String passwordString = passwordTxt.getText().toString();

        DatabaseManager DM = new DatabaseManager(new DatabaseHelper(this.getApplicationContext()));

        if (DM.loginAccount(emailString, passwordString)){

            //TODO: change activity_main to allProfiles_layout
            setContentView(R.layout.activity_main);

        }
        else {
            emailTxt.setText("");
            passwordTxt.setText("");
            View incorrectPopUp = findViewById(R.id.incorrectCreds_TextView_AccountLogin);
            incorrectPopUp.setVisibility(View.VISIBLE);

        }

    }
}
