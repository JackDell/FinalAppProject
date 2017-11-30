package com.example.ahame_000.seg2105;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;

import android.widget.Spinner;

import java.util.Date;
import java.util.List;
import java.util.UUID;



public class EditViewChoreActivity extends AppCompatActivity {

    private Chore chore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_view_chore);

        //DatabaseManager DM = new DatabaseManager(new DatabaseHelper(this.getApplicationContext()));
        String id = getIntent().getStringExtra("chore ID");
        Account account = Session.getLoggedInAccount();
        Profile profile = Session.getLoggedInProfile();

        chore = account.getChore(UUID.fromString(id));

        //initializing the buttons
        Button doneButton = (Button)findViewById(R.id.Done_Button_ChoreDetails);
        Button assignToMeButton = (Button)findViewById(R.id.AssignToMe_Button_ChoreDetails);
        Button saveButton = (Button)findViewById(R.id.Save_Button_ChoreDetails);
        Button deleteButton = (Button)findViewById(R.id.Delete_Button_ChoreDetails);

        LinearLayout assignLayout = (LinearLayout)findViewById(R.id.Assign_Layout_ChoreDetails);

        EditText choreField = (EditText) findViewById(R.id.EnterChore_EditText_ChoreDetails);
        choreField.setText(chore.getName());

        EditText creatorField = (EditText) findViewById(R.id.EnterCreator_EditText_ChoreDetails);
        creatorField.setText(chore.getCreator().toString());

        EditText completedDateField = (EditText)findViewById(R.id.EnterCompletedDate_EditText_ChoreDetails);
        completedDateField.setText(chore.getCompletedDate().toString());

        DatePicker dueDateField = (DatePicker) findViewById(R.id.DueDate_DatePicker_ChoreDetails);
         Date duedate =  chore.getDeadline();


         /*dueDateField.getYear(duedate.setYear());
         dueDateField.set()(duedate.getMonth());
         dueDateField.setDate()(duedate.getDayOfMonth);

            duedate.setYear(dueDateField.getYear());
            duedate.setMonth(dueDateField.getMonth());
            duedate.setDate(dueDateField.getDayOfMonth());

        */

        EditText descriptionField = (EditText)findViewById(R.id.EnterDescription_EditText_ChoreDetails);
        descriptionField.setText(chore.getDescription());

        EditText rewardsField = (EditText)findViewById(R.id.EnterRewards_EditText_ChoreDetails);
        rewardsField.setText(chore.getReward());

        EditText penaltyField = (EditText)findViewById(R.id.EnterPenalty_EditText_ChoreDetails);
        penaltyField.setText(chore.getPenalty());

        //TODO
       // EditText personField = (EditText)findViewById(R.id.Person_EditText_ChoreDetails);
        //personField.setText(chore.getAssignedTo().toString());


        // instance of the spinner
        Spinner spinner = findViewById(R.id.AssignToProfiles_Spinner_ChoreDetails);
        List<Profile> profiles =Session.getLoggedInAccount().getChildren();
       ProfileSpinnerAdapter adapter = new ProfileSpinnerAdapter(this.getApplicationContext(),profiles);

        adapter.setDropDownViewResource(R.layout.assign_to_profile_item_layout);
        spinner.setAdapter(adapter);

        //turning off visibility of some fields and components based on the profile type
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

    public void onDeleteBttnClick(View view){
        Button deleteButton = (Button)findViewById(R.id.Delete_Button_ChoreDetails);

        Account currentAccount = Session.getLoggedInAccount();
        Profile currentProfile = Session.getLoggedInProfile();

        if(chore.getState()==ChoreState.UNASSIGNED){
            currentAccount.removeChore(chore);

        }
    }



}
