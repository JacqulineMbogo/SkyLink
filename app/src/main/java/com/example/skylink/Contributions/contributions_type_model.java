package com.example.skylink.Contributions;

public class contributions_type_model {

    public   String savings_type_id,
            name,
            description,
            min_amount,
            max_amount,
            factor_loan,
            penalty_id,
            percent_amount,
            autodeduct,
            savings_tpe;

    public contributions_type_model(String savings_type_id, String name, String description, String min_amount, String max_amount, String factor_loan, String penalty_id, String percent_amount, String autodeduct, String savings_tpe) {
        this.savings_type_id = savings_type_id;
        this.name = name;
        this.description = description;
        this.min_amount = min_amount;
        this.max_amount = max_amount;
        this.factor_loan = factor_loan;
        this.penalty_id = penalty_id;
        this.percent_amount = percent_amount;
        this.autodeduct = autodeduct;
        this.savings_tpe = savings_tpe;
    }

    public String getSavings_type_id() {
        return savings_type_id;
    }

    public void setSavings_type_id(String savings_type_id) {
        this.savings_type_id = savings_type_id;
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

    public String getMin_amount() {
        return min_amount;
    }

    public void setMin_amount(String min_amount) {
        this.min_amount = min_amount;
    }

    public String getMax_amount() {
        return max_amount;
    }

    public void setMax_amount(String max_amount) {
        this.max_amount = max_amount;
    }

    public String getFactor_loan() {
        return factor_loan;
    }

    public void setFactor_loan(String factor_loan) {
        this.factor_loan = factor_loan;
    }

    public String getPenalty_id() {
        return penalty_id;
    }

    public void setPenalty_id(String penalty_id) {
        this.penalty_id = penalty_id;
    }

    public String getPercent_amount() {
        return percent_amount;
    }

    public void setPercent_amount(String percent_amount) {
        this.percent_amount = percent_amount;
    }

    public String getAutodeduct() {
        return autodeduct;
    }

    public void setAutodeduct(String autodeduct) {
        this.autodeduct = autodeduct;
    }

    public String getSavings_tpe() {
        return savings_tpe;
    }

    public void setSavings_tpe(String savings_tpe) {
        this.savings_tpe = savings_tpe;
    }
}
