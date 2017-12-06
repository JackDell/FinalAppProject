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

        // Setting the incorrect popup to invisible to start
        View incorrectPopUp = findViewById(R.id.IncorrectCreds_TextView_NewChore);
        incorrectPopUp.setVisibility(View.INVISIBLE);

        // Creating a new list to hold the profile names
        List<String> profileNames = new ArrayList<>();

        // For every child profile in this session, add their name to the list
        for (Profile profile : Session.getLoggedInAccount().getChildren()) {
            profileNames.add(profile.getName());
        }
        // Also add the current logged in profile to the list, and the option to not assign it to anyone
        profileNames.add(Session.getLoggedInProfile().getName());
        profileNames.add(UNASSIGNED);

        // Getting the spinner object
        Spinner spinner = findViewById(R.id.AssignTo_Spinner_CreateChore);

        // Filling an array adapter with the profile name information
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
                R.layout.assign_spinner_layout,
                R.id.spinner_text,
                profileNames);

        // Setting the adapter for the spinner the filled adapter
        spinner.setAdapter(spinnerAdapter);
        spinner.setSelection(profileNames.size()-1);

        // Getting the EditText that is clicked to enter the date
        EditText dateEditText = findViewById(R.id.DueDate_EditText_NewChore);
        // Creating a set date class to manage the onClick of the date EditText
        new SetDate(dateEditText, this);
    }

    /**
     * Adds/creates chore with given attributes
     * @param view
     */
    public void onAddBttnClick(View view){
        // Getting the input fields: name, due date, description, reward, and penalty
        EditText choreNameField = findViewById(R.id.Chore_EditText_NewChore);
        String choreName = choreNameField.getText().toString();

        EditText dueDateField = findViewById(R.id.DueDate_EditText_NewChore);
        Date duedate = DateHelper.dateFromString(dueDateField.getText().toString());

        EditText descriptionField = findViewById(R.id.Description_EditText_NewChore);
        String description = descriptionField.getText().toString();

        EditText rewardsField = findViewById(R.id.Rewards_EditText_NewChore);
        EditText penaltyField = findViewById(R.id.Penalty_EditText_NewChore);


        int reward, penalty;
        // If the user failed to input an integer for the reward value, set the reward to nothing
        try {
            reward = Integer.parseInt(rewardsField.getText().toString());
        }
        catch (Exception e ){
            reward = 0;
        }
        // If the user fails to input an integer for the penalty amount, set the penalty to 0
        try {
            penalty = Integer.parseInt(penaltyField.getText().toString());
        }
        catch (Exception e ){
            penalty = 0;
        }


        // Getting the spinner for chore assignment
        Spinner assignToSpinner = findViewById(R.id.AssignTo_Spinner_CreateChore);
        // Getting the selected item, and casting it to a String
        String assignToName = (String) assignToSpinner.getSelectedItem();
        Profile assignTo;
        ChoreState choreState;
        // Determining the ChoreState
        if(assignToName == UNASSIGNED){
            assignTo = null;
            choreState = ChoreState.UNASSIGNED;
        } else {
            assignTo = Session.getLoggedInAccount().getProfile(assignToName);
            choreState = ChoreState.TODO;
        }

        // Making sure that a chore name is inputted
        if(choreName.isEmpty() ) {
            TextView incorrectPopUp = findViewById(R.id.IncorrectCreds_TextView_NewChore);
            incorrectPopUp.setText("Chore name is mandatory!");
            incorrectPopUp.setVisibility(View.VISIBLE);
            return;
        }

        // Making sure that a due date is inputted
        if (duedate == null ){
            TextView incorrectPopUp = findViewById(R.id.IncorrectCreds_TextView_NewChore);
            incorrectPopUp.setText("Due date is mandatory!");
            incorrectPopUp.setVisibility(View.VISIBLE);
            return;
        }

        // If everything is inputted correctly, create a new chore with the gotten data
        Chore chore = new Chore(choreName, description, choreState, null, duedate,(Adult)Session.getLoggedInProfile(),
                assignTo, reward, penalty, Session.getLoggedInAccount(), UUID.randomUUID());

        // Accessing the database through the manager
        DatabaseManager DM = new DatabaseManager(new DatabaseHelper(this.getApplicationContext()));

        // Saving the chore
        DM.saveChore(chore);
        // Getting the logged in account and adding the chore to it
        Session.getLoggedInAccount().addChore(chore);
        // If the chore is assigned to someone, add that chore to their chore list as well
        if (assignTo != null){
            assignTo.addChore(chore);
        }
        onBackPressed();
    }

}
