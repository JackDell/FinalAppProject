package com.example.ahame_000.seg2105.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ahame_000.seg2105.R;
import com.example.ahame_000.seg2105.Helpers.Session;

public class ProfileLoginActivity extends AppCompatActivity {
private String profileName = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_login);

        // Getting the name of the profile that is trying to login, the name is always passed
        // because this activity is launched from clicking on a profile in the profile list
        profileName = getIntent().getStringExtra("profileName");
        TextView etName = findViewById(R.id.profileName_TextView_ProfileLogin);
        etName.setText(profileName);
    }

    /**
     * Logs in profile with given name and password and launches general chore list
     * @param view
     */
    public void onProfileLoginClick(View view) {
        // Getting the password box
        EditText etPassword = findViewById(R.id.profilePassword_editText_profileLogin);
        // Getting the password inputted
        String password = etPassword.getText().toString();
        // Attempting to login the profile with the profile name and password
        if (Session.loginProfile(profileName,password)){
            // On successfully login, start a new navigation activity
            Intent intent = new Intent(getApplicationContext(), NavigationActivity.class);
            startActivity(intent);
        } else {
            // If the login was unsuccessfully, let the user know and reset the password bar
            etPassword.setText("");
            View incorrectPopUp = findViewById(R.id.incorrectCreds_TextView_ProfileLogin);
            incorrectPopUp.setVisibility(View.VISIBLE);
        }
    }
}
