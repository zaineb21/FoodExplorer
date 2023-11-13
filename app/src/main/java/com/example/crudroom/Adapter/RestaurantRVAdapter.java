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
import com.example.crudroom.model.RecipeModal;
import com.example.crudroom.model.RestaurantModal;

public class RestaurantRVAdapter extends ListAdapter<RestaurantModal, RestaurantRVAdapter.ViewHolder> {

    // creating a variable for on item click listener.
    private RestaurantRVAdapter.OnItemClickListener listener;

    // creating a constructor class for our adapter class.
    public RestaurantRVAdapter() {
        super(DIFF_CALLBACK);
    }

    // creating a call back for item of recycler view.
    private static final DiffUtil.ItemCallback<RestaurantModal> DIFF_CALLBACK = new DiffUtil.ItemCallback<RestaurantModal>() {
        @Override
        public boolean areItemsTheSame(RestaurantModal oldItem, RestaurantModal newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(RestaurantModal oldItem, RestaurantModal newItem) {
            // below line is to check the course name, description and course duration.
            return oldItem.getName().equals(newItem.getName()) &&
                    oldItem.getAdress().equals(newItem.getAdress()) &&
                    oldItem.getCuisineType().equals(newItem.getCuisineType());
        }
    };

    @NonNull
    @Override
    public RestaurantRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // below line is use to inflate our layout
        // file for each item of our recycler view.
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.restaurant_rv_item, parent, false);
        return new RestaurantRVAdapter.ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantRVAdapter.ViewHolder holder, int position) {
        // below line of code is use to set data to
        // each item of our recycler view.
        RestaurantModal model = getCourseAt(position);
        holder.courseNameTV.setText(model.getName());
        holder.courseDescTV.setText(model.getAdress());
        holder.courseDurationTV.setText(model.getCuisineType());
    }

    // creating a method to get course modal for a specific position.
    public RestaurantModal getCourseAt(int position) {
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
        void onItemClick(RestaurantModal model);
    }
    public void setOnItemClickListener(RestaurantRVAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }
}
