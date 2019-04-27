package com.example.binarycalculator;

import android.text.InputFilter;
import android.text.Spanned;

public class CustomRangeInputFilter implements InputFilter {
    private int minValue, maxValue;

    public CustomRangeInputFilter(int minVal, int maxVal) {
        this.minValue = minVal;
        this.maxValue = maxVal;
    }

    public CustomRangeInputFilter(String minVal, String maxVal) {
        this.minValue = Integer.parseInt(minVal);
        this.maxValue = Integer.parseInt(maxVal);
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        try {
            int input = Integer.parseInt(dest.toString() + source.toString());

            if(isInRange(minValue, maxValue, input)) {
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
