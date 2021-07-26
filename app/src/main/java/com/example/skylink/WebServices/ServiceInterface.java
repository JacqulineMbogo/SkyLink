package com.example.skylink.WebServices;



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

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ServiceInterface {
    // method,, return type ,, secondary url
    @Multipart
    @POST("admin/app/new_user_registration.php")
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

    @Multipart
    @POST("admin/app/changePassword.php")
    Call<ChangePassword> ChangePasswordCall(
            @Part("id_number") RequestBody id_number,
            @Part("password") RequestBody password
    );
    ///  user signin request
    @Multipart
    @POST("admin/app/user_signin.php")
    Call<UserSignInRes> UserSigninCall(
            @Part("id_number") RequestBody id_number,
            @Part("password") RequestBody password
    );


    ///  New Meeting
    @Multipart
    @POST("admin/app/create_new_group_meeting.php")
    Call<newMeetingRes> newMeetingResCall(
            @Part("securecode") RequestBody securecode,
            @Part("created_by") RequestBody created_by,
            @Part("group_id") RequestBody group_id,
            @Part("meeting_venue") RequestBody meeting_venue,
            @Part("comments") RequestBody comments
    );
    ///  get all contributions
    @Multipart
    @POST("admin/app/get_all_contributions.php")
    Call<ContributionRes> ContributionCall(
            @Part("securecode") RequestBody securecode,
            @Part("user_id") RequestBody user_id
    );

    ///  get sibglecontributions
    @Multipart
    @POST("admin/app/get_single_contributions.php")
    Call<SingleContributionRes> SingleContributionCall(
            @Part("securecode") RequestBody securecode,
            @Part("saving_id") RequestBody saving_id,
            @Part("member_id") RequestBody member_id
    );

    ///  get all contributions
    @Multipart
    @POST("admin/app/get_all_mandatory_contributions.php")
    Call<mandatoryContributionRes> mandatoryContributionResCall(
            @Part("securecode") RequestBody securecode,
            @Part("user_id") RequestBody user_id
    );

    ///  get all cash contributions
    @Multipart
    @POST("admin/app/get_all_cash_collections.php")
    Call<CashContributionRes> CashContributionResCall(
            @Part("securecode") RequestBody securecode,
            @Part("user_id") RequestBody user_id
    );

    /// save new contributions
    @Multipart
    @POST("admin/app/save_new_contributions.php")
    Call<SaveContributionRes> SaveContributionCall(
            @Part("securecode") RequestBody securecode,
            @Part("contribution_type_id") RequestBody contribution_type_id,
            @Part("amount") RequestBody amount,
            @Part("variance") RequestBody variance,
            @Part("user_id") RequestBody user_id,
            @Part("created_by") RequestBody created_by,
            @Part("meeting_id") RequestBody meeting_id
    );

    /// save new mandatory contributions
    @Multipart
    @POST("admin/app/save_new_mandatory_collection.php")
    Call<SaveMandatoryContributionRes> SaveMandatoryContributionResCall(
            @Part("securecode") RequestBody securecode,
            @Part("mandatory_id") RequestBody mandatory_id,
            @Part("amount") RequestBody amount,
            @Part("variance") RequestBody variance,
            @Part("user_id") RequestBody user_id,
            @Part("created_by") RequestBody created_by,
            @Part("meeting_id") RequestBody meeting_id
    );

    /// save new cash contributions
    @Multipart
    @POST("admin/app/save_new_cash_account.php")
    Call<SaveCashContribution> SaveCashContributionCall(
            @Part("securecode") RequestBody securecode,
            @Part("cash_id") RequestBody cash_id,
            @Part("amount") RequestBody amount,
            @Part("variance") RequestBody variance,
            @Part("user_id") RequestBody user_id,
            @Part("created_by") RequestBody created_by,
            @Part("meeting_id") RequestBody meeting_id
    );

    ///  get all contributions types
    @Multipart
    @POST("admin/app/get_cash_types.php")
    Call<CashContributionsTypesRes> CashContributionsTypesResCall(
            @Part("securecode") RequestBody securecode

    );
    ///  get all cash contributions types
    @Multipart
    @POST("admin/app/get_contribution_types.php")
    Call<ContributionTypeRes> ContributionTypeCall(
            @Part("securecode") RequestBody securecode

    );

    ///  get mandatory contributions types
    @Multipart
    @POST("admin/app/get_mandatory_contribution_type.php")
    Call<MandatoryContributinTypeRes> MandatoryContributinTypeResCall(
            @Part("securecode") RequestBody securecode

    );
    ///  get loan types
    @Multipart
    @POST("admin/app/get_loan_types.php")
    Call<LoanTypeRes> LoanTypeCall(
            @Part("securecode") RequestBody securecode

    );

    ///  get all loans
    @Multipart
    @POST("admin/app/get_all_loans.php")
    Call<LoansApplicationRes> LoansCall(
            @Part("securecode") RequestBody securecode,
            @Part("user_id") RequestBody user_id
    );

    ///  get  loan payments
    @Multipart
    @POST("admin/app/get_loan_payments.php")
    Call<LoanPaymentsRes> LoanPaymentCall(
            @Part("securecode") RequestBody securecode,
            @Part("user_id") RequestBody user_id,
            @Part("application_id") RequestBody application_id
    );

    /// save new loan
    @Multipart
    @POST("admin/app/new_loan_application.php")
    Call<NewLoanApplicationRes> SaveNewLoanCall(
            @Part("securecode") RequestBody securecode,
            @Part("loan_type_id") RequestBody loan_type_id,
            @Part("amount") RequestBody amount,
            @Part("user_id") RequestBody user_id,
            @Part("created_by") RequestBody created_by

    );

    /// loan payment
    @Multipart
    @POST("admin/app/new_loan_payment.php")
    Call<MakeLoanPaymentRes> MakeLoanPaymentCall(
            @Part("securecode") RequestBody securecode,
            @Part("application_id") RequestBody application_id,
            @Part("amount") RequestBody amount,
            @Part("user_id") RequestBody user_id,
            @Part("code") RequestBody code

    );

    /// loan payment
    @Multipart
    @POST("admin/app/add_next_of_kin.php")
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
    @POST("admin/app/getfeedback.php")
    Call<feedbackAPI> feedbackcall(
            @Part("securecode") RequestBody securecode,
            @Part("feed_title") RequestBody feed_title,
            @Part("feed_comment") RequestBody feed_comment,
            @Part("user_id") RequestBody user_id

    );


    // get feedback history
    @Multipart
    @POST("admin/app/getallfeedback.php")
    Call<feedhistoryAPI> getfeedhistorycall(
            @Part("securecode") RequestBody securecode,
            @Part("user_id") RequestBody user_id
    );

    ///  get next of kin
    @Multipart
    @POST("admin/app/getUserKin.php")
    Call<KinRes> KinResCall(
            @Part("securecode") RequestBody securecode,
            @Part("user_id") RequestBody user_id
    );
    ///  get withdrawals
    @Multipart
    @POST("admin/app/getWithdrawals.php")
    Call<GetWithdrawalsRes> GetWithdrawalsResCall(
            @Part("securecode") RequestBody securecode,
            @Part("user_id") RequestBody user_id
    );

    ///  send withdrawals
    @Multipart
    @POST("admin/app/save_withdrawals.php")
    Call<WithdrawRes> WithdrawResCall(
            @Part("securecode") RequestBody securecode,
            @Part("amount") RequestBody amount,
            @Part("user_id") RequestBody user_id
    );

    @Multipart
    @POST("admin/app/get_notification.php")
    Call<TrainingRes> TrainingResCall(
            @Part("securecode") RequestBody securecode

    );


    ///  get all branches
    @Multipart
    @POST("admin/app/get_all_groups.php")
    Call<getBranchesResponse> getBranchesResponseCall(
            @Part("securecode") RequestBody securecode,
            @Part("user_id") RequestBody user_id
    );

    ///  get  loan payments
    @Multipart
    @POST("admin/app/get_branch_members.php")
    Call<BranchMembersRes> BranchMembersResCall(
            @Part("securecode") RequestBody securecode,
            @Part("user_id") RequestBody user_id,
            @Part("branch_id") RequestBody branch_id

    );


}




