package com.example.skylink;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.skylink.LogIn.LogIn;
import com.example.skylink.Utility.Constant;
import com.example.skylink.Utility.SharedPreferenceActivity;

public class SplashScreen extends AppCompatActivity {

    private String TAG ="splashAcctivity";
    Context context;
    SharedPreferenceActivity sharedPreferenceActivity;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = this;
        sharedPreferenceActivity = new SharedPreferenceActivity(context);
        setContentView(R.layout.splashscreen);
        init();
    }

    public void init(){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /// if user registered user
                // then show home screen
                // else  show login screen
                // key  register_user
                Log.e(TAG, "  counter running ");
                if (sharedPreferenceActivity.getItem(Constant.USER_DATA).equalsIgnoreCase("")){
                    // not registted user  so show login screen
                    Intent intent = new Intent(SplashScreen.this, LogIn.class);
                    startActivity(intent);
                }else {
                    // home sscreen
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);                finish();

                }

            }
        }, 5000 );

    }
}
