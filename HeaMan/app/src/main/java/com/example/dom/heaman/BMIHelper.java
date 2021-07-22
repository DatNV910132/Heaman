package com.example.dom.heaman;

import android.widget.Switch;

public class BMIHelper {

    public Float BMI(float cc, float cn){
        float bmi;
        bmi = cn/(cc*cc);
        return bmi;
    }
    public String DuDoan(float chiso){
        String dudoan = null;
        if(chiso < 18.5){
            dudoan = "Thiếu cân";
        }
        if( chiso >= 18.5 && chiso < 24.9){
            dudoan = "Bình thường ";
        }
        if( chiso >= 25 && chiso < 29.9){
            dudoan = "Thừa cân";
        }
        if( chiso >= 30 && chiso < 34.9){
            dudoan = "Béo phì cấp độ 1 ";
        }
        if( chiso >= 35 && chiso < 39.9){
            dudoan = "Béo phì cấp độ 2 ";
        }
        if( chiso >= 40){
            dudoan = "Béo phì cấp độ 3 ";
        }
        return dudoan;
    }
}
