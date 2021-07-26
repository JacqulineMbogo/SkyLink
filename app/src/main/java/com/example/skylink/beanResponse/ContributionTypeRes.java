package com.example.skylink.beanResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContributionTypeRes {

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

        @SerializedName("savings_type_id")
        @Expose
        private String savingsTypeId;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("min_amount")
        @Expose
        private String minAmount;
        @SerializedName("max_amount")
        @Expose
        private String maxAmount;
        @SerializedName("factor_loan")
        @Expose
        private String factorLoan;
        @SerializedName("penalty_id")
        @Expose
        private String penaltyId;
        @SerializedName("percent_amount")
        @Expose
        private String percentAmount;
        @SerializedName("autodeduct")
        @Expose
        private String autodeduct;
        @SerializedName("savings_tpe")
        @Expose
        private String savingsTpe;

        public String getSavingsTypeId() {
            return savingsTypeId;
        }

        public void setSavingsTypeId(String savingsTypeId) {
            this.savingsTypeId = savingsTypeId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getMinAmount() {
            return minAmount;
        }

        public void setMinAmount(String minAmount) {
            this.minAmount = minAmount;
        }

        public String getMaxAmount() {
            return maxAmount;
        }

        public void setMaxAmount(String maxAmount) {
            this.maxAmount = maxAmount;
        }

        public String getFactorLoan() {
            return factorLoan;
        }

        public void setFactorLoan(String factorLoan) {
            this.factorLoan = factorLoan;
        }

        public String getPenaltyId() {
            return penaltyId;
        }

        public void setPenaltyId(String penaltyId) {
            this.penaltyId = penaltyId;
        }

        public String getPercentAmount() {
            return percentAmount;
        }

        public void setPercentAmount(String percentAmount) {
            this.percentAmount = percentAmount;
        }

        public String getAutodeduct() {
            return autodeduct;
        }

        public void setAutodeduct(String autodeduct) {
            this.autodeduct = autodeduct;
        }

        public String getSavingsTpe() {
            return savingsTpe;
        }

        public void setSavingsTpe(String savingsTpe) {
            this.savingsTpe = savingsTpe;
        }

    }
}