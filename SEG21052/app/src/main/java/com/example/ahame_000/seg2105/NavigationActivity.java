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
import android.view.MenuItem;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        // Setting up the nav
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView =  findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Determining what functions to load depending of if Child or Adult
        if(Session.getLoggedInProfile() instanceof Child){
            navigationView.getMenu().findItem(R.id.nav_adult_options).setVisible(false);
        }


        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame,new ChoreGeneralListFragment()).commit();



    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Session.setViewedChild(null);

            Session.logoutProfile();
            Intent intent = new Intent(this, ProfileListActivity.class);
            startActivity(intent);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Determining what was clicked on in the navigation, and navigating there
        int id = item.getItemId();
        FragmentManager fragmentManager = getFragmentManager();
        if (id == R.id.nav_completed_list) {
            fragmentManager.beginTransaction().replace(R.id.content_frame,new ChoreCompletedListFragment()).commit();

        } else if (id == R.id.nav_general_list) {
            fragmentManager.beginTransaction().replace(R.id.content_frame,new ChoreGeneralListFragment()).commit();

        } else if (id == R.id.nav_todo_list) {
            fragmentManager.beginTransaction().replace(R.id.content_frame,new ChoreTodoListFragment()).commit();

        } else if (id == R.id.nav_viewOthers) {
            Intent intent = new Intent(this,ProfileListActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_addChore) {
            Intent intent = new Intent(this,CreateChoreActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_addOtherMember){
            Intent intent = new Intent(this,AddMemberActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
