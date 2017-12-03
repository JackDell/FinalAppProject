package com.example.ahame_000.seg2105;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class ProfileLoginActivity extends AppCompatActivity {
private String profileName = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

         profileName = getIntent().getStringExtra("profileName");
       // TextView etName = findViewById(R.id.profileName_TextView_ProfileLogin);
        // etName.setText(profileName);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_login);
    }

    public void onProfileLoginClick(View view) {

        EditText etPassword = findViewById(R.id.profilePassword_editText_profileLogin);

        String password = etPassword.getText().toString();
        if (Session.loginProfile(profileName,password)){
            Intent intent = new Intent(getApplicationContext(), ChoreListActivity.class);
            startActivity(intent);
        } else {
            //TODO error message
            Intent intent = new Intent(getApplicationContext(), ProfileListActivity.class);
            startActivity(intent);
        }
    }
}
