package com.example.ahame_000.seg2105.ChoreListFragments;

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

import com.example.ahame_000.seg2105.DataStructures.Account;
import com.example.ahame_000.seg2105.Activities.EditViewChoreActivity;
import com.example.ahame_000.seg2105.DataStructures.Chore;
import com.example.ahame_000.seg2105.ListAdapters.ChoreCustomAdapter;
import com.example.ahame_000.seg2105.R;
import com.example.ahame_000.seg2105.Helpers.Session;

import java.util.ArrayList;


public class ChoreGeneralListFragment extends Fragment {

    private View view;

    /**
     * Brings user to view of list of all unassigned chores for logged in account
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return view
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.chore_general_list_layout,container,false);



        TextView listTittleField =  this.view.findViewById(R.id.ChoresListTitle_TextView_GeneralList);

        Account account = Session.getLoggedInAccount();
        ArrayList<Chore> choreList = (ArrayList<Chore>) account.getUnassignedChores();

        ListView listView =  this.view.findViewById(R.id.ChoresList_ListView_GeneralList);
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
