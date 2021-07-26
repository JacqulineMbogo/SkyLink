package com.example.skylink.beanResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CashContributionRes {


    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("total_amount")
    @Expose
    private Integer totalAmount;
    @SerializedName("variance")
    @Expose
    private Integer variance;
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

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getVariance() {
        return variance;
    }

    public void setVariance(Integer variance) {
        this.variance = variance;
    }

    public List<Information> getInformation() {
        return information;
    }

    public void setInformation(List<Information> information) {
        this.information = information;
    }
    public class Information {

        @SerializedName("cash_collections_id")
        @Expose
        private String cashCollectionsId;
        @SerializedName("amount")
        @Expose
        private String amount;
        @SerializedName("variance")
        @Expose
        private String variance;
        @SerializedName("cash_id")
        @Expose
        private String cashId;
        @SerializedName("member_id")
        @Expose
        private String memberId;
        @SerializedName("meeting_id")
        @Expose
        private String meetingId;
        @SerializedName("create_date")
        @Expose
        private String createDate;
        @SerializedName("update_date")
        @Expose
        private String updateDate;
        @SerializedName("cash_name")
        @Expose
        private String cashName;

        public String getCashCollectionsId() {
            return cashCollectionsId;
        }

        public void setCashCollectionsId(String cashCollectionsId) {
            this.cashCollectionsId = cashCollectionsId;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getVariance() {
            return variance;
        }

        public void setVariance(String variance) {
            this.variance = variance;
        }

        public String getCashId() {
            return cashId;
        }

        public void setCashId(String cashId) {
            this.cashId = cashId;
        }

        public String getMemberId() {
            return memberId;
        }

        public void setMemberId(String memberId) {
            this.memberId = memberId;
        }

        public String getMeetingId() {
            return meetingId;
        }

        public void setMeetingId(String meetingId) {
            this.meetingId = meetingId;
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

        public String getCashName() {
            return cashName;
        }

        public void setCashName(String cashName) {
            this.cashName = cashName;
        }

    }
}
