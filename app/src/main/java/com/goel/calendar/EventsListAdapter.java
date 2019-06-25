package com.goel.calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class EventsListAdapter extends ArrayAdapter<Event> {
    private ArrayList<Event> events;

    public EventsListAdapter(Context context, ArrayList<Event> events) {
        super(context, R.layout.event_item);
        this.events = events;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.event_item, null, true);
        }

        TextView dateView = convertView.findViewById(R.id.dateView);
        Event currentEvent = this.getItem(position);
        dateView.setText(currentEvent.date);
        TextView descriptionView = convertView.findViewById(R.id.descriptionView);
        descriptionView.setText(currentEvent.description);

        return convertView;
    }

    @Override
    public Event getItem(int position) {
        return this.events.get(position);
    }

    @Override
    public int getCount() {
        return this.events.size();
    }
}
