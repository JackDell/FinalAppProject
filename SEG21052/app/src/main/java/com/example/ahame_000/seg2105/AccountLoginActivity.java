package com.example.ahame_000.seg2105;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.ahame_000.seg2105.databasing.DatabaseHelper;
import com.example.ahame_000.seg2105.databasing.DatabaseManager;

public class AccountLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_login);
    }

    /**
     * Takes user to create an account
     * @param view
     */
    public void createAccBttnClick(View view){

        EditText emailTxt = (EditText) findViewById(R.id.Email_EditText_AccountLogin);
        String emailString = emailTxt.getText().toString();


        Intent intent = new Intent(this,CreateAccountActivity.class);
        intent.putExtra("email",emailString);
        startActivity(intent);

    }

    /**
     * Logs user into account associated with given email and password
     * @param view
     */
    public void accLoginBttnClick (View view){

        // Getting the email inputted
        EditText emailTxt = findViewById(R.id.Email_EditText_AccountLogin);
        String emailString = emailTxt.getText().toString();

        EditText passwordTxt = findViewById(R.id.Password_EditText_CreateAccount);
        String passwordString = passwordTxt.getText().toString();

        DatabaseManager DM = new DatabaseManager(new DatabaseHelper(this.getApplicationContext()));

        if (DM.loginAccount(emailString, passwordString)){

            if(Session.getLoggedInAccount().getProfiles().isEmpty()) {
                Intent intent = new Intent(this, AddMemberActivity.class);
                startActivity(intent);
            }
            else {
                Intent intent = new Intent(this, ProfileListActivity.class);
                startActivity(intent);
            }
        }
        else {
            emailTxt.setText("");
            passwordTxt.setText("");
            View incorrectPopUp = findViewById(R.id.incorrectCreds_TextView_AccountLogin);
            incorrectPopUp.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onBackPressed() {
        finish();
        System.exit(0);
    }
}
