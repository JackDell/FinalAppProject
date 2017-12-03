package com.example.ahame_000.seg2105;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class ProfileListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_list);

        List<Profile> profiles = Session.getLoggedInAccount().getProfiles();
        ListView profileList = findViewById(R.id.ProfilesLoginListView);

        ProfileCustomAdapter adapter = new ProfileCustomAdapter(this.getApplicationContext(), profiles);
        profileList.setAdapter(adapter);

        //when a profile is selected it is set as logged on and choreList is launched
        profileList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {

                Profile selectedProfile = (Profile) parent.getItemAtPosition(position);

                Intent launchProfileLogin = new Intent(getApplicationContext(), ProfileLoginActivity.class);
                launchProfileLogin.putExtra("profileName", selectedProfile.getName());
                startActivity(launchProfileLogin);
            }
        });

    }
}
