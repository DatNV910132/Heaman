package com.example.dom.heaman;

import android.widget.Switch;

public class BMRHelper {

    public Float BMRCal(int tuoi, float trongluong, float chieucao, int gioitinh, int hoatdong) {
        float BMR = 0;
        float hd = 0;
        switch (hoatdong) {
            case 0:
                hd = 1.2f;
                break;
            case 1:
                hd = 1.375f;
                break;
            case 2:
                hd = 1.55f;
                break;
            case 3:
                hd = 1.725f;
                break;
            case 4:
                hd = 1.9f;
                break;


        }
        if (gioitinh == 0) {
            BMR = (float) (hd * (10 * trongluong + 6.25 * chieucao - 5 * tuoi + 5));

        } else {
            BMR = (float) (hd * (10 * trongluong + 6.25 * chieucao - 5 * tuoi - 161));
        }

        return BMR;
    }
}
