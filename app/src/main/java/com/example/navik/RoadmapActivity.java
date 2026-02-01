package com.example.navik;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class RoadmapActivity extends AppCompatActivity {
    
    private ImageView btnBack;
    private LinearLayout navHome, navFiles, navBooks, navProfile;
    private RecyclerView careerRoadmapRecyclerView;
    private CareerAdapter careerAdapter;
    private List<Career> careerList;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roadmap_new);
        
        initializeViews();
        loadCareers();
        setupNavigation();
    }
    
    private void initializeViews() {
        btnBack = findViewById(R.id.btnBack);
        careerRoadmapRecyclerView = findViewById(R.id.careerRoadmapRecyclerView);
        
        careerRoadmapRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        
        navHome = findViewById(R.id.navHome);
        navFiles = findViewById(R.id.navFiles);
        navBooks = findViewById(R.id.navBooks);
        navProfile = findViewById(R.id.navProfile);
        
        btnBack.setOnClickListener(v -> finish());
    }
    
    private void loadCareers() {
        careerList = new ArrayList<>();
        
        // Add careers with roadmap
        careerList.add(new Career(
            "Software Engineer",
            "View detailed roadmap for becoming a software engineer",
            "Computer Science",
            "₹3L - ₹8L per year",
            R.drawable.img_career_software
        ));
        
        careerList.add(new Career(
            "Data Scientist",
            "View detailed roadmap for becoming a data scientist",
            "Mathematics or Statistics",
            "₹5L - ₹12L per year",
            R.drawable.img_career_data
        ));
        
        careerList.add(new Career(
            "Marketing Manager",
            "View detailed roadmap for becoming a marketing manager",
            "Marketing or Business",
            "₹4L - ₹8L per year",
            R.drawable.img_career_marketing
        ));
        
        careerList.add(new Career(
            "UX/UI Designer",
            "View detailed roadmap for becoming a UX/UI designer",
            "Design or Computer Science",
            "₹2.5L - ₹7L per year",
            R.drawable.img_career_design
        ));
        
        careerList.add(new Career(
            "Product Manager",
            "View detailed roadmap for becoming a product manager",
            "Business or Engineering",
            "₹6L - ₹15L per year",
            R.drawable.img_career_business
        ));
        
        careerAdapter = new CareerAdapter(this, careerList);
        careerRoadmapRecyclerView.setAdapter(careerAdapter);
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
