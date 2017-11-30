package com.example.ahame_000.seg2105;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.ahame_000.seg2105.databasing.DatabaseHelper;
import com.example.ahame_000.seg2105.databasing.DatabaseManager;

import java.util.List;

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
    }
}
