package com.example.ahame_000.seg2105;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ahame_000.seg2105.databasing.DatabaseHelper;
import com.example.ahame_000.seg2105.databasing.DatabaseManager;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class CreateChoreActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_chore);

        View incorrectPopUp = findViewById(R.id.IncorrectCreds_TextView_NewChore);
        incorrectPopUp.setVisibility(View.INVISIBLE);


        Spinner spinner = findViewById(R.id.AssignTo_Spinner_CreateChore);
        List<Profile> profiles =Session.getLoggedInAccount().getChildren();
        profiles.add(Session.getLoggedInProfile());
        Profile unassignedProfile = new Adult("Unassigned","",null);
        profiles.add(unassignedProfile);
        ProfileSpinnerAdapter adapter = new ProfileSpinnerAdapter(this.getApplicationContext(),profiles);
        adapter.setDropDownViewResource(R.layout.assign_to_profile_item_layout);
        spinner.setAdapter(adapter);

    }

    public void onAddBttnClick(View view){



        EditText choreNameField = (EditText) findViewById(R.id.EnterChore_EditText_NewChore);
        String choreName = choreNameField.getText().toString();


        //TODO: to be done later
        //DatePicker dueDateField = (DatePicker) findViewById(R.id.DueDate_DatePicker_ChoreDetails);
        Date duedate = new Date();

        EditText descriptionField = (EditText)findViewById(R.id.EnterDescription_EditText_NewChore);
        String description = descriptionField.getText().toString();

        EditText rewardsField = (EditText)findViewById(R.id.EnterRewards_EditText_NewChore);


        int reward;
        //assigning the data in reward field to variable reward
        try {
            reward = Integer.parseInt(rewardsField.getText().toString());
        }
        catch (Exception e ){
            reward =0;
        }

        EditText penaltyField = (EditText)findViewById(R.id.EnterPenalty_EditText_NewChore);
        int penalty;

        //assigning penalty to the field
        try {
            penalty = Integer.parseInt(penaltyField.getText().toString());
        }
        catch (Exception e ){
            penalty = 0;
        }

        Spinner assignToSpinner = findViewById(R.id.AssignTo_Spinner_CreateChore);
        Profile assignToProfile = (Profile)assignToSpinner.getSelectedItem();
        if(assignToProfile.getAccount()==null){
            assignToProfile = null;
        }

        // TODO if statement in saving

        if(choreName.isEmpty() ) {
            TextView incorrectPopUp = findViewById(R.id.IncorrectCreds_TextView_NewChore);
            incorrectPopUp.setText("Chore name is mandatory!");
            incorrectPopUp.setVisibility(View.VISIBLE);
        }
       /* else if (duedate == null ){ //TODO make sure date is given
            TextView incorrectPopUp = findViewById(R.id.IncorrectCreds_TextView_NewChore);
            incorrectPopUp.setText("Due date is mandatory!");
            incorrectPopUp.setVisibility(View.VISIBLE);
        }*/
        else{
            //TODO add profile instead of null
            Chore chore = new Chore(choreName,description, null,duedate,(Adult)Session.getLoggedInProfile(),
                    assignToProfile, reward, penalty, Session.getLoggedInAccount(), UUID.randomUUID());

            DatabaseManager DM = new DatabaseManager(new DatabaseHelper(this.getApplicationContext()));
            DM.saveChore(chore);
            this.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
            onBackPressed();

        }

    }

}
