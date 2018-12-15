package com.example.ivode.friendsr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.RatingBar;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // receive intent
        Intent intent = getIntent();
        Friend retrievedFriend = (Friend) intent.getSerializableExtra("clicked_friend");

        // create rating bar
        RatingBar rating_bar = findViewById(R.id.rating_bar);
        RatingBarListener rating_barListener = new RatingBarListener();
        rating_bar.setOnRatingBarChangeListener(rating_barListener);

        String friend_name = retrievedFriend.getName();
        String friend_description = retrievedFriend.getDescription();
        int image_id = retrievedFriend.getImageId();

        String key = friend_name + "Rating";

        SharedPreferences prefs = getSharedPreferences("settings", MODE_PRIVATE);
        Float rating = prefs.getFloat(key, 0.0f);

        if (rating != 0.0f) {
            rating_bar.setRating(rating);
        }
        else {
            rating_bar.setRating(0.0f);
        }

        ImageView image = findViewById(R.id.image);
        image.setImageResource(image_id);

        TextView name = findViewById(R.id.name);
        TextView description = findViewById(R.id.description);
        name.setText(friend_name);
        description.setText(friend_description);
    }

    // rating bar listener
    private class RatingBarListener implements RatingBar.OnRatingBarChangeListener {
        @Override
        public void onRatingChanged(RatingBar rating_bar, float rating, boolean fromUser) {
            TextView name = findViewById(R.id.name);
            String key = name.getText() + "Rating";
            SharedPreferences.Editor editor = getSharedPreferences("settings", MODE_PRIVATE).edit();
            editor.putFloat(key, rating);
            editor.apply();
            System.out.println(key);
        }
    }
}
