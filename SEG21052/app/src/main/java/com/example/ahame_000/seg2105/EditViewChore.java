package com.example.ahame_000.seg2105;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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

        if (chore.getState() == ChoreState.COMPLETED){
            eLayout.setVisibility(View.VISIBLE);
            doneButton.setVisibility(View.INVISIBLE);
        }

    }

    public void onAssignToMeBttnClick(View view){
        Button assignToMeButton = (Button)findViewById(R.id.AssignToMe_Button_ChoreDetails);

        Button doneButton = (Button)findViewById(R.id.Done_Button_ChoreDetails);
        Profile currentProfile = Session.getLoggedInProfile();
        Account currentAccount = Session.getLoggedInAccount();

        if(currentAccount.getAllChores().contains(chore)) {
            currentProfile.addChore(chore);
            assignToMeButton.setVisibility(View.INVISIBLE);
            doneButton.setVisibility(View.VISIBLE);
        }
    }

    public void onDeleteBttnClick(View view){
        Button deleteButton = (Button) findViewById(R.id.Delete_Button_ChoreDetails);

        Account currentAccount = Session.getLoggedInAccount();
        Profile currentProfile = Session.getLoggedInProfile();

        currentAccount.removeChore(chore);
    }

}
