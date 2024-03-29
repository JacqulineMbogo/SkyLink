package com.example.skylink.Feedback;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
import com.example.skylink.beanResponse.feedhistoryAPI;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedbackHistory extends AppCompatActivity {

    private String TAG = "feedhistory";
    private TextView feednew;
    private RecyclerView recyclerView_order;
    private SharedPreferenceActivity sharedPreferenceActivity;
    Context context;
    private ArrayList<feedhistory_model> Models = new ArrayList<>();
    private feedhistory_Adapter adapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedbackhistory);

        context = this;
        sharedPreferenceActivity = new SharedPreferenceActivity(context);


        feednew = findViewById(R.id.newfeedback);

        recyclerView_order = (RecyclerView) findViewById(R.id.recycler_orderhistory);
        LinearLayoutManager mLayoutManger3 = new LinearLayoutManager( this, LinearLayoutManager.VERTICAL, false);
        recyclerView_order.setLayoutManager(mLayoutManger3);
        recyclerView_order.setItemAnimator(new DefaultItemAnimator());

        adapter = new feedhistory_Adapter( FeedbackHistory.this, Models);

        recyclerView_order.setAdapter(adapter);


        getUserFeedHistory();


        feednew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FeedbackHistory.this, feedback.class);
                startActivity(intent);
            }
        });
    }

    public void getUserFeedHistory(){
        if (!NetworkUtility.isNetworkConnected(FeedbackHistory.this)){
            Toast.makeText(getApplicationContext(),"Network error",Toast.LENGTH_LONG).show();

        }else {
            //  Log.e(TAG, "  user value "+ SharePreferenceUtils.getInstance().getString(Constant.USER_DATA));
            ServiceWrapper service = new ServiceWrapper(null);
            Call<feedhistoryAPI> call = service.getfeedhistorycall("1234", sharedPreferenceActivity.getItem(Constant.USER_DATA));
            call.enqueue(new Callback<feedhistoryAPI>() {
                @Override
                public void onResponse(Call<feedhistoryAPI> call, Response<feedhistoryAPI> response) {
                    Log.e(TAG, "response is " + response.body() + "  ---- " + new Gson().toJson(response.body()));
                    //  Log.e(TAG, "  ss sixe 1 ");
                    if (response.body() != null && response.isSuccessful()) {
                        //    Log.e(TAG, "  ss sixe 2 ");
                        if (response.body().getStatus() == 1) {
                            //      Log.e(TAG, "  ss sixe 3 ");
                            Models.clear();

                            if (response.body().getInformation().size()>0){

                                for (int i =0; i<response.body().getInformation().size(); i++){

                                    Models.add(  new feedhistory_model(response.body().getInformation().get(i).getComment(),response.body().getInformation().get(i).getReply(),response.body().getInformation().get(i).getCommentdate(),
                                            response.body().getInformation().get(i).getReplydate()));



                                }
                                adapter.notifyDataSetChanged();
                            }

                        } else {
                            AppUtilits.displayMessage(FeedbackHistory.this, response.body().getMsg());

                        }

                    } else {
                        AppUtilits.displayMessage(FeedbackHistory.this, getString(R.string.network_error));

                    }
                }
                @Override
                public void onFailure(Call<feedhistoryAPI> call, Throwable t) {
                    Log.e(TAG, "  fail- add to cart item "+ t.toString());
                    Toast.makeText(getApplicationContext(),"Failed to get order history",Toast.LENGTH_LONG).show();

                }
            });


        }


    }


    @Override
    public void onBackPressed() {


        Intent intent1 = new Intent(FeedbackHistory.this, MainActivity.class);

        startActivity(intent1);



    }

}
