package com.example.skylink.WebServices;



import com.example.skylink.beanResponse.ContributionRes;
import com.example.skylink.beanResponse.ContributionTypeRes;
import com.example.skylink.beanResponse.GetWithdrawalsRes;
import com.example.skylink.beanResponse.KinRes;
import com.example.skylink.beanResponse.LoanPaymentsRes;
import com.example.skylink.beanResponse.LoanTypeRes;
import com.example.skylink.beanResponse.LoansApplicationRes;
import com.example.skylink.beanResponse.MakeLoanPaymentRes;
import com.example.skylink.beanResponse.NewLoanApplicationRes;
import com.example.skylink.beanResponse.NewUserRegistration;
import com.example.skylink.beanResponse.NextofKinRes;
import com.example.skylink.beanResponse.SaveContributionRes;
import com.example.skylink.beanResponse.TrainingRes;
import com.example.skylink.beanResponse.UserSignInRes;
import com.example.skylink.beanResponse.WithdrawRes;
import com.example.skylink.beanResponse.feedbackAPI;
import com.example.skylink.beanResponse.feedhistoryAPI;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ServiceInterface {
    // method,, return type ,, secondary url
    @Multipart
    @POST("sky_link1/app/new_user_registration.php")
    Call<NewUserRegistration> NewUserRegistrationCall(
            @Part("id_number") RequestBody id_number,
            @Part("first_name") RequestBody first_name,
            @Part("last_name") RequestBody last_name,
            @Part("email") RequestBody email,
            @Part("phone_number") RequestBody phone_number,
            @Part("username") RequestBody username,
            @Part("amount") RequestBody amount,
            @Part("password") RequestBody password
    );

    ///  user signin request
    @Multipart
    @POST("sky_link1/app/user_signin.php")
    Call<UserSignInRes> UserSigninCall(
            @Part("id_number") RequestBody id_number,
            @Part("password") RequestBody password
    );

    ///  get all contributions
    @Multipart
    @POST("sky_link1/app/get_all_contributions.php")
    Call<ContributionRes> ContributionCall(
            @Part("securecode") RequestBody securecode,
            @Part("user_id") RequestBody user_id
    );

    /// save new contributions
    @Multipart
    @POST("sky_link1/app/save_new_contributions.php")
    Call<SaveContributionRes> SaveContributionCall(
            @Part("securecode") RequestBody securecode,
            @Part("contribution_type_id") RequestBody contribution_type_id,
            @Part("amount") RequestBody amount,
            @Part("user_id") RequestBody user_id,
            @Part("code") RequestBody code
    );

    ///  get all contributions types
    @Multipart
    @POST("sky_link1/app/get_contribution_types.php")
    Call<ContributionTypeRes> ContributionTypeCall(
            @Part("securecode") RequestBody securecode

    );

    ///  get loan types
    @Multipart
    @POST("sky_link1/app/get_loan_types.php")
    Call<LoanTypeRes> LoanTypeCall(
            @Part("securecode") RequestBody securecode,
            @Part("user_id") RequestBody user_id

    );

    ///  get all loans
    @Multipart
    @POST("sky_link1/app/get_all_loans.php")
    Call<LoansApplicationRes> LoansCall(
            @Part("securecode") RequestBody securecode,
            @Part("user_id") RequestBody user_id
    );

    ///  get  loan payments
    @Multipart
    @POST("sky_link1/app/get_loan_payments.php")
    Call<LoanPaymentsRes> LoanPaymentCall(
            @Part("securecode") RequestBody securecode,
            @Part("user_id") RequestBody user_id,
            @Part("application_id") RequestBody application_id
    );

    /// save new loan
    @Multipart
    @POST("sky_link1/app/new_loan_application.php")
    Call<NewLoanApplicationRes> SaveNewLoanCall(
            @Part("securecode") RequestBody securecode,
            @Part("loan_type_id") RequestBody loan_type_id,
            @Part("amount") RequestBody amount,
            @Part("user_id") RequestBody user_id,
            @Part("duration") RequestBody duration,
            @Part("total") RequestBody total,
            @Part("monthly") RequestBody monthly

    );

    /// loan payment
    @Multipart
    @POST("sky_link1/app/new_loan_payment.php")
    Call<MakeLoanPaymentRes> MakeLoanPaymentCall(
            @Part("securecode") RequestBody securecode,
            @Part("application_id") RequestBody application_id,
            @Part("amount") RequestBody amount,
            @Part("user_id") RequestBody user_id,
            @Part("code") RequestBody code

    );

    /// loan payment
    @Multipart
    @POST("sky_link1/app/add_next_of_kin.php")
    Call<NextofKinRes> NextofKinResCall(
            @Part("securecode") RequestBody securecode,
            @Part("name") RequestBody name,
            @Part("id") RequestBody id,
            @Part("relation") RequestBody relation,
            @Part("phone") RequestBody phone,
            @Part("amount") RequestBody amount,
            @Part("user_id") RequestBody user_id


    );

    // feedbackAPI
    @Multipart
    @POST("sky_link1/app/getfeedback.php")
    Call<feedbackAPI> feedbackcall(
            @Part("securecode") RequestBody securecode,
            @Part("feed_title") RequestBody feed_title,
            @Part("feed_comment") RequestBody feed_comment,
            @Part("user_id") RequestBody user_id

    );


    // get feedback history
    @Multipart
    @POST("sky_link1/app/getallfeedback.php")
    Call<feedhistoryAPI> getfeedhistorycall(
            @Part("securecode") RequestBody securecode,
            @Part("user_id") RequestBody user_id
    );

    ///  get next of kin
    @Multipart
    @POST("sky_link1/app/getUserKin.php")
    Call<KinRes> KinResCall(
            @Part("securecode") RequestBody securecode,
            @Part("user_id") RequestBody user_id
    );
    ///  get withdrawals
    @Multipart
    @POST("sky_link1/app/getWithdrawals.php")
    Call<GetWithdrawalsRes> GetWithdrawalsResCall(
            @Part("securecode") RequestBody securecode,
            @Part("user_id") RequestBody user_id
    );

    ///  send withdrawals
    @Multipart
    @POST("sky_link1/app/save_withdrawals.php")
    Call<WithdrawRes> WithdrawResCall(
            @Part("securecode") RequestBody securecode,
            @Part("amount") RequestBody amount,
            @Part("user_id") RequestBody user_id
    );

    @Multipart
    @POST("sky_link1/app/get_notification.php")
    Call<TrainingRes> TrainingResCall(
            @Part("securecode") RequestBody securecode

    );


}




