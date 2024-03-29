package com.example.ahame_000.seg2105.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ahame_000.seg2105.DataStructures.Account;
import com.example.ahame_000.seg2105.R;
import com.example.ahame_000.seg2105.Helpers.Session;
import com.example.ahame_000.seg2105.Database.*;

public class CreateAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        // If there was a passed email, setting it in the email input bar
        String initialEmail = getIntent().getStringExtra("email");
        EditText emailTxt = findViewById(R.id.Email_EditText_CreateAccount);
        emailTxt.setText(initialEmail);

    }

    /**
     * Creates a new account with given email and password, logs in user and takes them to add first adult member
     * @param view
     */
    public void doneCreateNewAccBttnClick(View view) {
        // Getting the activity's features so we can manipulate them
        EditText emailTxt = findViewById(R.id.Email_EditText_CreateAccount);
        String emailString = emailTxt.getText().toString();

        EditText passwordTxt = findViewById(R.id.Password_EditText_CreateAccount);
        String passwordString = passwordTxt.getText().toString();

        EditText confirmPasswordTxt = findViewById(R.id.ConfirmPassword_EditText_CreateAccount);
        String confirmPasswordString = confirmPasswordTxt.getText().toString();

        // Confirming that an email was entered, and that the passwords match
        if (passwordConfirmation(passwordString,confirmPasswordString)&&!emailString.isEmpty()) {
            Account account = new Account(emailString, passwordString);
            DatabaseManager DM = new DatabaseManager(new DatabaseHelper(getApplicationContext()));
            // saveAccount is a boolean method that returns false if an account with the same email is already databased
            // this prevents accounts using the same email from being created
            if(DM.saveAccount(account)) {
                Session.setLoggedInAccount(account);
                Intent intent = new Intent(this, AddMemberActivity.class);
                startActivity(intent);
            } else {
                TextView incorrectPopUp = findViewById(R.id.errorMassage_TextView_CreateAccount);
                incorrectPopUp.setText("Email already has an account");
                incorrectPopUp.setVisibility(View.VISIBLE);
            }
        }
    }



    /**
     * Cofirms the user's two passwords are the same
     * @param pass1
     * @param pass2
     * @return true if they are the same, false if they are not
     */
    private boolean passwordConfirmation( String pass1, String pass2) {
        // If the passwords are not the same, display a popup message stating so
        TextView incorrectPopUp = findViewById(R.id.errorMassage_TextView_CreateAccount);

        if (pass1.equals(pass2) && !pass1.isEmpty()) {
            incorrectPopUp.setVisibility(View.INVISIBLE);
            return true;


        }
        //Passwords are not the same
        incorrectPopUp.setText("Passwords are not the same");
        incorrectPopUp.setVisibility(View.VISIBLE);
        return false;

    }

}
