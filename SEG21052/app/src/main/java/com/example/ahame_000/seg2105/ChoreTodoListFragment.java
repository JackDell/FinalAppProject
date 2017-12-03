package com.example.ahame_000.seg2105;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class ChoreTodoListFragment extends Fragment {

    private View view;


    /**
     * Brings user to view of list of to-do chores for logged in profile
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return view
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.chore_todo_list_layout,container,false);





        TextView listTittleField =  this.view.findViewById(R.id.ListTitle_TextView_TodoList);
        listTittleField.setText("To-Do List");


        Profile profile = Session.getViewedChild();
        if (profile == null)
            profile = Session.getLoggedInProfile();
        Account account = Session.getLoggedInAccount();
        ArrayList<Chore> choreList = (ArrayList<Chore>) profile.getTodoChores();


        ListView listView =  this.view.findViewById(R.id.ChoresList_ListView_TodoList);
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
}
