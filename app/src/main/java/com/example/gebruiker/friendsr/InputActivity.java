package com.example.gebruiker.friendsr;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class InputActivity extends AppCompatActivity {
    ArrayList<Friend> friends;
    Boolean exists = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        // Retrieve list of friends, so it can be passed back
        Intent intent = getIntent();
        friends = (ArrayList<Friend>) intent.getSerializableExtra("friends");
        Button add = (Button) findViewById(R.id.add);
        add.setOnClickListener(new AddOnClickListener());
    }

    private class AddOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            // Get user input
            TextView nameInput = (TextView) findViewById(R.id.name);
            TextView bioInput = (TextView) findViewById(R.id.bio);
            RatingBar ratingInput = (RatingBar) findViewById(R.id.rating);

            // Iterate over friends' names and check if already exists
            for (int i=0; i<friends.size(); i++) {
                String friendI = friends.get(i).getName();
                String friendInput = String.valueOf(nameInput.getText());

                if (friendI.equalsIgnoreCase(friendInput)) {
                    exists = true;
                }
            }

            if (!exists) {
                // Store user input in new friend object
                Friend addedFriend = new Friend(nameInput.getText().toString(), bioInput.getText().toString(), getResources().getIdentifier("anonymous", "drawable", getPackageName()));

                // Store rating separately in SharedPreferences
                SharedPreferences prefs = getSharedPreferences("settings", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putFloat(addedFriend.getName(), ratingInput.getRating());
                editor.apply();

                // Pass friend object to MainActivity
                Intent intent = new Intent(InputActivity.this, MainActivity.class);
                intent.putExtra("added_friend", addedFriend);
                intent.putExtra("friends", friends);
                startActivity(intent);
            }

            else if (exists) {
                nameInput.setText("Name already exists");
                nameInput.setTextColor(Color.RED);
                exists = false;
            }
        }
    }
}
