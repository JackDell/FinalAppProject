package com.example.ahame_000.seg2105;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ChoreListActivity extends AppCompatActivity {

    private ListView generalList ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_chore_list);

       // ChoreState listType = ChoreState.valueOf(getIntent().getStringExtra("LIST_TYPE"));
        //Profile profile = Session.getViewedChild();
        //if(profile == null)
          //  profile = Session.getLoggedInProfile();
        Account account = Session.getLoggedInAccount();
        ArrayList<Chore> choreList = ( ArrayList<Chore>) account.getUnassignedChores();
        /*switch (listType){
            case TODO:
                choreList = ( ArrayList<Chore>) profile.getTodoChores();
            case COMPLETED:
                choreList = ( ArrayList<Chore>) profile.getCompletedChores();
            case UNASSIGNED:
                choreList = ( ArrayList<Chore>) account.getUnassignedChores();
        }*/


        ListView listView = findViewById(R.id.GeneralChoresList_ListView_HomePage);
        ChoreCustomAdapter adapter = new ChoreCustomAdapter(this,choreList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {

                Chore selectedChore = (Chore)parent.getItemAtPosition(position);

               String choreID = selectedChore.getStringId();


                Intent launchChoreEdit = new Intent(getApplicationContext(), EditViewChoreActivity.class);
                launchChoreEdit.putExtra("Chore_ID",choreID);
                startActivity(launchChoreEdit);

            }
        });
    }
}
