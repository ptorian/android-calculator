package com.example.sprice.myapplication.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.example.sprice.myapplication.R;

public class CalculatorNumericButton extends android.support.v7.widget.AppCompatButton {
    private Integer mNumericValue;

    public CalculatorNumericButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);


        TypedArray a = context.obtainStyledAttributes(attributeSet, R.styleable.CalculatorNumericButton);
        final int N = a.getIndexCount();
        for (int i = 0; i < N; ++i) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.CalculatorNumericButton_numericValue:
                    mNumericValue = a.getInteger(attr, 0);
                    break;
            }
        }
        a.recycle();
    }

    public Integer getNumericValue() {
        return mNumericValue;
    }
}
