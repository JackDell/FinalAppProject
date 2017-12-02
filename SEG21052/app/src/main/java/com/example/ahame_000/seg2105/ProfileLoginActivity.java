package com.example.ahame_000.seg2105;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.ahame_000.seg2105.databasing.DatabaseHelper;
import com.example.ahame_000.seg2105.databasing.DatabaseManager;
import android.widget.TextView;

public class ProfileLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        String profileName = getIntent().getStringExtra("profileName");
        TextView etName = findViewById(R.id.profileName_TextView_ProfileLogin);
        etName.setText(profileName);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_login);
    }

    public void onProfileLoginClick(View view) {

        TextView etName = findViewById(R.id.profileName_TextView_ProfileLogin);
        EditText etPassword = findViewById(R.id.etProfilePass);

        String name = etName.getText().toString();
        String password = etPassword.getText().toString();

        DatabaseManager DM = new DatabaseManager(new DatabaseHelper(this.getApplicationContext()));
        DM.loginProfile(name, password);
    }
}
