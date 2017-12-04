package com.example.ahame_000.seg2105;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.ahame_000.seg2105.databasing.DatabaseHelper;
import com.example.ahame_000.seg2105.databasing.DatabaseManager;

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
        //TODO make read only if chore is competed
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_view_chore);

        // Getting the passed chore ID
        UUID id = UUID.fromString(getIntent().getStringExtra("Chore_ID"));
        Chore chore = Session.getLoggedInAccount().getChore(id);
        Account account = Session.getLoggedInAccount();
        Profile profile = Session.getLoggedInProfile();
        this.chore = account.getChore(id);

        //initializing the object from the layout
        Button doneButton = findViewById(R.id.Complete_Button_ChoreDetails);
        EditText choreNameField = findViewById(R.id.ChoreName_EditText_ChoreDetails);
        LinearLayout completedDateLayout = findViewById(R.id.CompletedDate_ViewLayout_ChoreDetails);
        EditText dueDateField = findViewById(R.id.DueDate_EditText_ChoreDetails);
        EditText descriptionField = findViewById(R.id.Description_EditText_ChoreDetails);
        EditText rewardsField = findViewById(R.id.Rewards_EditText_ChoreDetails);
        EditText penaltyField = findViewById(R.id.Penalty_EditText_ChoreDetails);
        LinearLayout assignToLayout = findViewById(R.id.AssignTo_Layout_ChoreDetails);
        Spinner assignToSpinner = findViewById(R.id.AssignTo_Spinner_ChoreDetails);
        Button saveButton = findViewById(R.id.Save_Button_ChoreDetails);
        Button deleteButton = findViewById(R.id.Delete_Button_ChoreDetails);



        /*Button assignToMeButton = findViewById(R.id.AssignToMe_Button_ChoreDetails);
        Button saveButton = findViewById(R.id.Save_Button_ChoreDetails);
        Button deleteButton = findViewById(R.id.Delete_Button_ChoreDetails);

        //initialize the layout
        LinearLayout assignLayout = findViewById(R.id.Assign_Layout_ChoreDetails);

        EditText choreDescriptionField = findViewById(R.id.ChoreName_EditText_ChoreDetails);
        choreDescriptionField.setText(chore.getName());//TODO: what??

        EditText creatorField = findViewById(R.id.CreatorName_TextView_ChoreDetails);
        creatorField.setText(chore.getCreator().getName());

        // Checking for null
        EditText completedDateField = findViewById(R.id.CompletedDate_TextView_ChoreDetails);
        if(chore.getCompletedDate() == null) {
            completedDateField.setText("Not Completed");
        }
        else {
            completedDateField.setText(chore.getCompletedDate().toString());
        }

        EditText descriptionField = findViewById(R.id.Description_EditText_ChoreDetails);
        descriptionField.setText(chore.getDescription());

        EditText rewardsField = findViewById(R.id.Rewards_EditText_ChoreDetails);
        rewardsField.setText(String.valueOf(chore.getReward()));

        EditText penaltyField = findViewById(R.id.Penalty_EditText_ChoreDetails);
        penaltyField.setText(String.valueOf(chore.getPenalty()));

        EditText editTextFromDate = findViewById(R.id.DueDate_EditText_ChoreDetails);
        new SetDate(editTextFromDate, this);

        if (profile instanceof  Adult) {
            assignLayout.setVisibility(View.VISIBLE);
            doneButton.setVisibility(View.INVISIBLE);
            saveButton.setVisibility(View.VISIBLE);
            deleteButton.setVisibility(View.VISIBLE);
            assignToMeButton.setVisibility(View.VISIBLE);

            if(profile.getName().equals(chore.getAssignedTo().getName())) {
                doneButton.setVisibility(View.VISIBLE);
            }
        }
        else {
            assignLayout.setVisibility(View.INVISIBLE);
            doneButton.setVisibility(View.VISIBLE);
            saveButton.setVisibility(View.VISIBLE);
            deleteButton.setVisibility(View.INVISIBLE);
            assignToMeButton.setVisibility(View.VISIBLE);
        }

*/

    }

    public void onDoneBttnClick(View view) {

        chore.setCompletedDate(new Date());
        chore.updateState();
        Profile p = Session.getLoggedInProfile();
        p.addPoints(chore.getTodaysReward());
        DatabaseManager DM = new DatabaseManager(new DatabaseHelper(getApplicationContext()));
        DM.saveChore(chore);
        //TODO:remove the old chore
        this.onBackPressed();
    }

    /**
     * Assigns chore to current logged in profile
     * @param view
     */
    public void onAssignToMeBttnClick(View view){
        Button assignToMeButton = (Button)findViewById(R.id.AssignToMe_Button_ChoreDetails);

        Button doneButton = (Button)findViewById(R.id.Complete_Button_ChoreDetails);
        Profile currentProfile = Session.getLoggedInProfile();

        if (chore.getState() == ChoreState.UNASSIGNED){
            currentProfile.addChore(chore);
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


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
