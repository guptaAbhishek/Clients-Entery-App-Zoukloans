package com.example.abhishek.zoukloanscliententry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.parse.ParseObject;

import java.util.ArrayList;

/**
 * Created by abhishek on 04/02/16.
 */
public class FolderAdapter extends ArrayAdapter<ParseObject> {

    public FolderAdapter(Context context,ArrayList<ParseObject> list) {
        super(context,0, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ParseObject events = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.folder_name, parent, false);
        }
        // Lookup view for data population
        TextView eventName = (TextView) convertView.findViewById(R.id.eventName);
        // Populate the data into the template view using the data object
        if(events!=null){
            eventName.setText(events.getString("eventName"));
        }

        // Return the completed view to render on screen
        return convertView;
    }



}
