package com.example.ahame_000.seg2105;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.support.v7.app.AppCompatActivity;

import android.widget.Spinner;

import android.widget.TextView;

import com.example.ahame_000.seg2105.databasing.DatabaseHelper;
import com.example.ahame_000.seg2105.databasing.DatabaseManager;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;

import java.util.Locale;
import java.util.UUID;
import java.util.Calendar;
import android.app.DatePickerDialog;
import android.view.View.OnClickListener;

public class CreateChoreActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_chore);

        View incorrectPopUp = findViewById(R.id.IncorrectCreds_TextView_NewChore);
        incorrectPopUp.setVisibility(View.INVISIBLE);


        Spinner spinner = findViewById(R.id.AssignTo_Spinner_CreateChore);
        List<Profile> profiles = Session.getLoggedInAccount().getChildren();
        profiles.add(Session.getLoggedInProfile());
        Profile unassignedProfile = new Adult("Unassigned","",null);
        profiles.add(unassignedProfile);
        ProfileSpinnerAdapter adapter = new ProfileSpinnerAdapter(this.getApplicationContext(),profiles);
        adapter.setDropDownViewResource(R.layout.assign_to_profile_item_layout);
        spinner.setAdapter(adapter);



        final EditText edittext= (EditText) findViewById(R.id.EnterDueDate_EditText_NewChore);
        final DatePicker enterDate = (DatePicker)findViewById(R.id.DueDate_DatePicker_ChoreDetails);


        edittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog.OnDateSetListener dpd = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {

                        int s=monthOfYear+1;
                        String a = dayOfMonth+"/"+s+"/"+year;
                        edittext.setText(""+a);
                    }
                };



            }
        });



    }




    public void onAddBttnClick(View view){


        Button addButton = (Button)findViewById(R.id.Add_Button_NewChore);

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


        //TODO the spinner for assigning to someone


        // TODO if statement in saving

        if(choreName.isEmpty() ) {
            TextView incorrectPopUp = findViewById(R.id.IncorrectCreds_TextView_NewChore);
            incorrectPopUp.setText("Chore name is mandatory!");
            incorrectPopUp.setVisibility(View.VISIBLE);
        }
        else if (duedate == null ){ //TODO make sure date is given
            TextView incorrectPopUp = findViewById(R.id.IncorrectCreds_TextView_NewChore);
            incorrectPopUp.setText("Due date is mandatory!");
            incorrectPopUp.setVisibility(View.VISIBLE);
        }
        else{
            //TODO add profile instead of null
            Chore chore = new Chore(choreName,description, null,duedate,(Adult)Session.getLoggedInProfile(),
                    null, reward, penalty, Session.getLoggedInAccount(), UUID.randomUUID());

            DatabaseManager DM = new DatabaseManager(new DatabaseHelper(this.getApplicationContext()));

            DM.saveChore(chore);
        }


        /*
        if(chore.getName()== null){
            addButton.setVisibility(View.INVISIBLE);
        }

        if (chore.getPenalty() == 0){
            addButton.setVisibility(View.INVISIBLE);

        }

        if(chore.getReward() == 0){
            addButton.setVisibility(View.INVISIBLE);

        }

        if(chore.getDeadline() == null){
            addButton.setVisibility(View.INVISIBLE);

        }

        if (chore.getCreator() == null){
            addButton.setVisibility(View.INVISIBLE);

        }

        if (chore.getAccount() == null){
            addButton.setVisibility(View.INVISIBLE);

        }

        ****/
    }

}
