package com.example.ahame_000.seg2105;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.ahame_000.seg2105.databasing.DatabaseHelper;
import com.example.ahame_000.seg2105.databasing.DatabaseManager;

public class ProfileLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_login);
    }

    public void onProfileLoginClick(View view) {
        // Need to change XML to fix
        //EditText emailInput = (EditText) findViewById(R.id.etEmailInput);
        //EditText passInput = (EditText) findViewById(R.id.etPassInput);

        //String email = emailInput.getText().toString();
        //String password = passInput.getText().toString();

        DatabaseManager DM = new DatabaseManager(new DatabaseHelper(this.getApplicationContext()));

        // TODO: Profile login feature

        // For the code below to execute, the profile login must be un-successful

    }
}
