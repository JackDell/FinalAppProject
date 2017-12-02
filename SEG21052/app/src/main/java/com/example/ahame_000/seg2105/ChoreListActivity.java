package com.example.ahame_000.seg2105;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

public class ChoreListActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_chore_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView =  findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav, menu);
        return true;
    }




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        Log.d("nour","   in S onNavigationItemSelected of ChoreListAvtivity");

        int id = item.getItemId();
        FragmentManager fragmentManager = getFragmentManager();
        if (id == R.id.nav_completed_list) {
            ChoreListFragment fragment = new ChoreListFragment();
            fragment.setListType(ChoreState.COMPLETED);
            fragmentManager.beginTransaction().replace(R.id.content_frame,fragment).commit();

        } else if (id == R.id.nav_general_list) {
            ChoreListFragment fragment = new ChoreListFragment();
            fragment.setListType(ChoreState.UNASSIGNED);
            fragmentManager.beginTransaction().replace(R.id.content_frame,fragment).commit();
        } else if (id == R.id.nav_todo_list) {
            ChoreListFragment fragment = new ChoreListFragment();
            fragment.setListType(ChoreState.TODO);
            fragmentManager.beginTransaction().replace(R.id.content_frame,fragment).commit();
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
