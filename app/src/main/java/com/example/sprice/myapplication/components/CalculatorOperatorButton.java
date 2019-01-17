package com.example.sprice.myapplication.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.example.sprice.myapplication.R;

public class CalculatorOperatorButton extends android.support.v7.widget.AppCompatButton {
    private Operator mOperator;

    public CalculatorOperatorButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);


        TypedArray a = context.obtainStyledAttributes(attributeSet, R.styleable.CalculatorOperatorButton);
        final int N = a.getIndexCount();
        for (int i = 0; i < N; ++i) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.CalculatorOperatorButton_operator:
                    String textOperator = a.getString(attr);
                    assert textOperator != null;
                    setOperator(textOperator);
                    break;
            }
        }
        a.recycle();
    }

    private void setOperator(String operator) {
        switch (operator) {
            case "+":
                mOperator = Operator.ADD;
                break;
            case "-":
                mOperator = Operator.SUBTRACT;
                break;
            case "*":
                mOperator = Operator.MULTIPLY;
                break;
            case "/":
                mOperator = Operator.DIVIDE;
                break;
        }
    }

    public Operator getOperator() {
        return mOperator;
    }
}
