package com.example.skylink.Loans;

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

public class loans_adapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<loans_model> loans_models;
    private Context mContext;
    private String TAG ="loansAdapter";

    public loans_adapter(List<loans_model> loans_models, Context mContext) {
        this.loans_models = loans_models;
        this.mContext = mContext;
    }


    private class loansView extends RecyclerView.ViewHolder {

        TextView application_id,loan_type, amount,application_date,status,comment;
        CardView loancard;


        public loansView(View itemView) {
            super(itemView);

            application_id=  itemView.findViewById(R.id.application_id);
            amount =  itemView.findViewById(R.id.amount);
            loan_type=  itemView.findViewById(R.id.loan_type);
            application_date =  itemView.findViewById(R.id.loandate);
            loancard = itemView.findViewById(R.id.loancard);
            status = itemView.findViewById(R.id.status);
            comment = itemView.findViewById(R.id.comment);

        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_loans_adapter, parent,false);
        Log.e(TAG, "  view created ");
        return new loansView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        final loans_model model =  loans_models.get(position);

        ((loansView) holder).application_id.setText(" Application Id: " + " " + model.getApplication_id());
        ((loansView) holder).amount.setText(" Amount: " + " " +model.getAmount());
        ((loansView) holder).loan_type.setText(" Loan Type: " + " " +model.getLoan_id());
        ((loansView) holder).application_date.setText(" Date Applied: " + " " +model.getDate());
        ((loansView) holder).status.setText(" Status: " + " " +model.getStatus());
        ((loansView) holder).comment.setText(model.getComment());

        ((loansView) holder).loancard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(model.getStatus().equals("Pending")) {

                    AppUtilits.displayMessage(mContext, "Please wait for Admin Approval");

                }else if(model.getStatus().equals("Rejected")){

                    AppUtilits.displayMessage(mContext, "Sorry, your loan has been rejected. ");

                }else if(model.getStatus().equals("Approved")){

                    Intent intent = new Intent(mContext, loan_payments_home.class);
                    intent.putExtra("application_id", model.getApplication_id());
                    intent.putExtra("loan_amount", model.getAmount());
                    mContext.startActivity(intent);
                }else{

                    AppUtilits.displayMessage(mContext, "This loan has been rejected. Please contact the admin for more information");

                }
            }
        });

    }

    @Override
    public int getItemCount() {

        return loans_models.size();
    }
}
