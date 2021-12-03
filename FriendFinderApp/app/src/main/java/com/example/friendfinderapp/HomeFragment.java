package com.example.friendfinderapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements EventAdapter.OnEventListener{

    private ArrayList<ThumbnailEvent> thumbnailEvents;
    private ArrayList<Category> categories;
    private ArrayList<Event> events;
    private ArrayList<ThumbnailPlace> thumbnailPlaces;
    private TextView txt_link_seeAll_event;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // init
        txt_link_seeAll_event = view.findViewById(R.id.txt_link_seeAll_event);
        txt_link_seeAll_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController((Activity) view.getContext(), R.id.fragment);
                navController.navigate(R.id.homeSeeAllFragment2);
            }
        });

        // thumbnail event
        addEventThumbnailItem();
        RecyclerView recyclerViewThumbnailEvent = view.findViewById(R.id.recycle_view_event_thumbnail);
        ThumbnailEventAdapter thumbnailEventAdapter = new ThumbnailEventAdapter(thumbnailEvents);
        recyclerViewThumbnailEvent.setAdapter(thumbnailEventAdapter);
        RecyclerView.LayoutManager layoutManagerThumbnailEven = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewThumbnailEvent.setLayoutManager(layoutManagerThumbnailEven);

        // categories
        addCategoryItem();
        RecyclerView recyclerViewCategories = view.findViewById(R.id.recycle_view_category);
        CategoryAdapter categoryAdapter = new CategoryAdapter(categories);
        recyclerViewCategories.setAdapter(categoryAdapter);
        RecyclerView.LayoutManager layoutManagerCategories = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategories.setLayoutManager(layoutManagerCategories);

        // event class
        addEventItem();
        RecyclerView recyclerViewEvent = view.findViewById(R.id.recycle_view_event);
        EventAdapter eventAdapter = new EventAdapter(events, this);
        recyclerViewEvent.setAdapter(eventAdapter);
        RecyclerView.LayoutManager layoutManagerEvent = new LinearLayoutManager(view.getContext());
        recyclerViewEvent.setLayoutManager(layoutManagerEvent);

        // thumbnail places
        addThumbnailPlaceItem();
        RecyclerView recyclerViewThumbnailPlace = view.findViewById(R.id.recycle_view_place_thumbnail);
        RecyclerView.LayoutManager layoutManagerThumbnailPlace = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewThumbnailPlace.setLayoutManager(layoutManagerThumbnailPlace);
        ThumbnailPlaceAdapter thumbnailPlaceAdapter = new ThumbnailPlaceAdapter(thumbnailPlaces);
        recyclerViewThumbnailPlace.setAdapter(thumbnailPlaceAdapter);


        return view;
    }


    // add thumbnail event item
    private void addEventThumbnailItem() {
        thumbnailEvents = new ArrayList<>();
        thumbnailEvents.add(new ThumbnailEvent("Mobile Legend Tournament", "12 Desember 2021", "Sport", R.mipmap.event1));
        thumbnailEvents.add(new ThumbnailEvent("Education Tech Titan", "21 January 2022", "Sport", R.mipmap.event2));
        thumbnailEvents.add(new ThumbnailEvent("Hangout On Me!", "22 April 2021", "Sport", R.mipmap.event3));
    }

    // add category item
    private void addCategoryItem() {
        categories = new ArrayList<>();
        categories.add(new Category("Sport", R.mipmap.category));
        categories.add(new Category("Education", R.mipmap.category1));
        categories.add(new Category("Hangout", R.mipmap.category2));
        categories.add(new Category("Vacation", R.mipmap.category3));
    }

    // add event item
    private void addEventItem() {
        events = new ArrayList<>();
        events.add(new Event("Mobile Legend Tournament", "12 Desember 2021", R.mipmap.event1));
        events.add(new Event("Education Center", "21 Agustus 2021", R.mipmap.event2));
        events.add(new Event("Just Hangout", "22 April 2021", R.mipmap.event3));
    }

    // add thumbnail place item
    private void addThumbnailPlaceItem() {
        thumbnailPlaces = new ArrayList<>();
        thumbnailPlaces.add(new ThumbnailPlace("Lapangan Antaraja", "Jl. Sudirman", 100, R.mipmap.place1));
        thumbnailPlaces.add(new ThumbnailPlace("Lapangan Singasari", "Jl. Jember", 90, R.mipmap.place2));
        thumbnailPlaces.add(new ThumbnailPlace("Lapangan Andromeda", "Jl. Pandjaitan", 40, R.mipmap.place3));
    }

    // event click
    @Override
    public void onEventClick(int position) {
        Intent intent = new Intent(this.getContext(), DetailEvent.class);
        intent.putExtra("event_name", events.get(position).getEvent_name());
        intent.putExtra("event_date", events.get(position).getEvent_date());
        intent.putExtra("event_image", events.get(position).getEvent_image());
        startActivity(intent);
    }
}