package com.example.navik;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MentorDashboardActivity extends AppCompatActivity {

    private RecyclerView studentRecyclerView;
    private StudentAdapter studentAdapter;
    private List<Student> studentList;
    private TextView txtStudentCount, txtWelcomeMentor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_dashboard);

        initViews();
        setupWelcomeMessage();
        loadStudents();
        setupBottomNavigation();
    }

    private void initViews() {
        studentRecyclerView = findViewById(R.id.studentRecyclerView);
        txtStudentCount = findViewById(R.id.txtStudentCount);
        txtWelcomeMentor = findViewById(R.id.txtWelcomeMentor);

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        studentRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupWelcomeMessage() {
        SharedPreferences prefs = getSharedPreferences("NavikPrefs", MODE_PRIVATE);
        String userName = prefs.getString("userName", "Mentor");
        // Extract first name
        if (userName.contains(" ")) {
            userName = userName.split(" ")[0];
        }
        txtWelcomeMentor.setText("Welcome, " + userName + "!");
    }

    private void loadStudents() {
        studentList = new ArrayList<>();

        // Sample students seeking mentorship
        studentList.add(new Student(
                "Aarav Sharma",
                "Software Engineering",
                "B.Tech CSE, 3rd Year",
                "Looking for guidance in backend development and system design",
                R.drawable.ic_profile
        ));

        studentList.add(new Student(
                "Priya Patel",
                "Data Science",
                "M.Sc Statistics, 1st Year",
                "Need mentorship in machine learning and career transition",
                R.drawable.ic_profile
        ));

        studentList.add(new Student(
                "Rohan Deshmukh",
                "UI/UX Design",
                "B.Des, 4th Year",
                "Seeking advice on portfolio building and design interviews",
                R.drawable.ic_profile
        ));

        studentList.add(new Student(
                "Sneha Kulkarni",
                "Cloud Computing",
                "B.Tech IT, 2nd Year",
                "Want to learn about AWS certifications and DevOps career path",
                R.drawable.ic_profile
        ));

        studentList.add(new Student(
                "Vikram Joshi",
                "Cybersecurity",
                "BCA, 3rd Year",
                "Interested in ethical hacking and security analyst roles",
                R.drawable.ic_profile
        ));

        studentList.add(new Student(
                "Ananya Reddy",
                "Product Management",
                "MBA, 1st Year",
                "Looking for guidance on breaking into PM roles at tech companies",
                R.drawable.ic_profile
        ));

        // Update count
        txtStudentCount.setText(String.valueOf(studentList.size()));

        // Set adapter
        studentAdapter = new StudentAdapter(this, studentList);
        studentRecyclerView.setAdapter(studentAdapter);
    }

    private void setupBottomNavigation() {
        LinearLayout navHome = findViewById(R.id.navHome);
        LinearLayout navFiles = findViewById(R.id.navFiles);
        LinearLayout navSkills = findViewById(R.id.navSkills);
        LinearLayout navCareer = findViewById(R.id.navCareer);

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
