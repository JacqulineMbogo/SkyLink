package com.example.skylink.Contributions;

public class contributions_model {

    private String savings_id;
    private String amount, variance;
    private String member_id;
    private String create_date;
    private String update_date;
    private String comments;
    private String created_by;
    private String saving_type_id;
    private String savings_date;
    private String saving_name;

    public contributions_model(String savings_id, String amount,String variance, String member_id, String create_date, String update_date, String comments, String created_by, String saving_type_id, String savings_date, String saving_name) {
        this.savings_id = savings_id;
        this.variance = variance;
        this.amount = amount;
        this.member_id = member_id;
        this.create_date = create_date;
        this.update_date = update_date;
        this.comments = comments;
        this.created_by = created_by;
        this.saving_type_id = saving_type_id;
        this.savings_date = savings_date;
        this.saving_name = saving_name;
    }

    public String getSavings_id() {
        return savings_id;
    }

    public void setSavings_id(String savings_id) {
        this.savings_id = savings_id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getSaving_type_id() {
        return saving_type_id;
    }

    public void setSaving_type_id(String saving_type_id) {
        this.saving_type_id = saving_type_id;
    }

    public String getSavings_date() {
        return savings_date;
    }

    public void setSavings_date(String savings_date) {
        this.savings_date = savings_date;
    }

    public String getSaving_name() {
        return saving_name;
    }

    public void setSaving_name(String saving_name) {
        this.saving_name = saving_name;
    }

    public String getVariance() {
        return variance;
    }

    public void setVariance(String variance) {
        this.variance = variance;
    }
}
