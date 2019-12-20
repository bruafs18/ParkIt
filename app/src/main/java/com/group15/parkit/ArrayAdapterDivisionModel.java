package com.group15.parkit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ArrayAdapterDivisionModel extends ArrayAdapter<Division_Model> implements View.OnClickListener {

    private ArrayList<Division_Model> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView lblName;
        TextView lblSpots;
    }

    public ArrayAdapterDivisionModel(ArrayList<Division_Model> data, Context context) {
        super(context, R.layout.division_item, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        Division_Model dataModel=(Division_Model)object;

    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Division_Model dataModel = dataSet.get(position);


        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder;


        if (convertView == null) {
            viewHolder = new ViewHolder();

            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.division_item, parent, false);

            viewHolder.lblName = (TextView) convertView.findViewById(R.id.name);
            viewHolder.lblSpots = (TextView) convertView.findViewById(R.id.spots);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        lastPosition = position;

        viewHolder.lblName.setText(dataModel.getName());
        String HasUnknown = "";
        if(dataModel.AnyUnkown)
            HasUnknown="?";
        String textLblSpots = dataModel.getAvailable()+"/"+dataModel.getTotal()+HasUnknown;
        viewHolder.lblSpots.setText(textLblSpots);
        // Return the completed view to render on screen
        return convertView;
    }
}
