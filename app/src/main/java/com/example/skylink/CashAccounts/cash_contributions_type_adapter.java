package com.example.skylink.CashAccounts;

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

public class cash_contributions_type_adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<cash_contributions_type_model> cash_contributions_type_models;
    private Context mContext;
    private String TAG ="contributionsAdapter";

    public cash_contributions_type_adapter(List<cash_contributions_type_model> cash_contributions_type_models, Context mContext) {
        this.cash_contributions_type_models = cash_contributions_type_models;
        this.mContext = mContext;
    }



    private class cash_contributionsView extends RecyclerView.ViewHolder {

        TextView contribution_type,contribution_type_id;


        public cash_contributionsView(View itemView) {
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
        return new cash_contributions_type_adapter.cash_contributionsView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        final cash_contributions_type_model model =  cash_contributions_type_models.get(position);

        ((cash_contributions_type_adapter.cash_contributionsView) holder).contribution_type.setText(model.getCash_id());
        ((cash_contributions_type_adapter.cash_contributionsView) holder).contribution_type_id.setText(model.getCash_name());


    }



    @Override
    public int getItemCount() {
        return cash_contributions_type_models.size();
    }
}

