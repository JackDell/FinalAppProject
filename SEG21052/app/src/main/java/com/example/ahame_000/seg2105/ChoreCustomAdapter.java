package com.example.ahame_000.seg2105;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by amelie on 2017-11-20.
 */

public class ChoreCustomAdapter extends ArrayAdapter {

    private final Context context;
    private final String[] myChores;

    public ChoreCustomAdapter(Context context, String[] choreList) {
        super(context, R.layout.chore_item_layout, choreList);
        this.context = context;
        this.myChores = choreList;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.chore_item_layout, parent, false);
        TextView choreNameTextField = (TextView) rowView.findViewById(R.id.ChoreName_TextView_ItemLayout);
        TextView choreDueDateTextField = (TextView) rowView.findViewById(R.id.DueDate_TextView_ItemLayout);
        choreNameTextField.setText(myChores[position]);
        choreDueDateTextField.setText("Due Date");

        return rowView;
    }

}
