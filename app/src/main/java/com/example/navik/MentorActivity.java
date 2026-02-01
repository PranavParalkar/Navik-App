package com.example.navik;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MentorActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Redirect to the new mentor mode selection activity
        Intent intent = new Intent(this, MentorModeSelectionActivity.class);
        startActivity(intent);
        finish();
    }
}
