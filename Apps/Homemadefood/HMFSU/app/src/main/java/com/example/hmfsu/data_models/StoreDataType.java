package com.example.hmfsu.data_models;

public class StoreDataType {

    String ID;
    String Entrepreneur;
    String Mobile;
    String Distance;

    public String getEntrepreneur() {
        return Entrepreneur;
    }

    public void setEntrepreneur(String entrepreneur) {
        Entrepreneur = entrepreneur;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getDistance() {
        return Distance;
    }

    public void setDistance(String distance) {
        Distance = distance;
    }
}
