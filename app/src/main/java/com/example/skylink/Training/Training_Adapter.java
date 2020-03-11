package com.example.skylink.Training;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skylink.R;
import com.example.skylink.Utility.AppUtilits;

import java.util.List;

public class Training_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<Training_Model> training_models;
    private Context mContext;
    private String TAG ="trainingAdapter";

    public Training_Adapter(List<Training_Model> training_models, Context mContext) {
        this.training_models = training_models;
        this.mContext = mContext;
    }

    private class trainingView extends RecyclerView.ViewHolder {

        TextView title,start_date,end_date,venue,details;
        CardView traincard;


        public trainingView (View itemView) {
            super(itemView);

            title=  itemView.findViewById(R.id.title);
           start_date=  itemView.findViewById(R.id.start_date);
           end_date=  itemView.findViewById(R.id.end_date);
           venue=  itemView.findViewById(R.id.venue);
            details=  itemView.findViewById(R.id.details);
            traincard =  itemView.findViewById(R.id.trainingcard);


        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_training_adapter, parent,false);
        Log.e(TAG, "  view created ");
        return new trainingView (view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        final Training_Model model =  training_models.get(position);

        ((trainingView ) holder).title.setText(" Title: " + " " + model.getTitle());
        ((trainingView ) holder).start_date.setText(" Start: " + " " + model.getStart_date());
        ((trainingView ) holder).end_date.setText(" End: " + " " + model.getEnd_date());
        ((trainingView ) holder).venue.setText(" Venue: " + " " + model.getVenue());
        ((trainingView ) holder).details.setText(" Details: " + " " + model.getDetails());

    }

    @Override
    public int getItemCount() {

        return training_models.size();
    }
}
