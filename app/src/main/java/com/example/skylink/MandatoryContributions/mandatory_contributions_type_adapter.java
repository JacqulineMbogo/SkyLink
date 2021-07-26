package com.example.skylink.MandatoryContributions;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skylink.Contributions.contributions_type_adapter;
import com.example.skylink.Contributions.contributions_type_model;
import com.example.skylink.R;

import java.util.List;

public class mandatory_contributions_type_adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

private List<mandatory_contributions_type_model> mandatory_contributions_type_models;
private Context mContext;
private String TAG ="mandatory_contributionstypeAdapter";

public mandatory_contributions_type_adapter(List<mandatory_contributions_type_model> mandatory_contributions_type_models, Context mContext) {
        this.mandatory_contributions_type_models = mandatory_contributions_type_models;
        this.mContext = mContext;
        }



private class mandatory_contributionsView extends RecyclerView.ViewHolder {

    TextView contribution_type,contribution_type_id;


    public mandatory_contributionsView(View itemView) {
        super(itemView);

        contribution_type =  itemView.findViewById(R.id.contribution_type);
        contribution_type_id =  itemView.findViewById(R.id.contribution_type_id);

    }
}

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_contributions_type_adapter, parent,false);
        Log.e(TAG, "  view created ");
        return new mandatory_contributions_type_adapter.mandatory_contributionsView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        final mandatory_contributions_type_model mandatory_model =  mandatory_contributions_type_models.get(position);

        ((mandatory_contributions_type_adapter.mandatory_contributionsView) holder).contribution_type.setText(mandatory_model.getMandatory_id());
        ((mandatory_contributions_type_adapter.mandatory_contributionsView) holder).contribution_type_id.setText(mandatory_model.getMandatory_name());


    }



    @Override
    public int getItemCount() {
        return mandatory_contributions_type_models.size();
    }
}
