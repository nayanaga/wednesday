package com.example.hmfse.data_models;

public class OrderDataType {
    String OrderID;
    String ODate;
    String BillAmount;
    String Status;

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public String getODate() {
        return ODate;
    }

    public void setODate(String ODate) {
        this.ODate = ODate;
    }

    public String getBillAmount() {
        return BillAmount;
    }

    public void setBillAmount(String billAmount) {
        BillAmount = billAmount;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
