package com.example.friendfinderapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EventFragment extends Fragment implements EventAdapter.OnEventListener {

    private ArrayList<Event> events;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event, container, false);
        // event class
        addEventItem();
        RecyclerView recyclerViewEvent = view.findViewById(R.id.recycle_view_event);
        EventAdapter eventAdapter = new EventAdapter(events, this);
        recyclerViewEvent.setAdapter(eventAdapter);
        RecyclerView.LayoutManager layoutManagerEvent = new LinearLayoutManager(view.getContext());
        recyclerViewEvent.setLayoutManager(layoutManagerEvent);
        return view;
    }

    @Override
    public void onEventClick(int position) {
        Intent intent = new Intent(this.getContext(), Event_DetailEvent.class);
        intent.putExtra("event_name", events.get(position).getEvent_name());
        intent.putExtra("event_date", events.get(position).getEvent_date());
        intent.putExtra("event_image", events.get(position).getEvent_image());
        startActivity(intent);
    }

    // add new event item
    private void addEventItem() {
        events = new ArrayList<>();
        events.add(new Event("Mobile Legend Tournament", "12 Desember 2021", R.mipmap.event1));
        events.add(new Event("Education Center", "21 Agustus 2021", R.mipmap.event2));
        events.add(new Event("Just Hangout", "22 April 2021", R.mipmap.event3));
    }
}