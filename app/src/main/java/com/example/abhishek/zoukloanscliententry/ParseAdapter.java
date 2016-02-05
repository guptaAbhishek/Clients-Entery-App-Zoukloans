package com.example.abhishek.zoukloanscliententry;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.parse.ParseObject;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by abhishek on 29/01/16.
 */
public class ParseAdapter extends ArrayAdapter<ParseObject> {

    public ParseAdapter(Context context, ArrayList<ParseObject> list) {
        super(context, 0,list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ParseObject user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.simple_list_item_1, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView phone = (TextView) convertView.findViewById(R.id.phone);
        // Populate the data into the template view using the data object
        if(user!=null){
            tvName.setText(user.getString("company_name"));
            phone.setText(user.getString("phone"));
        }

        // Return the completed view to render on screen
        return convertView;
    }

}
