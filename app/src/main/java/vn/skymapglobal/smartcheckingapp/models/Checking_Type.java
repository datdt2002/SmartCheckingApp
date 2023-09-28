package vn.skymapglobal.smartcheckingapp.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Checking_Type extends RealmObject {
    @PrimaryKey
    private int id;
    private String name;
    private int order;

    public Checking_Type() {}

    public Checking_Type(int id, String name, int order) {
        this.id = id;
        this.name = name;
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
