package com.example.skylink.CashAccounts;

public class cash_contributions_type_model {

    String cash_id,cash_name,description,account_type,unpaid_interest,interest_account_type,unpaid_penalty,penalty_pay_type,autodeduct_from_savings,deduct_savings_type,factor_loan;

    public cash_contributions_type_model(String cash_id, String cash_name, String description, String account_type, String unpaid_interest, String interest_account_type, String unpaid_penalty, String penalty_pay_type, String autodeduct_from_savings, String deduct_savings_type, String factor_loan) {
        this.cash_id = cash_id;
        this.cash_name = cash_name;
        this.description = description;
        this.account_type = account_type;
        this.unpaid_interest = unpaid_interest;
        this.interest_account_type = interest_account_type;
        this.unpaid_penalty = unpaid_penalty;
        this.penalty_pay_type = penalty_pay_type;
        this.autodeduct_from_savings = autodeduct_from_savings;
        this.deduct_savings_type = deduct_savings_type;
        this.factor_loan = factor_loan;
    }

    public String getCash_id() {
        return cash_id;
    }

    public void setCash_id(String cash_id) {
        this.cash_id = cash_id;
    }

    public String getCash_name() {
        return cash_name;
    }

    public void setCash_name(String cash_name) {
        this.cash_name = cash_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public String getUnpaid_interest() {
        return unpaid_interest;
    }

    public void setUnpaid_interest(String unpaid_interest) {
        this.unpaid_interest = unpaid_interest;
    }

    public String getInterest_account_type() {
        return interest_account_type;
    }

    public void setInterest_account_type(String interest_account_type) {
        this.interest_account_type = interest_account_type;
    }

    public String getUnpaid_penalty() {
        return unpaid_penalty;
    }

    public void setUnpaid_penalty(String unpaid_penalty) {
        this.unpaid_penalty = unpaid_penalty;
    }

    public String getPenalty_pay_type() {
        return penalty_pay_type;
    }

    public void setPenalty_pay_type(String penalty_pay_type) {
        this.penalty_pay_type = penalty_pay_type;
    }

    public String getAutodeduct_from_savings() {
        return autodeduct_from_savings;
    }

    public void setAutodeduct_from_savings(String autodeduct_from_savings) {
        this.autodeduct_from_savings = autodeduct_from_savings;
    }

    public String getDeduct_savings_type() {
        return deduct_savings_type;
    }

    public void setDeduct_savings_type(String deduct_savings_type) {
        this.deduct_savings_type = deduct_savings_type;
    }

    public String getFactor_loan() {
        return factor_loan;
    }

    public void setFactor_loan(String factor_loan) {
        this.factor_loan = factor_loan;
    }
}
