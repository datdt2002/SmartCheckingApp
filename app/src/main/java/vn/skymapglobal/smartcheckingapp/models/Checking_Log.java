package vn.skymapglobal.smartcheckingapp.models;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Checking_Log extends RealmObject {
    @PrimaryKey
    private int id;
    private String username;
    private Date time;
    private int checkingTypeId;

    public Checking_Log() {}

    public Checking_Log(int id, String username, Date time, int checkingTypeId) {
        this.id = id;
        this.username = username;
        this.time = time;
        this.checkingTypeId = checkingTypeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getCheckingTypeId() {
        return checkingTypeId;
    }

    public void setCheckingTypeId(int checkingTypeId) {
        this.checkingTypeId = checkingTypeId;
    }

}
