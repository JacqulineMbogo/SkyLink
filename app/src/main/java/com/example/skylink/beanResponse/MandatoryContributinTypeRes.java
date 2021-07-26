package com.example.skylink.beanResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MandatoryContributinTypeRes {

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

        @SerializedName("mandatory_id")
        @Expose
        private String mandatoryId;
        @SerializedName("mandatory_name")
        @Expose
        private String mandatoryName;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("amount_per_cycle")
        @Expose
        private String amountPerCycle;
        @SerializedName("deduct_from_savings")
        @Expose
        private String deductFromSavings;
        @SerializedName("deduct_saving_type")
        @Expose
        private String deductSavingType;
        @SerializedName("loan_factor")
        @Expose
        private String loanFactor;
        @SerializedName("offset_loan")
        @Expose
        private String offsetLoan;
        @SerializedName("is_fixed")
        @Expose
        private String isFixed;
        @SerializedName("name")
        @Expose
        private String name;

        public String getMandatoryId() {
            return mandatoryId;
        }

        public void setMandatoryId(String mandatoryId) {
            this.mandatoryId = mandatoryId;
        }

        public String getMandatoryName() {
            return mandatoryName;
        }

        public void setMandatoryName(String mandatoryName) {
            this.mandatoryName = mandatoryName;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getAmountPerCycle() {
            return amountPerCycle;
        }

        public void setAmountPerCycle(String amountPerCycle) {
            this.amountPerCycle = amountPerCycle;
        }

        public String getDeductFromSavings() {
            return deductFromSavings;
        }

        public void setDeductFromSavings(String deductFromSavings) {
            this.deductFromSavings = deductFromSavings;
        }

        public String getDeductSavingType() {
            return deductSavingType;
        }

        public void setDeductSavingType(String deductSavingType) {
            this.deductSavingType = deductSavingType;
        }

        public String getLoanFactor() {
            return loanFactor;
        }

        public void setLoanFactor(String loanFactor) {
            this.loanFactor = loanFactor;
        }

        public String getOffsetLoan() {
            return offsetLoan;
        }

        public void setOffsetLoan(String offsetLoan) {
            this.offsetLoan = offsetLoan;
        }

        public String getIsFixed() {
            return isFixed;
        }

        public void setIsFixed(String isFixed) {
            this.isFixed = isFixed;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
