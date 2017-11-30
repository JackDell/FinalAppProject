<<<<<<< HEAD
package ca.uottawa.jackdell.choreapplication;
=======
package com.example.ahame_000.seg2105;
>>>>>>> 2052eebb79db5df0fdba4c0a8751795cb7bf0751

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

<<<<<<< HEAD
=======
import com.example.ahame_000.seg2105.databasing.DatabaseHelper;
import com.example.ahame_000.seg2105.databasing.DatabaseManager;

>>>>>>> 2052eebb79db5df0fdba4c0a8751795cb7bf0751
public class ProfileLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_login);
    }

    public void onProfileLoginClick(View view) {
<<<<<<< HEAD
        EditText emailInput = (EditText) findViewById(R.id.etEmailInput);
        EditText passInput = (EditText) findViewById(R.id.etPassInput);

        String email = emailInput.getText().toString();
        String password = passInput.getText().toString();

        DatabaseManager DM = new DatabaseManager(this.getBaseContext());

        for(Profile profile : DM.getDatabasedProfiles(Session.getAccount())) {
            if(profile.getEmail().equals(email) && profile.getPassword().equals(password)) {
                Session.setProfile(profile);
                Intent intent = new Intent(this, ProfileActivity.class);
                startActivity(intent);
            }
        }
=======
        // Need to change XML to fix
        //EditText emailInput = (EditText) findViewById(R.id.etEmailInput);
        //EditText passInput = (EditText) findViewById(R.id.etPassInput);

        //String email = emailInput.getText().toString();
        //String password = passInput.getText().toString();

        DatabaseManager DM = new DatabaseManager(new DatabaseHelper(this.getApplicationContext()));

        // TODO: Profile login feature
>>>>>>> 2052eebb79db5df0fdba4c0a8751795cb7bf0751

        // For the code below to execute, the profile login must be un-successful

    }
}
