package com.example.ahame_000.seg2105;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.ahame_000.seg2105.databasing.DatabaseHelper;
import com.example.ahame_000.seg2105.databasing.DatabaseManager;

public class AddMemberActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);

        // Getting the child radio button
        RadioButton childBttn = findViewById(R.id.Child_RadioButton_AddMember);

        // If the account has no profiles the user must create an Adult profile
        // therefore we remove the option to create a Child account if so
        if(Session.getLoggedInAccount().getProfiles().isEmpty()){
            childBttn.setVisibility(View.INVISIBLE);

        }
    }

    /**
     * Creates a new account with given email, password and account type
     * @param view
     */
    public void onSaveBttnClick(View view){
        // Getting the name, password, and if it is a Child or Adult account
        EditText nameTxt = findViewById(R.id.MemberName_EditText_AddMember);
        String nameString = nameTxt.getText().toString();

        EditText passwordTxt = findViewById(R.id.MemberPassword_EditText_AddMember);
        String passwordString = passwordTxt.getText().toString();

        RadioButton adultBttn = findViewById(R.id.Adult_RadioButton_AddMember);
        RadioButton childBttn = findViewById(R.id.Child_RadioButton_AddMember);
        // Profile chosen is a boolean variable that is true if and only if Child or Adult is selected
        // this stops users from creating accounts without a specified type
        boolean profileChosen = adultBttn.isChecked()||childBttn.isChecked();

        // Making sure the inputs are not empty, and that the profile type is selected
        if(!nameString.isEmpty() && !passwordString.isEmpty()&&profileChosen){
            DatabaseManager DM = new DatabaseManager(new DatabaseHelper(this.getApplicationContext()));
            // Getting the logged in account
            Account currentAccount = Session.getLoggedInAccount();
            Profile profile;
            // Determining if Child or Adult
            if(adultBttn.isChecked()){
                profile = new Adult(nameString,passwordString,currentAccount);
            }
            else {
                profile = new Child(nameString,passwordString,currentAccount);
            }
            // Not allowing users to create a profile with the same name as another profile
            if(!Session.getLoggedInAccount().getProfiles().contains(profile)) {
                DM.saveProfile(profile);
                currentAccount.getProfiles().add(profile);
                if(Session.getLoggedInProfile() == null){
                    Intent intent = new Intent(this, ProfileListActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(this, NavigationActivity.class);
                    startActivity(intent);
                }
            }
            // Notifying user that the profile name is already taken
            else {
                TextView nameTake = findViewById(R.id.tvProfileNameTaken);
                nameTake.setText("That profile name is already taken!");
                return;
            }
        }
    }

    @Override
    public void onBackPressed() {
        //log out the account if you just created the account and clicked on back
        if (Session.getLoggedInProfile() == null) {
            Session.logoutAccount();
            Intent intent = new Intent(getApplicationContext(), AccountLoginActivity.class);
            startActivity(intent);
        }
        else {
            super.onBackPressed();
        }
    }
}
