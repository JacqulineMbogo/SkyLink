package com.example.skylink.BranchMembers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skylink.Branches.branches_adapter;
import com.example.skylink.Branches.branches_model;
import com.example.skylink.HomeActivity;
import com.example.skylink.R;
import com.example.skylink.Utility.AppUtilits;
import com.example.skylink.Utility.Constant;
import com.example.skylink.Utility.NetworkUtility;
import com.example.skylink.Utility.SharedPreferenceActivity;
import com.example.skylink.WebServices.ServiceWrapper;
import com.example.skylink.beanResponse.BranchMembersRes;
import com.example.skylink.beanResponse.getBranchesResponse;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class branch_member_home extends AppCompatActivity {

    Context context;
    SharedPreferenceActivity sharedPreferenceActivity;
    Button contributions, loans, account, feedback, withdraw, about;
    RelativeLayout training;
    TextView logout, count;
    String TAG = "Branch_members";
    String branch_id;

    RecyclerView branch_member_recycler;
    private branch_member_adapter branch_member_adapter;
    private ArrayList<branch_member_model> branchMemberModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_member);


        context = this;

        this.setTitle("Branch Members");
        sharedPreferenceActivity = new SharedPreferenceActivity(context);
        branch_member_recycler = findViewById(R.id.branch_member_recycler);


        LinearLayoutManager mLayoutManger = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        branch_member_recycler.setLayoutManager(mLayoutManger);
        branch_member_recycler.setItemAnimator(new DefaultItemAnimator());

        branch_member_adapter = new branch_member_adapter(branch_member_recycler,branchMemberModels, context);
        branch_member_recycler.setAdapter(branch_member_adapter);


        getBranchMember();

    }

    private void getBranchMember() {

        if (!NetworkUtility.isNetworkConnected(branch_member_home.this)){
            AppUtilits.displayMessage(branch_member_home.this,  getString(R.string.network_not_connected));

        }else {

            ServiceWrapper service = new ServiceWrapper(null);
            Call<BranchMembersRes> call = service.BranchMembersResCall( "1234",sharedPreferenceActivity.getItem(Constant.USER_DATA),sharedPreferenceActivity.getItem("group_id") );

            call.enqueue(new Callback<BranchMembersRes>() {
                @Override
                public void onResponse(Call<BranchMembersRes> call, Response<BranchMembersRes> response) {
                    Log.e(TAG, "response is "+ response.body() + "  ---- "+ new Gson().toJson(response.body()));
                    //  Log.e(TAG, "  ss sixe 1 ");
                    if (response.body() != null && response.isSuccessful()) {
                        //    Log.e(TAG, "  ss sixe 2 ");
                        if (response.body().getStatus() == 1) {
                            //      Log.e(TAG, "  ss sixe 3 ");
                            //Log.e(TAG, " size is  "+ String.valueOf(response.body().getInformation().size()));

                            branchMemberModels.clear();
                            for (int i =0; i<response.body().getInformation().size(); i++){


                                branchMemberModels.add(  new branch_member_model(response.body().getInformation().get(i).getFullName(),response.body().getInformation().get(i).getIdNumber(),response.body().getInformation().get(i).getMemberId(),response.body().getInformation().get(i).getPhoneNumber(),""));

                            }
                            branch_member_adapter.notifyDataSetChanged();



                        }
                    }

                }

                @Override
                public void onFailure(Call<BranchMembersRes> call, Throwable t) {
                    Log.e(TAG, " Failed to get branch "+ t.toString());
                    //  AppUtilits.displayMessage(CartDetails.this, getString(R.string.fail_toGetcart));

                }
            });
        }


    }

}



