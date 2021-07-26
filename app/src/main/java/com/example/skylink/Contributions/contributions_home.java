package com.example.skylink.Contributions;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skylink.R;
import com.example.skylink.Spinner_Adapter;
import com.example.skylink.Utility.AppUtilits;
import com.example.skylink.Utility.Constant;
import com.example.skylink.Utility.DataValidation;
import com.example.skylink.Utility.NetworkUtility;
import com.example.skylink.Utility.SharedPreferenceActivity;
import com.example.skylink.WebServices.ServiceWrapper;
import com.example.skylink.beanResponse.ContributionRes;
import com.example.skylink.beanResponse.ContributionTypeRes;
import com.example.skylink.beanResponse.SaveContributionRes;
import com.example.skylink.beanResponse.SingleContributionRes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class contributions_home extends AppCompatActivity {


    private static Context context1;
    Context context;

    RecyclerView collection_recycler;
    static TextView total_amount,varianceamt;
    FloatingActionButton new_contribution;
    static ProgressBar progressbar;
    static String TAG = "contriutions";
    static SharedPreferenceActivity sharedPreferenceActivity;
    private static contributions_adapter  contributions_adapter;
    private static contribution_single_adapter contribution_single_adapter;
    private static final ArrayList<contributions_model> contributionsModels = new ArrayList<>();

    private  contributions_type_adapter  contributions_type_adapter;
    private static final ArrayList<contributions_type_model> contributionsTypeModels = new ArrayList<>();
    private static String pin,min;
    private String selectedItemText;

    private static Double variance;

    private ArrayList<contribution_type_model> goodModelArrayList;
    private ArrayList<String> contribution_type = new ArrayList<String>();
    static String counts;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contributions_home);

        context = this;
        context1 = this;

        this.setTitle("My Contributions");


        sharedPreferenceActivity = new SharedPreferenceActivity(context);
        collection_recycler = findViewById(R.id.collections_recycler);
        progressbar = findViewById(R.id.progressBar);
        new_contribution = findViewById(R.id.new_contribution);
        total_amount = findViewById(R.id.total_amount);
        varianceamt = findViewById(R.id.variance);

        LinearLayoutManager mLayoutManger = new LinearLayoutManager( context, RecyclerView.VERTICAL, false);
        collection_recycler.setLayoutManager(mLayoutManger);
        collection_recycler.setItemAnimator(new DefaultItemAnimator());

        contributions_adapter= new contributions_adapter(context, contributionsModels);
        collection_recycler.setAdapter(contributions_adapter);

       /* LinearLayoutManager mLayoutManger1 = new LinearLayoutManager( context, RecyclerView.VERTICAL, false);
        collection_recycler.setLayoutManager(mLayoutManger1);
        collection_recycler.setItemAnimator(new DefaultItemAnimator());

        contribution_single_adapter= new contribution_single_adapter(context, contributionsModels);
        collection_recycler.setAdapter(contribution_single_adapter);*/


        contributions_type_adapter= new contributions_type_adapter(contributionsTypeModels, context);

        new_contribution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                newcontribution();
            }
        });

        getAllContributions();
    }

    public static void newcontribution() {

        final View  view;



        final Dialog dialog;

        view = LayoutInflater.from(context1).inflate(R.layout.popup, null, false);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context1);

        alertDialog.setView(view);

        alertDialog.setCancelable(true);


        dialog = alertDialog.create();

        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        dialog.show();


        final Window dialogWindow = dialog.getWindow();
        dialogWindow.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialogWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);


        final LinearLayout codelinear = view.findViewById(R.id.codelinear);
        final EditText amount = view.findViewById(R.id.contributionamount);
        final EditText code = view.findViewById(R.id.code);
        final Button cancel = view.findViewById(R.id.cancel);
        final Button okay = view.findViewById(R.id.ok);
        final Spinner contributionspinner  = view.findViewById(R.id.contributiontypespinner);

        //   getContributionTypes();


        if (!NetworkUtility.isNetworkConnected(context1)) {
            progressbar.setVisibility(View.GONE);
            Toast.makeText(context1, "Network error", Toast.LENGTH_LONG).show();


        } else {
            progressbar.setVisibility(View.GONE);
            //  Log.e(TAG, "  user value "+ SharePreferenceUtils.getInstance().getString(Constant.USER_DATA));
            ServiceWrapper service;
            service = new ServiceWrapper(null);
            Call<ContributionTypeRes> call = service.ContributionTypeCall("1234");


            call.enqueue(new Callback<ContributionTypeRes>() {
                @Override
                public void onResponse(Call<ContributionTypeRes> call, Response<ContributionTypeRes> response) {
                    Log.e(TAG, "responsez is " + response.body() + "  ---- " + new Gson().toJson(response.body()));
                    Log.e(TAG, "  ss sixe 1 ");
                    if (response.body() != null && response.isSuccessful()) {
                        Log.e(TAG, "  ss sixe 2 ");
                        if (response.body().getStatus() == 1) {
                            Log.e(TAG, "  ss sixe 3 ");

                            contributionsTypeModels.clear();
                            if (response.body().getInformation().size()>0){

                                String[] code = new String[response.body().getInformation().size()];
                                String[] name = new String[response.body().getInformation().size()];
                                String[] min = new String[response.body().getInformation().size()];

                                Log.d("codey", String.valueOf(response.body().getInformation().size()));
                                for (int i =0; i<response.body().getInformation().size(); i++){

                                    contributionsTypeModels.add(  new contributions_type_model(response.body().getInformation().get(i).getSavingsTypeId(),response.body().getInformation().get(i).getName(),response.body().getInformation().get(i).getDescription(),response.body().getInformation().get(i).getMinAmount(),response.body().getInformation().get(i).getMaxAmount(),response.body().getInformation().get(i).getFactorLoan(),response.body().getInformation().get(i).getPenaltyId(),response.body().getInformation().get(i).getPercentAmount(),response.body().getInformation().get(i).getAutodeduct(),response.body().getInformation().get(i).getSavingsTpe()));
                                    code[i] = response.body().getInformation().get(i).getSavingsTypeId();
                                    name[i] = response.body().getInformation().get(i).getName();
                                    min[i] = response.body().getInformation().get(i).getMinAmount();
                                    Log.d("codee", code[i]);
                                    Log.d("codee", name[i]);
                                }

                                Spinner_Adapter spinner_adapter= new Spinner_Adapter(context1, code, name);
                                spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                contributionspinner.setAdapter(spinner_adapter);

                            }

                        } else {
                            progressbar.setVisibility(View.GONE);
                            AppUtilits.displayMessage(context1, response.body().getMsg());
                        }
                    } else {
                        progressbar.setVisibility(View.GONE);
                        Toast.makeText(context1, "Please try again", Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onFailure(Call<ContributionTypeRes> call, Throwable t) {
                    progressbar.setVisibility(View.GONE);

                    Toast.makeText(context1, "please try again. Failed to get user contributions ", Toast.LENGTH_LONG).show();

                }
            });


        }


        contributionspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                pin = parent.getItemAtPosition(position).toString();

                counts= contributionsTypeModels.get(position).getSavings_type_id();

                min= contributionsTypeModels.get(position).getMin_amount();
                Log.d("counts", counts);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                //  codelinear.setVisibility(View.VISIBLE);

            }
        });

        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!amount.getText().toString().isEmpty()){





                            if(Double.valueOf(amount.getText().toString()) >= Double.valueOf(min) ){
                                variance = 0.0;
                            }else{
                                    variance =  Double.valueOf(min) - Double.valueOf(amount.getText().toString());

                            }
                    saveNewContribution(counts, amount.getText().toString(), String.valueOf(variance),sharedPreferenceActivity.getItem("meeting_id"));
                    dialog.cancel();



                }else{

                    amount.setError("please input amount");
                    amount.requestFocus();

                }

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });


    }


    public static void getAllContributions(){

        progressbar.setVisibility(View.VISIBLE);

        if (!NetworkUtility.isNetworkConnected(context1)) {
            progressbar.setVisibility(View.GONE);
            Toast.makeText(context1, "Network error", Toast.LENGTH_LONG).show();


        } else {
            progressbar.setVisibility(View.GONE);
            //  Log.e(TAG, "  user value "+ SharePreferenceUtils.getInstance().getString(Constant.USER_DATA));
            ServiceWrapper service = new ServiceWrapper(null);
            Call<ContributionRes> call = service.ContributionCall("1234", sharedPreferenceActivity.getItem("member_id"));

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

                                progressbar.setVisibility(View.GONE);

                              total_amount.setText( "Contribution : "+response.body().getTotalAmount() );
                                varianceamt.setText( "variance: "+response.body().getVariance() );

                                sharedPreferenceActivity.putItem(Constant.TOTAL_CONTRIBUTIONS, response.body().getMsg());
                               contributionsModels.clear();


                                for (int i =0; i<response.body().getInformation().size(); i++){


                                    contributionsModels.add(  new contributions_model(response.body().getInformation().get(i).getSavingsId(),response.body().getInformation().get(i).getAmount(),response.body().getInformation().get(i).getVariance(),response.body().getInformation().get(i).getMemberId(),response.body().getInformation().get(i).getCreateDate(),response.body().getInformation().get(i).getUpdateDate(),response.body().getInformation().get(i).getComments(),response.body().getInformation().get(i).getCreatedBy(),response.body().getInformation().get(i).getSavingTypeId(),response.body().getInformation().get(i).getSavingsDate(),response.body().getInformation().get(i).getSavingName()));
                                    Log.d("model",response.body().getInformation().get(i).getSavingsId());


                                }
                                contributions_adapter.notifyDataSetChanged();
                            }

                        } else {
                            progressbar.setVisibility(View.GONE);
                            AppUtilits.displayMessage(context1, response.body().getMsg());
                        }
                    } else {
                        progressbar.setVisibility(View.GONE);
                        Toast.makeText(context1, "Please try again", Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onFailure(Call<ContributionRes> call, Throwable t) {
                    progressbar.setVisibility(View.GONE);

                    Toast.makeText(context1, "please try again. Failed to get user contributions ", Toast.LENGTH_LONG).show();

                }
            });


        }


    }

  /*  public static void getsingleContributions(String type_id){

        progressbar.setVisibility(View.VISIBLE);

        if (!NetworkUtility.isNetworkConnected(context1)) {
            progressbar.setVisibility(View.GONE);
            Toast.makeText(context1, "Network error", Toast.LENGTH_LONG).show();


        } else {
            progressbar.setVisibility(View.GONE);
            //  Log.e(TAG, "  user value "+ SharePreferenceUtils.getInstance().getString(Constant.USER_DATA));
            ServiceWrapper service = new ServiceWrapper(null);
            Call<SingleContributionRes> call = service.SingleContributionCall("1234", type_id,sharedPreferenceActivity.getItem("member_id"));

            call.enqueue(new Callback<SingleContributionRes>() {
                @Override
                public void onResponse(Call<SingleContributionRes> call, Response<SingleContributionRes> response) {
                    Log.e(TAG, "response is " + response.body() + "  ---- " + new Gson().toJson(response.body()));
                    Log.e(TAG, "  ss sixe 1 ");
                    if (response.body() != null && response.isSuccessful()) {

                        Log.e(TAG, "  ss sixe 2 ");
                        if (response.body().getStatus() == 1) {
                            Log.e(TAG, "  ss sixe 3 ");


                            if (response.body().getInformation().size()>0){

                                progressbar.setVisibility(View.GONE);

                                total_amount.setText( "Total Contribution : "+response.body().getTotalAmount() );
                                varianceamt.setText( "Total variance: "+response.body().getVariance() );

                                sharedPreferenceActivity.putItem(Constant.TOTAL_CONTRIBUTIONS, response.body().getMsg());
                                contributionsModels.clear();


                                for (int i =0; i<response.body().getInformation().size(); i++){


                                    contributionsModels.add(  new contributions_model(response.body().getInformation().get(i).getSavingsId(),response.body().getInformation().get(i).getAmount(),response.body().getInformation().get(i).getVariance(),response.body().getInformation().get(i).getMemberId(),response.body().getInformation().get(i).getCreateDate(),response.body().getInformation().get(i).getUpdateDate(),response.body().getInformation().get(i).getComments(),response.body().getInformation().get(i).getCreatedBy(),response.body().getInformation().get(i).getSavingTypeId(),response.body().getInformation().get(i).getSavingsDate(),response.body().getInformation().get(i).getSavingName()));
                                    Log.d("model",response.body().getInformation().get(i).getSavingsId());


                                }
                                contribution_single_adapter.notifyDataSetChanged();
                            }

                        } else {
                            progressbar.setVisibility(View.GONE);
                            AppUtilits.displayMessage(context1, response.body().getMsg());
                        }
                    } else {
                        progressbar.setVisibility(View.GONE);
                        Toast.makeText(context1, "Please try again", Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onFailure(Call<SingleContributionRes> call, Throwable t) {
                    progressbar.setVisibility(View.GONE);

                    Toast.makeText(context1, "please try again. Failed to get user contributions ", Toast.LENGTH_LONG).show();

                }
            });


        }


    }*/

    public static void saveNewContribution(String type, String amount,String variance, String meeting_id){

        if (!NetworkUtility.isNetworkConnected(context1)){
            AppUtilits.displayMessage(context1,  "Network error");


        }else {

            //  Log.e(TAG, "  user value "+ SharePreferenceUtils.getInstance().getString(Constant.USER_DATA));
            ServiceWrapper service = new ServiceWrapper(null);
            Call<SaveContributionRes> call = service.SaveContributionCall("12345", type,amount,variance,sharedPreferenceActivity.getItem("member_id"), sharedPreferenceActivity.getItem(Constant.USER_DATA), meeting_id);
            call.enqueue(new Callback<SaveContributionRes>() {
                @Override
                public void onResponse(Call<SaveContributionRes> call, Response<SaveContributionRes> response) {

                    if (response.body() != null && response.isSuccessful()) {
                        if (response.body().getStatus() == 1) {


                          AppUtilits.displayMessage(context1, "Operation Successful");
                            getAllContributions();


                        }else {

                            AppUtilits.displayMessage(context1, "failed to make new contribution");
                        }
                    }else {
                        AppUtilits.displayMessage(context1, "network error");
                    }


                }

                @Override
                public void onFailure(Call<SaveContributionRes> call, Throwable t) {
                    // Log.e(TAG, "  fail- add to cart item "+ t.toString());

                    AppUtilits.displayMessage(context1, "failed to make new contribution");
                }
            });
        }
    }


}
