package com.example.ahame_000.seg2105;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class ProfileListActivity extends AppCompatActivity {

    private boolean isChildrenViw = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_list);

        // Creating a list for profiles
        final List<Profile> profiles;

        // If a profile is currently not logged in, display a list of all the account profiles
        if (Session.getLoggedInProfile() == null) {

            profiles = Session.getLoggedInAccount().getProfiles();
            isChildrenViw = false;

        }
        // If there is already an account logged in, then this activity is being called by an Adult viewing
        // their children's profiles, so we only load the children of the account
        else {
            profiles = Session.getLoggedInAccount().getChildren();
            isChildrenViw = true;
        }
        ListView profileList = findViewById(R.id.ProfilesLoginListView);

        // Creating a custom profile adapter object
        ProfileCustomAdapter adapter = new ProfileCustomAdapter(this.getApplicationContext(), profiles);
        // Setting the adapter for the profile list to the adapter we made
        profileList.setAdapter(adapter);

        // When a profile is clicked on, an account activity is launched for the profile that was clicked
        profileList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                if (Session.getLoggedInProfile() == null) {
                    Intent intent = new Intent(getApplicationContext(), ProfileLoginActivity.class);
                    intent.putExtra("profileName", profiles.get(position).getName());
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(getApplicationContext(), NavigationActivity.class);
                    Session.setViewedChild((Child)profiles.get(position));
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        // If the profile is viewing a child account, set the viewed child to null on back
        // if they're not viewing a profile, logout the current profile
        if(isChildrenViw) {
            Session.setViewedChild(null);
            super.onBackPressed();
        }
        else {
            Session.logoutAccount();//log out of the account
            Intent intent = new Intent(getApplicationContext(), AccountLoginActivity.class);
            startActivity(intent);
        }

    }
}
