package com.dmcintyre.mylib;

import android.widget.TextView;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Forecast test.
 *
 */
public class ForecastTest {
    private static String TEMP = "test";

    // Test constructor
    @Test
    public void addition_isCorrect() {
        Forecast f = new Forecast(TEMP);
        assertEquals(f.getmTemp(), TEMP);
    }
}