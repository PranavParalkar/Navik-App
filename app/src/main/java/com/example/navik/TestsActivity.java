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
    private CardView cardIQTest, cardPersonalityTest, cardEQTest, cardMemoryTest, cardNumericalTest, cardSpaceRelationsTest, cardMechanicalTest, cardAbstractTest;
    private LinearLayout navHome, navFiles, navSkills, navCareer;

    private View layoutIQTest, layoutPersonalityTest, layoutEQTest, layoutMemoryTest, layoutNumericalTest, layoutSpaceRelationsTest, layoutMechanicalTest, layoutAbstractTest;
    private TextView tvIQStatus, tvPersonalityStatus, tvEQStatus, tvMemoryStatus, tvNumericalStatus, tvSpaceRelationsStatus, tvMechanicalStatus, tvAbstractStatus;
    private TextView tvProgressSummary, tvProgressPercent;
    private View progressBarFill;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tests);
        
        initializeViews();
        setupClickListeners();
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
        completed += updateSingleTestUi(TestProgressManager.TEST_IQ, layoutIQTest, tvIQStatus, "0/10", R.drawable.bg_skill_card_purple);
        completed += updateSingleTestUi(TestProgressManager.TEST_PERSONALITY, layoutPersonalityTest, tvPersonalityStatus, "0/10", R.drawable.bg_skill_card_purple);
        completed += updateSingleTestUi(TestProgressManager.TEST_EQ, layoutEQTest, tvEQStatus, "0/10", R.drawable.bg_skill_card_purple);
        completed += updateSingleTestUi(TestProgressManager.TEST_MEMORY, layoutMemoryTest, tvMemoryStatus, "0/10", R.drawable.bg_skill_card_purple);
        completed += updateSingleTestUi(TestProgressManager.TEST_NUMERICAL, layoutNumericalTest, tvNumericalStatus, "0/15", R.drawable.bg_skill_card_purple);
        completed += updateSingleTestUi(TestProgressManager.TEST_SPACE_RELATIONS, layoutSpaceRelationsTest, tvSpaceRelationsStatus, "0/10", R.drawable.bg_skill_card_purple);
        completed += updateSingleTestUi(TestProgressManager.TEST_MECHANICAL, layoutMechanicalTest, tvMechanicalStatus, "0/10", R.drawable.bg_skill_card_purple);
        completed += updateSingleTestUi(TestProgressManager.TEST_ABSTRACT, layoutAbstractTest, tvAbstractStatus, "0/10", R.drawable.bg_skill_card_purple);

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
    }

    private int updateSingleTestUi(String testName, View backgroundContainer, TextView statusView, String defaultText, int gradientResId) {
        if (backgroundContainer == null || statusView == null) {
            return 0;
        }

        boolean isCompleted = TestProgressManager.isCompleted(this, testName);

        if (isCompleted) {
            backgroundContainer.setBackgroundResource(R.drawable.bg_test_completed);
            statusView.setText("✓ Done");
            statusView.setTextColor(ContextCompat.getColor(this, R.color.white));
            return 1;
        } else {
            backgroundContainer.setBackgroundResource(gradientResId);
            statusView.setText(defaultText);
            statusView.setTextColor(ContextCompat.getColor(this, R.color.white));
            return 0;
        }
    }
    
    private void setupClickListeners() {
        cardIQTest.setOnClickListener(v -> startTest("IQ Test", 10));
        cardPersonalityTest.setOnClickListener(v -> startTest("Personality Assessment", 10));
        if (cardEQTest != null) {
            cardEQTest.setOnClickListener(v -> startTest("EQ Test", 10));
        }
        if (cardMemoryTest != null) {
            cardMemoryTest.setOnClickListener(v -> startActivity(new Intent(this, MemoryTestActivity.class)));
        }
        cardNumericalTest.setOnClickListener(v -> startTest("Numerical Ability", 15));
        if (cardSpaceRelationsTest != null) {
            cardSpaceRelationsTest.setOnClickListener(v -> startTest("Space Relations", 10));
        }
        cardMechanicalTest.setOnClickListener(v -> startTest("Mechanical Reasoning", 10));
        cardAbstractTest.setOnClickListener(v -> startTest("Abstract Reasoning", 10));
    }
    
    private void startTest(String testName, int totalQuestions) {
        Intent intent = new Intent(this, TestTakingActivity.class);
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
            navSkills.setOnClickListener(v -> 
                startActivity(new Intent(this, SkillPlatformActivity.class))
            );
        }
        
        if (navCareer != null) {
            navCareer.setOnClickListener(v -> 
                startActivity(new Intent(this, CareerExplorationActivity.class))
            );
        }
    }
}
