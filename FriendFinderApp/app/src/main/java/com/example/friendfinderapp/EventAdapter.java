package com.example.friendfinderapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private ArrayList<Event> events;
    private OnEventListener onEventListener;

    public EventAdapter(ArrayList<Event> events, OnEventListener onEventListener) {
        this.events = events;
        this.onEventListener = onEventListener;
    }

    @NonNull
    @Override
    public EventAdapter.EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.event_item, parent, false);
        return new EventViewHolder(view, onEventListener);
    }

    @Override
    public void onBindViewHolder(@NonNull EventAdapter.EventViewHolder holder, int position) {
        holder.txt_event_name.setText(events.get(position).getEvent_name());
        holder.txt_event_date.setText(events.get(position).getEvent_date());
        holder.img_event_image.setImageResource(events.get(position).getEvent_image());
    }

    @Override
    public int getItemCount() {
        return (events != null) ? events.size() : 0;
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txt_event_name, txt_event_date;
        private ImageView img_event_image;
        OnEventListener onEventListener;
        public EventViewHolder(@NonNull View itemView, OnEventListener onEventListener) {
            super(itemView);

            txt_event_name = itemView.findViewById(R.id.txt_event_name);
            txt_event_date = itemView.findViewById(R.id.txt_event_date);
            img_event_image = itemView.findViewById(R.id.img_event_image);
            this.onEventListener = onEventListener;

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onEventListener.onEventClick(getAdapterPosition());
        }
    }

    public interface OnEventListener {
        void onEventClick(int position);
    }
}
