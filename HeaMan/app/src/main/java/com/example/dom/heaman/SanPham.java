package com.example.dom.heaman;

import java.io.Serializable;

public class SanPham implements Serializable {
    private String Code;
    private String TenSP;
    private String Hangsx;
    private String Loai;
    int calorie;
    private boolean choice;

    public SanPham() {

    }

    @Override
    public String toString() {
        return "SanPham{" +
                "Code='" + Code + '\'' +
                ", TenSP='" + TenSP + '\'' +
                ", Hangsx='" + Hangsx + '\'' +
                ", Loai='" + Loai + '\'' +
                ", calorie=" + calorie +
                '}';
    }

    public SanPham(String tenSP, String hangsx, String loai, int calorie) {
        TenSP = tenSP;
        Hangsx = hangsx;
        Loai = loai;
        this.calorie = calorie;
    }

    public SanPham(String code, String tenSP, String hangsx, String loai, int calorie) {
        Code = code;
        TenSP = tenSP;
        Hangsx = hangsx;
        Loai = loai;
        this.calorie = calorie;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String tenSP) {
        TenSP = tenSP;
    }

    public String getHangsx() {
        return Hangsx;
    }

    public void setHangsx(String hangsx) {
        Hangsx = hangsx;
    }

    public String getLoai() {
        return Loai;
    }

    public void setLoai(String loai) {
        Loai = loai;
    }

    public int getCalorie() {
        return calorie;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }


}
