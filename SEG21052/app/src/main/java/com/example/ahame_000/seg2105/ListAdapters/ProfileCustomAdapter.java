package com.example.ahame_000.seg2105.ListAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ahame_000.seg2105.DataStructures.Profile;
import com.example.ahame_000.seg2105.R;

import java.util.List;

/**
 * Created by Jack on 2017-11-30.
 */

public class ProfileCustomAdapter extends ArrayAdapter {

    private Context context;
    private List<Profile> profiles;

    public ProfileCustomAdapter(Context context, List<Profile> profiles) {
        super(context, R.layout.profile_row_layout, profiles);
        this.context = context;
        this.profiles = profiles;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Loading the profile row information
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.profile_row_layout, parent, false);

        // Getting the objects from the profile row
        TextView profileName = rowView.findViewById(R.id.tvProfileNameDisplay);
        TextView profilePoints = rowView.findViewById(R.id.tvProfilePointsDisplay);

        // Setting the text of the objects for the current profile row
        profileName.setText(this.profiles.get(position).getName());
        profilePoints.setText(String.valueOf(this.profiles.get(position).getPoints()));

        // Returning the datafilled row
        return rowView;
    }
}
