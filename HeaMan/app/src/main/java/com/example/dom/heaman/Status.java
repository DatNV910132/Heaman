package com.example.dom.heaman;

public class Status {

    private String UID;
    private String date;
    private String time;
    private String SID;
    private String Namesp;
    private int Calorie;


    public Status() {

    }

    public String getSID() {
        return SID;
    }

    public void setSID(String SID) {
        this.SID = SID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public int getCalorie() {
        return Calorie;
    }

    public void setCalorie(int calorie) {
        Calorie = calorie;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Status(String UID, String date, String time, String SID, String namesp, int calorie) {
        this.UID = UID;
        this.date = date;
        this.time = time;
        this.SID = SID;
        Namesp = namesp;
        Calorie = calorie;
    }

    public String getNamesp() {
        return Namesp;
    }

    public void setNamesp(String namesp) {
        Namesp = namesp;
    }

    @Override
    public String toString() {
        return "Status{" +
                "UID='" + UID + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", SID='" + SID + '\'' +
                ", Namesp='" + Namesp + '\'' +
                ", Calorie=" + Calorie +
                '}';
    }
}
