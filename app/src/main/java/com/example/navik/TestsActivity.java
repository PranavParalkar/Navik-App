package com.example.navik;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class TestsActivity extends AppCompatActivity {
    
    private ImageView btnBack;
    private CardView cardIQTest, cardPersonalityTest, cardNumericalTest, cardMechanicalTest, cardAbstractTest;
    private LinearLayout navHome, navFiles, navBooks, navProfile;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tests);
        
        initializeViews();
        setupClickListeners();
        setupNavigation();
    }
    
    private void initializeViews() {
        btnBack = findViewById(R.id.btnBack);
        cardIQTest = findViewById(R.id.cardIQTest);
        cardPersonalityTest = findViewById(R.id.cardPersonalityTest);
        cardNumericalTest = findViewById(R.id.cardNumericalTest);
        cardMechanicalTest = findViewById(R.id.cardMechanicalTest);
        cardAbstractTest = findViewById(R.id.cardAbstractTest);
        
        navHome = findViewById(R.id.navHome);
        navFiles = findViewById(R.id.navFiles);
        navBooks = findViewById(R.id.navBooks);
        navProfile = findViewById(R.id.navProfile);
        
        btnBack.setOnClickListener(v -> finish());
    }
    
    private void setupClickListeners() {
        cardIQTest.setOnClickListener(v -> startTest("IQ Test", 10));
        cardPersonalityTest.setOnClickListener(v -> startTest("Personality Assessment", 10));
        cardNumericalTest.setOnClickListener(v -> startTest("Numerical Ability", 15));
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
        
        if (navBooks != null) {
            navBooks.setOnClickListener(v -> 
                startActivity(new Intent(this, BooksActivity.class))
            );
        }
        
        if (navProfile != null) {
            navProfile.setOnClickListener(v -> 
                startActivity(new Intent(this, ProfileActivity.class))
            );
        }
    }
}
