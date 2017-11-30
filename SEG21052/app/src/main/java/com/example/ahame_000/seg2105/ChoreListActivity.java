package com.example.ahame_000.seg2105;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class ChoreListActivity extends AppCompatActivity {

    private ListView generalList ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_chore_list);

        ChoreState listType = ChoreState.valueOf(getIntent().getStringExtra("LIST_TYPE"));
        Profile profile = Session.getViewedChild();
        if(profile == null)
            profile = Session.getLoggedInProfile();
        Account account = Session.getLoggedInAccount();
        ArrayList<Chore> choreList = ( ArrayList<Chore>) account.getUnassignedChores();
        switch (listType){
            case TODO:
                choreList = ( ArrayList<Chore>) profile.getTodoChores();
            case COMPLETED:
                choreList = ( ArrayList<Chore>) profile.getCompletedChores();
            case UNASSIGNED:
                choreList = ( ArrayList<Chore>) account.getUnassignedChores();
        }


        ListView listView = (ListView) findViewById(R.id.GeneralChoresList_ListView_HomePage);
        ChoreCustomAdapter adapter = new ChoreCustomAdapter(this,choreList);
        listView.setAdapter(adapter);



    }
}
