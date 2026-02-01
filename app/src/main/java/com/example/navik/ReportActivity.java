package com.example.navik;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ReportActivity extends AppCompatActivity {
    
    private ImageView btnBack;
    private LinearLayout navHome, navFiles, navBooks, navProfile;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        
        initializeViews();
        setupNavigation();
    }
    
    private void initializeViews() {
        btnBack = findViewById(R.id.btnBack);
        
        navHome = findViewById(R.id.navHome);
        navFiles = findViewById(R.id.navFiles);
        navBooks = findViewById(R.id.navBooks);
        navProfile = findViewById(R.id.navProfile);
        
        btnBack.setOnClickListener(v -> finish());
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
