package com.example.calendarapp;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Custome ListView for Placeable objects.
 * Used for having custom callbacks for Placeables (not implemented)
 * @author rbutler
 *
 */
public class PlaceableListView extends ListView {


	public PlaceableListView(Context context) {
		super(context);
	}

	public PlaceableListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public PlaceableListView(Context context, AttributeSet attrs, 
			int defStyle) {
		super(context, attrs, defStyle);
	}


}
