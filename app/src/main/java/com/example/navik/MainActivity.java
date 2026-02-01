package com.example.navik;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {
    
    private TextView welcomeText;
    private CardView careerExplorationCard, mentorCommunityCard, reportsCard, roadmapCard;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initializeViews();
        setupClickListeners();
    }
    
    private void initializeViews() {
        welcomeText = findViewById(R.id.welcomeText);
        careerExplorationCard = findViewById(R.id.careerExplorationCard);
        mentorCommunityCard = findViewById(R.id.mentorCommunityCard);
        reportsCard = findViewById(R.id.reportsCard);
        roadmapCard = findViewById(R.id.roadmapCard);
        
        // Set user name (can be fetched from SharedPreferences later)
        welcomeText.setText("Welcome\nUser XYZ");
    }
    
    private void setupClickListeners() {
        careerExplorationCard.setOnClickListener(v -> 
            startActivity(new Intent(MainActivity.this, CareerExplorationActivity.class))
        );
        
        roadmapCard.setOnClickListener(v -> 
            startActivity(new Intent(MainActivity.this, RoadmapActivity.class))
        );
        
        mentorCommunityCard.setOnClickListener(v -> {
            // TODO: Implement Mentor Community
        });
        
        reportsCard.setOnClickListener(v -> {
            // TODO: Implement Reports
        });
    }
}
