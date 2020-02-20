package com.example.skylink.LogIn;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.skylink.Contributions.contributions_home;
import com.example.skylink.MainActivity;
import com.example.skylink.R;
import com.example.skylink.Utility.AppUtilits;
import com.example.skylink.Utility.Constant;
import com.example.skylink.Utility.DataValidation;
import com.example.skylink.Utility.NetworkUtility;
import com.example.skylink.Utility.SharedPreferenceActivity;
import com.example.skylink.WebServices.ServiceWrapper;
import com.example.skylink.beanResponse.UserSignInRes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LogIn extends AppCompatActivity {

    Context context;
    TextView  signup_btn;
    LinearLayout login_btn;
    EditText id_number, password;
    SharedPreferenceActivity sharedPreferenceActivity;

    private String TAG = "LoginActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        context = this;

        this.setTitle("LogIn");

        sharedPreferenceActivity= new SharedPreferenceActivity(context);
        login_btn = findViewById(R.id.login_btn);
        signup_btn = findViewById(R.id.signup_btn);

        id_number = findViewById(R.id.id_number);
        password = findViewById(R.id.password);


        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogIn.this,SignUp.class);
                startActivity(intent);
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( DataValidation.isNotValidFullName(id_number.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Invalid username",Toast.LENGTH_LONG).show();

                }else if (DataValidation.isNotValidPassword(password.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Invalid password",Toast.LENGTH_LONG).show();

                }else {

                    LogIn_method();

                }
            }
        });


    }

    public  void  LogIn_method(){

        final AlertDialog progressbar = AppUtilits.createProgressBar(this);


        if (!NetworkUtility.isNetworkConnected(LogIn.this)){
            Toast.makeText(getApplicationContext(),"Network error",Toast.LENGTH_LONG).show();
            AppUtilits.destroyDialog(progressbar);

        }else {

            ServiceWrapper serviceWrapper = new ServiceWrapper(null);
            Call<UserSignInRes> userSigninCall = serviceWrapper.UserSigninCall(id_number.getText().toString(), password.getText().toString());
            userSigninCall.enqueue(new Callback<UserSignInRes>() {
                @Override
                public void onResponse(Call<UserSignInRes> call, Response<UserSignInRes> response) {

                    Log.d(TAG, "reponse : "+ response.toString());
                    if (response.body()!= null && response.isSuccessful()){
                        if (response.body().getStatus() ==1){
                            Log.e(TAG, "  user data "+  response.body().getInformation());
                            sharedPreferenceActivity.putItem(Constant.USER_DATA, response.body().getInformation().getUserId());
                            sharedPreferenceActivity.putItem(Constant.ID_NUMBER, response.body().getInformation().getIdNumber());
                            sharedPreferenceActivity.putItem(Constant.FIRST_NAME, response.body().getInformation().getFirstName());
                            sharedPreferenceActivity.putItem(Constant.LAST_NAME, response.body().getInformation().getLastName());
                            sharedPreferenceActivity.putItem(Constant.USER_name, response.body().getInformation().getUsername());
                            sharedPreferenceActivity.putItem(Constant.USER_email, response.body().getInformation().getEmail());
                            sharedPreferenceActivity.putItem(Constant.USER_phone, response.body().getInformation().getPhoneNumber());


                            AppUtilits.destroyDialog(progressbar);
                            // start home activity
                            Intent intent = new Intent(LogIn.this, MainActivity.class);
                            startActivity(intent);
                            finish();





                        }else  if (response.body().getStatus() ==0){
                            AppUtilits.displayMessage(LogIn.this,  response.body().getMsg());
                            AppUtilits.destroyDialog(progressbar);
                        }
                    }else {
                        AppUtilits.displayMessage(LogIn.this,  response.body().getMsg());
                        Toast.makeText(getApplicationContext(),"Request failed",Toast.LENGTH_LONG).show();
                        AppUtilits.destroyDialog(progressbar);

                    }
                }

                @Override
                public void onFailure(Call<UserSignInRes> call, Throwable t) {
                    Log.e(TAG, " failure "+ t.toString());
                    Toast.makeText(getApplicationContext(),"Request failed",Toast.LENGTH_LONG).show();
                    AppUtilits.destroyDialog(progressbar);

                }
            });




        }





    }
}
