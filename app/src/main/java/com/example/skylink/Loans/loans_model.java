package com.example.skylink.Loans;

public class loans_model {

    private  String  application_id, amount, date,status, loan_id, comment,duration,monthly, amounts;

    public loans_model(String application_id, String amount, String date, String status, String loan_id, String comment,String duration,String monthly, String amounts) {
        this.application_id = application_id;
        this.amount = amount;
        this.date = date;
        this.status = status;
        this.loan_id = loan_id;
        this.comment = comment;
        this.duration = duration;
        this.monthly = monthly;
        this.amounts = amounts;
    }


    public String getAmounts() {
        return amounts;
    }

    public void setAmounts(String amounts) {
        this.amounts = amounts;
    }

    public String getMonthly() {
        return monthly;
    }

    public void setMonthly(String monthly) {
        this.monthly = monthly;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setApplication_id(String application_id) {
        this.application_id = application_id;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setLoan_id(String loan_id) {
        this.loan_id = loan_id;
    }

    public String getApplication_id() {
        return application_id;
    }

    public String getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public String getLoan_id() {
        return loan_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
