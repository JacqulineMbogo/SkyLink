package com.example.skylink.Branches;

public class meeting_model {

     String meeting_id, group_id, contact_person, service_fee, meeting_venue, create_date, update_date, created_by,prefix,comments;

    public meeting_model(String meeting_id, String group_id, String contact_person, String service_fee, String meeting_venue, String create_date, String update_date, String created_by, String prefix, String comments) {
        this.meeting_id = meeting_id;
        this.group_id = group_id;
        this.contact_person = contact_person;
        this.service_fee = service_fee;
        this.meeting_venue = meeting_venue;
        this.create_date = create_date;
        this.update_date = update_date;
        this.created_by = created_by;
        this.prefix = prefix;
        this.comments = comments;
    }

    public String getMeeting_id() {
        return meeting_id;
    }

    public void setMeeting_id(String meeting_id) {
        this.meeting_id = meeting_id;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getContact_person() {
        return contact_person;
    }

    public void setContact_person(String contact_person) {
        this.contact_person = contact_person;
    }

    public String getService_fee() {
        return service_fee;
    }

    public void setService_fee(String service_fee) {
        this.service_fee = service_fee;
    }

    public String getMeeting_venue() {
        return meeting_venue;
    }

    public void setMeeting_venue(String meeting_venue) {
        this.meeting_venue = meeting_venue;
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

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
