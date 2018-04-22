package com.example.gebruiker.friendsr;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;



public class ProfileActivity extends AppCompatActivity {

    private class RatingBarChangeListener implements RatingBar.OnRatingBarChangeListener {
        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            SharedPreferences prefs = getSharedPreferences("settings", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putFloat("rating", ratingBar.getRating());
            editor.apply();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent = getIntent();
        Friend retrievedFriend = (Friend) intent.getSerializableExtra("clicked_friend");

        ImageView profpicture = (ImageView) findViewById(R.id.profpicture);
        profpicture.setImageDrawable(getResources().getDrawable(retrievedFriend.getDrawableId()));

        TextView tvName = (TextView) findViewById(R.id.profname);
        tvName.setText(retrievedFriend.getName());

        TextView tvBio = (TextView) findViewById(R.id.bio);
        tvBio.setText(retrievedFriend.getBio());

        RatingBar rb = findViewById(R.id.score);
        rb.setOnRatingBarChangeListener(new RatingBarChangeListener());

        SharedPreferences prefs = getSharedPreferences("settings", MODE_PRIVATE);
        Float storedRating = prefs.getFloat("rating", 0);

        if (storedRating != null) {
            // we have something stored under "example_key"
            rb.setRating(storedRating);
        }
        else {
            // there is nothing stored under "example_key"
            rb.setRating(0);
        }

    }
}
