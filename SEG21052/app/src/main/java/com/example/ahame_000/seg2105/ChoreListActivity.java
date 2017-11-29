package com.example.ahame_000.seg2105;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class ChoreListActivity extends AppCompatActivity {

    private ListView generalList ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_chore_list);

        String[] choreList = {"Walk Dog", "Do the Dishes", "Clean Room", "Make Bed", "Take Trash Out"};
        ListView listView = (ListView) findViewById(R.id.GeneralChoresList_ListView_HomePage);
        ChoreCustomAdapter adapter = new ChoreCustomAdapter(this,choreList);
        listView.setAdapter(adapter);

       /* generalList = (ListView) findViewById( R.id.generalList);
        String[] chores ={ "clean bathroom", "wash dishes"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, chores);

        generalList.setAdapter(adapter);
        */


    }
}
