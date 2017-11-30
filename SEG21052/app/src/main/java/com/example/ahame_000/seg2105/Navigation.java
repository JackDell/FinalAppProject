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

public class Navigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
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
            Intent intent = new Intent(this,CreateChore.class);
            startActivity(intent);
        }else if (id == R.id.nav_addOtherMember){
            //TODO:
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
