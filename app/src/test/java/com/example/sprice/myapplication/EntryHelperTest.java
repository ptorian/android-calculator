package com.example.sprice.myapplication;

import com.example.sprice.myapplication.helpers.EntryHelper;
import com.example.sprice.myapplication.helpers.EntryState;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TestEntryHelper extends EntryHelper {
    @Override
    public void entryUpdated(String string) {
        // do nothing
    }

    EntryState getEntryState() {
        return mEntryState;
    }
}

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class EntryHelperTest {
    @Test
    public void entryState_starts_empty() {
        TestEntryHelper mockedEntryHelper = spy(TestEntryHelper.class);
        assertEquals(mockedEntryHelper.getEntryState(), EntryState.START);
    }

    @Test
    public void numericInput_callsEntryUpdated() {
        TestEntryHelper mockedEntryHelper = spy(TestEntryHelper.class);

        mockedEntryHelper.numericInput(1);
        verify(mockedEntryHelper).entryUpdated("1");
    }

    @Test
    public void numericInput_updatesEntryStateToLeadingZero() {
        TestEntryHelper mockedEntryHelper = spy(TestEntryHelper.class);

        mockedEntryHelper.numericInput(0);
        assertEquals(mockedEntryHelper.getEntryState(), EntryState.OPERAND_LEADING_ZERO);
    }

    @Test
    public void numericInput_updatesEntryState() {
        TestEntryHelper mockedEntryHelper = spy(TestEntryHelper.class);

        mockedEntryHelper.numericInput(1);
        assertEquals(mockedEntryHelper.getEntryState(), EntryState.OPERAND_ENTRY);
    }
}