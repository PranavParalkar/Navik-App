package com.example.navik;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeActivity extends AppCompatActivity {
    
    private DrawerLayout drawerLayout;
    private ImageView menuIcon;
    private TextView userName, drawerUserName, drawerUserEmail;
    private CardView cardJobListings, cardMentorCommunity, cardReport, cardRoadmap;
    private FloatingActionButton chatButton;
    private LinearLayout navHome, navFiles, navSkills, navCareer;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        
        initializeViews();
        loadUserData();
        setupClickListeners();
        setupDrawer();
    }
    
    private void initializeViews() {
        drawerLayout = findViewById(R.id.drawerLayout);
        menuIcon = findViewById(R.id.menuIcon);
        userName = findViewById(R.id.userName);
        
        drawerUserName = findViewById(R.id.drawerUserName);
        drawerUserEmail = findViewById(R.id.drawerUserEmail);
        
        cardJobListings = findViewById(R.id.cardJobListings);
        cardMentorCommunity = findViewById(R.id.cardMentorCommunity);
        cardReport = findViewById(R.id.cardReport);
        cardRoadmap = findViewById(R.id.cardRoadmap);
        
        chatButton = findViewById(R.id.chatButton);
        
        navHome = findViewById(R.id.navHome);
        navFiles = findViewById(R.id.navFiles);
        navSkills = findViewById(R.id.navSkills);
        navCareer = findViewById(R.id.navCareer);
    }
    
    private void loadUserData() {
        SharedPreferences prefs = getSharedPreferences("NavikPrefs", MODE_PRIVATE);
        String email = prefs.getString("userEmail", "User");
        String displayName = email.split("@")[0];
        userName.setText(displayName);
        drawerUserName.setText(displayName);
        drawerUserEmail.setText(email);
    }
    
    private void setupDrawer() {
        menuIcon.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));
        
        // Close button
        View btnCloseDrawer = findViewById(R.id.btnCloseDrawer);
        if (btnCloseDrawer != null) {
            btnCloseDrawer.setOnClickListener(v -> drawerLayout.closeDrawer(GravityCompat.START));
        }
        
        // Drawer menu items
        findViewById(R.id.drawerHome).setOnClickListener(v -> {
            drawerLayout.closeDrawer(GravityCompat.START);
        });
        
        findViewById(R.id.drawerJobListing).setOnClickListener(v -> {
            drawerLayout.closeDrawer(GravityCompat.START);
            startActivity(new Intent(this, JobListingsActivity.class));
        });
        
        findViewById(R.id.drawerScholarship).setOnClickListener(v -> {
            drawerLayout.closeDrawer(GravityCompat.START);
            startActivity(new Intent(this, BooksActivity.class));
        });
        
        findViewById(R.id.drawerDiscussion).setOnClickListener(v -> {
            drawerLayout.closeDrawer(GravityCompat.START);
            startActivity(new Intent(this, ChatbotActivity.class));
        });
        
        findViewById(R.id.drawerSettings).setOnClickListener(v -> {
            drawerLayout.closeDrawer(GravityCompat.START);
            startActivity(new Intent(this, ProfileActivity.class));
        });
        
        findViewById(R.id.drawerHelp).setOnClickListener(v -> {
            drawerLayout.closeDrawer(GravityCompat.START);
            startActivity(new Intent(this, ChatbotActivity.class));
        });
        
        findViewById(R.id.drawerLogout).setOnClickListener(v -> {
            drawerLayout.closeDrawer(GravityCompat.START);
            showLogoutDialog();
        });
    }
    
    private void showLogoutDialog() {
        new AlertDialog.Builder(this)
            .setTitle("Logout")
            .setMessage("Are you sure you want to logout?")
            .setPositiveButton("Yes", (dialog, which) -> {
                SharedPreferences prefs = getSharedPreferences("NavikPrefs", MODE_PRIVATE);
                prefs.edit().clear().apply();
                
                Intent intent = new Intent(this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            })
            .setNegativeButton("Cancel", null)
            .show();
    }
    
    private void setupClickListeners() {
        cardJobListings.setOnClickListener(v -> 
            startActivity(new Intent(this, JobListingsActivity.class))
        );
        
        cardMentorCommunity.setOnClickListener(v -> 
            startActivity(new Intent(this, MentorActivity.class))
        );
        
        cardReport.setOnClickListener(v -> 
        {
            if (!TestProgressManager.areAllTestsCompleted(this)) {
                android.widget.Toast.makeText(this, "Complete all tests to unlock the report.", android.widget.Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, TestsActivity.class));
                return;
            }
            startActivity(new Intent(this, ReportActivity.class));
        });
        
        cardRoadmap.setOnClickListener(v -> 
            startActivity(new Intent(this, RoadmapActivity.class))
        );
        
        chatButton.setOnClickListener(v -> 
            startActivity(new Intent(this, ChatbotActivity.class))
        );
        
        navHome.setOnClickListener(v -> {
            // Already on home
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
    
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
