package com.example.ahame_000.seg2105;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ahame_000.seg2105.databasing.DatabaseHelper;
import com.example.ahame_000.seg2105.databasing.DatabaseManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


public class CreateChoreActivity extends AppCompatActivity {
private final String UNASSIGNED = "Unassigned";

    /**
     * Loads create chore page
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_chore);

        View incorrectPopUp = findViewById(R.id.IncorrectCreds_TextView_NewChore);
        incorrectPopUp.setVisibility(View.INVISIBLE);

        List<String> profileNames = new ArrayList<>();

        for (Profile profile : Session.getLoggedInAccount().getChildren()) {
            profileNames.add(profile.getName());
        }
        profileNames.add(Session.getLoggedInProfile().getName());
        profileNames.add(UNASSIGNED);
        Spinner spinner = (Spinner) findViewById(R.id.AssignTo_Spinner_CreateChore);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
                R.layout.assign_spinner_layout,
                R.id.spinner_text,
                profileNames);

        spinner.setAdapter(spinnerAdapter);

        EditText dateEditText = (EditText) findViewById(R.id.DueDate_EditText_NewChore);

        setDate fromDate = new setDate(dateEditText, this);
    }

    /**
     * Adds/creates chore with given attributes
     * @param view
     */
    public void onAddBttnClick(View view){
        EditText choreNameField = (EditText) findViewById(R.id.Chore_EditText_NewChore);
        String choreName = choreNameField.getText().toString();

        EditText dueDateField = findViewById(R.id.DueDate_EditText_NewChore);
        Date duedate = DateHelper.dateFromString(dueDateField.getText().toString());

        EditText descriptionField = (EditText)findViewById(R.id.Description_EditText_NewChore);
        String description = descriptionField.getText().toString();

        EditText rewardsField = (EditText)findViewById(R.id.Rewards_EditText_NewChore);


        int reward;
        //assigning the data in reward field to variable reward
        try {
            reward = Integer.parseInt(rewardsField.getText().toString());
        }
        catch (Exception e ){
            reward =0;
        }

        EditText penaltyField = (EditText)findViewById(R.id.Penalty_EditText_NewChore);
        int penalty;

        //assigning penalty to the field
        try {
            penalty = Integer.parseInt(penaltyField.getText().toString());
        }
        catch (Exception e ){
            penalty = 0;
        }


        Spinner assignToSpinner = findViewById(R.id.AssignTo_Spinner_CreateChore);
        String assignToName = (String) assignToSpinner.getSelectedItem();
        Profile assignTo;
        ChoreState choreState;
        if(assignToName == UNASSIGNED){
            assignTo = null;
            choreState = ChoreState.UNASSIGNED;
        } else {
            assignTo = Session.getLoggedInAccount().getProfile(assignToName);
            choreState = ChoreState.TODO;
        }

        if(choreName.isEmpty() ) {
            TextView incorrectPopUp = findViewById(R.id.IncorrectCreds_TextView_NewChore);
            incorrectPopUp.setText("Chore name is mandatory!");
            incorrectPopUp.setVisibility(View.VISIBLE);
        }
       else if (duedate == null ){
            TextView incorrectPopUp = findViewById(R.id.IncorrectCreds_TextView_NewChore);
            incorrectPopUp.setText("Due date is mandatory!");
            incorrectPopUp.setVisibility(View.VISIBLE);
        } else{
            Chore chore = new Chore(choreName, description, choreState, null, duedate,(Adult)Session.getLoggedInProfile(),
                    assignTo, reward, penalty, Session.getLoggedInAccount(), UUID.randomUUID());

            DatabaseManager DM = new DatabaseManager(new DatabaseHelper(this.getApplicationContext()));

            DM.saveChore(chore);
            Session.getLoggedInAccount().addChore(chore);
            if (assignTo != null){
                assignTo.addChore(chore);
            }
            onBackPressed();

        }


    }

}
