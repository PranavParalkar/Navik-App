package com.example.navik;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class TestLandingActivity extends AppCompatActivity {

    private String testName;
    private int totalQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_landing);

        testName = getIntent().getStringExtra("testName");
        totalQuestions = getIntent().getIntExtra("totalQuestions", 10);

        setupHeader();
        setupContent();
        setupGetStarted();
    }

    private void setupHeader() {
        ImageView btnBack = findViewById(R.id.btnBack);
        TextView tvHeaderTitle = findViewById(R.id.tvHeaderTitle);

        if (btnBack != null)
            btnBack.setOnClickListener(v -> finish());

        if (tvHeaderTitle != null && testName != null) {
            tvHeaderTitle.setText(getShortTitle(testName));
        }
    }

    private void setupContent() {
        TextView tvTestIcon = findViewById(R.id.ivTestIcon);
        TextView tvTestDescription = findViewById(R.id.tvTestDescription);
        TextView tvBullet1 = findViewById(R.id.tvBullet1);
        TextView tvBullet2 = findViewById(R.id.tvBullet2);
        TextView tvBullet3 = findViewById(R.id.tvBullet3);
        TextView tvBullet4 = findViewById(R.id.tvBullet4);
        TextView tvSectionCoversTitle = findViewById(R.id.tvSectionCoversTitle);
        TextView tvSectionCoversBody = findViewById(R.id.tvSectionCoversBody);

        if (testName == null)
            return;

        switch (testName) {
            case TestProgressManager.TEST_IQ:
                if (tvTestIcon != null)
                    tvTestIcon.setText("🧠");
                if (tvTestDescription != null)
                    tvTestDescription.setText(
                            "Intellectual Quotient is a measure of cognitive abilities and displays various traits like");
                if (tvBullet1 != null)
                    tvBullet1.setText("●  Adaptability");
                if (tvBullet2 != null)
                    tvBullet2.setText("●  Memory");
                if (tvBullet3 != null)
                    tvBullet3.setText("●  Pattern Recognition");
                if (tvBullet4 != null)
                    tvBullet4.setText("●  Abstract Thinking.");
                if (tvSectionCoversBody != null)
                    tvSectionCoversBody.setText(
                            "Intellectual Quotient has a total of 10 questions based on a person's intellect.\nEach question has 1 point which will be awarded to you when you choose the correct answer");
                break;

            case TestProgressManager.TEST_PERSONALITY:
                if (tvTestIcon != null)
                    tvTestIcon.setText("🧑‍💼");
                if (tvTestDescription != null)
                    tvTestDescription.setText(
                            "Personality Assessment  give insight into an individual's typical behavior and emotional responses");
                if (tvBullet1 != null)
                    tvBullet1.setText("●  Openness to Experience");
                if (tvBullet2 != null)
                    tvBullet2.setText("●  Conscientiousness");
                if (tvBullet3 != null)
                    tvBullet3.setText("●  Extraversion");
                if (tvBullet4 != null)
                    tvBullet4.setText("●  Emotional Stability");
                if (tvSectionCoversBody != null)
                    tvSectionCoversBody.setText(
                            "Personality Assessment has a total of 10 questions based on a person's emotional features and stability.\nThere will be no right or wrong answers for the questions\nEach question will check different personality aspect of a person.");
                break;

            case TestProgressManager.TEST_EQ:
                if (tvTestIcon != null)
                    tvTestIcon.setText("😊");
                if (tvTestDescription != null)
                    tvTestDescription
                            .setText("Emotional intelligence (EQ) is a crucial skill that reflects traits such as");
                if (tvBullet1 != null)
                    tvBullet1.setText("●  Self-awareness");
                if (tvBullet2 != null)
                    tvBullet2.setText("●  Empathy");
                if (tvBullet3 != null)
                    tvBullet3.setText("●  Emotion regulation");
                if (tvBullet4 != null)
                    tvBullet4.setText("●  Social Skills and Understanding");
                if (tvSectionCoversBody != null)
                    tvSectionCoversBody.setText(
                            "Emotional intelligence assessments usually comprise 10 questions designed to evaluate a person's emotional characteristics and stability  such as self-awareness, empathy, and emotional regulation\nThere will be no right or wrong answers for the questions\nEach question will check different personality aspect of a person.");
                break;

            case TestProgressManager.TEST_NUMERICAL:
                if (tvTestIcon != null)
                    tvTestIcon.setText("🔢");
                if (tvTestDescription != null)
                    tvTestDescription.setText("Numerical ability is a crucial skill that reveals traits such as");
                if (tvBullet1 != null)
                    tvBullet1.setText("●  Problem-solving ability");
                if (tvBullet2 != null)
                    tvBullet2.setText("●  Attention to detail");
                if (tvBullet3 != null)
                    tvBullet3.setText("●  Decision-making skills");
                if (tvBullet4 != null)
                    tvBullet4.setText("●  Logical reasoning.");
                if (tvSectionCoversBody != null)
                    tvSectionCoversBody.setText(
                            "Numerical Ability has a total of 15 question based on a persons problem solving skills\nEach question has 1 point which will be awarded to you when you choose the correct answer");
                break;

            case TestProgressManager.TEST_SPACE_RELATIONS:
                if (tvTestIcon != null) tvTestIcon.setText("📐");
                if (tvTestDescription != null)
                    tvTestDescription.setText("Spatial relation ability is an important cognitive skill that reveals traits such as");
                if (tvBullet1 != null) tvBullet1.setText("●  Spatial awareness");
                if (tvBullet2 != null) tvBullet2.setText("●  Problem-solving capacity");
                if (tvBullet3 != null) tvBullet3.setText("●  Visual perception");
                if (tvBullet4 != null) tvBullet4.setText("●  Understand 3D space");
                if (tvSectionCoversBody != null)
                    tvSectionCoversBody.setText("Spatial relation assessments typically consist of 20 questions aimed at evaluating a person's ability to understand and manipulate objects in a three-dimensional space.\nEach question has 1 point which will be awarded to you when you choose the correct answer");
                break;

            case TestProgressManager.TEST_MECHANICAL:
                if (tvTestIcon != null) tvTestIcon.setText("⚙️");
                if (tvTestDescription != null)
                    tvTestDescription.setText("Mechanical reasoning is an essential skill that reflects traits such as");
                if (tvBullet1 != null) tvBullet1.setText("●  Problem-solving ability");
                if (tvBullet2 != null) tvBullet2.setText("●  Spatial awareness");
                if (tvBullet3 != null) tvBullet3.setText("●  Practical Thinking");
                if (tvBullet4 != null) tvBullet4.setText("●  Understanding of physical principles");
                if (tvSectionCoversBody != null)
                    tvSectionCoversBody.setText("Mechanical Reasoning has a total of 20 questions based on a person's physical properties and situation based interpretation\nEach question has 1 point which will be awarded to you when you choose the correct answer");
                break;

            // Future tests will be added here as the user provides designs
            default:
                if (tvTestIcon != null)
                    tvTestIcon.setText("📝");
                if (tvTestDescription != null)
                    tvTestDescription.setText(testName + " assesses your skills in this area.");
                if (tvBullet1 != null)
                    tvBullet1.setText("●  Focus");
                if (tvBullet2 != null)
                    tvBullet2.setText("●  Analysis");
                if (tvBullet3 != null)
                    tvBullet3.setText("●  Critical Thinking");
                if (tvBullet4 != null)
                    tvBullet4.setText("●  Problem Solving.");
                if (tvSectionCoversBody != null)
                    tvSectionCoversBody.setText(testName + " has a total of " + totalQuestions
                            + " questions.\nEach question has 1 point which will be awarded to you when you choose the correct answer");
                break;
        }
    }

    private void setupGetStarted() {
        Button btnGetStarted = findViewById(R.id.btnGetStarted);
        if (btnGetStarted == null)
            return;

        btnGetStarted.setOnClickListener(v -> {
            if (testName != null && testName.equalsIgnoreCase(TestProgressManager.TEST_MEMORY)) {
                startActivity(new Intent(this, MemoryTestActivity.class));
            } else {
                Intent intent = new Intent(this, TestTakingActivity.class);
                intent.putExtra("testName", testName);
                intent.putExtra("totalQuestions", totalQuestions);
                startActivity(intent);
            }
            finish();
        });
    }

    private String getShortTitle(String testName) {
        if (testName == null)
            return "Test";
        switch (testName) {
            case TestProgressManager.TEST_IQ:
                return "IQ";
            case TestProgressManager.TEST_PERSONALITY:
                return "Personality";
            case TestProgressManager.TEST_EQ:
                return "EQ";
            case TestProgressManager.TEST_NUMERICAL:
                return "Numerical";
            case TestProgressManager.TEST_SPACE_RELATIONS:
                return "Space";
            case TestProgressManager.TEST_MECHANICAL:
                return "Mechanical";
            case TestProgressManager.TEST_ABSTRACT:
                return "Abstract";
            case TestProgressManager.TEST_MEMORY:
                return "Memory";
            default:
                return testName;
        }
    }
}
