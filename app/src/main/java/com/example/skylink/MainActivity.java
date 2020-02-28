package com.example.skylink;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.skylink.Contributions.contributions_home;
import com.example.skylink.Feedback.FeedbackHistory;
import com.example.skylink.Feedback.feedback;
import com.example.skylink.Loans.loans_home;
import com.example.skylink.LogIn.LogIn;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {


    Context context;
    Button contributions, loans, account, feedback, withdraw,about;
    TextView logout;
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

        contributions = findViewById(R.id.contributions);
        loans = findViewById(R.id.loans);
        account = findViewById(R.id.account);
        feedback = findViewById(R.id.feedback);
        withdraw = findViewById(R.id.withdraw);
        about = findViewById(R.id.about);
        logout = findViewById(R.id.logout);

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



    }

    }


