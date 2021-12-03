package com.example.friendfinderapp;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class HomeSeeAllFragment extends Fragment implements EventAdapter.OnEventListener {

    private ArrayList<Category> categories;
    private ArrayList<Event> events;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_see_all, container, false);

        // init
        NavController navController = Navigation.findNavController((Activity) view.getContext(), R.id.fragment);

        ImageView btn_back_to_home = view.findViewById(R.id.btn_back_to_home);

        // event
        // back to home
        btn_back_to_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.homeFragment);
            }
        });

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

        return view;
    }

    // add category item
    private void addCategoryItem() {
        categories = new ArrayList<>();
        categories.add(new Category("Place", R.mipmap.place));
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

    @Override
    public void onEventClick(int position) {
        Toast.makeText(this.getContext(), "Selected" + events.get(position).getEvent_name(), Toast.LENGTH_SHORT).show();
    }
}