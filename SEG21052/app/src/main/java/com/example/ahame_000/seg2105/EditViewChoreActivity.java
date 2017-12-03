package com.example.ahame_000.seg2105;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.Date;
import java.util.UUID;



public class EditViewChoreActivity extends AppCompatActivity {

    private Chore chore;

    /**
     * Initializes layout according to profile type
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_view_chore);

        // Getting the passed chore ID
        String id = getIntent().getStringExtra("Chore_ID");

        Account account = Session.getLoggedInAccount();
        Profile profile = Session.getLoggedInProfile();

        Chore chore = account.getChore(UUID.fromString(id));

        //initializing the buttons
        Button doneButton = findViewById(R.id.Done_Button_ChoreDetails);
        Button assignToMeButton = findViewById(R.id.AssignToMe_Button_ChoreDetails);
        Button saveButton = findViewById(R.id.Save_Button_ChoreDetails);
        Button deleteButton = findViewById(R.id.Delete_Button_ChoreDetails);

        //initialize the layout
        LinearLayout assignLayout = findViewById(R.id.Assign_Layout_ChoreDetails);

        EditText choreDescriptionField = findViewById(R.id.Chore_EditText_ChoreDetails);
        choreDescriptionField.setText(chore.getName());

        EditText creatorField = findViewById(R.id.Creator_EditText_ChoreDetails);
        creatorField.setText(chore.getCreator().toString());

        EditText completedDateField = findViewById(R.id.CompletedDate_EditText_ChoreDetails);
        completedDateField.setText(chore.getCompletedDate().toString());

        EditText descriptionField = findViewById(R.id.Description_EditText_ChoreDetails);
        descriptionField.setText(chore.getDescription());

        EditText rewardsField = findViewById(R.id.Rewards_EditText_ChoreDetails);
        rewardsField.setText(chore.getReward());

        EditText penaltyField = findViewById(R.id.Penalty_EditText_ChoreDetails);
        penaltyField.setText(chore.getPenalty());

        EditText editTextFromDate = findViewById(R.id.etEditDeadlineDate);
        setDate fromDate = new setDate(editTextFromDate, this);

        if (profile instanceof  Adult) {
            assignLayout.setVisibility(View.VISIBLE);
            doneButton.setVisibility(View.VISIBLE);
            saveButton.setVisibility(View.VISIBLE);
            deleteButton.setVisibility(View.VISIBLE);
            assignToMeButton.setVisibility(View.VISIBLE);

        }
        if (profile instanceof  Child){
            assignLayout.setVisibility(View.INVISIBLE);
            doneButton.setVisibility(View.VISIBLE);
            saveButton.setVisibility(View.VISIBLE);
            deleteButton.setVisibility(View.INVISIBLE);
            assignToMeButton.setVisibility(View.VISIBLE);
        }

    }

    public void onDoneBttnClick(View view){
        LinearLayout eLayout = (LinearLayout)findViewById(R.id.CreatedByDate_Layout_ChoreDetails);
        Button doneButton = (Button)findViewById(R.id.Done_Button_ChoreDetails);

        if (chore.complete()){
            eLayout.setVisibility(View.VISIBLE);
            doneButton.setVisibility(View.INVISIBLE);
        }

    }

    /**
     * Assigns chore to current logged in profile
     * @param view
     */
    public void onAssignToMeBttnClick(View view){
        Button assignToMeButton = (Button)findViewById(R.id.AssignToMe_Button_ChoreDetails);

        Button doneButton = (Button)findViewById(R.id.Done_Button_ChoreDetails);
        Profile currentProfile = Session.getLoggedInProfile();
        Account currentAccount = Session.getLoggedInAccount();

        if (chore.getState()== ChoreState.UNASSIGNED){
            currentProfile.addChore(chore);
            currentAccount.removeChore(chore);
            chore.setAssignedTo(currentProfile);
            assignToMeButton.setVisibility(View.INVISIBLE);
            doneButton.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Deletes chore from logged in account
     * @param view
     */
    public void onDeleteBttnClick(View view){
        Button deleteButton = (Button)findViewById(R.id.Delete_Button_ChoreDetails);

        Account currentAccount = Session.getLoggedInAccount();
        Profile currentProfile = Session.getLoggedInProfile();

        if(chore.getState()==ChoreState.UNASSIGNED){
            currentAccount.removeChore(chore);

        }
    }



}
