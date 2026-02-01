package com.example.navik;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MentorModeSelectionActivity extends AppCompatActivity {
    
    private ImageView btnBack;
    private CardView cardAsMentor, cardAsStudent;
    private LinearLayout navHome, navFiles, navBooks, navProfile;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_mode_selection);
        
        initializeViews();
        setupClickListeners();
        setupNavigation();
    }
    
    private void initializeViews() {
        btnBack = findViewById(R.id.btnBack);
        cardAsMentor = findViewById(R.id.cardAsMentor);
        cardAsStudent = findViewById(R.id.cardAsStudent);
        
        navHome = findViewById(R.id.navHome);
        navFiles = findViewById(R.id.navFiles);
        navBooks = findViewById(R.id.navBooks);
        navProfile = findViewById(R.id.navProfile);
    }
    
    private void setupClickListeners() {
        btnBack.setOnClickListener(v -> finish());
        
        cardAsMentor.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddMentorProfileActivity.class);
            startActivity(intent);
        });
        
        cardAsStudent.setOnClickListener(v -> {
            Intent intent = new Intent(this, BrowseMentorsActivity.class);
            startActivity(intent);
        });
    }
    
    private void setupNavigation() {
        navHome.setOnClickListener(v -> {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        });
        
        navFiles.setOnClickListener(v -> 
            startActivity(new Intent(this, TestsActivity.class))
        );
        
        navBooks.setOnClickListener(v -> 
            startActivity(new Intent(this, BooksActivity.class))
        );
        
        navProfile.setOnClickListener(v -> 
            startActivity(new Intent(this, ProfileActivity.class))
        );
    }
}