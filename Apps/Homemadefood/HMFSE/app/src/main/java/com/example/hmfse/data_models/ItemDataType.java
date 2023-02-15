package com.example.hmfse.data_models;

public class ItemDataType {
    String ID;
    String Item;
    String Price;
    String Image;
    String EntrepreneurID;

    public String getEntrepreneurID() {
        return EntrepreneurID;
    }

    public void setEntrepreneurID(String entrepreneurID) {
        EntrepreneurID = entrepreneurID;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getItem() {
        return Item;
    }

    public void setItem(String item) {
        Item = item;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
