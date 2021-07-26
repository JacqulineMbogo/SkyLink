package com.example.skylink.beanResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SingleContributionRes {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("total_amount")
    @Expose
    private String totalAmount;
    @SerializedName("variance")
    @Expose
    private String variance;
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

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getVariance() {
        return variance;
    }

    public void setVariance(String variance) {
        this.variance = variance;
    }

    public List<Information> getInformation() {
        return information;
    }

    public void setInformation(List<Information> information) {
        this.information = information;
    }

    public class Information {

        @SerializedName("savings_id")
        @Expose
        private String savingsId;
        @SerializedName("amount")
        @Expose
        private String amount;
        @SerializedName("amount")
        @Expose
        private String variance;
        @SerializedName("member_id")
        @Expose
        private String memberId;
        @SerializedName("create_date")
        @Expose
        private String createDate;
        @SerializedName("update_date")
        @Expose
        private String updateDate;
        @SerializedName("comments")
        @Expose
        private String comments;
        @SerializedName("created_by")
        @Expose
        private String createdBy;
        @SerializedName("saving_type_id")
        @Expose
        private String savingTypeId;
        @SerializedName("savings_date")
        @Expose
        private String savingsDate;
        @SerializedName("saving_name")
        @Expose
        private String savingName;

        public String getSavingsId() {
            return savingsId;
        }

        public void setSavingsId(String savingsId) {
            this.savingsId = savingsId;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getMemberId() {
            return memberId;
        }

        public void setMemberId(String memberId) {
            this.memberId = memberId;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getComments() {
            return comments;
        }

        public void setComments(String comments) {
            this.comments = comments;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public String getSavingTypeId() {
            return savingTypeId;
        }

        public void setSavingTypeId(String savingTypeId) {
            this.savingTypeId = savingTypeId;
        }

        public String getSavingsDate() {
            return savingsDate;
        }

        public void setSavingsDate(String savingsDate) {
            this.savingsDate = savingsDate;
        }

        public String getSavingName() {
            return savingName;
        }

        public void setSavingName(String savingName) {
            this.savingName = savingName;
        }

        public String getVariance() {
            return variance;
        }

        public void setVariance(String variance) {
            this.variance = variance;
        }
    }
}