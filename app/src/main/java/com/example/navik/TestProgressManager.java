package com.example.navik;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

public final class TestProgressManager {

    private static final String PREFS_NAME = "NavikTestProgress";

    public static final String TEST_IQ = "IQ Test";
    public static final String TEST_PERSONALITY = "Personality Assessment";
    public static final String TEST_EQ = "EQ Test";
    public static final String TEST_MEMORY = "Memory Test";
    public static final String TEST_NUMERICAL = "Numerical Ability";
    public static final String TEST_SPACE_RELATIONS = "Space Relations";
    public static final String TEST_MECHANICAL = "Mechanical Reasoning";
    public static final String TEST_ABSTRACT = "Abstract Reasoning";

    public static final String[] REQUIRED_TESTS = new String[] {
            TEST_IQ,
            TEST_PERSONALITY,
            TEST_EQ,
            TEST_MEMORY,
            TEST_NUMERICAL,
            TEST_SPACE_RELATIONS,
            TEST_MECHANICAL,
            TEST_ABSTRACT
    };

    private TestProgressManager() {
    }

    public static void markCompleted(@NonNull Context context, @NonNull String testName) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        prefs.edit().putBoolean(keyForTest(testName), true).apply();
    }

    public static boolean isCompleted(@NonNull Context context, @NonNull String testName) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getBoolean(keyForTest(testName), false);
    }

    public static boolean areAllTestsCompleted(@NonNull Context context) {
        for (String test : REQUIRED_TESTS) {
            if (!isCompleted(context, test)) {
                return false;
            }
        }
        return true;
    }

    private static String keyForTest(@NonNull String testName) {
        String normalized = testName.trim().toLowerCase();
        normalized = normalized.replaceAll("\\s+", "_");
        return "completed_" + normalized;
    }
}
