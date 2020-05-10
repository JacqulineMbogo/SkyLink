package com.example.skylink;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
    Button contributions, loans, account, feedback, withdraw,about,help,myaccount;
    RelativeLayout training;
    TextView logout,count;
    String TAG = "Main";
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
        sharedPreferenceActivity = new SharedPreferenceActivity(context);

        contributions = findViewById(R.id.contributions);
        loans = findViewById(R.id.loans);
        account = findViewById(R.id.account);
        feedback = findViewById(R.id.feedback);
        withdraw = findViewById(R.id.withdraw);
        about = findViewById(R.id.about);
        logout = findViewById(R.id.logout);
        training = findViewById(R.id.training);
        count = findViewById(R.id.count);
        help = findViewById(R.id.help);
        myaccount = findViewById(R.id.myaccount);


       myaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this, my_account.class);
                startActivity(intent);
            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this, help_home.class);
                startActivity(intent);
            }
        });
        contributions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent( MainActivity.this, contributions_home.class);
                startActivity(intent);

            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent( MainActivity.this, LogIn.class);
                startActivity(intent);

            }
        });

      /*  withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent( MainActivity.this, withdraw_home.class);
                startActivity(intent);

            }
        });*/
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent( MainActivity.this, acount_home.class);
                startActivity(intent1);
            }
        });
        loans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent( MainActivity.this, loans_home.class);
                startActivity(intent1);
            }
        });

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent( MainActivity.this, FeedbackHistory.class);
                startActivity(intent1);
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent( MainActivity.this, About.class);
                startActivity(intent1);
            }
        });

        training.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent11 = new Intent( MainActivity.this, training_home.class);
                startActivity(intent11);
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
                            Log.e(TAG, " size is  "+ String.valueOf(response.body().getInformation().size()));

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


