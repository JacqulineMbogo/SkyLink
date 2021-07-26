package com.example.skylink.Branches;




import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skylink.BranchMembers.branch_member_adapter;
import com.example.skylink.BranchMembers.branch_member_home;
import com.example.skylink.CashAccounts.cash_contribution_home;
import com.example.skylink.Contributions.contributions_home;
import com.example.skylink.Contributions.contributions_type_model;
import com.example.skylink.Loans.loans_home;
import com.example.skylink.MandatoryContributions.mandatory_contributions_home;
import com.example.skylink.R;
import com.example.skylink.Spinner_Adapter;
import com.example.skylink.Training.Training_Adapter;
import com.example.skylink.Training.Training_Model;
import com.example.skylink.Utility.AppUtilits;
import com.example.skylink.Utility.Constant;
import com.example.skylink.Utility.ExpandListener;
import com.example.skylink.Utility.ExpandableLinearLayout;
import com.example.skylink.Utility.NetworkUtility;
import com.example.skylink.Utility.SharedPreferenceActivity;
import com.example.skylink.WebServices.ServiceWrapper;
import com.example.skylink.beanResponse.ContributionTypeRes;
import com.example.skylink.beanResponse.SaveContributionRes;
import com.example.skylink.beanResponse.newMeetingRes;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class branches_adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int lastExpandedCardPosition;
    private List<branches_model> branches_models;
    private Context mContext;
    private RecyclerView recyclerView;
    private String TAG ="branchesAdapter";
    SharedPreferenceActivity sharedPreferenceActivity;

    public branches_adapter( RecyclerView recyclerView ,List<branches_model> branches_models, Context mContext) {
        this.branches_models = branches_models;
        this.mContext = mContext;
        this.recyclerView = recyclerView;
        sharedPreferenceActivity = new SharedPreferenceActivity(mContext);
    }

    private class branchesView extends RecyclerView.ViewHolder {

        TextView name,last_meeting;
        LinearLayout branchescard;

        ImageView dropdown;



        public branchesView (View itemView) {
            super(itemView);

            name=  itemView.findViewById(R.id.branch_name);
            branchescard=  itemView.findViewById(R.id.branchcard);
      }

    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_branches_adapter, parent,false);
        Log.e(TAG, "  view created ");
        return new branches_adapter.branchesView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {

        final branches_model model =  branches_models.get(position);




        ((branches_adapter.branchesView) holder).name.setText(model.getBranch_name());


        ((branchesView)  holder).branchescard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopupMenu dropDownMenu = new PopupMenu(mContext,((branches_adapter.branchesView) holder).dropdown);

                dropDownMenu.getMenuInflater().inflate(R.menu.groupmenu, dropDownMenu.getMenu());

                dropDownMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        int id = menuItem.getItemId();
                        if (id == R.id.venue) {

                            return true;
                        }else if (id == R.id.serve) {
                            Log.e(TAG, "  meeting details ");
                            //new meeting details

                            //meeting_id, group_id, contact_person, service_fee, meeting_venue, create_date, update_date, created_by,prefix,comments
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
                            alertDialog.setTitle("Meeting Confirmation!");
                            alertDialog.setIcon(R.drawable.ic_baseline_add_alert_24);
                            alertDialog.setMessage("You are about to start a meeting for ," + model.getBranch_name() + "\n\n" +
                                    "You cannot make any changes upon submitting\n\n" +
                                    "Would you like to proceed?\n\n");
                            alertDialog.setNeutralButton("No, Stop", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    dialog.cancel();

                                }
                            }).setPositiveButton("Yes, Continue", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {


                                    final Dialog dialog1;

                                    View view = LayoutInflater.from(mContext).inflate(R.layout.meeting_popup, null, false);
                                    android.app.AlertDialog.Builder alertDialog = new android.app.AlertDialog.Builder(mContext);

                                    alertDialog.setView(view);

                                    alertDialog.setCancelable(true);


                                    dialog1 = alertDialog.create();

                                    dialog1.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

                                    dialog1.show();


                                    final Window dialogWindow = dialog1.getWindow();
                                    dialogWindow.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
                                    dialogWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);



                                    final EditText venue = view.findViewById(R.id.venue);
                                    final EditText comments = view.findViewById(R.id.comments);
                                    final Button cancel = view.findViewById(R.id.cancel);
                                    final Button okay = view.findViewById(R.id.ok);



                                    okay.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            createNewMeeting( venue.getText().toString(),comments.getText().toString(),model.getBranch_id(),sharedPreferenceActivity.getItem(Constant.USER_DATA));
                                            dialog1.cancel();


                                        }
                                    });
                                    cancel.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            dialog1.cancel();
                                        }
                                    });


                                }


                            }).show();

                        }
                        else if (id == R.id.reports) {

                            return true;
                        }

                        return true;
                    }
                });
                dropDownMenu.show();




            }
        });
    }

    private void createNewMeeting(String venue, String comments, final String group_id, String user_id){


        if (!NetworkUtility.isNetworkConnected(mContext)) {

            Toast.makeText(mContext, "Network error", Toast.LENGTH_LONG).show();


        } else {

            //  Log.e(TAG, "  user value "+ SharePreferenceUtils.getInstance().getString(Constant.USER_DATA));
                ServiceWrapper service = new ServiceWrapper(null);
                Call<newMeetingRes> call = service.newMeetingResCall("12345",user_id,group_id,venue,comments);
                call.enqueue(new Callback<newMeetingRes>() {
                    @Override
                    public void onResponse(Call<newMeetingRes> call, Response<newMeetingRes> response) {

                        if (response.body() != null && response.isSuccessful()) {
                            if (response.body().getStatus() == 1) {

                                sharedPreferenceActivity.putItem("meeting_id",response.body().getMsg());
                                sharedPreferenceActivity.putItem("group_id",group_id);
                                Toast.makeText(mContext, "Meeting Started", Toast.LENGTH_LONG).show();


                                Intent intent = new Intent(mContext, branch_member_home.class);

                                mContext.startActivity(intent);


                            }else {

                                AppUtilits.displayMessage(mContext, "failed to make new contribution");
                            }
                        }else {
                            AppUtilits.displayMessage(mContext, "network error");
                        }


                    }

                    @Override
                    public void onFailure(Call<newMeetingRes> call, Throwable t) {
                        // Log.e(TAG, "  fail- add to cart item "+ t.toString());

                        AppUtilits.displayMessage(mContext, "failed to make new contribution");
                    }
                });


        }


    }

    @Override
    public int getItemCount() {
        return branches_models.size();
    }
}
