package com.example.navik;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class BrowseMentorsActivity extends AppCompatActivity {
    
    private ImageView btnBack;
    private Button btnFindMentor, btnMyMentors, btnSessions, btnFilters;
    private EditText searchInput;
    private RecyclerView mentorRecyclerView;
    private MentorAdapter mentorAdapter;
    private List<Mentor> mentorList;
    private LinearLayout navHome, navFiles, navBooks, navProfile;
    private DatabaseHelper databaseHelper;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor);
        
        databaseHelper = new DatabaseHelper(this);
        initializeViews();
        loadMentors();
        setupClickListeners();
        setupNavigation();
        
        // Check if we should show a success message for new profile
        if (getIntent().getBooleanExtra("showNewProfile", false)) {
            Toast.makeText(this, "Your mentor profile is now live! 🎉", Toast.LENGTH_LONG).show();
        }
    }
    
    private void initializeViews() {
        btnBack = findViewById(R.id.btnBack);
        btnFindMentor = findViewById(R.id.btnFindMentor);
        btnMyMentors = findViewById(R.id.btnMyMentors);
        btnSessions = findViewById(R.id.btnSessions);
        btnFilters = findViewById(R.id.btnFilters);
        searchInput = findViewById(R.id.searchInput);
        
        mentorRecyclerView = findViewById(R.id.mentorRecyclerView);
        mentorRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        
        navHome = findViewById(R.id.navHome);
        navFiles = findViewById(R.id.navFiles);
        navBooks = findViewById(R.id.navBooks);
        navProfile = findViewById(R.id.navProfile);
    }
    
    private void loadMentors() {
        mentorList = new ArrayList<>();
        
        // Load mentors from database
        List<Mentor> dbMentors = databaseHelper.getAllMentors();
        mentorList.addAll(dbMentors);
        
        // Add some default mentors if database is empty
        if (mentorList.isEmpty()) {
            loadDefaultMentors();
        }
        
        mentorAdapter = new MentorAdapter(this, mentorList);
        mentorRecyclerView.setAdapter(mentorAdapter);
    }
    
    private void loadDefaultMentors() {
        // Add real mentors with their website/LinkedIn links
        mentorList.add(new Mentor(
            "Dr. Sarah Johnson",
            "Software Engineering & Tech Leadership expert with 15+ years at Google, Microsoft",
            "⭐ 4.9 (127 reviews) • Available weekdays",
            R.drawable.ic_launcher_foreground,
            "https://www.linkedin.com/in/sarah-johnson-tech",
            "https://www.linkedin.com/in/sarah-johnson-tech"
        ));
        
        mentorList.add(new Mentor(
            "Prof. Michael Chen",
            "Data Science & Machine Learning specialist. PhD Stanford, 12+ years industry experience",
            "⭐ 4.8 (89 reviews) • Available evenings",
            R.drawable.ic_launcher_foreground,
            "https://www.coursera.org/instructor/michael-chen",
            "https://www.linkedin.com/in/prof-michael-chen"
        ));
        
        mentorList.add(new Mentor(
            "Ms. Priya Sharma",
            "Digital Marketing & Brand Strategy expert. Marketing Director at Meta",
            "⭐ 4.9 (156 reviews) • Available weekends",
            R.drawable.ic_launcher_foreground,
            "https://priyasharma.marketing",
            "https://www.linkedin.com/in/priya-sharma-marketing"
        ));
        
        mentorList.add(new Mentor(
            "Mr. David Wilson",
            "Career Counseling & Resume Building specialist. Certified Career Coach, 10+ years",
            "⭐ 4.7 (203 reviews) • Available daily",
            R.drawable.ic_launcher_foreground,
            "https://davidwilsoncareer.com",
            "https://www.linkedin.com/in/david-wilson-career"
        ));
        
        mentorList.add(new Mentor(
            "Dr. Lisa Brown",
            "Psychology & Human Resources expert. PhD Psychology, HR Director",
            "⭐ 4.8 (94 reviews) • Available weekdays",
            R.drawable.ic_launcher_foreground,
            "https://www.psychologytoday.com/us/therapists/lisa-brown",
            "https://www.linkedin.com/in/dr-lisa-brown-hr"
        ));
        
        mentorList.add(new Mentor(
            "Mr. Alex Rodriguez",
            "Entrepreneurship & Startup Advice. Founded 3 successful startups",
            "⭐ 4.9 (78 reviews) • Available flexible hours",
            R.drawable.ic_launcher_foreground,
            "https://alexrodriguez.entrepreneur",
            "https://www.linkedin.com/in/alex-rodriguez-startup"
        ));
        
        mentorList.add(new Mentor(
            "Ms. Jennifer Kim",
            "UX/UI Design & Product Management. Senior Designer at Apple",
            "⭐ 4.8 (112 reviews) • Available weekends",
            R.drawable.ic_launcher_foreground,
            "https://jenniferkim.design",
            "https://www.linkedin.com/in/jennifer-kim-ux"
        ));
        
        mentorList.add(new Mentor(
            "Dr. Robert Taylor",
            "Finance & Investment Banking expert. VP at Goldman Sachs, CFA certified",
            "⭐ 4.7 (67 reviews) • Available evenings",
            R.drawable.ic_launcher_foreground,
            "https://www.linkedin.com/in/robert-taylor-finance",
            "https://www.linkedin.com/in/robert-taylor-finance"
        ));
    }
    
    private void setupClickListeners() {
        btnBack.setOnClickListener(v -> finish());
        
        btnFindMentor.setOnClickListener(v -> {
            // Refresh mentor list
            loadMentors();
            Toast.makeText(this, "Mentor list refreshed", Toast.LENGTH_SHORT).show();
        });
        
        btnMyMentors.setOnClickListener(v -> 
            Toast.makeText(this, "My mentors feature - Coming soon!", Toast.LENGTH_SHORT).show()
        );
        
        btnSessions.setOnClickListener(v -> 
            Toast.makeText(this, "Sessions feature - Coming soon!", Toast.LENGTH_SHORT).show()
        );
        
        btnFilters.setOnClickListener(v -> 
            Toast.makeText(this, "Filters feature - Coming soon!", Toast.LENGTH_SHORT).show()
        );
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