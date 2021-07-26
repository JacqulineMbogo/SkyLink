package com.example.skylink.MandatoryContributions;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skylink.Contributions.contributions_adapter;
import com.example.skylink.Contributions.contributions_home;
import com.example.skylink.Contributions.contributions_model;
import com.example.skylink.R;
import com.example.skylink.Utility.SharedPreferenceActivity;

import java.util.List;

public class mandatory_contributions_adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<mandatory_contributions_model> mandatory_contributions_models;
    private Context mContext;
    private String TAG ="mandatory_contributionsAdapter";
    SharedPreferenceActivity sharedPreferenceActivity;


    public mandatory_contributions_adapter (Context context, List<mandatory_contributions_model> mandatory_contributionModels){
        this.mandatory_contributions_models = mandatory_contributionModels;
        this.mContext = context;
        sharedPreferenceActivity = new SharedPreferenceActivity(mContext);

    }

    private class mandatory_contributionsView extends RecyclerView.ViewHolder {

        TextView contribution_type, amount, contribution_date,contribution_id,variance;
        LinearLayout typelinear;


        public mandatory_contributionsView(View itemView) {
            super(itemView);

            contribution_type =  itemView.findViewById(R.id.contribution_type);
            typelinear = itemView.findViewById(R.id.typelinear);
            amount =  itemView.findViewById(R.id.amount);
            variance =  itemView.findViewById(R.id.variance);
            contribution_date =  itemView.findViewById(R.id.contribution_date);

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_contributions_adapter, parent,false);
        Log.e(TAG, "  view created ");
        return new mandatory_contributions_adapter.mandatory_contributionsView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        final mandatory_contributions_model mandatory_model =  mandatory_contributions_models.get(position);

        ((mandatory_contributions_adapter.mandatory_contributionsView) holder).contribution_type.setText(mandatory_model.getMandatory_name());
        ((mandatory_contributions_adapter.mandatory_contributionsView) holder).amount.setText(mandatory_model.getAmount());
        ((mandatory_contributions_adapter.mandatory_contributionsView) holder).variance.setText(mandatory_model.getVariance());
        ((mandatory_contributions_adapter.mandatory_contributionsView) holder).contribution_date.setText(mandatory_model.getCreate_date());

        ((mandatory_contributions_adapter.mandatory_contributionsView) holder).typelinear.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                View view;
                final Dialog dialog;

                view = LayoutInflater.from(mContext).inflate(R.layout.mandatory_dialog_popup, null, false);
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);

                alertDialog.setView(view);

                alertDialog.setCancelable(true);


                dialog = alertDialog.create();

                dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

                dialog.show();

                TextView newentry = view.findViewById(R.id.newentry);
                TextView viewstatements = view.findViewById(R.id.view);
                TextView deduct = view.findViewById(R.id.deduct);



                newentry.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {

                        mandatory_contributions_home.newmandatory_contribution();
                        dialog.cancel();

                    }
                });


                viewstatements.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {

                        //contributions_home.getsingleContributions(model.getSavings_id());
                        dialog.cancel();

                    }
                });
                deduct.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {


                        dialog.cancel();

                    }
                });



            }
        });



    }


    @Override
    public int getItemCount() {
        // return 0;
        return mandatory_contributions_models.size();
    }
}


