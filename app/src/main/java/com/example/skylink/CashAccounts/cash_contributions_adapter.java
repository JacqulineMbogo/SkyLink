package com.example.skylink.CashAccounts;

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
import com.example.skylink.Utility.SharedPreferenceActivity;

import java.util.List;

public class cash_contributions_adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<cash_contributions_model> cash_contributions_models;
    private Context mContext;
    private String TAG ="cash_contributionsAdapter";
    SharedPreferenceActivity sharedPreferenceActivity;


    public cash_contributions_adapter (Context context, List<cash_contributions_model> cash_contributionModels){
        this.cash_contributions_models = cash_contributionModels;
        this.mContext = context;
        sharedPreferenceActivity = new SharedPreferenceActivity(mContext);

    }

    private class cash_contributionsView extends RecyclerView.ViewHolder {

        TextView contribution_type, amount, contribution_date,contribution_id,variance;
        LinearLayout typelinear;


        public cash_contributionsView(View itemView) {
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
        return new cash_contributions_adapter.cash_contributionsView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        final cash_contributions_model model =  cash_contributions_models.get(position);

        ((cash_contributions_adapter.cash_contributionsView) holder).contribution_type.setText(model.getCash_name());
        ((cash_contributions_adapter.cash_contributionsView) holder).amount.setText(model.getAmount());
        ((cash_contributions_adapter.cash_contributionsView) holder).variance.setText(model.getVariance());
        ((cash_contributions_adapter.cash_contributionsView) holder).contribution_date.setText(model.getCreate_date());

        ((cash_contributions_adapter.cash_contributionsView) holder).typelinear.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                View view;
                final Dialog dialog;

                view = LayoutInflater.from(mContext).inflate(R.layout.cash_dialog_popup, null, false);
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);

                alertDialog.setView(view);

                alertDialog.setCancelable(true);


                dialog = alertDialog.create();

                dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

                dialog.show();

                TextView collect = view.findViewById(R.id.collect);
                TextView viewstatements = view.findViewById(R.id.view);



                collect.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {

                       // contributions_home.newcontribution();
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



            }
        });



    }


    @Override
    public int getItemCount() {
        // return 0;
        return cash_contributions_models.size();
    }
}
