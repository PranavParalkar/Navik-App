package com.example.navik;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class TestResultActivity extends AppCompatActivity {
    
    private TextView scoreText, percentageText;
    private Button btnViewReport, btnBackToTests;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_result);
        
        int score = getIntent().getIntExtra("score", 0);
        int total = getIntent().getIntExtra("total", 10);
        String testName = getIntent().getStringExtra("testName");
        
        initializeViews();
        displayResults(score, total);
        setupClickListeners();
    }
    
    private void initializeViews() {
        scoreText = findViewById(R.id.scoreText);
        percentageText = findViewById(R.id.percentageText);
        btnViewReport = findViewById(R.id.btnViewReport);
        btnBackToTests = findViewById(R.id.btnBackToTests);
    }
    
    private void displayResults(int score, int total) {
        String testName = getIntent().getStringExtra("testName");
        if (testName != null && (testName.equalsIgnoreCase("Personality Assessment") || testName.equalsIgnoreCase("EQ Test"))) {
            scoreText.setText(total + "/" + total);
            percentageText.setText("Completed");
            return;
        }

        scoreText.setText(score + "/" + total);
        int percentage = (score * 100) / total;
        percentageText.setText(percentage + "%");
    }
    
    private void setupClickListeners() {
        btnViewReport.setOnClickListener(v -> {
            startActivity(new Intent(this, ReportActivity.class));
            finish();
        });
        
        btnBackToTests.setOnClickListener(v -> {
            startActivity(new Intent(this, TestsActivity.class));
            finish();
        });
    }
}
