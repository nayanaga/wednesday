package com.example.hmfsu.data_models;

public class CartDataType {

    String Item;
    String Price;
    String Qty;
    String Amount;
    String EntrepreneurID;
    String UserID;

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

    public String getQty() {
        return Qty;
    }

    public void setQty(String qty) {
        Qty = qty;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getEntrepreneurID() {
        return EntrepreneurID;
    }

    public void setEntrepreneurID(String entrepreneurID) {
        EntrepreneurID = entrepreneurID;
    }
}
