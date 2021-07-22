package com.example.dom.heaman;

public class StatusItem {

    private String StDate;
    private String StCalo;
    private String StPercentCalo;

    public StatusItem(String stDate, String stCalo, String stPercentCalo) {
        StDate = stDate;
        StCalo = stCalo;
        StPercentCalo = stPercentCalo;
    }

    public String getStDate() {
        return StDate;
    }

    public void setStDate(String stDate) {
        StDate = stDate;
    }

    public String getStCalo() {
        return StCalo;
    }

    public void setStCalo(String stCalo) {
        StCalo = stCalo;
    }

    public String getStPercentCalo() {
        return StPercentCalo;
    }

    public void setStPercentCalo(String stPercentCalo) {
        StPercentCalo = stPercentCalo;
    }

    @Override
    public String toString() {
        return "StatusItem{" +
                "StDate='" + StDate + '\'' +
                ", StCalo='" + StCalo + '\'' +
                ", StPercentCalo='" + StPercentCalo + '\'' +
                '}';
    }
}
