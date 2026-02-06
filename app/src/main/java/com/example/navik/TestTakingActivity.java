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

        if (testName != null && testName.equalsIgnoreCase("IQ Test")) {
            questions.add(new Question(
                "Question 1: Which number comes next in the sequence - 1, 4, 9, 25, ?",
                "36",
                "49",
                "16",
                "81",
                3
            ));

            questions.add(new Question(
                "Question 2: Which number should replace the question mark - 10, 14, 19, 25, ?",
                "32",
                "30",
                "31",
                "34",
                0
            ));

            questions.add(new Question(
                "Question 3: Which of the following is the odd one out - 3, 5, 7, 9, 11",
                "3",
                "7",
                "9",
                "11",
                2
            ));

            questions.add(new Question(
                "Question 4: Complete the pattern: AA, AB, AC, AD, ?",
                "BA",
                "AE",
                "BB",
                "CA",
                1
            ));

            questions.add(new Question(
                "Question 5: In a certain code, ‘APPLE’ is written as 124516. How is ‘GRAPE’ written?",
                "716451",
                "715641",
                "651741",
                "715461",
                0
            ));

            questions.add(new Question(
                "Question 6: What is the missing number - 1, 4, 27, 16, 125, ?",
                "216",
                "64",
                "343",
                "25",
                0
            ));

            questions.add(new Question(
                "Question 7: Which number completes this triangle?\n\n1\n2 3\n4 5 6\n7 8 ? 10",
                "9",
                "11",
                "12",
                "13",
                0
            ));

            questions.add(new Question(
                "Question 8: In a family, the son is twice as old as the daughter. The mother is 20 years older than the daughter, and the father is 3 years older than the mother. If the son is 12 years old, how old is the father?",
                "42",
                "45",
                "48",
                "50",
                1
            ));

            questions.add(new Question(
                "Question 9: You have three containers labeled A, B, and C. A contains 9 liters, B contains 3 liters, and C contains 5 liters. You are asked to make exactly 6 liters in container A. What would you do?",
                "Transfer 3 liters from A to C",
                "Transfer 3 liters from C to B",
                "Transfer 3 liters from B to C",
                "Transfer 6 liters from A to C",
                0
            ));

            questions.add(new Question(
                "Question 10: What is the next letter in the sequence - B, D, G, K, ?",
                "M",
                "N",
                "O",
                "P",
                2
            ));
        } else if (testName != null && testName.equalsIgnoreCase("Numerical Ability")) {
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
        } else if (testName != null && testName.equalsIgnoreCase("EQ Test")) {
            questions.add(new Question(
                "Question 1: You are feeling overwhelmed at work. What do you do?",
                "Push through it and work without a break",
                "Take a short break and return to work",
                "Complain to a coworker about your workload",
                "Leave work early",
                -1
            ));

            questions.add(new Question(
                "Question 2: Your friend is upset, and you don’t fully understand why. You:",
                "Ignore it and move on",
                "Ask them if they want to talk about it",
                "Tell them it’s not a big deal",
                "Try to cheer them up without addressing the issue",
                -1
            ));

            questions.add(new Question(
                "Question 3: You receive negative feedback at work. What’s your reaction?",
                "Defend yourself immediately",
                "Reflect on the feedback and see if it’s valid",
                "Ignore the feedback",
                "Feel personally attacked and get upset",
                -1
            ));

            questions.add(new Question(
                "Question 4: A colleague makes a mistake on a team project. You:",
                "Point out their mistake publicly",
                "Help them fix it without making a big deal",
                "Ignore it and let them handle it",
                "Report them to your boss",
                -1
            ));

            questions.add(new Question(
                "Question 5: In a heated argument, you:",
                "Raise your voice to make your point",
                "Stay calm and listen to the other person’s perspective",
                "Walk away to avoid confrontation",
                "Try to win the argument at all costs",
                -1
            ));

            questions.add(new Question(
                "Question 6: You see a coworker struggling with a task. You:",
                "Offer to help them with it",
                "Ignore it and focus on your work",
                "Criticize them for not being able to handle it",
                "Wait for them to ask for help",
                -1
            ));

            questions.add(new Question(
                "Question 7: You and your friend had a disagreement. What do you do?",
                "Apologize if needed and move on",
                "Wait for them to apologize first",
                "Avoid talking to them for a while",
                "Confront them aggressively",
                -1
            ));

            questions.add(new Question(
                "Question 8: You’re feeling down, but you have an important meeting. What do you do?",
                "Focus on the meeting and deal with your emotions later",
                "Skip the meeting and take time for yourself",
                "Complain to your coworkers about how you feel",
                "Participate in the meeting but with little effort",
                -1
            ));

            questions.add(new Question(
                "Question 9: A friend is going through a tough time and shares their feelings with you. You:",
                "Listen actively and offer emotional support",
                "Give them practical advice without much listening",
                "Tell them to cheer up and focus on the positive",
                "Change the subject if it gets too emotional",
                -1
            ));

            questions.add(new Question(
                "Question 10: You made a mistake at work. How do you handle it?",
                "Admit the mistake and try to fix it",
                "Blame someone else to avoid repercussions",
                "Hide the mistake and hope no one notices",
                "Ignore it and continue working as usual",
                -1
            ));
        } else if (testName != null && testName.equalsIgnoreCase("Space Relations")) {
            questions.add(new Question(
                "Question 1: Which object has the same volume as a cylinder with a radius of 3 cm and height of 5 cm?",
                "Sphere with radius 4.5 cm",
                "Cube with side 6 cm",
                "Rectangular prism with dimensions 5x5x5 cm",
                "Cone with radius 3 cm and height 6 cm",
                1
            ));

            questions.add(new Question(
                "Question 2: If you view an object from different angles and see different shapes, what type of perspective is this?",
                "Linear perspective",
                "Aerial perspective",
                "Axonometric perspective",
                "Isometric perspective",
                2
            ));

            questions.add(new Question(
                "Question 3: If you move a box 3 steps forward and 2 steps to the left, what is the new position relative to the starting point?",
                "(3, 2)",
                "(2, 3)",
                "(3, -2)",
                "(0, 0)",
                2
            ));

            questions.add(new Question(
                "Question 4: What will be the shape of the shadow of a cylinder when light is cast directly overhead?",
                "Circle",
                "Rectangle",
                "Ellipse",
                "Triangle",
                0
            ));

            questions.add(new Question(
                "Question 5: Which of the following shapes cannot be transformed into a cylinder by rotating?",
                "Rectangle",
                "Triangle",
                "Square",
                "Circle",
                3
            ));

            questions.add(new Question(
                "Question 6: Which shape has the largest area?",
                "Circle with radius 1 cm",
                "Square with side 1 cm",
                "Triangle with base 1 cm and height 1 cm",
                "Rectangle with length 1 cm and width 1 cm",
                0
            ));

            questions.add(new Question(
                "Question 7: What shape do you get when you cut a sphere in half?",
                "Cube",
                "Circle",
                "Hemisphere",
                "Oval",
                2
            ));

            questions.add(new Question(
                "Question 8: In a 3D model, which axis represents height?",
                "X-axis",
                "Y-axis",
                "Z-axis",
                "W-axis",
                2
            ));

            questions.add(new Question(
                "Question 9: What is the angle between two parallel lines?",
                "45 degrees",
                "90 degrees",
                "0 degrees",
                "180 degrees",
                2
            ));

            questions.add(new Question(
                "Question 10: Which shape can be folded into a cube?",
                "T-shape",
                "L-shape",
                "I-shape",
                "Z-shape",
                0
            ));
        } else if (testName != null && testName.equalsIgnoreCase("Mechanical Reasoning")) {
            questions.add(new Question(
                "Question 1: Which of the following tools is best for cutting wood?",
                "Hammer",
                "Saw",
                "Screwdriver",
                "Wrench",
                1
            ));

            questions.add(new Question(
                "Question 2: If a car accelerates from rest to 60 km/h in 10 seconds, what is its acceleration?",
                "6 km/h²",
                "12 km/h²",
                "3 km/h²",
                "1 km/h²",
                0
            ));

            questions.add(new Question(
                "Question 3: What type of simple machine is a lever?",
                "Inclined plane",
                "Wheel and axle",
                "Fulcrum",
                "Pulley",
                2
            ));

            questions.add(new Question(
                "Question 4: In a hydraulic lift, what principle is used to lift heavy objects?",
                "Archimedes' principle",
                "Pascal's principle",
                "Bernoulli's principle",
                "Newton's law",
                1
            ));

            questions.add(new Question(
                "Question 5: What is the main purpose of a pulley?",
                "To change the direction of a force",
                "To increase speed",
                "To store energy",
                "To create friction",
                0
            ));

            questions.add(new Question(
                "Question 6: In which part of the lever is the load placed?",
                "Effort",
                "Fulcrum",
                "Load arm",
                "Resistance",
                2
            ));

            questions.add(new Question(
                "Question 7: What will happen to the pressure in a fluid if the volume is decreased?",
                "It increases",
                "It decreases",
                "It remains the same",
                "It doubles",
                0
            ));

            questions.add(new Question(
                "Question 8: What is the primary purpose of a gear?",
                "To store energy",
                "To transmit motion",
                "To change speed",
                "To create friction",
                1
            ));

            questions.add(new Question(
                "Question 9: Which of the following best describes the concept of torque?",
                "Force applied to an object",
                "A twisting force",
                "Speed of rotation",
                "Weight of an object",
                1
            ));

            questions.add(new Question(
                "Question 10: What type of lever has the load between the fulcrum and the effort?",
                "First class lever",
                "Second class lever",
                "Third class lever",
                "Compound lever",
                1
            ));
        } else if (testName != null && testName.equalsIgnoreCase("Abstract Reasoning")) {
            questions.add(new Question(
                "Question 1: If the first two shapes are circles, what will be the third shape if the pattern alternates between circles and squares?",
                "Circle",
                "Square",
                "Triangle",
                "Rectangle",
                1
            ));

            questions.add(new Question(
                "Question 2: Which number logically follows this series? (2, 4, 8, 16, ?)",
                "20",
                "24",
                "32",
                "36",
                2
            ));

            questions.add(new Question(
                "Question 3: Which number does not belong in this set? (1, 3, 5, 7, 8)",
                "1",
                "3",
                "5",
                "8",
                3
            ));

            questions.add(new Question(
                "Question 4: What comes next in this number pattern: (1, 1, 2, 3, 5, ?)",
                "8",
                "10",
                "12",
                "13",
                0
            ));

            questions.add(new Question(
                "Question 5: Which number logically follows this sequence? (1, 4, 9, 16, ?)",
                "20",
                "25",
                "30",
                "36",
                1
            ));

            questions.add(new Question(
                "Question 6: What is the next number in the pattern: (5, 10, 20, 40, ?)",
                "50",
                "60",
                "70",
                "80",
                3
            ));

            questions.add(new Question(
                "Question 7: Which shape is similar to a circle?",
                "Triangle",
                "Square",
                "Oval",
                "Rectangle",
                2
            ));

            questions.add(new Question(
                "Question 8: What number is represented by the Roman numeral V?",
                "4",
                "5",
                "6",
                "7",
                1
            ));

            questions.add(new Question(
                "Question 9: What is the missing number in the sequence (2, 5, 11, 23, ?)",
                "47",
                "46",
                "45",
                "44",
                0
            ));

            questions.add(new Question(
                "Question 10: What is the next number in this series (4, 8, 14, 22, 32, ?)",
                "44",
                "45",
                "46",
                "48",
                0
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
