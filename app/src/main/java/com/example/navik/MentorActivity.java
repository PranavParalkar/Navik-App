package com.example.navik;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MentorActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Route based on user role
        if (UserRoleManager.isMentor(this)) {
            // Mentors go directly to their dashboard showing students
            startActivity(new Intent(this, MentorDashboardActivity.class));
        } else {
            // Regular users go straight to browsing mentors
            startActivity(new Intent(this, BrowseMentorsActivity.class));
        }
        finish();
    }
}
