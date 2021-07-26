package com.example.skylink.beanResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class mandatoryContributionRes {

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

        @SerializedName("mandatory_collection_id")
        @Expose
        private String mandatoryCollectionId;
        @SerializedName("amount")
        @Expose
        private String amount;
        @SerializedName("variance")
        @Expose
        private String variance;
        @SerializedName("create_date")
        @Expose
        private String createDate;
        @SerializedName("update_date")
        @Expose
        private String updateDate;
        @SerializedName("member_id")
        @Expose
        private String memberId;
        @SerializedName("meeting_id")
        @Expose
        private String meetingId;
        @SerializedName("mandatory_id")
        @Expose
        private String mandatoryId;
        @SerializedName("mandatory_name")
        @Expose
        private String mandatoryName;

        public String getMandatoryCollectionId() {
            return mandatoryCollectionId;
        }

        public void setMandatoryCollectionId(String mandatoryCollectionId) {
            this.mandatoryCollectionId = mandatoryCollectionId;
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

    }
}
