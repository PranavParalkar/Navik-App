package com.example.navik;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ReportActivity extends AppCompatActivity {
    
    private ImageView btnBack;
    private LinearLayout navHome, navFiles, navSkills, navCareer;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        if (!TestProgressManager.areAllTestsCompleted(this)) {
            Toast.makeText(this, "Complete all tests to view the report.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, TestsActivity.class));
            finish();
            return;
        }
        
        initializeViews();
        setupNavigation();
    }
    
    private void initializeViews() {
        btnBack = findViewById(R.id.btnBack);
        
        navHome = findViewById(R.id.navHome);
        navFiles = findViewById(R.id.navFiles);
        navSkills = findViewById(R.id.navSkills);
        navCareer = findViewById(R.id.navCareer);
        
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
        
        navSkills.setOnClickListener(v -> 
            startActivity(new Intent(this, SkillPlatformActivity.class))
        );
        
        navCareer.setOnClickListener(v -> 
            startActivity(new Intent(this, CareerExplorationActivity.class))
        );
    }
}
