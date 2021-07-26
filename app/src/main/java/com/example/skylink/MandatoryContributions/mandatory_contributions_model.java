package com.example.skylink.MandatoryContributions;

public class mandatory_contributions_model {

    String mandatory_collection_id,amount,variance,create_date,update_date,member_id,meeting_id,mandatory_id,mandatory_name;

    public mandatory_contributions_model(String mandatory_collection_id, String amount, String variance, String create_date, String update_date, String member_id, String meeting_id, String mandatory_id, String mandatory_name) {
        this.mandatory_collection_id = mandatory_collection_id;
        this.amount = amount;
        this.variance = variance;
        this.create_date = create_date;
        this.update_date = update_date;
        this.member_id = member_id;
        this.meeting_id = meeting_id;
        this.mandatory_id = mandatory_id;
        this.mandatory_name = mandatory_name;
    }

    public String getMandatory_collection_id() {
        return mandatory_collection_id;
    }

    public void setMandatory_collection_id(String mandatory_collection_id) {
        this.mandatory_collection_id = mandatory_collection_id;
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

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getMeeting_id() {
        return meeting_id;
    }

    public void setMeeting_id(String meeting_id) {
        this.meeting_id = meeting_id;
    }

    public String getMandatory_id() {
        return mandatory_id;
    }

    public void setMandatory_id(String mandatory_id) {
        this.mandatory_id = mandatory_id;
    }

    public String getMandatory_name() {
        return mandatory_name;
    }

    public void setMandatory_name(String mandatory_name) {
        this.mandatory_name = mandatory_name;
    }
}
