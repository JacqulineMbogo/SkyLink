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

import com.example.skylink.MainActivity;
import com.example.skylink.R;
import com.example.skylink.Utility.AppUtilits;
import com.example.skylink.Utility.Constant;
import com.example.skylink.Utility.DataValidation;
import com.example.skylink.Utility.NetworkUtility;
import com.example.skylink.Utility.SharedPreferenceActivity;
import com.example.skylink.WebServices.ServiceWrapper;
import com.example.skylink.beanResponse.ChangePassword;
import com.example.skylink.beanResponse.UserSignInRes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPassword extends AppCompatActivity {

    Context context;

    LinearLayout login_btn;
    EditText id_number, password,password2;

    SharedPreferenceActivity sharedPreferenceActivity;

    private String TAG = "ChangePassActivity";
    int doubleBackToExitPressed = 1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resetpassword);


        context = this;

        this.setTitle("Change Password");

        id_number = findViewById(R.id.id_number);
        password = findViewById(R.id.password);
        password2 = findViewById(R.id.password2);

        login_btn = findViewById(R.id.login_btn);


        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( DataValidation.isNotValidFullName(id_number.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Invalid username",Toast.LENGTH_LONG).show();

                }else if (DataValidation.isNotValidPassword(password.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Password should be at least 6 characters",Toast.LENGTH_LONG).show();

                }else if (!password.getText().toString().equals(password2.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "passwords do not match", Toast.LENGTH_LONG).show();


                }else{

                    resetPassword();


                }
            }
        });
    }

    public void resetPassword() {

        final AlertDialog progressbar = AppUtilits.createProgressBar(this);


        if (!NetworkUtility.isNetworkConnected(ResetPassword.this)){
            Toast.makeText(getApplicationContext(),"Network error",Toast.LENGTH_LONG).show();
            AppUtilits.destroyDialog(progressbar);

        }else {

            ServiceWrapper serviceWrapper = new ServiceWrapper(null);
            Call<ChangePassword> ChangePasswordCall = serviceWrapper.ChangePasswordCall(id_number.getText().toString(), password2.getText().toString());
            ChangePasswordCall.enqueue(new Callback<ChangePassword>() {
                @Override
                public void onResponse(Call<ChangePassword> call, Response<ChangePassword> response) {

                    Log.d(TAG, "reponse : "+ response.toString());
                    if (response.body()!= null && response.isSuccessful()){
                        if (response.body().getStatus() ==1){
                            Log.e(TAG, "  user data "+  response.body().getMsg());
                             AppUtilits.destroyDialog(progressbar);
                            // start home activity
                            Toast.makeText(getApplicationContext(),response.body().getMsg(),Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(ResetPassword.this, LogIn.class);
                            startActivity(intent);
                            finish();





                        }else  if (response.body().getStatus() ==0){
                            AppUtilits.displayMessage(ResetPassword.this,  response.body().getMsg());
                            AppUtilits.destroyDialog(progressbar);
                        }
                    }else {
                        AppUtilits.displayMessage(ResetPassword.this,  response.body().getMsg());
                        Toast.makeText(getApplicationContext(),"Request failed",Toast.LENGTH_LONG).show();
                        AppUtilits.destroyDialog(progressbar);

                    }
                }

                @Override
                public void onFailure(Call<ChangePassword> call, Throwable t) {
                    Log.e(TAG, " failure "+ t.toString());
                    Toast.makeText(getApplicationContext(),"Request failed",Toast.LENGTH_LONG).show();
                    AppUtilits.destroyDialog(progressbar);

                }
            });




        }
    }
}
