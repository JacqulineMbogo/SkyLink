package com.example.skylink.WebServices;


import com.example.skylink.BuildConfig;
import com.example.skylink.Utility.Constant;
import com.example.skylink.beanResponse.BranchMembersRes;
import com.example.skylink.beanResponse.CashContributionRes;
import com.example.skylink.beanResponse.CashContributionsTypesRes;
import com.example.skylink.beanResponse.ChangePassword;
import com.example.skylink.beanResponse.ContributionRes;
import com.example.skylink.beanResponse.ContributionTypeRes;
import com.example.skylink.beanResponse.GetWithdrawalsRes;
import com.example.skylink.beanResponse.KinRes;
import com.example.skylink.beanResponse.LoanPaymentsRes;
import com.example.skylink.beanResponse.LoanTypeRes;
import com.example.skylink.beanResponse.LoansApplicationRes;
import com.example.skylink.beanResponse.MakeLoanPaymentRes;
import com.example.skylink.beanResponse.MandatoryContributinTypeRes;
import com.example.skylink.beanResponse.NewLoanApplicationRes;
import com.example.skylink.beanResponse.NewUserRegistration;
import com.example.skylink.beanResponse.NextofKinRes;
import com.example.skylink.beanResponse.SaveCashContribution;
import com.example.skylink.beanResponse.SaveContributionRes;
import com.example.skylink.beanResponse.SaveMandatoryContributionRes;
import com.example.skylink.beanResponse.SingleContributionRes;
import com.example.skylink.beanResponse.TrainingRes;
import com.example.skylink.beanResponse.UserSignInRes;
import com.example.skylink.beanResponse.WithdrawRes;
import com.example.skylink.beanResponse.feedbackAPI;
import com.example.skylink.beanResponse.feedhistoryAPI;
import com.example.skylink.beanResponse.getBranchesResponse;
import com.example.skylink.beanResponse.mandatoryContributionRes;
import com.example.skylink.beanResponse.newMeetingRes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ServiceWrapper  {

    private ServiceInterface mServiceInterface;

    public ServiceWrapper(Interceptor mInterceptorheader) {
        mServiceInterface = getRetrofit(mInterceptorheader).create(ServiceInterface.class);
    }

    public Retrofit getRetrofit(Interceptor mInterceptorheader) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient mOkHttpClient = null;
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(Constant.API_CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        builder.readTimeout(Constant.API_READ_TIMEOUT, TimeUnit.SECONDS);

//        if (BuildConfig.DEBUG)
//            builder.addInterceptor(loggingInterceptor);

        if (BuildConfig.DEBUG) {
//            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }


        mOkHttpClient = builder.build();
        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(mOkHttpClient)
                .build();
        return retrofit;
    }

    public Call<NewUserRegistration> newUserRegistrationCall(String id_number, String first_name, String last_name, String email, String phone_number, String username, String amount, String password){
        return mServiceInterface.NewUserRegistrationCall( convertPlainString(id_number),convertPlainString(first_name),convertPlainString(last_name),convertPlainString(email), convertPlainString(phone_number), convertPlainString(username),convertPlainString(amount), convertPlainString(password));
    }
    ///  user signin
    public Call<ChangePassword> ChangePasswordCall(String id_number, String password){
        return mServiceInterface.ChangePasswordCall(convertPlainString(id_number), convertPlainString(password));
    }
    ///  user signin
    public Call<UserSignInRes> UserSigninCall(String id_number, String password){
        return mServiceInterface.UserSigninCall(convertPlainString(id_number), convertPlainString(password));
    }

    ///  get all contributions
    public Call<ContributionRes> ContributionCall(String securecode, String user_id){
        return mServiceInterface.ContributionCall(convertPlainString(securecode), convertPlainString(user_id));
    }
    public Call<SingleContributionRes> SingleContributionCall(String securecode, String saving_id,String member_id){
        return mServiceInterface.SingleContributionCall(convertPlainString(securecode), convertPlainString(saving_id), convertPlainString(member_id));
    }
    public Call<mandatoryContributionRes> mandatoryContributionResCall(String securecode, String user_id){
        return mServiceInterface.mandatoryContributionResCall(convertPlainString(securecode), convertPlainString(user_id));
    }
    public Call<CashContributionRes> CashContributionResCall(String securecode, String user_id){
        return mServiceInterface.CashContributionResCall(convertPlainString(securecode), convertPlainString(user_id));
    }
    public Call<newMeetingRes> newMeetingResCall(String securecode, String created_by,String group_id,String meeting_venue,String comments){
        return mServiceInterface.newMeetingResCall(convertPlainString(securecode), convertPlainString(created_by),convertPlainString(group_id),convertPlainString(meeting_venue),convertPlainString(comments));
    }

    ///  new contribution
    public Call<SaveContributionRes> SaveContributionCall(String securecode, String contribution_type_id, String amount,String variance, String user_id, String created_by,String meeting_id){
        return mServiceInterface.SaveContributionCall(convertPlainString(securecode),convertPlainString(contribution_type_id),convertPlainString(amount),convertPlainString(variance), convertPlainString(user_id), convertPlainString(created_by), convertPlainString(meeting_id));
    }

    ///  new contribution
    public Call<SaveCashContribution> SaveCashContributionCall(String securecode, String cash_id, String amount, String variance, String user_id, String created_by, String meeting_id){
        return mServiceInterface.SaveCashContributionCall(convertPlainString(securecode),convertPlainString(cash_id),convertPlainString(amount),convertPlainString(variance), convertPlainString(user_id), convertPlainString(created_by), convertPlainString(meeting_id));
    }

    ///  new contribution
    public Call<SaveMandatoryContributionRes> SaveMandatoryContributionResCall(String securecode, String mandatory_id, String amount, String variance, String user_id, String created_by, String meeting_id){
        return mServiceInterface.SaveMandatoryContributionResCall(convertPlainString(securecode),convertPlainString(mandatory_id),convertPlainString(amount),convertPlainString(variance), convertPlainString(user_id), convertPlainString(created_by), convertPlainString(meeting_id));
    }


    ///  get mandatory contributions types
    public Call<MandatoryContributinTypeRes> MandatoryContributinTypeResCall(String securecode){
        return mServiceInterface.MandatoryContributinTypeResCall(convertPlainString(securecode));
    }


    ///  get cash contributions types
    public Call<CashContributionsTypesRes> CashContributionsTypesResCall(String securecode){
        return mServiceInterface.CashContributionsTypesResCall(convertPlainString(securecode));
    }
    ///  get all contributions types
    public Call<ContributionTypeRes> ContributionTypeCall(String securecode){
        return mServiceInterface.ContributionTypeCall(convertPlainString(securecode));
    }


    ///  get all loan types
    public Call<LoanTypeRes> LoanTypeCall(String securecode){
        return mServiceInterface.LoanTypeCall(convertPlainString(securecode));
    }


    ///  get all loans
    public Call<LoansApplicationRes> LoansCall(String securecode, String user_id){
        return mServiceInterface.LoansCall(convertPlainString(securecode), convertPlainString(user_id));
    }

    ///  get all loan payments
    public Call<LoanPaymentsRes> LoanPaymentCall(String securecode, String user_id, String application_id){
        return mServiceInterface.LoanPaymentCall(convertPlainString(securecode), convertPlainString(user_id),  convertPlainString(application_id));
    }

    ///  new loan
    public Call<NewLoanApplicationRes> SaveNewLoanCall(String securecode, String loan_type_id, String amount, String user_id, String created_by){
        return mServiceInterface.SaveNewLoanCall(convertPlainString(securecode),convertPlainString(loan_type_id),convertPlainString(amount), convertPlainString(user_id), convertPlainString(created_by));
    }

    ///  make loan payment
    public Call<MakeLoanPaymentRes> MakeLoanPaymentCall(String securecode, String application_id, String amount, String user_id, String code){
        return mServiceInterface.MakeLoanPaymentCall(convertPlainString(securecode),convertPlainString(application_id),convertPlainString(amount), convertPlainString(user_id),convertPlainString(code));
    }

    /// next of kin
    public Call<NextofKinRes>NextofKinResCall(String securecode, String name, String id, String relation, String phone, String amount , String user_id){
        return mServiceInterface.NextofKinResCall(convertPlainString(securecode),convertPlainString(name),convertPlainString(id) ,convertPlainString(relation),convertPlainString(phone), convertPlainString(amount), convertPlainString(user_id));
    }

    // convert aa param into plain text
    public RequestBody convertPlainString(String data){
        RequestBody plainString = RequestBody.create(MediaType.parse("text/plain"), data);
        return  plainString;
    }

    public Call<feedbackAPI> feedbackcall(String securcode, String feed_title , String feed_comment, String user_id){
        return mServiceInterface. feedbackcall(convertPlainString(securcode), convertPlainString(feed_title) , convertPlainString(feed_comment), convertPlainString(user_id) );
    }

    // get feedback history
    public Call<feedhistoryAPI> getfeedhistorycall(String securcode, String user_id){
        return mServiceInterface.getfeedhistorycall(convertPlainString(securcode), convertPlainString(user_id) );
    }

    ///  get kin
    public Call<KinRes> KinResCall(String securecode, String user_id){
        return mServiceInterface.KinResCall(convertPlainString(securecode), convertPlainString(user_id));
    }

    ///  get withdrawals
    public Call<GetWithdrawalsRes> GetWithdrawalsResCall(String securecode, String user_id){
        return mServiceInterface.GetWithdrawalsResCall(convertPlainString(securecode), convertPlainString(user_id));
    }

    ///  get kin
    public Call<WithdrawRes> WithdrawResCall(String securecode, String amount, String user_id){
        return mServiceInterface.WithdrawResCall(convertPlainString(securecode), convertPlainString(amount),convertPlainString(user_id));
    }

    ///  get notification
    public Call<TrainingRes> TrainingResCall(String securecode){
        return mServiceInterface.TrainingResCall(convertPlainString(securecode));
    }

    ///  get all user branches
    public Call<getBranchesResponse> getBranchesResponseCall(String securecode, String user_id){
        return mServiceInterface.getBranchesResponseCall(convertPlainString(securecode), convertPlainString(user_id));
    }


    ///  get all branch members
    public Call<BranchMembersRes> BranchMembersResCall(String securecode, String user_id,String branch_id){
        return mServiceInterface.BranchMembersResCall(convertPlainString(securecode), convertPlainString(user_id), convertPlainString(branch_id));
    }
}


