package com.example.sprice.myapplication.helpers;

public class EntryHelper {
    protected EntryState mEntryState = EntryState.START;
    private int mParenCount = 0;
    private String mEntry = "";

    public void numericInput(int value) {
        switch (mEntryState) {

            case START:
            case EXPRESSION_START:
            case OPERAND_START:
                if (value == 0) {
                    mEntryState = EntryState.OPERAND_LEADING_ZERO;
                } else {
                    mEntryState = EntryState.OPERAND_ENTRY;
                }
                appendToEntry(Integer.toString(value));
                break;

            case OPERAND_LEADING_ZERO:
                if (value != 0) {
                    mEntryState = EntryState.OPERAND_ENTRY;
                    appendToEntry(Integer.toString(value), true);
                }
                break;

            case OPERAND_DECIMAL_START:
                mEntryState = EntryState.OPERAND_DECIMAL_ENTRY;
                appendToEntry(Integer.toString(value));
                break;

            default:
                appendToEntry(Integer.toString(value));
        }
    }

    public void decimalPointInput() {
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

    public void operatorInput(Operator value) {
        if (mEntryState != EntryState.EXPRESSION_START && mEntryState != EntryState.OPERAND_ENTRY && mEntryState != EntryState.OPERAND_DECIMAL_ENTRY) {
            return;
        }

        switch (value) {
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

    public void parenInput() {
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

    public void clearInput() {
        mEntryState = EntryState.START;
        setEntry("");
    }

    private void setEntry(String entry) {
        mEntry = entry;
        callEntryUpdated();
    }

    public void appendToEntry(String entry) {
        appendToEntry(entry, false);
    }

    private void appendToEntry(String entry, boolean replacePreviousCharacter) {
        String currentText = mEntry;
        String currentTextAfterRemovingLastCharacter = replacePreviousCharacter ? currentText.substring(0, currentText.length() - 1) : currentText;
        String newText = currentTextAfterRemovingLastCharacter + entry;
        setEntry(newText);
    }

    private void callEntryUpdated() {
        entryUpdated(mEntry);
    }

    public void entryUpdated(String entry) {
        throw new UnsupportedOperationException();
    }
}
