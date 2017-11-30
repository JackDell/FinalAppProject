package com.example.ahame_000.seg2105;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.ahame_000.seg2105.databasing.DatabaseHelper;
import com.example.ahame_000.seg2105.databasing.DatabaseManager;

public class AddMemberActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);
    }

    public void onSaveBttnClick(View view){
        EditText nameTxt = findViewById(R.id.MemberName_EditText_AddMember);
        String nameString = nameTxt.getText().toString();

        EditText passwordTxt = findViewById(R.id.MemberPassword_EditText_AddMember);
        String passwordString = passwordTxt.getText().toString();

        RadioGroup profileTypeBttn = findViewById(R.id.ProfileType_RadioButton_AddMember);
        RadioButton adultBttn = findViewById(R.id.Adult_RadioButton_AddMember);
        RadioButton childBttn = findViewById(R.id.Child_RadioButton_AddMember);
        boolean profileChosen = adultBttn.isChecked()||childBttn.isChecked();

        if(!nameString.isEmpty() && !passwordString.isEmpty()&&profileChosen){
            DatabaseManager DM = new DatabaseManager(new DatabaseHelper(this.getApplicationContext()));
            Account currentAccount = Session.getLoggedInAccount();
            if(adultBttn.isChecked()){
                DM.saveProfile(new Adult(nameString,passwordString,currentAccount));
            }
            else if (childBttn.isChecked()){
                DM.saveProfile(new Child(nameString,passwordString,currentAccount));
            }
            Intent intent = new Intent(this,CreateAccountActivity.class);
            startActivity(intent);
        }

    }


}
