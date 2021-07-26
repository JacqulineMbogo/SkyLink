package com.example.skylink.BranchMembers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skylink.Branches.branches_adapter;
import com.example.skylink.Branches.branches_model;
import com.example.skylink.CashAccounts.cash_contribution_home;
import com.example.skylink.Contributions.contributions_home;
import com.example.skylink.Loans.loans_home;
import com.example.skylink.MainActivity;
import com.example.skylink.MandatoryContributions.mandatory_contributions_home;
import com.example.skylink.R;
import com.example.skylink.Utility.SharedPreferenceActivity;
import android.widget.PopupMenu;
import java.util.List;
import com.example.skylink.Utility.ExpandListener;
import com.example.skylink.Utility.ExpandableLinearLayout;

public class branch_member_adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private int lastExpandedCardPosition;
    private int i=0;
    private Context context;
    private List<branch_member_model> branch_member_models;
    private RecyclerView recyclerView;
    private Context mContext;
    private String TAG ="branchesMemberAdapter";
    SharedPreferenceActivity sharedPreferenceActivity;



    public branch_member_adapter(RecyclerView recyclerView,List<branch_member_model> branch_member_models, Context mContext) {
        this.branch_member_models = branch_member_models;
        this.mContext = mContext;

        sharedPreferenceActivity= new SharedPreferenceActivity(mContext);
        this.recyclerView = recyclerView;
    }

    private class branchMemberView extends RecyclerView.ViewHolder {

        TextView name,idnumber,phonenumber,memberID;
        LinearLayout membercard;
        RelativeLayout relativeTransact;
        ImageView dropdown;

        ExpandableLinearLayout expandableView;

        ExpandListener expandListener = new ExpandListener() {
            @Override
            public void onExpandComplete() {
                if(lastExpandedCardPosition!=getAdapterPosition() && recyclerView.findViewHolderForAdapterPosition(lastExpandedCardPosition)!=null){
                    ((ExpandableLinearLayout)recyclerView.findViewHolderForAdapterPosition(lastExpandedCardPosition).itemView.findViewById(R.id.expandableView)).setExpanded(false);
                    branch_member_models.get(lastExpandedCardPosition).setExpanded(false);
                    ((ExpandableLinearLayout)recyclerView.findViewHolderForAdapterPosition(lastExpandedCardPosition).itemView.findViewById(R.id.expandableView)).toggle();
                }
                else if(lastExpandedCardPosition!=getAdapterPosition() && recyclerView.findViewHolderForAdapterPosition(lastExpandedCardPosition)==null){
                    branch_member_models.get(lastExpandedCardPosition).setExpanded(false);
                }
                lastExpandedCardPosition = getAdapterPosition();
            }

            @Override
            public void onCollapseComplete() {

            }
        };
        public branchMemberView (View itemView) {
            super(itemView);

            name=  itemView.findViewById(R.id.membername);
            idnumber=  itemView.findViewById(R.id.nationalid);
            phonenumber=  itemView.findViewById(R.id.phonenumber);
            membercard=  itemView.findViewById(R.id.membercard);
            expandableView = itemView.findViewById(R.id.expandableView);
            expandableView.setExpandListener(expandListener);
            relativeTransact = itemView.findViewById(R.id.relativeTransact);
            dropdown = itemView.findViewById(R.id.dropdown);
            memberID = itemView.findViewById(R.id.memberID);
            initializeClicks();

        }
        private void initializeClicks() {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    if (expandableView.isExpanded()) {
                        expandableView.setExpanded(false);
                        expandableView.toggle();
                        branch_member_models.get(getAdapterPosition()).setExpanded(false);
                    } else {
                        expandableView.setExpanded(true);
                        branch_member_models.get(getAdapterPosition()).setExpanded(true);
                        expandableView.toggle();
                    }
                }
            });
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_branch_member_adapter, parent,false);
        Log.e(TAG, "  view created ");
        return new branch_member_adapter.branchMemberView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {

        final branch_member_model model =  branch_member_models.get(position);

        if(branch_member_models.get(position).isExpanded()){
            ((branchMemberView) holder).expandableView.setVisibility(View.VISIBLE);
            ((branchMemberView) holder).expandableView.setExpanded(true);

        }
        else{
            ((branchMemberView) holder).expandableView.setVisibility(View.GONE);
            ((branchMemberView) holder).expandableView.setExpanded(false);
        }
        ((branch_member_adapter.branchMemberView) holder).name.setText(model.getName());
        ((branchMemberView) holder).idnumber.setText(model.getNational_id());
        ((branchMemberView) holder).phonenumber.setText(model.getPhone_number());
        ((branchMemberView) holder).memberID.setText(model.getUser_id());


        ((branchMemberView)  holder).relativeTransact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu dropDownMenu = new PopupMenu(mContext,((branchMemberView) holder).dropdown);

                dropDownMenu.getMenuInflater().inflate(R.menu.transactions_menu, dropDownMenu.getMenu());

                dropDownMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        int id = menuItem.getItemId();
                        if (id == R.id.loan) {
                            Activity activity   =   (Activity)mContext;
                            Intent intent =new Intent(mContext, loans_home.class);

                            sharedPreferenceActivity.putItem("member_id",model.getUser_id());
                            activity.startActivity(intent);

                            return true;
                        }else if (id == R.id.savings) {

                            Activity activity   =   (Activity)mContext;
                            Intent intent =new Intent(mContext, contributions_home.class);

                            sharedPreferenceActivity.putItem("member_id",model.getUser_id());
                            activity.startActivity(intent);
                             return true;
                        }
                        else if (id == R.id.mandatory) {

                            Activity activity   =   (Activity)mContext;
                            Intent intent =new Intent(mContext, mandatory_contributions_home.class);

                            sharedPreferenceActivity.putItem("member_id",model.getUser_id());
                            activity.startActivity(intent);
                            return true;
                        }
                        else if (id == R.id.cash_accounts) {

                            Activity activity   =   (Activity)mContext;
                            Intent intent =new Intent(mContext, cash_contribution_home.class);

                            sharedPreferenceActivity.putItem("member_id",model.getUser_id());
                            activity.startActivity(intent);
                            return true;
                        }
                        return true;
                    }
                });
                dropDownMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {

        return branch_member_models.size();
    }
}
