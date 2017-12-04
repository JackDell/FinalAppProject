package com.example.ahame_000.seg2105;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.profile_row_layout, parent, false);

        TextView profileName = rowView.findViewById(R.id.tvProfileNameDisplay);
        TextView profilePoints = rowView.findViewById(R.id.tvProfilePointsDisplay);

        profileName.setText(this.profiles.get(position).getName());
        profilePoints.setText(String.valueOf(this.profiles.get(position).getPoints()));

        return rowView;
    }
}
