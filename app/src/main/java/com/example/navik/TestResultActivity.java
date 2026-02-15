package com.example.navik;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class TestResultActivity extends AppCompatActivity {

    private TextView scoreText, percentageText;
    private TextView correctCount, wrongCount, totalCount;
    private TextView resultEmoji, resultTitle, resultSubtitle;
    private TextView messageEmoji, messageText;
    private LinearLayout messageContainer, statsRow;
    private View scoreRingContainer;
    private Button btnViewReport, btnNextTest, btnBackToTests;

    private String testName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_result);

        int score = getIntent().getIntExtra("score", 0);
        int total = getIntent().getIntExtra("total", 10);
        testName = getIntent().getStringExtra("testName");

        initializeViews();
        displayResults(score, total);
        setupButtons();
        setupClickListeners();
    }

    private void initializeViews() {
        scoreText = findViewById(R.id.scoreText);
        percentageText = findViewById(R.id.percentageText);
        correctCount = findViewById(R.id.correctCount);
        wrongCount = findViewById(R.id.wrongCount);
        totalCount = findViewById(R.id.totalCount);
        resultEmoji = findViewById(R.id.resultEmoji);
        resultTitle = findViewById(R.id.resultTitle);
        resultSubtitle = findViewById(R.id.resultSubtitle);
        messageEmoji = findViewById(R.id.messageEmoji);
        messageText = findViewById(R.id.messageText);
        messageContainer = findViewById(R.id.messageContainer);
        statsRow = findViewById(R.id.statsRow);
        scoreRingContainer = findViewById(R.id.scoreRingContainer);
        btnViewReport = findViewById(R.id.btnViewReport);
        btnNextTest = findViewById(R.id.btnNextTest);
        btnBackToTests = findViewById(R.id.btnBackToTests);
    }

    private void setupButtons() {
        boolean isLastTest = testName != null && TestProgressManager.isLastTest(testName);

        if (isLastTest) {
            // Memory Test (last test) — show "View Report" + "Back to Tests"
            if (btnViewReport != null) {
                btnViewReport.setVisibility(View.VISIBLE);
                // Only enable if all tests are completed
                boolean allDone = TestProgressManager.areAllTestsCompleted(this);
                if (!allDone) {
                    btnViewReport.setEnabled(false);
                    btnViewReport.setAlpha(0.6f);
                }
            }
            if (btnNextTest != null) {
                btnNextTest.setVisibility(View.GONE);
            }
        } else {
            // All other tests — show "Next Test" + "Back to Tests"
            if (btnViewReport != null) {
                btnViewReport.setVisibility(View.GONE);
            }
            if (btnNextTest != null) {
                btnNextTest.setVisibility(View.VISIBLE);
                // Set button text with the next test name
                String nextTest = testName != null ? TestProgressManager.getNextTest(testName) : null;
                if (nextTest != null) {
                    btnNextTest.setText("Next: " + nextTest);
                } else {
                    btnNextTest.setText("Next Test");
                }
            }
        }

        // Score ring and stats are always hidden — scores visible only in report
        if (scoreRingContainer != null)
            scoreRingContainer.setVisibility(View.GONE);
        if (statsRow != null)
            statsRow.setVisibility(View.GONE);
    }

    private void displayResults(int score, int total) {
        // Since we're hiding marks, we just set the motivational/completion message
        if (testName != null
                && (testName.equalsIgnoreCase("Personality Assessment") || testName.equalsIgnoreCase("EQ Test"))) {
            setCompletedState();
        } else if (testName != null && testName.equalsIgnoreCase("Memory Test")) {
            setMemoryCompletedState();
        } else {
            setTestCompletedState();
        }
    }

    private void setCompletedState() {
        if (resultEmoji != null)
            resultEmoji.setText("✅");
        if (resultTitle != null)
            resultTitle.setText("Assessment Complete!");
        if (resultSubtitle != null)
            resultSubtitle.setText("Your responses have been recorded");
        if (messageEmoji != null)
            messageEmoji.setText("📋");
        if (messageText != null)
            messageText.setText(
                    "Thank you for completing the assessment. Your responses will be included in the final report.");
    }

    private void setMemoryCompletedState() {
        if (resultEmoji != null)
            resultEmoji.setText("🏁");
        if (resultTitle != null)
            resultTitle.setText("All Tests Done!");
        if (resultSubtitle != null)
            resultSubtitle.setText("You've completed the final test");
        if (messageEmoji != null)
            messageEmoji.setText("📊");
        if (messageText != null)
            messageText.setText(
                    "Congratulations! You've finished all assessments. View your full report to see your detailed results and scores.");
    }

    private void setTestCompletedState() {
        if (resultEmoji != null)
            resultEmoji.setText("🎉");
        if (resultTitle != null)
            resultTitle.setText("Test Completed!");
        if (resultSubtitle != null)
            resultSubtitle.setText("Great work, keep going!");

        // Show which test number this was
        int order = testName != null ? TestProgressManager.getTestOrder(testName) : -1;
        int totalTests = TestProgressManager.TEST_ORDER.length;

        if (messageEmoji != null)
            messageEmoji.setText("🚀");
        if (messageText != null) {
            if (order > 0) {
                int remaining = totalTests - order;
                if (remaining > 0) {
                    messageText.setText("You've completed test " + order + " of " + totalTests + ". " + remaining
                            + " more to go — keep up the momentum!");
                } else {
                    messageText.setText(
                            "You've completed all " + totalTests + " tests! View your report to see the results.");
                }
            } else {
                messageText.setText("Well done! Continue to the next test to complete your assessment.");
            }
        }
    }

    private void setupClickListeners() {
        if (btnViewReport != null) {
            btnViewReport.setOnClickListener(v -> {
                if (!TestProgressManager.areAllTestsCompleted(this)) {
                    Toast.makeText(this, "Complete all tests to view the report.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, TestsActivity.class));
                    finish();
                    return;
                }

                startActivity(new Intent(this, ReportActivity.class));
                finish();
            });
        }

        if (btnNextTest != null) {
            btnNextTest.setOnClickListener(v -> {
                String nextTest = testName != null ? TestProgressManager.getNextTest(testName) : null;
                if (nextTest != null) {
                    // Navigate to the landing page for the next test
                    Intent intent = new Intent(this, TestLandingActivity.class);
                    intent.putExtra("testName", nextTest);
                    intent.putExtra("totalQuestions", TestProgressManager.getTotalQuestions(nextTest));
                    startActivity(intent);
                    finish();
                } else {
                    // No next test, go back to tests list
                    startActivity(new Intent(this, TestsActivity.class));
                    finish();
                }
            });
        }

        if (btnBackToTests != null) {
            btnBackToTests.setOnClickListener(v -> {
                startActivity(new Intent(this, TestsActivity.class));
                finish();
            });
        }
    }
}
