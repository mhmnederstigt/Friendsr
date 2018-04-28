package com.example.gebruiker.friendsr;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        Intent intent = getIntent();
        friends = (ArrayList<Friend>) intent.getSerializableExtra("friends");
        Button add = (Button) findViewById(R.id.add);
        add.setOnClickListener(new AddOnClickListener());
    }

    private class AddOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            TextView nameInput = (TextView) findViewById(R.id.name);
            TextView bioInput = (TextView) findViewById(R.id.bio);
            RatingBar ratingInput = (RatingBar) findViewById(R.id.rating);
            Friend addedFriend = new Friend(nameInput.getText().toString(),bioInput.getText().toString(), getResources().getIdentifier("arya","drawable", getPackageName()));
            addedFriend.setRating(ratingInput.getRating());

            Intent intent = new Intent(InputActivity.this, MainActivity.class);
            intent.putExtra("added_friend", addedFriend);
            intent.putExtra("friends", friends);
            startActivity(intent);
        }
    }
}
