package com.example.ahame_000.seg2105;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Nour on 2017-12-02.
 */

public class ChoreListFragment extends Fragment {

    private ChoreState listType = ChoreState.UNASSIGNED;
    private View view;

    public void setListType(ChoreState listType) {
        this.listType = listType;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.chore_list_layout,container,false);





        TextView listTittleField =  this.view.findViewById(R.id.ChoresListTitle_TextView_HomePage);


        Profile profile = Session.getViewedChild();
        if (profile == null)
            profile = Session.getLoggedInProfile();
        Account account = Session.getLoggedInAccount();
        ArrayList<Chore> choreList = (ArrayList<Chore>) account.getUnassignedChores();
        switch (listType) {
                   case TODO:
                choreList = (ArrayList<Chore>) profile.getTodoChores();
                       Log.d("nour","   in Switch TODO in create view of CHORELIST FRAGMENT");
                listTittleField.setText("To-Do List");
            case COMPLETED:
                Log.d("nour","   in Switch completed in create view of CHORELIST FRAGMENT");

                choreList = (ArrayList<Chore>) profile.getCompletedChores();
                listTittleField.setText("Completed List");

            case UNASSIGNED:
                Log.d("nour","   in Switch unissgined in create view of CHORELIST FRAGMENT");

                choreList = (ArrayList<Chore>) account.getUnassignedChores();
                listTittleField.setText("General List");

        }


        ListView listView =  this.view.findViewById(R.id.GeneralChoresList_ListView_HomePage);
        ChoreCustomAdapter adapter = new ChoreCustomAdapter(getContext(), choreList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {

                Chore selectedChore = (Chore) parent.getItemAtPosition(position);

                String choreID = selectedChore.getStringId();


                Intent launchChoreEdit = new Intent(getContext(), EditViewChoreActivity.class);
                launchChoreEdit.putExtra("Chore_ID", choreID);
                startActivity(launchChoreEdit);

            }
        });

        return this.view;
    }




    @Override
    public void onResume() {
        TextView listTittleField = this.view.findViewById(R.id.ChoresListTitle_TextView_HomePage);

        Profile profile = Session.getViewedChild();
        if (profile == null)
            profile = Session.getLoggedInProfile();
        Account account = Session.getLoggedInAccount();
        ArrayList<Chore> choreList = (ArrayList<Chore>) account.getUnassignedChores();
        switch (listType) {
            case TODO:
                choreList = (ArrayList<Chore>) profile.getTodoChores();
                Log.d("nour","   in Switch TODO in create view of CHORELIST FRAGMENT");
                listTittleField.setText("To-Do List");
            case COMPLETED:
                Log.d("nour","   in Switch completed in create view of CHORELIST FRAGMENT");

                choreList = (ArrayList<Chore>) profile.getCompletedChores();
                listTittleField.setText("Completed List");

            case UNASSIGNED:
                Log.d("nour","   in Switch unissgined in create view of CHORELIST FRAGMENT");

                choreList = (ArrayList<Chore>) account.getUnassignedChores();
                listTittleField.setText("General List");

        }


        ListView listView =  this.view.findViewById(R.id.GeneralChoresList_ListView_HomePage);
        ChoreCustomAdapter adapter = new ChoreCustomAdapter(getContext(), choreList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {

                Chore selectedChore = (Chore) parent.getItemAtPosition(position);

                String choreID = selectedChore.getStringId();


                Intent launchChoreEdit = new Intent(getContext(), EditViewChoreActivity.class);
                launchChoreEdit.putExtra("Chore_ID", choreID);
                startActivity(launchChoreEdit);

            }
        });
        super.onResume();
    }
}
