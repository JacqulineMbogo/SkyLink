package com.example.skylink;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skylink.Utility.AppUtilits;
import com.example.skylink.Utility.Constant;
import com.example.skylink.Utility.NetworkUtility;
import com.example.skylink.Utility.SharedPreferenceActivity;
import com.example.skylink.WebServices.ServiceWrapper;
import com.example.skylink.beanResponse.ContributionRes;
import com.example.skylink.beanResponse.LoanPaymentsRes;
import com.example.skylink.beanResponse.LoansApplicationRes;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class acount_home extends AppCompatActivity {


    TextView fname, lname,id, username, email, phone, newkin,savings,loans,balances, payments;
    Context context;
    String total_amount;
    SharedPreferenceActivity sharedPreferenceActivity;
    private RecyclerView recycler_kin;
    private  String TAG =" account_home";

  Float balance;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);


        context = this;
        sharedPreferenceActivity = new SharedPreferenceActivity(context);
        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        id = findViewById(R.id.id);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        savings= findViewById(R.id.savings);
        loans = findViewById(R.id.loans);
        balances = findViewById(R.id.balances);
        payments = findViewById(R.id.payments);

        fname.setText(sharedPreferenceActivity.getItem(Constant.FIRST_NAME));
        lname.setText(sharedPreferenceActivity.getItem(Constant.LAST_NAME));
        id.setText(sharedPreferenceActivity.getItem(Constant.ID_NUMBER));
        username.setText(sharedPreferenceActivity.getItem(Constant.USER_name));
        email.setText(sharedPreferenceActivity.getItem(Constant.USER_email));
        phone.setText("+254 "+sharedPreferenceActivity.getItem(Constant.USER_phone));

        getAllContributions();
        getUserLoans();
        getLoanPayments();

    }


    public  void getAllContributions(){



        if (!NetworkUtility.isNetworkConnected(acount_home.this)) {

            Toast.makeText(getApplicationContext(), "Network error", Toast.LENGTH_LONG).show();


        } else {

            //  Log.e(TAG, "  user value "+ SharePreferenceUtils.getInstance().getString(Constant.USER_DATA));
            ServiceWrapper service = new ServiceWrapper(null);
            Call<ContributionRes> call = service.ContributionCall("1234", sharedPreferenceActivity.getItem(Constant.USER_DATA));

            call.enqueue(new Callback<ContributionRes>() {
                @Override
                public void onResponse(Call<ContributionRes> call, Response<ContributionRes> response) {
                    Log.e(TAG, "response is " + response.body() + "  ---- " + new Gson().toJson(response.body()));
                    Log.e(TAG, "  ss sixe 1 ");
                    if (response.body() != null && response.isSuccessful()) {

                        Log.e(TAG, "  ss sixe 2 ");
                        if (response.body().getStatus() == 1) {
                            Log.e(TAG, "  ss sixe 3 ");


                            if (response.body().getInformation().size()>0){



                               savings.setText(response.body().getMsg() );



                            }

                        } else {

                            AppUtilits.displayMessage(acount_home.this, response.body().getMsg());
                        }
                    } else {

                        Toast.makeText(getApplicationContext(), "Please try again", Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onFailure(Call<ContributionRes> call, Throwable t) {

                    Toast.makeText(getApplicationContext(), "please try again. Failed to get user contributions ", Toast.LENGTH_LONG).show();

                }
            });


        }


    }



    public void getUserLoans() {



        if (!NetworkUtility.isNetworkConnected(acount_home.this)) {

            Toast.makeText(getApplicationContext(), "Network error", Toast.LENGTH_LONG).show();


        } else {

            //  Log.e(TAG, "  user value "+ SharePreferenceUtils.getInstance().getString(Constant.USER_DATA));
            ServiceWrapper service = new ServiceWrapper(null);
            Call<LoansApplicationRes> call = service.LoansCall("1234", sharedPreferenceActivity.getItem(Constant.USER_DATA));

            call.enqueue(new Callback<LoansApplicationRes>() {
                @Override
                public void onResponse(Call<LoansApplicationRes> call, Response<LoansApplicationRes> response) {
                    Log.e(TAG, "response is " + response.body() + "  ---- " + new Gson().toJson(response.body()));
                    Log.e(TAG, "  ss sixe 1 ");
                    if (response.body() != null && response.isSuccessful()) {

                        Log.e(TAG, "  ss sixe 2 ");
                        if (response.body().getStatus() == 1) {
                            Log.e(TAG, "  ss sixe 3 ");


                            if (response.body().getInformation().size() > 0) {


                                if (response.body().getMsg() == null) {

                                    loans.setText("0");
                                } else {
                                   loans.setText( response.body().getMsg());
                                }

                            }

                        } else {

                            AppUtilits.displayMessage(acount_home.this, response.body().getMsg());
                        }
                    } else {

                        Toast.makeText(getApplicationContext(), "Please try again", Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onFailure(Call<LoansApplicationRes> call, Throwable t) {


                    Toast.makeText(getApplicationContext(), "please try again. Failed to get user contributions ", Toast.LENGTH_LONG).show();

                }
            });


        }
    }

    public  void  getLoanPayments(){
        if (!NetworkUtility.isNetworkConnected(acount_home.this)) {

            Toast.makeText(getApplicationContext(), "Network error", Toast.LENGTH_LONG).show();


        } else {

            //  Log.e(TAG, "  user value "+ SharePreferenceUtils.getInstance().getString(Constant.USER_DATA));
            ServiceWrapper service = new ServiceWrapper(null);
            Call<LoanPaymentsRes> call = service.LoanPaymentCall("1234", sharedPreferenceActivity.getItem(Constant.USER_DATA),"");

            call.enqueue(new Callback<LoanPaymentsRes>() {
                @Override
                public void onResponse(Call<LoanPaymentsRes> call, Response<LoanPaymentsRes> response) {
                    Log.e(TAG, "response is " + response.body() + "  ---- " + new Gson().toJson(response.body()));
                    Log.e(TAG, "  ss sixe 1 ");
                    if (response.body() != null && response.isSuccessful()) {

                        Log.e(TAG, "  ss sixe 2 ");
                        if (response.body().getStatus() == 1) {
                            Log.e(TAG, "  ss sixe 3 ");


                            if (response.body().getInformation().size()>0){

                                if (response.body().getMsg() == null){

                                  payments.setText("0");
                                }else{

                                    payments.setText(response.body().getMsg());

                                }

                                balance = Float.valueOf(loans.getText().toString()) -  Float.valueOf(payments.getText().toString());
                                balances.setText(String.valueOf(balance));

                            }

                        } else {
                           payments.setText("0");


                           // AppUtilits.displayMessage(acount_home.this, response.body().getMsg());
                        }
                    } else {

                        Toast.makeText(getApplicationContext(), "Please try again", Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onFailure(Call<LoanPaymentsRes> call, Throwable t) {


                    Toast.makeText(getApplicationContext(), "please try again. Failed to get user contributions ", Toast.LENGTH_LONG).show();

                }
            });


        }

    }

    }
