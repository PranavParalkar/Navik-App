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

    // Ordered test sequence (1-8), Memory Test is always last
    public static final String[] TEST_ORDER = new String[] {
            TEST_IQ, // 1
            TEST_PERSONALITY, // 2
            TEST_EQ, // 3
            TEST_NUMERICAL, // 4
            TEST_SPACE_RELATIONS, // 5
            TEST_MECHANICAL, // 6
            TEST_ABSTRACT, // 7
            TEST_MEMORY // 8 (last)
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

    /**
     * Returns the next test name in the ordered sequence, or null if this was the
     * last test.
     */
    public static String getNextTest(@NonNull String currentTestName) {
        for (int i = 0; i < TEST_ORDER.length; i++) {
            if (TEST_ORDER[i].equalsIgnoreCase(currentTestName)) {
                if (i + 1 < TEST_ORDER.length) {
                    return TEST_ORDER[i + 1];
                } else {
                    return null; // last test
                }
            }
        }
        return null;
    }

    /**
     * Returns the 1-based order number of the given test, or -1 if not found.
     */
    public static int getTestOrder(@NonNull String testName) {
        for (int i = 0; i < TEST_ORDER.length; i++) {
            if (TEST_ORDER[i].equalsIgnoreCase(testName)) {
                return i + 1;
            }
        }
        return -1;
    }

    /**
     * Returns the total number of questions for a given test.
     */
    public static int getTotalQuestions(@NonNull String testName) {
        if (testName.equalsIgnoreCase(TEST_NUMERICAL))
            return 15;
        return 10; // default for all other tests
    }

    /**
     * Returns true if this test is the last one in the sequence (Memory Test).
     */
    public static boolean isLastTest(@NonNull String testName) {
        return TEST_ORDER.length > 0 && TEST_ORDER[TEST_ORDER.length - 1].equalsIgnoreCase(testName);
    }

    /**
     * Returns true if this test is unlocked (i.e. all previous tests in TEST_ORDER
     * have been completed). The first test is always unlocked.
     */
    public static boolean isTestUnlocked(@NonNull Context context, @NonNull String testName) {
        for (int i = 0; i < TEST_ORDER.length; i++) {
            if (TEST_ORDER[i].equalsIgnoreCase(testName)) {
                // First test is always unlocked
                if (i == 0)
                    return true;
                // Check that ALL previous tests are completed
                for (int j = 0; j < i; j++) {
                    if (!isCompleted(context, TEST_ORDER[j])) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false; // test not found in order
    }
}
