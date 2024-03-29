package com.example.ahame_000.seg2105.ListAdapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ahame_000.seg2105.DataStructures.Chore;
import com.example.ahame_000.seg2105.Helpers.DateHelper;
import com.example.ahame_000.seg2105.R;

import java.util.ArrayList;


public class ChoreCustomAdapter extends ArrayAdapter {

    private final Context context;
    private final ArrayList<Chore> myChores;

    public ChoreCustomAdapter(Context context, ArrayList<Chore> choreList) {
        super(context, R.layout.chore_item_layout, choreList);
        this.context = context;
        this.myChores = choreList;
    }

    /**
     * Populates the list with chore name and due date
     *
     * @param position
     * @param convertView
     * @param parent
     * @return rowView
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.chore_item_layout, parent, false);

        TextView choreNameTextField =  rowView.findViewById(R.id.ChoreName_TextView_ItemLayout);
        TextView choreDueDateTextField =  rowView.findViewById(R.id.DueDate_TextView_ItemLayout);
        if(myChores.get(position).isLate()) {
            choreDueDateTextField.setTextColor(Color.RED);
        }
        choreNameTextField.setText(myChores.get(position).getName());
        choreDueDateTextField.setText(DateHelper.getDateString(myChores.get(position).getDeadline()));

        return rowView;
    }

}
