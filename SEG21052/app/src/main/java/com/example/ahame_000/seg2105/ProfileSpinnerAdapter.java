package com.example.ahame_000.seg2105;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ahame_000 on 30-Nov-17.
 */

public class ProfileSpinnerAdapter extends ArrayAdapter {
    private Context context;
    private List<Profile> profiles;

    public ProfileSpinnerAdapter(Context context, List<Profile> profiles) {
        super(context, R.layout.profile_row_layout, profiles);
        this.context = context;
        this.profiles = profiles;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.assign_to_profile_item_layout, parent, false);

        TextView profileName = rowView.findViewById(R.id.ProfileName_TextView_AssignToSpinner);

        profileName.setText(this.profiles.get(position).getName());


        return rowView;
    }



}
