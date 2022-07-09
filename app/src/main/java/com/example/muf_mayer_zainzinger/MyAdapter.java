package com.example.muf_mayer_zainzinger;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.muf_mayer_zainzinger.data.SensorData;

import java.util.ArrayList;
import java.util.List;

// adapted source: https://developer.android.com/guide/topics/ui/layout/recyclerview

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    // source only processes List -> here: ArrayList!
    private List<SensorData> localSensorDataSet = new ArrayList<>();

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public ViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.dataView);
        }

        public TextView getTextView() {
            return textView;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * List<Data> containing the data to populate views to be used
     * by RecyclerView.
     */
    public MyAdapter(List<SensorData> sensorDataSet) {
        localSensorDataSet = sensorDataSet;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.text_row_item, viewGroup, false);

        return new ViewHolder(view);
    }
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getTextView().setText(String.valueOf(localSensorDataSet.get(position)));
    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localSensorDataSet.size();
    }
}

