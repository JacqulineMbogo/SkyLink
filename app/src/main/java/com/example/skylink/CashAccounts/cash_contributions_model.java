package com.example.skylink.CashAccounts;

public class cash_contributions_model {

   String cash_collections_id,amount,variance,cash_id,member_id,meeting_id,create_date,update_date,cash_name;

    public cash_contributions_model(String cash_collections_id, String amount, String variance, String cash_id, String member_id, String meeting_id, String create_date, String update_date, String cash_name) {
        this.cash_collections_id = cash_collections_id;
        this.amount = amount;
        this.variance = variance;
        this.cash_id = cash_id;
        this.member_id = member_id;
        this.meeting_id = meeting_id;
        this.create_date = create_date;
        this.update_date = update_date;
        this.cash_name = cash_name;
    }

    public String getCash_collections_id() {
        return cash_collections_id;
    }

    public void setCash_collections_id(String cash_collections_id) {
        this.cash_collections_id = cash_collections_id;
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

    public String getCash_id() {
        return cash_id;
    }

    public void setCash_id(String cash_id) {
        this.cash_id = cash_id;
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

    public String getCash_name() {
        return cash_name;
    }

    public void setCash_name(String cash_name) {
        this.cash_name = cash_name;
    }
}
