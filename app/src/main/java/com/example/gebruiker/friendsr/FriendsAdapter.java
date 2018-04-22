package com.example.gebruiker.friendsr;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class FriendsAdapter extends ArrayAdapter<Friend> {
    private ArrayList<Friend> friends;

    public FriendsAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Friend> objects) {
        super(context, resource, objects);

        friends = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.grid_item, parent, false);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.name);
        textView.setText(friends.get(position).getName());
        ImageView picture = (ImageView) convertView.findViewById(R.id.picture);

        picture.setImageDrawable(getContext().getResources().getDrawable(friends.get(position).getDrawableId()));

        return convertView;
    }



}
