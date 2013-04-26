package com.example.calendarapp;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class TodoListViewAdapter extends ArrayAdapter<Todo>{
	Context context;
	
	public TodoListViewAdapter(Context context, int resourceId,
            List<Todo> items) {
        super(context, resourceId, items);
        this.context = context;
    }
 
    /*private view holder class*/
    private class ViewHolder {
        TextView taskView;
        TextView dateView;
        TextView time_reqView;
    }
 
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        Todo todo = getItem(position);
 
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.todo_text_view, null);
            holder = new ViewHolder();
            holder.taskView = (TextView) convertView.findViewById(R.id.taskView);
            holder.dateView = (TextView) convertView.findViewById(R.id.dateView);
            holder.time_reqView = (TextView) convertView.findViewById(R.id.time_reqView);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
 
        holder.taskView.setText(todo.getTodo());
        holder.dateView.setText(todo.getDate());
        holder.time_reqView.setText(""+todo.getTime_req());
 
        return convertView;
    }
}
