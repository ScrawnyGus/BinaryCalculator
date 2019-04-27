package com.example.binarycalculator;

import android.text.InputFilter;
import android.text.Spanned;

public class CustomOktalRangeInputFilter implements InputFilter {
    private double minOktal;
    private double maxOktal;

    public CustomOktalRangeInputFilter(double minOkt, double maxOkt) {
        this.minOktal = minOkt;
        this.maxOktal = maxOkt;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        try {
            String newVal = dest.toString().substring(0,dstart) + dest.toString().substring(dend,dest.toString().length());
            newVal = newVal.substring(0,dstart) + source.toString() + newVal.substring(dstart,newVal.length());
            double input = Double.parseDouble(newVal);

            if(isInRange(minOktal, maxOktal, input)) {
                return null;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return "";
    }

    private boolean isInRange(double a, double b, double c) {
        return b > a ? c >= a && c <= b : c >= b && c <= a;
    }
}
