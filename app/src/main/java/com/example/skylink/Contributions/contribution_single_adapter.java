package com.example.skylink.Contributions;

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


import com.example.skylink.R;
import com.example.skylink.Utility.AppUtilits;
import com.example.skylink.Utility.Constant;
import com.example.skylink.Utility.NetworkUtility;
import com.example.skylink.Utility.SharedPreferenceActivity;
import com.example.skylink.WebServices.ServiceWrapper;
import com.example.skylink.beanResponse.SaveContributionRes;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.example.skylink.Contributions.contributions_home;

public class contribution_single_adapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<contributions_model> contributions_models;
    private Context mContext;
    private String TAG ="contributionsAdapter";
    SharedPreferenceActivity sharedPreferenceActivity;


    public contribution_single_adapter (Context context, List<contributions_model> contributionModels){
        this.contributions_models = contributionModels;
        this.mContext = context;
        sharedPreferenceActivity = new SharedPreferenceActivity(mContext);

    }

    private class contributionsView extends RecyclerView.ViewHolder {

        TextView contribution_type, amount, contribution_date,contribution_id,variance;
        LinearLayout typelinear;


        public contributionsView(View itemView) {
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
        return new contributionsView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        final contributions_model model =  contributions_models.get(position);

        ((contributionsView) holder).contribution_type.setText(model.getSavings_id());
        ((contributionsView) holder).amount.setText(model.getAmount());
        ((contributionsView) holder).variance.setText(model.getVariance());
        ((contributionsView) holder).contribution_date.setText(model.getSavings_date());

        ((contributionsView) holder).typelinear.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                View view;
                final Dialog dialog;

                view = LayoutInflater.from(mContext).inflate(R.layout.dialog_popup_1, null, false);
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);

                alertDialog.setView(view);

                alertDialog.setCancelable(true);


                dialog = alertDialog.create();

                dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

                dialog.show();

                TextView settle = view.findViewById(R.id.settle);


                settle.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {

                        //contributions_home.getsingleContributions(model.getSavings_id());
                        dialog.cancel();

                    }
                });






            }
        });



    }


    @Override
    public int getItemCount() {
        // return 0;
        return contributions_models.size();
    }
}
