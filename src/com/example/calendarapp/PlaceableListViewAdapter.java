package com.example.calendarapp;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class PlaceableListViewAdapter extends ArrayAdapter<Placeable>{
	Context context;
	private int break1 = 20;
	private int break2 = 40;
	private int break3 = 60;
	private int break4 = 80;
	
	public PlaceableListViewAdapter(Context context, int resourceId,
            List<Placeable> items) {
        super(context, resourceId, items);
        this.context = context;
    }
 
    /*private view holder class*/
    private class ViewHolder {
        TextView descriptionView;
        TextView startView;
        TextView durationView;
    }
 
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        Placeable placeable = getItem(position);
 
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.placeable_list_text_view, null);
            holder = new ViewHolder();
            holder.descriptionView = (TextView) convertView.findViewById(R.id.placeableListDescriptionView);
            holder.startView = (TextView) convertView.findViewById(R.id.placeableListStartView);
            holder.durationView = (TextView) convertView.findViewById(R.id.placeableListDurationView);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
        int focus = placeable.getHardness();
        int color = context.getResources().getColor(R.color.white); 
        if(focus > break1){
        	if(focus > break2){
        		if(focus > break3){
        			if(focus > break4){
        				color = context.getResources().getColor(R.color.list_blue0);
        			}else{
        				color = context.getResources().getColor(R.color.list_blue1);
        			}
        		}else{
        			color = context.getResources().getColor(R.color.list_blue2);
        		}
        	}else{
        		color = context.getResources().getColor(R.color.list_blue3);
        	}
        }else{
        	color = context.getResources().getColor(R.color.white);
        }
        convertView.setBackgroundColor(color);
        holder.descriptionView.setText(placeable.getDescription());
        holder.startView.setText(placeable.getStart().toString());
        holder.durationView.setText(placeable.getDuration().toString());
 
        return convertView;
    }
}
