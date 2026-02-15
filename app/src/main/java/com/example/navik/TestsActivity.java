package com.example.navik;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

public class TestsActivity extends AppCompatActivity {

    private ImageView btnBack;
    private CardView cardIQTest, cardPersonalityTest, cardEQTest, cardMemoryTest, cardNumericalTest,
            cardSpaceRelationsTest, cardMechanicalTest, cardAbstractTest;
    private LinearLayout navHome, navFiles, navSkills, navCareer;

    private View layoutIQTest, layoutPersonalityTest, layoutEQTest, layoutMemoryTest, layoutNumericalTest,
            layoutSpaceRelationsTest, layoutMechanicalTest, layoutAbstractTest;
    private TextView tvIQStatus, tvPersonalityStatus, tvEQStatus, tvMemoryStatus, tvNumericalStatus,
            tvSpaceRelationsStatus, tvMechanicalStatus, tvAbstractStatus;
    private TextView tvProgressSummary, tvProgressPercent;
    private View progressBarFill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tests);

        initializeViews();
        setupNavigation();
        updateCompletionUi();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateCompletionUi();
    }

    private void initializeViews() {
        btnBack = findViewById(R.id.btnBack);
        cardIQTest = findViewById(R.id.cardIQTest);
        cardPersonalityTest = findViewById(R.id.cardPersonalityTest);
        cardEQTest = findViewById(R.id.cardEQTest);
        cardMemoryTest = findViewById(R.id.cardMemoryTest);
        cardNumericalTest = findViewById(R.id.cardNumericalTest);
        cardSpaceRelationsTest = findViewById(R.id.cardSpaceRelationsTest);
        cardMechanicalTest = findViewById(R.id.cardMechanicalTest);
        cardAbstractTest = findViewById(R.id.cardAbstractTest);

        layoutIQTest = findViewById(R.id.layoutIQTest);
        layoutPersonalityTest = findViewById(R.id.layoutPersonalityTest);
        layoutEQTest = findViewById(R.id.layoutEQTest);
        layoutMemoryTest = findViewById(R.id.layoutMemoryTest);
        layoutNumericalTest = findViewById(R.id.layoutNumericalTest);
        layoutSpaceRelationsTest = findViewById(R.id.layoutSpaceRelationsTest);
        layoutMechanicalTest = findViewById(R.id.layoutMechanicalTest);
        layoutAbstractTest = findViewById(R.id.layoutAbstractTest);

        tvIQStatus = findViewById(R.id.tvIQStatus);
        tvPersonalityStatus = findViewById(R.id.tvPersonalityStatus);
        tvEQStatus = findViewById(R.id.tvEQStatus);
        tvMemoryStatus = findViewById(R.id.tvMemoryStatus);
        tvNumericalStatus = findViewById(R.id.tvNumericalStatus);
        tvSpaceRelationsStatus = findViewById(R.id.tvSpaceRelationsStatus);
        tvMechanicalStatus = findViewById(R.id.tvMechanicalStatus);
        tvAbstractStatus = findViewById(R.id.tvAbstractStatus);

        navHome = findViewById(R.id.navHome);
        navFiles = findViewById(R.id.navFiles);
        navSkills = findViewById(R.id.navSkills);
        navCareer = findViewById(R.id.navCareer);

        tvProgressSummary = findViewById(R.id.tvProgressSummary);
        tvProgressPercent = findViewById(R.id.tvProgressPercent);
        progressBarFill = findViewById(R.id.progressBarFill);

        btnBack.setOnClickListener(v -> finish());
    }

    private void updateCompletionUi() {
        int completed = 0;
        completed += updateSingleTestUi(TestProgressManager.TEST_IQ, layoutIQTest, tvIQStatus, "0/10");
        completed += updateSingleTestUi(TestProgressManager.TEST_PERSONALITY, layoutPersonalityTest,
                tvPersonalityStatus, "0/10");
        completed += updateSingleTestUi(TestProgressManager.TEST_EQ, layoutEQTest, tvEQStatus, "0/10");
        completed += updateSingleTestUi(TestProgressManager.TEST_NUMERICAL, layoutNumericalTest, tvNumericalStatus,
                "0/15");
        completed += updateSingleTestUi(TestProgressManager.TEST_SPACE_RELATIONS, layoutSpaceRelationsTest,
                tvSpaceRelationsStatus, "0/10");
        completed += updateSingleTestUi(TestProgressManager.TEST_MECHANICAL, layoutMechanicalTest, tvMechanicalStatus,
                "0/10");
        completed += updateSingleTestUi(TestProgressManager.TEST_ABSTRACT, layoutAbstractTest, tvAbstractStatus,
                "0/10");
        completed += updateSingleTestUi(TestProgressManager.TEST_MEMORY, layoutMemoryTest, tvMemoryStatus, "0/10");

        // Update progress summary
        if (tvProgressSummary != null) {
            tvProgressSummary.setText(completed + " of 8 tests completed");
        }
        if (tvProgressPercent != null) {
            int pct = (completed * 100) / 8;
            tvProgressPercent.setText(pct + "%");
        }
        final int completedCount = completed;
        if (progressBarFill != null) {
            progressBarFill.post(() -> {
                View parent = (View) progressBarFill.getParent();
                int parentWidth = parent.getWidth();
                int fillWidth = (parentWidth * completedCount) / 8;
                ViewGroup.LayoutParams params = progressBarFill.getLayoutParams();
                params.width = fillWidth;
                progressBarFill.setLayoutParams(params);
            });
        }

        // Setup click listeners with lock awareness
        setupClickListeners();
    }

    /**
     * Updates a single test card UI to show completed, unlocked, or locked state.
     * Returns 1 if completed, 0 otherwise.
     */
    private int updateSingleTestUi(String testName, View backgroundContainer, TextView statusView, String defaultText) {
        if (backgroundContainer == null || statusView == null) {
            return 0;
        }

        boolean isCompleted = TestProgressManager.isCompleted(this, testName);
        boolean isUnlocked = TestProgressManager.isTestUnlocked(this, testName);

        if (isCompleted) {
            // Completed state — green gradient
            backgroundContainer.setBackgroundResource(R.drawable.bg_test_completed);
            statusView.setText("✓ Done");
            statusView.setTextColor(ContextCompat.getColor(this, R.color.white));
            backgroundContainer.setAlpha(1f);
            return 1;
        } else if (isUnlocked) {
            // Unlocked & available — normal purple gradient
            backgroundContainer.setBackgroundResource(R.drawable.bg_skill_card_purple);
            statusView.setText(defaultText);
            statusView.setTextColor(ContextCompat.getColor(this, R.color.white));
            backgroundContainer.setAlpha(1f);
            return 0;
        } else {
            // Locked — dark greyed out
            backgroundContainer.setBackgroundResource(R.drawable.bg_test_locked);
            statusView.setText("🔒");
            statusView.setTextColor(ContextCompat.getColor(this, R.color.white));
            backgroundContainer.setAlpha(0.7f);
            return 0;
        }
    }

    private void setupClickListeners() {
        setupTestCardClick(cardIQTest, TestProgressManager.TEST_IQ, 10);
        setupTestCardClick(cardPersonalityTest, TestProgressManager.TEST_PERSONALITY, 10);
        setupTestCardClick(cardEQTest, TestProgressManager.TEST_EQ, 10);
        setupTestCardClick(cardNumericalTest, TestProgressManager.TEST_NUMERICAL, 15);
        setupTestCardClick(cardSpaceRelationsTest, TestProgressManager.TEST_SPACE_RELATIONS, 10);
        setupTestCardClick(cardMechanicalTest, TestProgressManager.TEST_MECHANICAL, 10);
        setupTestCardClick(cardAbstractTest, TestProgressManager.TEST_ABSTRACT, 10);
        setupTestCardClick(cardMemoryTest, TestProgressManager.TEST_MEMORY, 10);
    }

    /**
     * Sets up a click listener for a test card that respects the lock state.
     * If locked — shows a Toast. If unlocked — launches the landing page.
     */
    private void setupTestCardClick(CardView card, String testName, int totalQuestions) {
        if (card == null) return;

        card.setOnClickListener(v -> {
            if (!TestProgressManager.isTestUnlocked(this, testName)) {
                String previousTest = getPreviousIncompleteTest(testName);
                if (previousTest != null) {
                    Toast.makeText(this, "Complete \"" + previousTest + "\" first to unlock this test.",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Complete the previous test to unlock this one.", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            if (TestProgressManager.isCompleted(this, testName)) {
                Toast.makeText(this, "You've already completed this test!", Toast.LENGTH_SHORT).show();
                return;
            }

            startTest(testName, totalQuestions);
        });
    }

    /**
     * Returns the name of the first incomplete test that comes before the given
     * test
     * in the TEST_ORDER sequence. Used for the "Complete X first" message.
     */
    private String getPreviousIncompleteTest(String testName) {
        for (int i = 0; i < TestProgressManager.TEST_ORDER.length; i++) {
            if (TestProgressManager.TEST_ORDER[i].equalsIgnoreCase(testName)) {
                // Walk backwards to find the first incomplete predecessor
                for (int j = i - 1; j >= 0; j--) {
                    if (!TestProgressManager.isCompleted(this, TestProgressManager.TEST_ORDER[j])) {
                        return TestProgressManager.TEST_ORDER[j];
                    }
                }
                break;
            }
        }
        return null;
    }

    private void startTest(String testName, int totalQuestions) {
        Intent intent = new Intent(this, TestLandingActivity.class);
        intent.putExtra("testName", testName);
        intent.putExtra("totalQuestions", totalQuestions);
        startActivity(intent);
    }

    private void setupNavigation() {
        if (navHome != null) {
            navHome.setOnClickListener(v -> {
                startActivity(new Intent(this, HomeActivity.class));
                finish();
            });
        }

        if (navFiles != null) {
            navFiles.setOnClickListener(v -> {
                // Already on tests page
            });
        }

        if (navSkills != null) {
            navSkills.setOnClickListener(v -> startActivity(new Intent(this, SkillPlatformActivity.class)));
        }

        if (navCareer != null) {
            navCareer.setOnClickListener(v -> startActivity(new Intent(this, CareerExplorationActivity.class)));
        }
    }
}
