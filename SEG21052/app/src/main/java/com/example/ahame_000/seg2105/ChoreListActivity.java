package com.example.ahame_000.seg2105;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChoreListActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_chore_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ChoreState listType = ChoreState.valueOf(getIntent().getStringExtra("LIST_TYPE"));


        TextView listTittleField = findViewById(R.id.ChoresListTitle_TextView_HomePage);


        Profile profile = Session.getViewedChild();
        if (profile == null)
            profile = Session.getLoggedInProfile();
        Account account = Session.getLoggedInAccount();
        ArrayList<Chore> choreList = (ArrayList<Chore>) account.getUnassignedChores();
        switch (listType) {
            case TODO:
                choreList = (ArrayList<Chore>) profile.getTodoChores();
                listTittleField.setText("To-Do List");
            case COMPLETED:
                choreList = (ArrayList<Chore>) profile.getCompletedChores();
                listTittleField.setText("Completed List");

            case UNASSIGNED:
                choreList = (ArrayList<Chore>) account.getUnassignedChores();
                listTittleField.setText("General List");

        }


        ListView listView = findViewById(R.id.GeneralChoresList_ListView_HomePage);
        ChoreCustomAdapter adapter = new ChoreCustomAdapter(this, choreList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {

                Chore selectedChore = (Chore) parent.getItemAtPosition(position);

                String choreID = selectedChore.getStringId();


                Intent launchChoreEdit = new Intent(getApplicationContext(), EditViewChoreActivity.class);
                launchChoreEdit.putExtra("Chore_ID", choreID);
                startActivity(launchChoreEdit);

            }
        });

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav, menu);
        return true;
    }
*/



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_completed_list) {
            Intent intent = new Intent(this,ChoreListActivity.class);
            intent.putExtra("LIST_TYPE", ChoreState.COMPLETED);
            startActivity(intent);
        } else if (id == R.id.nav_general_list) {
            Intent intent = new Intent(this,ChoreListActivity.class);
            intent.putExtra("LIST_TYPE", ChoreState.UNASSIGNED);
            startActivity(intent);
        } else if (id == R.id.nav_todo_list) {
            Intent intent = new Intent(this,ChoreListActivity.class);
            intent.putExtra("LIST_TYPE", ChoreState.TODO);
            startActivity(intent);
        } else if (id == R.id.nav_viewOthers) {
            //Todo

        } else if (id == R.id.nav_addChore) {
            Intent intent = new Intent(this,CreateChoreActivity.class);
            startActivity(intent);
        }else if (id == R.id.nav_addOtherMember){
            //TODO:
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
