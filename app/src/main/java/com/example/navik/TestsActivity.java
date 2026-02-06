package com.example.navik;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
        
        btnBack.setOnClickListener(v -> finish());
    }

    private void updateCompletionUi() {
        updateSingleTestUi(TestProgressManager.TEST_IQ, layoutIQTest, tvIQStatus, "0/10");
        updateSingleTestUi(TestProgressManager.TEST_PERSONALITY, layoutPersonalityTest, tvPersonalityStatus, "0/10");
        updateSingleTestUi(TestProgressManager.TEST_EQ, layoutEQTest, tvEQStatus, "0/10");
        updateSingleTestUi(TestProgressManager.TEST_MEMORY, layoutMemoryTest, tvMemoryStatus, "0/10");
        updateSingleTestUi(TestProgressManager.TEST_NUMERICAL, layoutNumericalTest, tvNumericalStatus, "0/15");
        updateSingleTestUi(TestProgressManager.TEST_SPACE_RELATIONS, layoutSpaceRelationsTest, tvSpaceRelationsStatus, "0/10");
        updateSingleTestUi(TestProgressManager.TEST_MECHANICAL, layoutMechanicalTest, tvMechanicalStatus, "0/10");
        updateSingleTestUi(TestProgressManager.TEST_ABSTRACT, layoutAbstractTest, tvAbstractStatus, "0/10");
    }

    private void updateSingleTestUi(String testName, View backgroundContainer, TextView statusView, String defaultText) {
        if (backgroundContainer == null || statusView == null) {
            return;
        }

        boolean isCompleted = TestProgressManager.isCompleted(this, testName);

        if (isCompleted) {
            backgroundContainer.setBackgroundColor(ContextCompat.getColor(this, R.color.success));
            statusView.setText("Completed ✓");
            statusView.setTextColor(ContextCompat.getColor(this, R.color.white));
        } else {
            backgroundContainer.setBackgroundColor(ContextCompat.getColor(this, R.color.purple_card));
            statusView.setText(defaultText);
            statusView.setTextColor(ContextCompat.getColor(this, R.color.white));
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
