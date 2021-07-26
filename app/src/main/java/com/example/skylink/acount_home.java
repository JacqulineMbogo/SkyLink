package com.example.skylink;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skylink.Utility.Constant;
import com.example.skylink.Utility.SharedPreferenceActivity;

import java.util.ArrayList;

public class acount_home extends AppCompatActivity {


    TextView fname, lname,id, username, email, phone, newkin;
    Context context;
    SharedPreferenceActivity sharedPreferenceActivity;
    private RecyclerView recycler_kin;
    private  String TAG =" account_home";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);


        context = this;

        this.setTitle("Account");
        sharedPreferenceActivity = new SharedPreferenceActivity(context);
        fname = findViewById(R.id.fname);
        //lname = findViewById(R.id.lname);
        id = findViewById(R.id.id);
        //username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);

        fname.setText(sharedPreferenceActivity.getItem(Constant.FULL_NAME));
        //lname.setText(sharedPreferenceActivity.getItem(Constant.LAST_NAME));
        id.setText(sharedPreferenceActivity.getItem(Constant.ID_NUMBER));
        //username.setText(sharedPreferenceActivity.getItem(Constant.USER_name));
        email.setText(sharedPreferenceActivity.getItem(Constant.USER_email));
        phone.setText(sharedPreferenceActivity.getItem(Constant.USER_phone));

    }


    }
