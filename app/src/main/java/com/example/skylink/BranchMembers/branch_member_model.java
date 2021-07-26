package com.example.skylink.BranchMembers;

public class branch_member_model {

    private  String  name, national_id,user_id,phone_number,branch;

    public branch_member_model(String name, String national_id, String user_id, String phone_number, String branch) {
        this.name = name;
        this.national_id = national_id;
        this.user_id = user_id;
        this.phone_number = phone_number;
        this.branch = branch;
    }
    private boolean expanded;

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public boolean isExpanded() {
        return expanded;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNational_id() {
        return national_id;
    }

    public void setNational_id(String national_id) {
        this.national_id = national_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}
