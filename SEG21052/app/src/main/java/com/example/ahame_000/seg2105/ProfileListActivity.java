package com.example.ahame_000.seg2105;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.ahame_000.seg2105.databasing.DatabaseHelper;
import com.example.ahame_000.seg2105.databasing.DatabaseManager;

import java.util.List;
import android.widget.AdapterView;
import android.view.View;
import android.content.Intent;

public class ProfileListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_list);

        DatabaseManager DM = new DatabaseManager(new DatabaseHelper(this.getApplicationContext()));

        List<Profile> profiles = DM.getDatabasedProfiles();

        ListView profileList = findViewById(R.id.ProfilesLoginListView);

        ProfileCustomAdapter adapter = new ProfileCustomAdapter(this.getApplicationContext(), DM.getDatabasedProfiles());
        profileList.setAdapter(adapter);

        //when a profile is selected it is set as logged on and choreList is launched
        profileList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {

                Profile selectedProfile = (Profile)parent.getItemAtPosition(position);
                Session.setLoggedInProfile(selectedProfile);

                Intent launchChoreList = new Intent(getApplicationContext(), ChoreListActivity.class);

                startActivity(launchChoreList);

            }
        });

    }
}