package com.example.sprice.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.sprice.myapplication.components.CalculatorNumericButton;
import com.example.sprice.myapplication.components.CalculatorOperatorButton;
import com.example.sprice.myapplication.components.Operator;

enum EntryState {
    START,
    EXPRESSION_START,
    OPERAND_START,
    OPERAND_LEADING_ZERO,
    OPERAND_ENTRY,
    OPERAND_DECIMAL_START,
    OPERAND_DECIMAL_ENTRY
}

public class MainActivity extends AppCompatActivity {
    private EntryState mEntryState = EntryState.START;
    private int mParenCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setEntry("");
    }

    public void numericButton_onClick(View view) {
        CalculatorNumericButton button = (CalculatorNumericButton)view;
        Integer buttonNumericValue = button.getNumericValue();

        switch (mEntryState) {

            case START:
            case EXPRESSION_START:
            case OPERAND_START:
                if (buttonNumericValue == 0) {
                    mEntryState = EntryState.OPERAND_LEADING_ZERO;
                } else {
                    mEntryState = EntryState.OPERAND_ENTRY;
                }
                appendToEntry(buttonNumericValue.toString());
                break;

            case OPERAND_LEADING_ZERO:
                if (buttonNumericValue != 0) {
                    mEntryState = EntryState.OPERAND_ENTRY;
                    appendToEntry(buttonNumericValue.toString(), true);
                }
                break;

            case OPERAND_DECIMAL_START:
                mEntryState = EntryState.OPERAND_DECIMAL_ENTRY;
                appendToEntry(buttonNumericValue.toString());
                break;

            default:
                appendToEntry(buttonNumericValue.toString());
        }
    }

    public void buttonDecimal_onClick(View view) {
        switch (mEntryState) {

            case START:
            case EXPRESSION_START:
            case OPERAND_START:
                appendToEntry("0.");
                mEntryState = EntryState.OPERAND_DECIMAL_START;
                break;

            case OPERAND_LEADING_ZERO:
            case OPERAND_ENTRY:
                appendToEntry(".");
                mEntryState = EntryState.OPERAND_DECIMAL_START;
        }
    }

    public void operatorButton_onClick(View view) {
        if (mEntryState != EntryState.EXPRESSION_START && mEntryState != EntryState.OPERAND_ENTRY && mEntryState != EntryState.OPERAND_DECIMAL_ENTRY) {
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

        mEntryState = EntryState.OPERAND_START;
    }

    public void buttonParen_onClick(View view) {
        switch (mEntryState) {
            case OPERAND_LEADING_ZERO:
            case OPERAND_ENTRY:
            case OPERAND_DECIMAL_ENTRY:
                if (mParenCount == 0) {
                    ++mParenCount;
                    appendToEntry("*(");
                } else {
                    --mParenCount;
                    appendToEntry(")");
                }
                mEntryState = EntryState.EXPRESSION_START;
                break;

            case START:
            case EXPRESSION_START:
            case OPERAND_START:
                ++mParenCount;
                appendToEntry("(");
                mEntryState = EntryState.EXPRESSION_START;
                break;
        }
    }

    public void buttonClear_onClick(View view) {
        mEntryState = EntryState.START;
        setEntry("");
}

    private void setEntry(String entry) {
        TextView display = findViewById(R.id.display);
        display.setText(entry);
    }

    private void appendToEntry(String entry) {
        appendToEntry(entry, false);
    }

    private void appendToEntry(String entry, boolean replacePreviousCharacter) {
        TextView display = findViewById(R.id.display);
        String currentText = display.getText().toString();
        String currentTextAfterRemovingLastCharacter = replacePreviousCharacter ? currentText.substring(0, currentText.length() - 1) : currentText;
        String newText = currentTextAfterRemovingLastCharacter + entry;
        display.setText(newText);
    }
}
