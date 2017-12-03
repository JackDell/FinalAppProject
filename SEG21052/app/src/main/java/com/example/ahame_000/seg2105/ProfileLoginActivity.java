package com.example.ahame_000.seg2105;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ProfileLoginActivity extends AppCompatActivity {
private String profileName = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_login);
//TODO: make this activity look nice

        profileName = getIntent().getStringExtra("profileName");
        TextView etName = findViewById(R.id.profileName_TextView_ProfileLogin);
        etName.setText(profileName);
    }

    public void onProfileLoginClick(View view) {

        EditText etPassword = findViewById(R.id.profilePassword_editText_profileLogin);

        String password = etPassword.getText().toString();
        if (Session.loginProfile(profileName,password)){
            Intent intent = new Intent(getApplicationContext(), NavigationActivity.class);
            startActivity(intent);
        } else {
            etPassword.setText("");
            View incorrectPopUp = findViewById(R.id.incorrectCreds_TextView_ProfileLogin);
            incorrectPopUp.setVisibility(View.VISIBLE);
        }
    }
}
