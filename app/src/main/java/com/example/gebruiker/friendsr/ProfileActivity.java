package com.example.gebruiker.friendsr;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {
    private Friend retrievedFriend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Retrieve friend from intent and set layout according to info
        Intent intent = getIntent();
        retrievedFriend = (Friend) intent.getSerializableExtra("clicked_friend");

        ImageView profpicture = (ImageView) findViewById(R.id.picture);
        profpicture.setImageDrawable(getResources().getDrawable(retrievedFriend.getDrawableId()));

        TextView tvName = (TextView) findViewById(R.id.name);
        tvName.setText(retrievedFriend.getName());

        TextView tvBio = (TextView) findViewById(R.id.bio);
        tvBio.setText(retrievedFriend.getBio());

        RatingBar rb = findViewById(R.id.score);

        // Create listener on ratingbar
        rb.setOnRatingBarChangeListener(new RatingBarChangeListener());

        // Display ratingbar according to stored rating
        SharedPreferences prefs = getSharedPreferences("settings", MODE_PRIVATE);
        Float storedRating = prefs.getFloat(retrievedFriend.getName(), 0);

        // If friend is created by default in code, and never change by user:
        if (storedRating == null) {
            rb.setRating(retrievedFriend.getRating());

        }
        // If use has changed or created friend
        else {
            rb.setRating(storedRating);
        }
    }

    // If ratingbar is changed, this is saved in shared preferences
    private class RatingBarChangeListener implements RatingBar.OnRatingBarChangeListener {
        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            SharedPreferences prefs = getSharedPreferences("settings", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putFloat(retrievedFriend.getName(), ratingBar.getRating());
            editor.apply();
        }
    }
}
