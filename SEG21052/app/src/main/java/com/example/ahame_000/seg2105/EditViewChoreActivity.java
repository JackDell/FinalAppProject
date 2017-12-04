package com.example.ahame_000.seg2105;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ahame_000.seg2105.databasing.DatabaseHelper;
import com.example.ahame_000.seg2105.databasing.DatabaseManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;



public class EditViewChoreActivity extends AppCompatActivity {

    private Chore chore;
    private final String UNASSIGNED = "Unassigned";

    /**
     * Initializes layout according to profile type
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_view_chore);

        // Getting the passed chore ID
        UUID id = UUID.fromString(getIntent().getStringExtra("Chore_ID"));
        this.chore = Session.getLoggedInAccount().getChore(id);
        reload();


    }
    private void reload(){
        Profile loggedInProfile = Session.getLoggedInProfile();

        //initializing the object from the layout
        Button completeButton = findViewById(R.id.Complete_Button_ChoreDetails);
        EditText choreNameField = findViewById(R.id.ChoreName_EditText_ChoreDetails);
        TextView creatorField = findViewById(R.id.CreatorName_TextView_ChoreDetails);
        LinearLayout completedDateLayout = findViewById(R.id.CompletedDate_ViewLayout_ChoreDetails);
        TextView completedDateField = findViewById(R.id.CompletedDate_TextView_ChoreDetails);
        EditText dueDateField = findViewById(R.id.DueDate_EditText_ChoreDetails);
        EditText descriptionField = findViewById(R.id.Description_EditText_ChoreDetails);
        EditText rewardsField = findViewById(R.id.Rewards_EditText_ChoreDetails);
        EditText penaltyField = findViewById(R.id.Penalty_EditText_ChoreDetails);
        LinearLayout assignToLayout = findViewById(R.id.AssignTo_Layout_ChoreDetails);
        Spinner assignToSpinner = findViewById(R.id.AssignTo_Spinner_ChoreDetails);
        Button saveButton = findViewById(R.id.Save_Button_ChoreDetails);
        Button deleteButton = findViewById(R.id.Delete_Button_ChoreDetails);
        Button assignToMe = findViewById(R.id.AssignToMe_Button_ChoreDetails);


        //Set the visibilities and Editable
        if(loggedInProfile instanceof Child){
            choreNameField.setEnabled(false);
            dueDateField.setEnabled(false);
            descriptionField.setEnabled(false);
            rewardsField.setEnabled(false);
            penaltyField.setEnabled(false);
            assignToLayout.setVisibility(View.GONE);
            saveButton.setVisibility(View.GONE);
            deleteButton.setVisibility(View.GONE);
            assignToMe.setVisibility(View.VISIBLE);
        } else if (loggedInProfile instanceof Adult) {
            choreNameField.setEnabled(true);
            dueDateField.setEnabled(true);
            descriptionField.setEnabled(true);
            rewardsField.setEnabled(true);
            penaltyField.setEnabled(true);
            assignToLayout.setVisibility(View.VISIBLE);
            saveButton.setVisibility(View.VISIBLE);
            deleteButton.setVisibility(View.VISIBLE);
            assignToMe.setVisibility(View.GONE);

            List<String> profileNames = new ArrayList<>();
            int assignPosition = 0;
            int i = 0;
            for (Profile profile : Session.getLoggedInAccount().getChildren()) {
                profileNames.add(profile.getName());
                if (profile == chore.getAssignedTo())
                    assignPosition = i;
                i++;
            }
            profileNames.add(Session.getLoggedInProfile().getName());
            profileNames.add(UNASSIGNED);
            if (Session.getLoggedInProfile() == chore.getAssignedTo())
                assignPosition = i;
            if (chore.getAssignedTo() == null)
                assignPosition = i + 1;
            ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
                    R.layout.assign_spinner_layout,
                    R.id.spinner_text,
                    profileNames);
            assignToSpinner.setAdapter(spinnerAdapter);
            assignToSpinner.setSelection(assignPosition);
        }

        chore.isLate();
        if(chore.getState()== ChoreState.UNASSIGNED) {
            completeButton.setVisibility(View.GONE);
            completedDateLayout.setVisibility(View.GONE);
        }
        if(chore.getState()== ChoreState.TODO) {
            if (Session.getViewedChild() != null)
                completeButton.setVisibility(View.GONE);
            completedDateLayout.setVisibility(View.GONE);
            assignToMe.setVisibility(View.GONE);
        }
        if(chore.getState()== ChoreState.PASTDUE ){
            if (Session.getViewedChild() != null)
                completeButton.setVisibility(View.GONE);
            completedDateLayout.setVisibility(View.GONE);
            assignToMe.setVisibility(View.GONE);
        }
        if(chore.getState()== ChoreState.COMPLETED) {
            completeButton.setVisibility(View.GONE);
            choreNameField.setEnabled(false);
            dueDateField.setEnabled(false);
            descriptionField.setEnabled(false);
            rewardsField.setEnabled(false);
            penaltyField.setEnabled(false);
            assignToLayout.setVisibility(View.GONE);
            saveButton.setVisibility(View.GONE);
            deleteButton.setVisibility(View.GONE);
            assignToMe.setVisibility(View.GONE);
            completedDateField.setText(DateHelper.getDateString(chore.getCompletedDate()));
        }


        //Filling in the values
        choreNameField.setText(new String(chore.getName()));
        creatorField.setText(chore.getCreator().getName());
        SetDate fromDate = new SetDate(dueDateField, this);
        dueDateField.setText(DateHelper.getDateString(chore.getDeadline()));
        descriptionField.setText(new String(chore.getDescription()));
        rewardsField.setText(Integer.toString(chore.getReward()));
        penaltyField.setText(Integer.toString(chore.getPenalty()));
    }

    public void onDoneBttnClick(View view) {

        chore.setCompletedDate(new Date());
        chore.getAssignedTo().addPoints(chore.getTodaysReward());
        DatabaseManager DM = new DatabaseManager(new DatabaseHelper(getApplicationContext()));
        DM.updateProfilePoints(chore.getAssignedTo());
        DM.saveChore(chore);
        onBackPressed();
    }

    /**
     * Assigns chore to current logged in profile
     * @param view
     */
    public void onAssignToMeBttnClick(View view) {
        Button assignToMeButton = findViewById(R.id.AssignToMe_Button_ChoreDetails);
        Button completedButton = findViewById(R.id.Complete_Button_ChoreDetails);
        Profile currentProfile = Session.getLoggedInProfile();
        currentProfile.addChore(chore);
        chore.setAssignedTo(currentProfile);
        chore.assign();
        chore.isLate();
        assignToMeButton.setVisibility(View.INVISIBLE);
        completedButton.setVisibility(View.VISIBLE);
    }


    /**
     * Deletes chore from logged in account
     * @param view
     */
    public void onDeleteBttnClick(View view){
        Account currentAccount = Session.getLoggedInAccount();
        Profile assignedProfile = chore.getAssignedTo();

        if(chore.getState()==ChoreState.UNASSIGNED){
            currentAccount.removeChore(chore);
        }
        if (assignedProfile != null){
            assignedProfile.removeChore(chore);
        }
        DatabaseManager DM = new DatabaseManager(new DatabaseHelper(getApplicationContext()));
        DM.removeChore(chore);
        onBackPressed();
    }
     public void onSaveBttnClick(View view) {

         Profile loggedInProfile = Session.getLoggedInProfile();

         //initializing the object from the layout
         EditText choreNameField = findViewById(R.id.ChoreName_EditText_ChoreDetails);
         EditText dueDateField = findViewById(R.id.DueDate_EditText_ChoreDetails);
         EditText descriptionField = findViewById(R.id.Description_EditText_ChoreDetails);
         EditText rewardsField = findViewById(R.id.Rewards_EditText_ChoreDetails);
         EditText penaltyField = findViewById(R.id.Penalty_EditText_ChoreDetails);
         Spinner assignToSpinner = findViewById(R.id.AssignTo_Spinner_ChoreDetails);


         //Getting the variables
         String choreNameNew = choreNameField.getText().toString();
         Date dueDateNew = DateHelper.dateFromString(dueDateField.getText().toString());
         String descriptionNew = descriptionField.getText().toString();

         int rewardsNew;
         try{
             rewardsNew = Integer.parseInt(rewardsField.getText().toString());
         } catch (Exception e){ rewardsNew = chore.getReward(); }
         int penaltyNew;
         try{
             penaltyNew = Integer.parseInt(penaltyField.getText().toString());
         } catch (Exception e){ penaltyNew = chore.getPenalty(); }

         String assignToName = (String) assignToSpinner.getSelectedItem();
         Profile assignToNew;
         ChoreState choreStateNew;
         if(assignToName.equals(UNASSIGNED)){
             assignToNew = null;
             choreStateNew = ChoreState.UNASSIGNED;
         } else {
             assignToNew = Session.getLoggedInAccount().getProfile(assignToName);
             choreStateNew = ChoreState.TODO;
         }

         //Checking if anything has changed
         if (!chore.getName().equals(choreNameNew) ||
                 !chore.getDescription().equals(descriptionNew)||
                 !DateHelper.sameDate(chore.getDeadline(),dueDateNew)||
                 chore.getReward() != rewardsNew ||
                 chore.getPenalty() != penaltyNew ||
                 chore.getAssignedTo() != assignToNew){

             if(choreNameNew.isEmpty() ) {
                 TextView incorrectPopUp = findViewById(R.id.IncorrectCreds_TextView_ChoreDetails);
                 incorrectPopUp.setText("Chore name is mandatory!");
                 incorrectPopUp.setVisibility(View.VISIBLE);
             }
             else if (dueDateNew == null ){
                 TextView incorrectPopUp = findViewById(R.id.IncorrectCreds_TextView_ChoreDetails);
                 incorrectPopUp.setText("Due date is mandatory!");
                 incorrectPopUp.setVisibility(View.VISIBLE);
             } else {
                 if (chore.getAssignedTo() != null)
                     chore.getAssignedTo().removeChore(chore);
                 chore.setName(choreNameNew);
                 chore.setDescription(descriptionNew);
                 chore.setState(choreStateNew);
                 chore.setDeadline(dueDateNew);
                 chore.setCreator((Adult) loggedInProfile);
                 chore.setAssignedTo(assignToNew);
                 chore.setReward(rewardsNew);
                 chore.setPenalty(penaltyNew);
                 DatabaseManager DM = new DatabaseManager(new DatabaseHelper(this.getApplicationContext()));
                 DM.saveChore(chore);
                 if (chore.getAssignedTo() != null)
                     chore.getAssignedTo().addChore(chore);
                 TextView incorrectPopUp = findViewById(R.id.IncorrectCreds_TextView_ChoreDetails);
                 incorrectPopUp.setText("Changes have been saved");
                 incorrectPopUp.setVisibility(View.VISIBLE);
             }

         }
     }

}
