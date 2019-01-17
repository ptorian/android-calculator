package com.example.sprice.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.sprice.myapplication.components.CalculatorNumericButton;
import com.example.sprice.myapplication.components.CalculatorOperatorButton;
import com.example.sprice.myapplication.components.Operator;

enum EntryState {
    EMPTY,
    OPERAND_VALUE_ENTRY,
    OPERAND_DECIMAL_ENTRY,
    OPERAND_DECIMAL_VALUE_ENTRY,
    OPERATOR_ENTRY
}

public class MainActivity extends AppCompatActivity {
    private EntryState mEntryState = EntryState.EMPTY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setEntry("");
    }

    public void numericButton_onClick(View view) {
        if (mEntryState == EntryState.EMPTY) {
            mEntryState = EntryState.OPERAND_VALUE_ENTRY;
        } else if (mEntryState == EntryState.OPERATOR_ENTRY) {
            mEntryState = EntryState.OPERAND_VALUE_ENTRY;
        } else if (mEntryState == EntryState.OPERAND_DECIMAL_ENTRY) {
            mEntryState = EntryState.OPERAND_DECIMAL_VALUE_ENTRY;
        }
        CalculatorNumericButton button = (CalculatorNumericButton)view;

        Integer buttonNumericValue = button.getNumericValue();
        appendToEntry(buttonNumericValue.toString());
    }

    public void buttonDecimal_onClick(View view) {
        if (mEntryState == EntryState.OPERAND_VALUE_ENTRY) {
            mEntryState = EntryState.OPERAND_DECIMAL_ENTRY;
            appendToEntry(".");
        }
    }

    public void operatorButton_onClick(View view) {
        if (mEntryState != EntryState.OPERAND_VALUE_ENTRY && mEntryState != EntryState.OPERAND_DECIMAL_VALUE_ENTRY) {
            return;
        }
        CalculatorOperatorButton button = (CalculatorOperatorButton)view;

        Operator buttonOperandValue = button.getOperator();
        switch (buttonOperandValue) {
            case ADD:
                appendToEntry("+");
                break;
            case SUBTRACT:
                appendToEntry("-");
                break;
            case MULTIPLY:
                appendToEntry("*");
                break;
            case DIVIDE:
                appendToEntry("/");
                break;
        }

        mEntryState = EntryState.OPERATOR_ENTRY;
    }

    public void buttonClear_onClick(View view) {
        mEntryState = EntryState.EMPTY;
        setEntry("");
}

    private void setEntry(String entry) {
        TextView display = findViewById(R.id.display);
        display.setText(entry);
    }

    private void appendToEntry(String entry) {
        TextView display = findViewById(R.id.display);
        String currentText = display.getText().toString();
        String newText = currentText + entry;
        display.setText(newText);
    }
}
