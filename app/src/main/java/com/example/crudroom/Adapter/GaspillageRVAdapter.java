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
import com.example.crudroom.model.GaspillageModal;


public class GaspillageRVAdapter extends ListAdapter<GaspillageModal, GaspillageRVAdapter.ViewHolder> {

    // creating a variable for on item click listener.
    private GaspillageRVAdapter.OnItemClickListener listener;

    // creating a constructor class for our adapter class.
    public GaspillageRVAdapter() {
        super(DIFF_CALLBACK);
    }

    // creating a call back for item of recycler view.
    private static final DiffUtil.ItemCallback<GaspillageModal> DIFF_CALLBACK = new DiffUtil.ItemCallback<GaspillageModal>() {
        @Override
        public boolean areItemsTheSame(GaspillageModal oldItem, GaspillageModal newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(GaspillageModal oldItem, GaspillageModal newItem) {
            // below line is to check the course name, description and course duration.
            return oldItem.getTypeDeDechet().equals(newItem.getTypeDeDechet()) &&
                    oldItem.getQuantite().equals(newItem.getQuantite()) &&
                    oldItem.getLieu().equals(newItem.getLieu());
        }
    };

    @NonNull
    @Override
    public GaspillageRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // below line is use to inflate our layout
        // file for each item of our recycler view.
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gaspillage_rv_item, parent, false);
        return new GaspillageRVAdapter.ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull GaspillageRVAdapter.ViewHolder holder, int position) {
        // below line of code is use to set data to
        // each item of our recycler view.
        GaspillageModal model = getCourseAt(position);
        holder.courseNameTV.setText(model.getTypeDeDechet());
        holder.courseDescTV.setText(model.getQuantite());
        holder.courseDurationTV.setText(model.getLieu());
    }

    // creating a method to get course modal for a specific position.
    public GaspillageModal getCourseAt(int position) {
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
        void onItemClick(GaspillageModal model);
    }
    public void setOnItemClickListener(GaspillageRVAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }
}
