package com.example.sprice.myapplication.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.sprice.myapplication.R;
import com.example.sprice.myapplication.components.CalculatorNumericButton;
import com.example.sprice.myapplication.components.CalculatorOperatorButton;
import com.example.sprice.myapplication.helpers.EntryHelper;
import com.example.sprice.myapplication.helpers.Operator;

public class MainActivity extends AppCompatActivity {
    private EntryHelper mEntryHelper = new EntryHelper() {
        @Override
        public void entryUpdated(String entry) {
            setEntry(entry);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEntryHelper.clearInput();
    }

    public void numericButton_onClick(View view) {
        CalculatorNumericButton button = (CalculatorNumericButton)view;
        Integer buttonNumericValue = button.getNumericValue();
        mEntryHelper.numericInput(buttonNumericValue);
    }

    public void buttonDecimal_onClick(View view) {
        mEntryHelper.decimalPointInput();
    }

    public void operatorButton_onClick(View view) {
        CalculatorOperatorButton button = (CalculatorOperatorButton)view;
        Operator buttonOperatorValue = button.getOperator();
        mEntryHelper.operatorInput(buttonOperatorValue);
    }

    public void buttonParen_onClick(View view) {
        mEntryHelper.parenInput();
    }

    public void buttonClear_onClick(View view) {
        mEntryHelper.clearInput();
    }

    private void setEntry(String entry) {
        TextView display = findViewById(R.id.display);
        display.setText(entry);
    }
}
