package com.example.ahame_000.seg2105;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.ahame_000.seg2105.databasing.DatabaseHelper;
import com.example.ahame_000.seg2105.databasing.DatabaseManager;

public class CreateAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
    }


    //creates a new DBmanager ( which creates a new account )
    public void doneCreateNewAccBttnClick(View view) {

        // ********** NEED TO ADD NAME EditText just putting this here for implementation -jack
        String name = "Name Goes Here";

        EditText emailTxt = findViewById(R.id.Email_EditText_CreateAccount);
        String emailString = emailTxt.getText().toString();

        EditText passwordTxt = findViewById(R.id.Password_EditText_CreateAccount);
        String passwordString = passwordTxt.getText().toString();

        EditText confirmPasswordTxt = findViewById(R.id.ConfirmPassword_EditText_CreateAccount);
        String confirmPasswordString = confirmPasswordTxt.getText().toString();


        if (passwordTxt.equals(confirmPasswordTxt)) {
            DatabaseManager DM = new DatabaseManager(new DatabaseHelper(this.getApplicationContext()));
            DM.saveAccount(new Account(name, emailString, passwordString));
            Intent intent = new Intent(this,AccountLogin.class);
            startActivity(intent);
        }
    }

    public void addMemberBttnClick (View view){
        Intent intent = new Intent(this,AccountLogin.class);
        startActivity(intent);
    }


    //confirm the users 2 passwords are the same/ not empty, returns true if they are
    public boolean passwordConfirm( String pass1, String pass2){

        boolean confirmed = false;

        View incorrectPopUp = findViewById(R.id.passNotSame_TextView_CreateAccount);

        if(pass1.equals(pass2)){
            incorrectPopUp.setVisibility(View.INVISIBLE);
            confirmed = true;


        }
        else {
            incorrectPopUp.setVisibility(View.VISIBLE);

        }
        if (pass1.isEmpty() || pass2.isEmpty()){
            confirmed = false;
        }

        return confirmed;

    }
}
