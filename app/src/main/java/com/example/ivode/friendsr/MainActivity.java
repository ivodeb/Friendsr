package com.example.ivode.friendsr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.RatingBar;
import android.content.Intent;


public class MainActivity extends AppCompatActivity {
    ArrayList<Friend> friends = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] names = {"Arya", "Cersei", "Daenerys", "Jaime", "Jon", "Jorah",
                "Margaery", "Melisandre", "Sansa", "Tyrion"};
        String[] bio = {"Arya description", "Cersei description", "Daenerys description",
                "Jaime description", "Jon description", "Jorah description", "Margaery description",
                "Melisandre description", "Sansa description", "Tyrion description"};
        int counter = 0;
        for (String name : names) {
            int id = getResources().getIdentifier(name.toLowerCase(), "drawable", getPackageName());
            Friend friend = new Friend(name, bio[counter], id);
            friends.add(friend);
            counter++;
        }

        FriendsAdapter adapter = new FriendsAdapter(this, R.layout.grid_item, friends);
        GridView grid = findViewById(R.id.gridView);
        grid.setAdapter(adapter);
        GridItemClickListener gridListener = new GridItemClickListener();
        grid.setOnItemClickListener(gridListener);
    }

    private class GridItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Friend clickedFriend = (Friend) parent.getItemAtPosition(position);

            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            intent.putExtra("clicked_friend", clickedFriend);
            startActivity(intent);
        }
    }
}
