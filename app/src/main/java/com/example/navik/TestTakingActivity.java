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

        if (testName != null && testName.equalsIgnoreCase("Numerical Ability")) {
            questions.add(new Question(
                "Question 1: A mobile phone originally costs ₹25,000 and is offered at a 20% discount during a sale. What is the sale price?",
                "₹20,000",
                "₹18,000",
                "₹22,500",
                "₹23,000",
                0
            ));

            questions.add(new Question(
                "Question 2: If the price of a laptop increases from ₹45,000 to ₹54,000, what is the percentage increase?",
                "15%",
                "20%",
                "25%",
                "30%",
                1
            ));

            questions.add(new Question(
                "Question 3: A factory produces 1,200 units of a product in 8 days. How many units will it produce in 15 days at the same rate?",
                "2,000 units",
                "2,250 units",
                "2,400 units",
                "2,600 units",
                1
            ));

            questions.add(new Question(
                "Question 4: If a car's fuel efficiency is 15 km per liter, how much fuel is needed to travel 450 km?",
                "25 liters",
                "30 liters",
                "35 liters",
                "40 liters",
                1
            ));

            questions.add(new Question(
                "Question 5: Solve for x: 3x + 15 = 0",
                "-5",
                "5",
                "-3",
                "3",
                0
            ));

            questions.add(new Question(
                "Question 6: What is 25% of ₹1,200?",
                "₹200",
                "₹250",
                "₹300",
                "₹350",
                2
            ));

            questions.add(new Question(
                "Question 7: A train travels at an average speed of 90 km/h. How long will it take to cover a distance of 450 km?",
                "4 hours",
                "4.5 hours",
                "5 hours",
                "5.5 hours",
                2
            ));

            questions.add(new Question(
                "Question 8: What is the value of π (pi) rounded to two decimal places?",
                "3.12",
                "3.14",
                "3.16",
                "3.18",
                1
            ));

            questions.add(new Question(
                "Question 9: If 10 workers can complete a task in 15 days, how many days will it take for 5 workers to complete the same task, assuming they work at the same rate?",
                "15 days",
                "20 days",
                "25 days",
                "30 days",
                3
            ));

            questions.add(new Question(
                "Question 10: What is the sum of the interior angles of a pentagon?",
                "540 degrees",
                "600 degrees",
                "720 degrees",
                "900 degrees",
                0
            ));

            questions.add(new Question(
                "Question 11: If a rectangular field has a length of 50 meters and a width of 30 meters, what is its area?",
                "1,500 m²",
                "1,800 m²",
                "2,000 m²",
                "2,500 m²",
                0
            ));

            questions.add(new Question(
                "Question 12: Simplify the expression: 4(x + 5) - 3x",
                "x + 20",
                "7x + 20",
                "x + 10",
                "x + 15",
                0
            ));

            questions.add(new Question(
                "Question 13: What is the probability of drawing an Ace from a standard deck of 52 playing cards?",
                "1/13",
                "1/26",
                "1/52",
                "1/4",
                0
            ));

            questions.add(new Question(
                "Question 14: If the exchange rate is ₹75 per USD, how many Indian Rupees will you get for 500 USD?",
                "₹35,000",
                "₹37,500",
                "₹40,000",
                "₹45,000",
                1
            ));

            questions.add(new Question(
                "Question 15: A triangle has angles measuring 50°, 60°, and ___?",
                "60°",
                "70°",
                "80°",
                "90°",
                1
            ));
        } else if (testName != null && testName.equalsIgnoreCase("Personality Assessment")) {
            questions.add(new Question(
                "Question 1: How do you prefer to spend your free time?",
                "Reading",
                "Socializing",
                "Watching movies",
                "Playing sports",
                -1
            ));

            questions.add(new Question(
                "Question 2: When faced with a challenge, you tend to:",
                "Analyze the situation carefully",
                "Jump into action",
                "Seek advice from others",
                "Avoid the situation",
                -1
            ));

            questions.add(new Question(
                "Question 3: What motivates you most?",
                "Personal achievement",
                "Recognition from others",
                "Financial rewards",
                "Helping others",
                -1
            ));

            questions.add(new Question(
                "Question 4: How do you approach group projects?",
                "Take the lead",
                "Support others",
                "Stay in the background",
                "Avoid them",
                -1
            ));

            questions.add(new Question(
                "Question 5: How do you handle criticism?",
                "Reflect on it constructively",
                "Take it personally",
                "Dismiss it",
                "Become defensive",
                -1
            ));

            questions.add(new Question(
                "Question 6: What role do you usually take in social situations?",
                "The listener",
                "The speaker",
                "The organizer",
                "The entertainer",
                -1
            ));

            questions.add(new Question(
                "Question 7: In a conflict, you are more likely to:",
                "Compromise",
                "Stand your ground",
                "Avoid confrontation",
                "Seek a third party",
                -1
            ));

            questions.add(new Question(
                "Question 8: What best describes your decision-making style?",
                "Analytical",
                "Intuitive",
                "Impulsive",
                "Emotional",
                -1
            ));

            questions.add(new Question(
                "Question 9: How do you view change?",
                "As an opportunity for growth",
                "As a threat",
                "With indifference",
                "With anxiety",
                -1
            ));

            questions.add(new Question(
                "Question 10: When you set a goal, you usually:",
                "Plan every detail",
                "Go with the flow",
                "Set vague goals",
                "Rely on others to help",
                -1
            ));
        } else {
            // Default placeholder questions for other tests
            for (int i = 1; i <= totalQuestions; i++) {
                questions.add(new Question(
                    "Question " + i + ": What is " + (i * 2) + " + " + (i * 3) + "?",
                    String.valueOf(i * 4),
                    String.valueOf(i * 5),
                    String.valueOf(i * 6),
                    String.valueOf(i * 7),
                    1
                ));
            }
        }

        totalQuestions = questions.size();
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

            int correctAnswer = questions.get(currentQuestionIndex).getCorrectAnswer();
            if (correctAnswer >= 0 && selectedAnswer == correctAnswer) {
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
