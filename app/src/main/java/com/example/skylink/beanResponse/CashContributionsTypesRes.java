package com.example.skylink.beanResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CashContributionsTypesRes {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("Information")
    @Expose
    private List<Information> information = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Information> getInformation() {
        return information;
    }

    public void setInformation(List<Information> information) {
        this.information = information;
    }

    public class Information {


        @SerializedName("cash_id")
        @Expose
        private String cashId;
        @SerializedName("cash_name")
        @Expose
        private String cashName;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("account_type")
        @Expose
        private String accountType;
        @SerializedName("unpaid_interest")
        @Expose
        private String unpaidInterest;
        @SerializedName("interest_account_type")
        @Expose
        private String interestAccountType;
        @SerializedName("unpaid_penalty")
        @Expose
        private String unpaidPenalty;
        @SerializedName("penalty_pay_type")
        @Expose
        private String penaltyPayType;
        @SerializedName("autodeduct_from_savings")
        @Expose
        private String autodeductFromSavings;
        @SerializedName("deduct_savings_type")
        @Expose
        private String deductSavingsType;
        @SerializedName("factor_loan")
        @Expose
        private String factorLoan;

        public String getCashId() {
            return cashId;
        }

        public void setCashId(String cashId) {
            this.cashId = cashId;
        }

        public String getCashName() {
            return cashName;
        }

        public void setCashName(String cashName) {
            this.cashName = cashName;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getAccountType() {
            return accountType;
        }

        public void setAccountType(String accountType) {
            this.accountType = accountType;
        }

        public String getUnpaidInterest() {
            return unpaidInterest;
        }

        public void setUnpaidInterest(String unpaidInterest) {
            this.unpaidInterest = unpaidInterest;
        }

        public String getInterestAccountType() {
            return interestAccountType;
        }

        public void setInterestAccountType(String interestAccountType) {
            this.interestAccountType = interestAccountType;
        }

        public String getUnpaidPenalty() {
            return unpaidPenalty;
        }

        public void setUnpaidPenalty(String unpaidPenalty) {
            this.unpaidPenalty = unpaidPenalty;
        }

        public String getPenaltyPayType() {
            return penaltyPayType;
        }

        public void setPenaltyPayType(String penaltyPayType) {
            this.penaltyPayType = penaltyPayType;
        }

        public String getAutodeductFromSavings() {
            return autodeductFromSavings;
        }

        public void setAutodeductFromSavings(String autodeductFromSavings) {
            this.autodeductFromSavings = autodeductFromSavings;
        }

        public String getDeductSavingsType() {
            return deductSavingsType;
        }

        public void setDeductSavingsType(String deductSavingsType) {
            this.deductSavingsType = deductSavingsType;
        }

        public String getFactorLoan() {
            return factorLoan;
        }

        public void setFactorLoan(String factorLoan) {
            this.factorLoan = factorLoan;
        }
    }
}
