package com.example.friendfinderapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements EventAdapter.OnEventListener {

    private List<ThumbnailEvent> thumbnailEvents = new ArrayList<>();
    private ArrayList<Category> categories;
    private List<Event> events = new ArrayList<>();
    private ArrayList<ThumbnailPlace> thumbnailPlaces;

    // recycler view init
    RecyclerView recyclerViewEvent, recyclerViewThumbnailEvent;

    // url for get all event data
    public static final String EVENT_URL = "http://192.168.1.16/friend-finder/public/API/getAllEvent";
    public static final String THUMBNAIL_EVENT_URL = "http://192.168.1.16/friend-finder/public/API/getEventThumbnail";
    public static String username;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // init
        TextView tvNama = view.findViewById(R.id.tvNama);
        if (username.length() > 0) {
            tvNama.setText(username);
        }
        TextView txt_link_seeAll_event = view.findViewById(R.id.txt_link_seeAll_event);
        txt_link_seeAll_event.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController((Activity) view.getContext(), R.id.fragment);
            navController.navigate(R.id.homeSeeAllFragment2);
        });

        // thumbnail event
        addEventThumbnailItem();
        recyclerViewThumbnailEvent = view.findViewById(R.id.recycle_view_event_thumbnail);
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
        recyclerViewEvent = view.findViewById(R.id.recycle_view_event);
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
        StringRequest stringRequest = new StringRequest(Request.Method.GET, THUMBNAIL_EVENT_URL, response -> {
            try {
                JSONArray thumbnailEventArray = new JSONArray(response);
                for (int i = 0; i < thumbnailEventArray.length(); i++) {
                    JSONObject thumbnailEventsJSONObject = thumbnailEventArray.getJSONObject(i);
                    int id = thumbnailEventsJSONObject.getInt("id");
                    String name_event = thumbnailEventsJSONObject.getString("name_event");
                    String event_start_date = thumbnailEventsJSONObject.getString("event_start_date");
                    String event_picture = thumbnailEventsJSONObject.getString("event_picture");
                    String category = thumbnailEventsJSONObject.getString("category");

                    ThumbnailEvent thumbnailEvent = new ThumbnailEvent(name_event, event_start_date, category, event_picture, id);
                    thumbnailEvents.add(thumbnailEvent);
                }
                ThumbnailEventAdapter thumbnailEventAdapter = new ThumbnailEventAdapter(thumbnailEvents);
                recyclerViewThumbnailEvent.setAdapter(thumbnailEventAdapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }, error -> Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show());

        Volley.newRequestQueue(getContext()).add(stringRequest);
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
        StringRequest stringRequest = new StringRequest(Request.Method.GET, EVENT_URL, response -> {
            try {
                JSONArray eventArray = new JSONArray(response);
                for (int i = 0; i < eventArray.length(); i++) {
                    JSONObject eventsJSONObject = eventArray.getJSONObject(i);
                    int id = eventsJSONObject.getInt("id");
                    String name_event = eventsJSONObject.getString("name_event");
                    String event_start_date = eventsJSONObject.getString("event_start_date");
                    String event_picture = eventsJSONObject.getString("event_picture");

                    Event event = new Event(name_event, event_picture, event_start_date, id);
                    events.add(event);
                }
                EventAdapter eventAdapter = new EventAdapter(events, HomeFragment.this);
                recyclerViewEvent.setAdapter(eventAdapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }, error -> Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show());

        Volley.newRequestQueue(getContext()).add(stringRequest);
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