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
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        RatingBarListener ratingBarListener = new RatingBarListener();
        ratingBar.setOnRatingBarChangeListener(ratingBarListener);

        String friendName = retrievedFriend.getName();
        String friendBio = retrievedFriend.getBio();
        int drawableId = retrievedFriend.getDrawableId();

        String key = friendName + "Rating";

        SharedPreferences prefs = getSharedPreferences("settings", MODE_PRIVATE);
        Float rating = prefs.getFloat(key, 0.0f);

        if (rating != 0.0f) {
            ratingBar.setRating(rating);
        }
        else {
            ratingBar.setRating(0.0f);
        }

        ImageView image = findViewById(R.id.image);
        image.setImageResource(drawableId);

        TextView name = findViewById(R.id.name);
        TextView bio = findViewById(R.id.bio);
        name.setText(friendName);
        bio.setText(friendBio);
    }
    private class RatingBarListener implements RatingBar.OnRatingBarChangeListener {
        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            TextView name = findViewById(R.id.name);
            String key = name.getText() + "Rating";
            SharedPreferences.Editor editor = getSharedPreferences("settings", MODE_PRIVATE).edit();
            editor.putFloat(key, rating);
            editor.apply();
            System.out.println(key);
        }
    }
}
