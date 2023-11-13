package com.example.restaurent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class RestaurantRVAdapter extends ListAdapter<RestaurantModal, RestaurantRVAdapter.ViewHolder> {

    // Creating a variable for on item click listener.
    private OnItemClickListener listener;

    // Creating a constructor class for our adapter class.
    RestaurantRVAdapter() {
        super(DIFF_CALLBACK);
    }

    // Creating a callback for items of the recycler view.
    private static final DiffUtil.ItemCallback<RestaurantModal> DIFF_CALLBACK = new DiffUtil.ItemCallback<RestaurantModal>() {
        @Override
        public boolean areItemsTheSame(RestaurantModal oldItem, RestaurantModal newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(RestaurantModal oldItem, RestaurantModal newItem) {
            // Below line is to check the restaurant name, cuisine type, and address.
            return oldItem.getName().equals(newItem.getName()) &&
                    oldItem.getCuisineType().equals(newItem.getCuisineType()) &&
                    oldItem.getAddress().equals(newItem.getAddress());
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Below line is used to inflate our layout
        // file for each item of our recycler view.
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.restaurant_rv_item, parent, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Below line of code is used to set data to
        // each item of our recycler view.
        RestaurantModal model = getRestaurantAt(position);
        holder.restaurantNameTV.setText(model.getName());
        holder.restaurantCuisineTypeTV.setText(model.getCuisineType());
        holder.restaurantAddressTV.setText(model.getAddress());
    }

    // Creating a method to get restaurant modal for a specific position.
    public RestaurantModal getRestaurantAt(int position) {
        return getItem(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // View holder class to create a variable for each view.
        TextView restaurantNameTV, restaurantCuisineTypeTV, restaurantAddressTV;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initializing each view of our recycler view.
            restaurantNameTV = itemView.findViewById(R.id.idTVRestaurantName);
            restaurantCuisineTypeTV = itemView.findViewById(R.id.idTVRestaurantCuisineType);
            restaurantAddressTV = itemView.findViewById(R.id.idTVRestaurantAddress);

            // Adding on click listener for each item of recycler view.
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Inside on click listener, we are passing
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

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
