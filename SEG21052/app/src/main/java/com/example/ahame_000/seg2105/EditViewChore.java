package com.example.ahame_000.seg2105;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class EditViewChore extends AppCompatActivity {

    private Chore chore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_view_chore);
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
        DBmangment dBmangment = DBmangment.getInstance();
        Button doneButton = (Button)findViewById(R.id.Done_Button_ChoreDetails);
        Profile currentProfile = dBmangment.getProfile();
        Account currentAccount = dBmangment.getAccount();

        if (chore.getState()== Chore.State.UNASSIGNED){
            currentProfile.addChore(chore);
            currentAccount.removeChore(chore);
            chore.setAssignedTo(currentProfile);
            assignToMeButton.setVisibility(View.INVISIBLE);
            doneButton.setVisibility(View.VISIBLE);
        }
    }

    public void onDeleteBttnClick(View view){
        Button deleteButton = (Button)findViewById(R.id.Delete_Button_ChoreDetails);
        DBmangment dBmangment = DBmangment.getInstance();
        Account currentAccount = dBmangment.getAccount();
        Profile currentProfile = dBmangment.getProfile();

        if(chore.getState()==Chore.State.UNASSIGNED){
            currentAccount.removeChore(chore);
            
        }
    }

}
