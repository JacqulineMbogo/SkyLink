package com.example.skylink;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skylink.Branches.branches_model;
import com.example.skylink.LogIn.LogIn;
import com.example.skylink.Branches.branches_adapter;
import com.example.skylink.Training.Training_Adapter;
import com.example.skylink.Training.Training_Model;
import com.example.skylink.Training.training_home;
import com.example.skylink.Utility.AppUtilits;
import com.example.skylink.Utility.Constant;
import com.example.skylink.Utility.NetworkUtility;
import com.example.skylink.Utility.SharedPreferenceActivity;
import com.example.skylink.WebServices.ServiceWrapper;
import com.example.skylink.beanResponse.TrainingRes;
import com.example.skylink.beanResponse.getBranchesResponse;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {


    Context context;
    SharedPreferenceActivity sharedPreferenceActivity;
    Button contributions, loans, account, feedback, withdraw,about;
    RelativeLayout training;
    TextView logout,count,welcome;
    String TAG = "Home";

    RecyclerView branches_recycler;
    private branches_adapter branches_adapter;
    private ArrayList<branches_model> branchesModels = new ArrayList<>();


   private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    Intent intent1 = new Intent(HomeActivity.this , HomeActivity.class);
                    startActivity(intent1);

                    return true;
                case R.id.navigation_account:
                    Intent intent2 = new Intent(HomeActivity.this , acount_home.class);
                    startActivity(intent2);

                    return true;
                case R.id.navigation_logout:

                    Intent intent3 = new Intent(HomeActivity.this , LogIn.class);
                    startActivity(intent3);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView navView = findViewById(R.id.nav_view);

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        context= this;
        this.setTitle("My Groups");
        sharedPreferenceActivity = new SharedPreferenceActivity(context);
        branches_recycler = findViewById(R.id.branches_recycler);


        count = findViewById(R.id.count);




        LinearLayoutManager mLayoutManger = new LinearLayoutManager( context, RecyclerView.VERTICAL, false);
        branches_recycler.setLayoutManager(mLayoutManger);
        branches_recycler.setItemAnimator(new DefaultItemAnimator());

        branches_adapter= new branches_adapter(branches_recycler,branchesModels, context );
        branches_recycler.setAdapter(branches_adapter);


        getBranches();

    }


    private void getBranches() {
        if (!NetworkUtility.isNetworkConnected(HomeActivity.this)){
            AppUtilits.displayMessage(HomeActivity.this,  getString(R.string.network_not_connected));

        }else {

            ServiceWrapper service = new ServiceWrapper(null);
            Call<getBranchesResponse> call = service.getBranchesResponseCall( "1234",sharedPreferenceActivity.getItem(Constant.USER_DATA) );

            call.enqueue(new Callback<getBranchesResponse>() {
                @Override
                public void onResponse(Call<getBranchesResponse> call, Response<getBranchesResponse> response) {
                    Log.e(TAG, "response is "+ response.body() + "  ---- "+ new Gson().toJson(response.body()));
                    //  Log.e(TAG, "  ss sixe 1 ");
                    if (response.body() != null && response.isSuccessful()) {
                        //    Log.e(TAG, "  ss sixe 2 ");
                        if (response.body().getStatus() == 1) {
                            //      Log.e(TAG, "  ss sixe 3 ");
                            //Log.e(TAG, " size is  "+ String.valueOf(response.body().getInformation().size()));

                            branchesModels.clear();
                            for (int i =0; i<response.body().getInformation().getBranchDetails().size(); i++){


                                branchesModels.add(  new branches_model(response.body().getInformation().getBranchDetails().get(i).getId(),response.body().getInformation().getBranchDetails().get(i).getName()));

                            }
                            branches_adapter.notifyDataSetChanged();



                        }
                    }

                }

                @Override
                public void onFailure(Call<getBranchesResponse> call, Throwable t) {
                    Log.e(TAG, " Failed to get branch "+ t.toString());
                    //  AppUtilits.displayMessage(CartDetails.this, getString(R.string.fail_toGetcart));

                }
            });
        }
    }
}
