package com.example.ahame_000.seg2105;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.ahame_000.seg2105.databasing.*;

public class CreateAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        String initialEmail = getIntent().getStringExtra("email");
        EditText emailTxt = findViewById(R.id.Email_EditText_CreateAccount);
        emailTxt.setText(initialEmail);

    }


    //creates a new DBManager ( which creates a new account )
    public void doneCreateNewAccBttnClick(View view) {


        EditText emailTxt = findViewById(R.id.Email_EditText_CreateAccount);
        String emailString = emailTxt.getText().toString();

        EditText passwordTxt = findViewById(R.id.Password_EditText_CreateAccount);
        String passwordString = passwordTxt.getText().toString();

        EditText confirmPasswordTxt = findViewById(R.id.ConfirmPassword_EditText_CreateAccount);
        String confirmPasswordString = confirmPasswordTxt.getText().toString();


        if (passwordConfirmation(passwordString,confirmPasswordString)&&!emailString.isEmpty()) {
            DatabaseManager DM = new DatabaseManager(new DatabaseHelper(this.getApplicationContext()));
            DM.saveAccount(new Account(emailString, passwordString));


            Intent intent = new Intent(this,AccountLoginActivity.class);
            startActivity(intent);
        }
    }

    public void addMemberBttnClick (View view){

        if(saveContent()) {
            Intent intent = new Intent(this, AddMemberActivity.class);
            startActivity(intent);
        }
    }
    private boolean saveContent(){
        EditText emailTxt = findViewById(R.id.Email_EditText_CreateAccount);
        String emailString = emailTxt.getText().toString();

        EditText passwordTxt = findViewById(R.id.Password_EditText_CreateAccount);
        String passwordString = passwordTxt.getText().toString();

        EditText confirmPasswordTxt = findViewById(R.id.ConfirmPassword_EditText_CreateAccount);
        String confirmPasswordString = confirmPasswordTxt.getText().toString();

        boolean confirmation = false;
        if (passwordConfirmation(passwordString,confirmPasswordString)&&!emailString.isEmpty()) {
            DatabaseManager DM = new DatabaseManager(new DatabaseHelper(this.getApplicationContext()));
            DM.saveAccount(new Account(emailString, passwordString));
            confirmation = true;
        }
        return confirmation;
    }


    //confirm the users 2 passwords are the same/ not empty, returns true if they are
    private boolean passwordConfirmation( String pass1, String pass2) {


        View incorrectPopUp = findViewById(R.id.passNotSame_TextView_CreateAccount);

        if (pass1.equals(pass2) && !pass1.isEmpty()) {
            incorrectPopUp.setVisibility(View.INVISIBLE);
            return true;


        }

        incorrectPopUp.setVisibility(View.VISIBLE);
        return false;

    }

}