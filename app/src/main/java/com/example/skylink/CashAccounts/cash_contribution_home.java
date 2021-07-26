package com.example.skylink.CashAccounts;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
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

import com.example.skylink.Contributions.contributions_model;
import com.example.skylink.Contributions.contributions_type_model;
import com.example.skylink.R;
import com.example.skylink.Spinner_Adapter;
import com.example.skylink.Utility.AppUtilits;
import com.example.skylink.Utility.Constant;
import com.example.skylink.Utility.NetworkUtility;
import com.example.skylink.Utility.SharedPreferenceActivity;
import com.example.skylink.WebServices.ServiceWrapper;
import com.example.skylink.beanResponse.CashContributionRes;
import com.example.skylink.beanResponse.CashContributionsTypesRes;
import com.example.skylink.beanResponse.ContributionRes;
import com.example.skylink.beanResponse.ContributionTypeRes;
import com.example.skylink.beanResponse.SaveCashContribution;
import com.example.skylink.beanResponse.SaveContributionRes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class cash_contribution_home extends AppCompatActivity {


    private static Context context1;
    Context context;

    RecyclerView cash_collection_recycler;
    static TextView cash_total_amount,cash_varianceamt;
    FloatingActionButton new_contribution;
    static ProgressBar progressbar;
    static String TAG = "cash_contriutions";
    static SharedPreferenceActivity sharedPreferenceActivity;
    private cash_contributions_adapter cash_contributions_adapter;
    //private cash_contribution_single_adapter cash_contribution_single_adapter;
    private static final ArrayList<cash_contributions_model> cash_contributionsModels = new ArrayList<>();

    private cash_contributions_type_adapter cash_contributions_type_adapter;
    private static final ArrayList<cash_contributions_type_model> cash_contributionsTypeModels = new ArrayList<>();
    private static String cash_pin,cash_min;
    private String cash_selectedItemText;

    private static Double cash_variance;

    private ArrayList<cash_contributions_type_model> cash_goodModelArrayList;
    private ArrayList<String> cash_contribution_type = new ArrayList<String>();
    static String cash_counts;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cash_activity_contributions_home);

        context = this;
        context1 = this;

        this.setTitle("My Contributions");


        sharedPreferenceActivity = new SharedPreferenceActivity(context);
        cash_collection_recycler = findViewById(R.id.collections_recycler);
        progressbar = findViewById(R.id.progressBar);
        new_contribution = findViewById(R.id.new_contribution);
        cash_total_amount = findViewById(R.id.total_amount);
        cash_varianceamt = findViewById(R.id.variance);

        LinearLayoutManager mLayoutManger = new LinearLayoutManager( context, RecyclerView.VERTICAL, false);
        cash_collection_recycler.setLayoutManager(mLayoutManger);
        cash_collection_recycler.setItemAnimator(new DefaultItemAnimator());

        cash_contributions_adapter= new cash_contributions_adapter(context, cash_contributionsModels);
        cash_collection_recycler.setAdapter(cash_contributions_adapter);

       /* LinearLayoutManager mLayoutManger1 = new LinearLayoutManager( context, RecyclerView.VERTICAL, false);
        collection_recycler.setLayoutManager(mLayoutManger1);
        collection_recycler.setItemAnimator(new DefaultItemAnimator());

        contribution_single_adapter= new contribution_single_adapter(context, contributionsModels);
        collection_recycler.setAdapter(contribution_single_adapter);*/


        cash_contributions_type_adapter= new cash_contributions_type_adapter(cash_contributionsTypeModels, context);

        new_contribution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                newcashcontribution();
            }
        });

        getAllCashContributions();
    }

    private void newcashcontribution() {


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
            Call<CashContributionsTypesRes> call = service.CashContributionsTypesResCall("1234");


            call.enqueue(new Callback<CashContributionsTypesRes>() {
                @Override
                public void onResponse(Call<CashContributionsTypesRes> call, Response<CashContributionsTypesRes> response) {
                    Log.e(TAG, "responsez is " + response.body() + "  ---- " + new Gson().toJson(response.body()));
                    Log.e(TAG, "  ss sixe 1 ");
                    if (response.body() != null && response.isSuccessful()) {
                        Log.e(TAG, "  ss sixe 2 ");
                        if (response.body().getStatus() == 1) {
                            Log.e(TAG, "  ss sixe 3 ");

                            cash_contributionsTypeModels.clear();
                            if (response.body().getInformation().size()>0){

                                String[] code = new String[response.body().getInformation().size()];
                                String[] name = new String[response.body().getInformation().size()];
                                String[] min = new String[response.body().getInformation().size()];

                                Log.d("codey", String.valueOf(response.body().getInformation().size()));
                                for (int i =0; i<response.body().getInformation().size(); i++){

                                    cash_contributionsTypeModels.add(  new cash_contributions_type_model(response.body().getInformation().get(i).getCashId(),response.body().getInformation().get(i).getCashName(),response.body().getInformation().get(i).getDescription(),response.body().getInformation().get(i).getAccountType(),response.body().getInformation().get(i).getUnpaidInterest(),response.body().getInformation().get(i).getInterestAccountType(),response.body().getInformation().get(i).getUnpaidPenalty(),response.body().getInformation().get(i).getPenaltyPayType(),response.body().getInformation().get(i).getAutodeductFromSavings(),response.body().getInformation().get(i).getDeductSavingsType(),response.body().getInformation().get(i).getFactorLoan()));
                                    code[i] = response.body().getInformation().get(i).getCashId();
                                    name[i] = response.body().getInformation().get(i).getCashName();
                                   // min[i] = response.body().getInformation().get(i).getMinAmount();
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
                public void onFailure(Call<CashContributionsTypesRes> call, Throwable t) {
                    progressbar.setVisibility(View.GONE);

                    Toast.makeText(context1, "please try again. Failed to get user contributions ", Toast.LENGTH_LONG).show();

                }
            });


        }


        contributionspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                cash_pin = parent.getItemAtPosition(position).toString();

                cash_counts=  cash_contributionsTypeModels.get(position).getCash_id();

                cash_min=  "0";


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



                    cash_variance = 0.0;


                    saveNewcashContribution(cash_counts, amount.getText().toString(), String.valueOf(cash_variance),sharedPreferenceActivity.getItem("meeting_id"));
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

    private void saveNewcashContribution(String type, String amount,String variance, String meeting_id){

        if (!NetworkUtility.isNetworkConnected(context1)){
            AppUtilits.displayMessage(context1,  "Network error");


        }else {

            //  Log.e(TAG, "  user value "+ SharePreferenceUtils.getInstance().getString(Constant.USER_DATA));
            ServiceWrapper service = new ServiceWrapper(null);
            Call<SaveCashContribution> call = service.SaveCashContributionCall("12345", type,amount,variance,sharedPreferenceActivity.getItem("member_id"), sharedPreferenceActivity.getItem(Constant.USER_DATA), meeting_id);
            call.enqueue(new Callback<SaveCashContribution>() {
                @Override
                public void onResponse(Call<SaveCashContribution> call, Response<SaveCashContribution> response) {

                    if (response.body() != null && response.isSuccessful()) {
                        if (response.body().getStatus() == 1) {


                            AppUtilits.displayMessage(context1, "Operation Successful");
                            getAllCashContributions();


                        }else {

                            AppUtilits.displayMessage(context1, "failed to make new contribution");
                        }
                    }else {
                        AppUtilits.displayMessage(context1, "network error");
                    }


                }

                @Override
                public void onFailure(Call<SaveCashContribution> call, Throwable t) {
                    // Log.e(TAG, "  fail- add to cart item "+ t.toString());

                    AppUtilits.displayMessage(context1, "failed to make new contribution");
                }
            });
        }
    }
    private void getAllCashContributions() {

        progressbar.setVisibility(View.VISIBLE);

        if (!NetworkUtility.isNetworkConnected(context1)) {
            progressbar.setVisibility(View.GONE);
            Toast.makeText(context1, "Network error", Toast.LENGTH_LONG).show();


        } else {
            progressbar.setVisibility(View.GONE);
            //  Log.e(TAG, "  user value "+ SharePreferenceUtils.getInstance().getString(Constant.USER_DATA));
            ServiceWrapper service = new ServiceWrapper(null);
            Call<CashContributionRes> call = service.CashContributionResCall("1234", sharedPreferenceActivity.getItem("member_id"));

            call.enqueue(new Callback<CashContributionRes>() {
                @Override
                public void onResponse(Call<CashContributionRes> call, Response<CashContributionRes> response) {
                    Log.e(TAG, "response is " + response.body() + "  ---- " + new Gson().toJson(response.body()));
                    Log.e(TAG, "  ss sixe 1 ");
                    if (response.body() != null && response.isSuccessful()) {

                        Log.e(TAG, "  ss sixe 2 ");
                        if (response.body().getStatus() == 1) {
                            Log.e(TAG, "  ss sixe 3 ");


                            if (response.body().getInformation().size()>0){

                                progressbar.setVisibility(View.GONE);

                                cash_total_amount.setText( "Contribution : "+response.body().getTotalAmount() );
                                cash_varianceamt.setText( "variance: "+response.body().getVariance() );

                                sharedPreferenceActivity.putItem(Constant.TOTAL_CONTRIBUTIONS, response.body().getMsg());
                                cash_contributionsModels.clear();


                                for (int i =0; i<response.body().getInformation().size(); i++){


                                    cash_contributionsModels.add(  new cash_contributions_model(response.body().getInformation().get(i).getCashCollectionsId(),response.body().getInformation().get(i).getAmount(),response.body().getInformation().get(i).getVariance(),response.body().getInformation().get(i).getCashId(),response.body().getInformation().get(i).getMemberId(),response.body().getInformation().get(i).getMeetingId(),response.body().getInformation().get(i).getCreateDate(),response.body().getInformation().get(i).getUpdateDate(),response.body().getInformation().get(i).getCashName()));
                                    Log.d("model",response.body().getInformation().get(i).getCashCollectionsId());


                                }
                                cash_contributions_adapter.notifyDataSetChanged();
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
                public void onFailure(Call<CashContributionRes> call, Throwable t) {
                    progressbar.setVisibility(View.GONE);

                    Toast.makeText(context1, "please try again. Failed to get user contributions ", Toast.LENGTH_LONG).show();

                }
            });


        }

    }
}
