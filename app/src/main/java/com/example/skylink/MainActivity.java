package com.example.skylink;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.skylink.Contributions.contributions_home;
import com.example.skylink.Feedback.FeedbackHistory;
import com.example.skylink.Feedback.feedback;
import com.example.skylink.Loans.loans_home;
import com.example.skylink.LogIn.LogIn;
import com.example.skylink.Training.training_home;
import com.example.skylink.Utility.AppUtilits;
import com.example.skylink.Utility.NetworkUtility;
import com.example.skylink.Utility.SharedPreferenceActivity;
import com.example.skylink.WebServices.ServiceWrapper;
import com.example.skylink.beanResponse.TrainingRes;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    Context context;
    SharedPreferenceActivity sharedPreferenceActivity;
    RelativeLayout training;
    TextView mygroupsText,todaygroupsText,count;
    LinearLayout mygroupsLinear,todaygroupsLinear,newgroupsLinear;
    String TAG = "Main";
    String member_id;
   /* private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    Intent intent1 = new Intent(MainActivity.this , contributions_home.class);
                    startActivity(intent1);

                    return true;
                case R.id.navigation_dashboard:
                    Intent intent2 = new Intent(MainActivity.this , loans_home.class);
                    startActivity(intent2);

                    return true;
                case R.id.navigation_notifications:

                    Intent intent3 = new Intent(MainActivity.this , FeedbackHistory.class);
                    startActivity(intent3);
                    return true;
            }
            return false;
        }
    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*BottomNavigationView navView = findViewById(R.id.nav_view);

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);*/

        context= this;

        this.setTitle("Home");
        sharedPreferenceActivity = new SharedPreferenceActivity(context);




        newgroupsLinear = findViewById(R.id.newgroupsLinear);
        mygroupsLinear = findViewById(R.id.mygroupsLinear);
        todaygroupsLinear = findViewById(R.id.todaygroupsLinear);
        //logout = findViewById(R.id.logout);
        training = findViewById(R.id.training);
        count = findViewById(R.id.count);



        todaygroupsLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent( MainActivity.this, HomeActivity.class);

                startActivity(intent1);

            }
        });

        mygroupsLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent( MainActivity.this, HomeActivity.class);
                startActivity(intent1);
            }
        });




getNotification();

    }

    private void getNotification() {
        if (!NetworkUtility.isNetworkConnected(MainActivity.this)){
            AppUtilits.displayMessage(MainActivity.this,  getString(R.string.network_not_connected));

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
                            Log.e(TAG, " size is  "+ sharedPreferenceActivity.getItem("member_id"));

                            count.setText(String.valueOf(response.body().getInformation().size()));


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


