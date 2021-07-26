package com.example.skylink.MandatoryContributions;

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
import com.example.skylink.beanResponse.ContributionRes;
import com.example.skylink.beanResponse.ContributionTypeRes;
import com.example.skylink.beanResponse.MandatoryContributinTypeRes;
import com.example.skylink.beanResponse.SaveContributionRes;
import com.example.skylink.beanResponse.SaveMandatoryContributionRes;
import com.example.skylink.beanResponse.mandatoryContributionRes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class mandatory_contributions_home extends AppCompatActivity {
    private static Context context1;
    Context context;

    RecyclerView mandatory_collection_recycler;
    static TextView mandatory_total_amount,mandatory_varianceamt;
    FloatingActionButton new_contribution;
    static ProgressBar progressbar;
    static String TAG = "contriutions";
    static SharedPreferenceActivity sharedPreferenceActivity;
    private static mandatory_contributions_adapter mandatory_contributions_adapter;
    //private static mandatory_contribution_single_adapter mandatory_contribution_single_adapter;
    private static final ArrayList<mandatory_contributions_model> mandatory_contributionsModels = new ArrayList<>();

    private mandatory_contributions_type_adapter mandatory_contributions_type_adapter;
    private static final ArrayList<mandatory_contributions_type_model> mandatory_contributionsTypeModels = new ArrayList<>();
    private static String mandatory_pin,mandatory_min;
    private String mandatory_selectedItemText;

    private static Double variance;

    private ArrayList<mandatory_contributions_type_model> mandatory_goodModelArrayList;
    private ArrayList<String> mandatory_contribution_type = new ArrayList<String>();
    static String mandatory_counts;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mandatory_contributions_home);

        context = this;
        context1 = this;

        this.setTitle("Mandatory Contributions");


        sharedPreferenceActivity = new SharedPreferenceActivity(context);
        mandatory_collection_recycler = findViewById(R.id.mandatory_collections_recycler);
        progressbar = findViewById(R.id.progressBar);
        new_contribution = findViewById(R.id.new_contribution);
        mandatory_total_amount = findViewById(R.id.mandatory_total_amount);
        mandatory_varianceamt = findViewById(R.id.mandatory_variance);

        LinearLayoutManager mLayoutManger = new LinearLayoutManager( context, RecyclerView.VERTICAL, false);
        mandatory_collection_recycler.setLayoutManager(mLayoutManger);
        mandatory_collection_recycler.setItemAnimator(new DefaultItemAnimator());

        mandatory_contributions_adapter= new mandatory_contributions_adapter(context, mandatory_contributionsModels);
        mandatory_collection_recycler.setAdapter(mandatory_contributions_adapter);

       /* LinearLayoutManager mLayoutManger1 = new LinearLayoutManager( context, RecyclerView.VERTICAL, false);
        collection_recycler.setLayoutManager(mLayoutManger1);
        collection_recycler.setItemAnimator(new DefaultItemAnimator());

        contribution_single_adapter= new contribution_single_adapter(context, contributionsModels);
        collection_recycler.setAdapter(contribution_single_adapter);*/


        mandatory_contributions_type_adapter= new mandatory_contributions_type_adapter(mandatory_contributionsTypeModels, context);

        new_contribution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                newmandatory_contribution();
            }
        });

        getAllMandatoryContributions();
    }

    public static void newmandatory_contribution() {

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
            Call<MandatoryContributinTypeRes> call = service.MandatoryContributinTypeResCall("1234");


            call.enqueue(new Callback<MandatoryContributinTypeRes>() {
                @Override
                public void onResponse(Call<MandatoryContributinTypeRes> call, Response<MandatoryContributinTypeRes> response) {
                    Log.e(TAG, "responsez is " + response.body() + "  ---- " + new Gson().toJson(response.body()));
                    Log.e(TAG, "  ss sixe 1 ");
                    if (response.body() != null && response.isSuccessful()) {
                        Log.e(TAG, "  ss sixe 2 ");
                        if (response.body().getStatus() == 1) {
                            Log.e(TAG, "  ss sixe 3 ");

                            mandatory_contributionsTypeModels.clear();
                            if (response.body().getInformation().size()>0){

                                String[] code = new String[response.body().getInformation().size()];
                                String[] name = new String[response.body().getInformation().size()];
                                String[] min = new String[response.body().getInformation().size()];

                                Log.d("codey", String.valueOf(response.body().getInformation().size()));
                                for (int i =0; i<response.body().getInformation().size(); i++){

                                    mandatory_contributionsTypeModels.add(  new mandatory_contributions_type_model(response.body().getInformation().get(i).getMandatoryId(),response.body().getInformation().get(i).getMandatoryName(),response.body().getInformation().get(i).getDescription(),response.body().getInformation().get(i).getAmountPerCycle(),response.body().getInformation().get(i).getDeductFromSavings(),response.body().getInformation().get(i).getDeductSavingType(),response.body().getInformation().get(i).getLoanFactor(),response.body().getInformation().get(i).getOffsetLoan(),response.body().getInformation().get(i).getIsFixed(),response.body().getInformation().get(i).getName()));
                                    code[i] = response.body().getInformation().get(i).getMandatoryId();
                                    name[i] = response.body().getInformation().get(i).getMandatoryName();
                                    min[i] = response.body().getInformation().get(i).getAmountPerCycle();
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
                public void onFailure(Call<MandatoryContributinTypeRes> call, Throwable t) {
                    progressbar.setVisibility(View.GONE);

                    Toast.makeText(context1, "please try again. Failed to get user contributions ", Toast.LENGTH_LONG).show();

                }
            });


        }


        contributionspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                mandatory_pin = parent.getItemAtPosition(position).toString();

                mandatory_counts=  mandatory_contributionsTypeModels.get(position).getMandatory_id();

                mandatory_min=  mandatory_contributionsTypeModels.get(position).getAmount_per_cycle();
                Log.d("counts",  mandatory_counts);

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





                    if(Double.valueOf(amount.getText().toString()) >= Double.valueOf( mandatory_min) ){
                        variance = 0.0;
                    }else{
                        variance =  Double.valueOf( mandatory_min) - Double.valueOf(amount.getText().toString());

                    }
                    mandatory_saveNewContribution( mandatory_counts, amount.getText().toString(), String.valueOf(variance),sharedPreferenceActivity.getItem("meeting_id"));
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

    private static void mandatory_saveNewContribution(String type, String amount, String variance, String meeting_id){

        if (!NetworkUtility.isNetworkConnected(context1)){
            AppUtilits.displayMessage(context1,  "Network error");


        }else {

            //  Log.e(TAG, "  user value "+ SharePreferenceUtils.getInstance().getString(Constant.USER_DATA));
            ServiceWrapper service = new ServiceWrapper(null);
            Call<SaveMandatoryContributionRes> call = service.SaveMandatoryContributionResCall("12345", type,amount,variance,sharedPreferenceActivity.getItem("member_id"), sharedPreferenceActivity.getItem(Constant.USER_DATA), meeting_id);
            call.enqueue(new Callback<SaveMandatoryContributionRes>() {
                @Override
                public void onResponse(Call<SaveMandatoryContributionRes> call, Response<SaveMandatoryContributionRes> response) {

                    if (response.body() != null && response.isSuccessful()) {
                        if (response.body().getStatus() == 1) {


                            AppUtilits.displayMessage(context1, "Operation Successful");
                            getAllMandatoryContributions();


                        }else {

                            AppUtilits.displayMessage(context1, "failed to make new contribution");
                        }
                    }else {
                        AppUtilits.displayMessage(context1, "network error");
                    }


                }

                @Override
                public void onFailure(Call<SaveMandatoryContributionRes> call, Throwable t) {
                    // Log.e(TAG, "  fail- add to cart item "+ t.toString());

                    AppUtilits.displayMessage(context1, "failed to make new contribution");
                }
            });
        }
    }

    private static void getAllMandatoryContributions() {

        progressbar.setVisibility(View.VISIBLE);

        if (!NetworkUtility.isNetworkConnected(context1)) {
            progressbar.setVisibility(View.GONE);
            Toast.makeText(context1, "Network error", Toast.LENGTH_LONG).show();


        } else {
            progressbar.setVisibility(View.GONE);
            //  Log.e(TAG, "  user value "+ SharePreferenceUtils.getInstance().getString(Constant.USER_DATA));
            ServiceWrapper service = new ServiceWrapper(null);
            Call<mandatoryContributionRes> call = service.mandatoryContributionResCall("1234", sharedPreferenceActivity.getItem("member_id"));

            call.enqueue(new Callback<mandatoryContributionRes>() {
                @Override
                public void onResponse(Call<mandatoryContributionRes> call, Response<mandatoryContributionRes> response) {
                    Log.e(TAG, "response is " + response.body() + "  ---- " + new Gson().toJson(response.body()));
                    Log.e(TAG, "  ss sixe 1 ");
                    if (response.body() != null && response.isSuccessful()) {

                        Log.e(TAG, "  ss sixe 2 ");
                        if (response.body().getStatus() == 1) {
                            Log.e(TAG, "  ss sixe 3 ");


                            if (response.body().getInformation().size()>0){

                                progressbar.setVisibility(View.GONE);

                                mandatory_total_amount.setText( "Contribution : "+response.body().getTotalAmount() );
                                mandatory_varianceamt.setText( "variance: "+response.body().getVariance() );

                                sharedPreferenceActivity.putItem(Constant.TOTAL_CONTRIBUTIONS, response.body().getMsg());
                                mandatory_contributionsModels.clear();


                                for (int i =0; i<response.body().getInformation().size(); i++){


                                    mandatory_contributionsModels.add(  new mandatory_contributions_model(response.body().getInformation().get(i).getMandatoryCollectionId(),response.body().getInformation().get(i).getAmount(),response.body().getInformation().get(i).getVariance(),response.body().getInformation().get(i).getCreateDate(),response.body().getInformation().get(i).getUpdateDate(),response.body().getInformation().get(i).getMemberId(),response.body().getInformation().get(i).getMeetingId(),response.body().getInformation().get(i).getMandatoryId(),response.body().getInformation().get(i).getMandatoryName()));
                                    Log.d("model",response.body().getInformation().get(i).getMandatoryCollectionId());


                                }
                                mandatory_contributions_adapter.notifyDataSetChanged();
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
                public void onFailure(Call<mandatoryContributionRes> call, Throwable t) {
                    progressbar.setVisibility(View.GONE);

                    Toast.makeText(context1, "please try again. Failed to get user contributions ", Toast.LENGTH_LONG).show();

                }
            });


        }

    }

}
