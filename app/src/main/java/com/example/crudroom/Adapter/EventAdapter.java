package com.example.crudroom.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crudroom.R;
import com.example.crudroom.model.EventModal;


public class EventAdapter extends ListAdapter<EventModal, EventAdapter.ViewHolder> {

    // creating a variable for on item click listener.
    private EventAdapter.OnItemClickListener listener;

    // creating a constructor class for our adapter class.
    public EventAdapter() {
        super(DIFF_CALLBACK);
    }

    // creating a call back for item of recycler view.
    private static final DiffUtil.ItemCallback<EventModal> DIFF_CALLBACK = new DiffUtil.ItemCallback<EventModal>() {
        @Override
        public boolean areItemsTheSame(EventModal oldItem, EventModal newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(EventModal oldItem, EventModal newItem) {
            // below line is to check the course name, description and course duration.
            return oldItem.getEventName().equals(newItem.getEventName()) &&
                    oldItem.getDescription().equals(newItem.getDescription()) &&
                    oldItem.getType().equals(newItem.getType());
        }
    };

    @NonNull
    @Override
    public EventAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // below line is use to inflate our layout
        // file for each item of our recycler view.
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_rv_item, parent, false);
        return new EventAdapter.ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull EventAdapter.ViewHolder holder, int position) {
        // below line of code is use to set data to
        // each item of our recycler view.
        EventModal model = getCourseAt(position);
        holder.courseNameTV.setText(model.getEventName());
        holder.courseDescTV.setText(model.getDescription());
        holder.courseDurationTV.setText(model.getType());
    }

    // creating a method to get course modal for a specific position.
    public EventModal getCourseAt(int position) {
        return getItem(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // view holder class to create a variable for each view.
        TextView courseNameTV, courseDescTV, courseDurationTV;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing each view of our recycler view.
            courseNameTV = itemView.findViewById(R.id.idTVCourseName);
            courseDescTV = itemView.findViewById(R.id.idTVCourseDescription);
            courseDurationTV = itemView.findViewById(R.id.idTVCourseDuration);

            // adding on click listener for each item of recycler view.
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // inside on click listener we are passing
                    // position to our item of recycler view.
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(EventModal model);
    }
    public void setOnItemClickListener(EventAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }
}

