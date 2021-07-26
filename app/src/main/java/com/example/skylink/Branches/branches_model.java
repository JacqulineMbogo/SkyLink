package com.example.skylink.Branches;

public class branches_model {

    private  String  branch_id, branch_name;

    public branches_model(String branch_id, String branch_name) {
        this.branch_id = branch_id;
        this.branch_name = branch_name;
    }

    public String getBranch_id() {
        return branch_id;
    }

    public void setBranch_id(String branch_id) {
        this.branch_id = branch_id;
    }

    public String getBranch_name() {
        return branch_name;
    }

    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }
}
