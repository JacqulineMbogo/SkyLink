package com.example.skylink.Training;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skylink.MainActivity;
import com.example.skylink.R;
import com.example.skylink.Utility.AppUtilits;
import com.example.skylink.Utility.Constant;
import com.example.skylink.Utility.NetworkUtility;
import com.example.skylink.Utility.SharedPreferenceActivity;
import com.example.skylink.WebServices.ServiceWrapper;
import com.example.skylink.beanResponse.TrainingRes;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class training_home extends AppCompatActivity {

    Context context;
    SharedPreferenceActivity sharedPreferenceActivity;
    String TAG = "TRAINING";

    RecyclerView training_recyclerview;
    private  Training_Adapter training_adapter;
    private ArrayList<Training_Model> trainingModels = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_home);

        context= this;
        sharedPreferenceActivity = new SharedPreferenceActivity(context);
        training_recyclerview = findViewById(R.id.training_recycler);



        LinearLayoutManager mLayoutManger = new LinearLayoutManager( context, RecyclerView.VERTICAL, false);
        training_recyclerview.setLayoutManager(mLayoutManger);
        training_recyclerview.setItemAnimator(new DefaultItemAnimator());

        training_adapter= new Training_Adapter(trainingModels, context );
        training_recyclerview.setAdapter(training_adapter);


        getNotification();
    }




    private void getNotification() {
        if (!NetworkUtility.isNetworkConnected(training_home.this)){
            AppUtilits.displayMessage(training_home.this,  getString(R.string.network_not_connected));

        }else {

            ServiceWrapper service = new ServiceWrapper(null);
            Call<TrainingRes> call = service.TrainingResCall( "1234" );

            call.enqueue(new Callback<TrainingRes>() {
                @Override
                public void onResponse(Call<TrainingRes> call, Response<TrainingRes> response) {
                    Log.e(TAG, "response is "+ response.body() + "  ---- "+ new Gson().toJson(response.body()));
                    //  Log.e(TAG, "  ss sixe 1 ");
                    if (response.body() != null && response.isSuccessful()) {
                        //    Log.e(TAG, "  ss sixe 2 ");
                        if (response.body().getStatus() == 1) {
                            //      Log.e(TAG, "  ss sixe 3 ");
                            Log.e(TAG, " size is  "+ String.valueOf(response.body().getInformation().size()));

                            trainingModels.clear();
                            for (int i =0; i<response.body().getInformation().size(); i++){


                               trainingModels.add(  new Training_Model(response.body().getInformation().get(i).getTitle(),response.body().getInformation().get(i).getStartDate(),response.body().getInformation().get(i).getEndDate(),response.body().getInformation().get(i).getVenue(),response.body().getInformation().get(i).getDetails()));

                            }
                           training_adapter.notifyDataSetChanged();



                        }
                    }

                }

                @Override
                public void onFailure(Call<TrainingRes> call, Throwable t) {
                    Log.e(TAG, "  fail- add to cart item "+ t.toString());
                    //  AppUtilits.displayMessage(CartDetails.this, getString(R.string.fail_toGetcart));

                }
            });
        }
    }
}
