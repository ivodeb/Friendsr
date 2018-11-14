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
        String[] descriptions = {"Not today.", "Everyone who isn’t us is an enemy.", "I have way too many titles.",
                "The things I do for love...", "I know nothing.", "No one can survive in this world without help.", "I want to be the queen.",
                "The night is dark and full of terrors.", "Winter is coming.", "Where is the god of tits and wine?"};
        int friend_number = 0;
        for (String name : names) {
            int id = getResources().getIdentifier(name.toLowerCase(), "drawable", getPackageName());
            Friend friend = new Friend(name, descriptions[friend_number], id);
            friends.add(friend);
            friend_number++;
        }

        FriendsAdapter adapter = new FriendsAdapter(this, R.layout.grid_item, friends);
        GridView grid = findViewById(R.id.gridView);
        GridItemClickListener gridListener = new GridItemClickListener();

        grid.setAdapter(adapter);
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
