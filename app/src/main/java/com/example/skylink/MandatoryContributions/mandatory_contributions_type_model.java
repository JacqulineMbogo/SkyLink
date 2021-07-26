package com.example.skylink.MandatoryContributions;

public class mandatory_contributions_type_model {

    String mandatory_id,mandatory_name,description,amount_per_cycle,deduct_from_savings,deduct_saving_type,loan_factor,offset_loan,is_fixed,name;

    public mandatory_contributions_type_model(String mandatory_id, String mandatory_name, String description, String amount_per_cycle, String deduct_from_savings, String deduct_saving_type, String loan_factor, String offset_loan, String is_fixed, String name) {
        this.mandatory_id = mandatory_id;
        this.mandatory_name = mandatory_name;
        this.description = description;
        this.amount_per_cycle = amount_per_cycle;
        this.deduct_from_savings = deduct_from_savings;
        this.deduct_saving_type = deduct_saving_type;
        this.loan_factor = loan_factor;
        this.offset_loan = offset_loan;
        this.is_fixed = is_fixed;
        this.name = name;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAmount_per_cycle() {
        return amount_per_cycle;
    }

    public void setAmount_per_cycle(String amount_per_cycle) {
        this.amount_per_cycle = amount_per_cycle;
    }

    public String getDeduct_from_savings() {
        return deduct_from_savings;
    }

    public void setDeduct_from_savings(String deduct_from_savings) {
        this.deduct_from_savings = deduct_from_savings;
    }

    public String getDeduct_saving_type() {
        return deduct_saving_type;
    }

    public void setDeduct_saving_type(String deduct_saving_type) {
        this.deduct_saving_type = deduct_saving_type;
    }

    public String getLoan_factor() {
        return loan_factor;
    }

    public void setLoan_factor(String loan_factor) {
        this.loan_factor = loan_factor;
    }

    public String getOffset_loan() {
        return offset_loan;
    }

    public void setOffset_loan(String offset_loan) {
        this.offset_loan = offset_loan;
    }

    public String getIs_fixed() {
        return is_fixed;
    }

    public void setIs_fixed(String is_fixed) {
        this.is_fixed = is_fixed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
