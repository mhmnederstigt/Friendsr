package com.example.gebruiker.friendsr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Friend> friends = new ArrayList<Friend>();
    FriendsAdapter adapter;
    GridView gv;

    private class GridItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Friend clickedFriend = (Friend) parent.getItemAtPosition(position);

            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            intent.putExtra("clicked_friend", clickedFriend);
            startActivity(intent);
        }
    }

    private class AddOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, InputActivity.class);
            startActivity(intent);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create friend objects and add to list
        Friend arya = new Friend("Arya","A girl is Arya Stark of Winterfell, and I'm going home.", getResources().getIdentifier("arya","drawable", getPackageName()));
        friends.add(arya);
        Friend cersei = new Friend("Cersei","When you play the game of thrones, you win or you die. There is no middle ground.", getResources().getIdentifier("cersei","drawable", getPackageName()));
        friends.add(cersei);
        Friend daenerys = new Friend("Daenerys","So many men have tried to kill me, I don't remember all their names.", getResources().getIdentifier("daenerys","drawable", getPackageName()));
        friends.add(daenerys);
        Friend jaime = new Friend("Jaime","There are no men like me. Only me.", getResources().getIdentifier("jaime","drawable", getPackageName()));
        friends.add(jaime);
        Friend jon = new Friend("Jon","He's never been a bastard. He's the heir to the Iron Throne.", getResources().getIdentifier("jon","drawable", getPackageName()));
        friends.add(jon);
        Friend jorah = new Friend("Jorah","empty", getResources().getIdentifier("jorah","drawable", getPackageName()));
        friends.add(jorah);
        Friend margaery = new Friend("Margeary","empty", getResources().getIdentifier("margaery","drawable", getPackageName()));
        friends.add(margaery);
        Friend melisandre = new Friend("Melisandre","empty", getResources().getIdentifier("melisandre","drawable", getPackageName()));
        friends.add(melisandre);
        Friend sansa = new Friend("Sansa","empty", getResources().getIdentifier("sansa","drawable", getPackageName()));
        friends.add(sansa);
        Friend tyrion = new Friend("Tyrion","empty", getResources().getIdentifier("tyrion","drawable", getPackageName()));
        friends.add(tyrion);

        // Create adapter and set layout accordingly
        adapter = new FriendsAdapter(this, R.layout.grid_item, friends);
        gv = (GridView) findViewById(R.id.dynamic);
        gv.setAdapter(adapter);
        gv.setOnItemClickListener(new GridItemClickListener());

        Button add = (Button) findViewById(R.id.add);
        add.setOnClickListener(new AddOnClickListener());

    }

    @Override
    protected void onResume(){
        super.onResume();

        // Check if a friend is added in intent
        Intent intent = getIntent();
        Friend addedFriend = (Friend) intent.getSerializableExtra("added_friend");
        if (addedFriend != null) {
            friends.add(addedFriend);
//            getIntent().removeExtra("added_friend");
        }

        // Update adapter and set layout accordingly
        adapter = new FriendsAdapter(this, R.layout.grid_item, friends);
        gv.setAdapter(adapter);
    }
}
