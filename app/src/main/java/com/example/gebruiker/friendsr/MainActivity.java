package com.example.gebruiker.friendsr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Friend> friends = new ArrayList<Friend>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Friend arya = new Friend("Arya","empty", getResources().getIdentifier("arya","drawable", getPackageName()));
        friends.add(arya);
        Friend cersei = new Friend("Cersei","empty", getResources().getIdentifier("cersei","drawable", getPackageName()));
        friends.add(cersei);
        Friend daenerys = new Friend("Cersei","empty", getResources().getIdentifier("cersei","drawable", getPackageName()));
        friends.add(daenerys);
        Friend jaime = new Friend("Cersei","empty", getResources().getIdentifier("cersei","drawable", getPackageName()));
        friends.add(jaime);
        Friend jon = new Friend("Cersei","empty", getResources().getIdentifier("cersei","drawable", getPackageName()));
        friends.add(jon);
        Friend jorah = new Friend("Cersei","empty", getResources().getIdentifier("cersei","drawable", getPackageName()));
        friends.add(jorah);

        FriendsAdapter adapter = new FriendsAdapter(this, R.layout.grid_item, friends);

        GridView gridView = (GridView) findViewById(R.id.dynamic);
        gridView.setAdapter(adapter);



    }
}
