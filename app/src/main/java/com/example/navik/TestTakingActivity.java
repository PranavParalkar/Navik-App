package com.example.navik;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import java.util.ArrayList;
import java.util.List;

public class TestTakingActivity extends AppCompatActivity {
    
    private TextView testTitle, questionCounter, questionText;
    private TextView optionAText, optionBText, optionCText, optionDText;
    private CardView optionA, optionB, optionC, optionD;
    private Button btnNext;
    
    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int selectedAnswer = -1;
    private int score = 0;
    private String testName;
    private int totalQuestions;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_taking);
        
        testName = getIntent().getStringExtra("testName");
        totalQuestions = getIntent().getIntExtra("totalQuestions", 10);
        
        initializeViews();
        loadQuestions();
        displayQuestion();
        setupClickListeners();
    }
    
    private void initializeViews() {
        testTitle = findViewById(R.id.testTitle);
        questionCounter = findViewById(R.id.questionCounter);
        questionText = findViewById(R.id.questionText);
        
        optionA = findViewById(R.id.optionA);
        optionB = findViewById(R.id.optionB);
        optionC = findViewById(R.id.optionC);
        optionD = findViewById(R.id.optionD);
        
        optionAText = findViewById(R.id.optionAText);
        optionBText = findViewById(R.id.optionBText);
        optionCText = findViewById(R.id.optionCText);
        optionDText = findViewById(R.id.optionDText);
        
        btnNext = findViewById(R.id.btnNext);
        
        testTitle.setText(testName);
    }
    
    private void loadQuestions() {
        questions = new ArrayList<>();
        
        // Sample questions - you can customize these
        for (int i = 1; i <= totalQuestions; i++) {
            questions.add(new Question(
                "Question " + i + ": What is " + (i * 2) + " + " + (i * 3) + "?",
                String.valueOf(i * 4),
                String.valueOf(i * 5),
                String.valueOf(i * 6),
                String.valueOf(i * 7),
                1 // Correct answer is B
            ));
        }
    }
    
    private void displayQuestion() {
        if (currentQuestionIndex < questions.size()) {
            Question q = questions.get(currentQuestionIndex);
            
            questionCounter.setText((currentQuestionIndex + 1) + "/" + totalQuestions);
            questionText.setText(q.getQuestion());
            optionAText.setText("A. " + q.getOptionA());
            optionBText.setText("B. " + q.getOptionB());
            optionCText.setText("C. " + q.getOptionC());
            optionDText.setText("D. " + q.getOptionD());
            
            resetOptions();
            selectedAnswer = -1;
        }
    }
    
    private void resetOptions() {
        optionA.setCardBackgroundColor(Color.WHITE);
        optionB.setCardBackgroundColor(Color.WHITE);
        optionC.setCardBackgroundColor(Color.WHITE);
        optionD.setCardBackgroundColor(Color.WHITE);
    }
    
    private void setupClickListeners() {
        optionA.setOnClickListener(v -> selectOption(0, optionA));
        optionB.setOnClickListener(v -> selectOption(1, optionB));
        optionC.setOnClickListener(v -> selectOption(2, optionC));
        optionD.setOnClickListener(v -> selectOption(3, optionD));
        
        btnNext.setOnClickListener(v -> {
            if (selectedAnswer == -1) {
                return;
            }
            
            if (selectedAnswer == questions.get(currentQuestionIndex).getCorrectAnswer()) {
                score++;
            }
            
            currentQuestionIndex++;
            
            if (currentQuestionIndex < questions.size()) {
                displayQuestion();
            } else {
                showResults();
            }
        });
    }
    
    private void selectOption(int option, CardView card) {
        resetOptions();
        selectedAnswer = option;
        card.setCardBackgroundColor(Color.parseColor("#6366F1"));
    }
    
    private void showResults() {
        Intent intent = new Intent(this, TestResultActivity.class);
        intent.putExtra("score", score);
        intent.putExtra("total", totalQuestions);
        intent.putExtra("testName", testName);
        startActivity(intent);
        finish();
    }
}
