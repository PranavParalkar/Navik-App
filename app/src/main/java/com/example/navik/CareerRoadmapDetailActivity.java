package com.example.navik;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class CareerRoadmapDetailActivity extends AppCompatActivity {
    
    private ImageView btnBack, careerHeaderImage;
    private TextView careerTitle, careerEmoji;
    private RecyclerView roadmapStepsRecyclerView;
    private RoadmapStepAdapter stepAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career_roadmap_detail);
        
        initializeViews();
        loadCareerData();
    }
    
    private void initializeViews() {
        btnBack = findViewById(R.id.btnBack);
        careerHeaderImage = findViewById(R.id.careerHeaderImage);
        careerTitle = findViewById(R.id.careerTitle);
        careerEmoji = findViewById(R.id.careerEmoji);
        roadmapStepsRecyclerView = findViewById(R.id.roadmapStepsRecyclerView);
        
        roadmapStepsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        
        btnBack.setOnClickListener(v -> finish());
    }
    
    private void loadCareerData() {
        // Get career name from intent
        String careerName = getIntent().getStringExtra("career_name");
        int careerImage = getIntent().getIntExtra("career_image", R.drawable.img_career_default);
        String emoji = getIntent().getStringExtra("career_emoji");
        
        careerTitle.setText(careerName != null ? careerName : "Career Roadmap");
        careerEmoji.setText(emoji != null ? emoji : "⭐");
        careerHeaderImage.setImageResource(careerImage);
        
        // Load roadmap steps based on career
        List<RoadmapStep> steps = getRoadmapSteps(careerName);
        stepAdapter = new RoadmapStepAdapter(this, steps);
        roadmapStepsRecyclerView.setAdapter(stepAdapter);
    }
    
    private List<RoadmapStep> getRoadmapSteps(String careerName) {
        List<RoadmapStep> steps = new ArrayList<>();
        
        if (careerName == null) {
            return steps;
        }
        
        if (careerName.contains("Software") || careerName.contains("Developer")) {
            steps.add(new RoadmapStep(1, "📚 Complete 10+2", 
                "Focus on Mathematics, Physics, and Computer Science",
                "Board Exams (CBSE/State)",
                "Science Stream (PCM with CS)",
                "2 years"));
            
            steps.add(new RoadmapStep(2, "🎓 Entrance Exams", 
                "Prepare for engineering entrance exams",
                "JEE Main, JEE Advanced, BITSAT, State CETs",
                "Engineering",
                "1 year preparation"));
            
            steps.add(new RoadmapStep(3, "🏫 Bachelor's Degree", 
                "Pursue B.Tech/B.E. in Computer Science or related field",
                "Semester exams, Internal assessments",
                "CSE, IT, Software Engineering, AI/ML",
                "4 years"));
            
            steps.add(new RoadmapStep(4, "💼 Internships & Projects", 
                "Gain practical experience through internships",
                "Company assessments, Coding tests",
                "Web Dev, Mobile Dev, Backend, Full Stack",
                "6 months - 1 year"));
            
            steps.add(new RoadmapStep(5, "🎯 Certifications", 
                "Get industry-recognized certifications",
                "AWS, Azure, Google Cloud certifications",
                "Cloud, DevOps, Security",
                "3-6 months"));
            
            steps.add(new RoadmapStep(6, "🚀 Job Placement", 
                "Apply for software engineering positions",
                "Technical interviews, Coding rounds",
                "Product companies, Service companies, Startups",
                "Ongoing"));
            
        } else if (careerName.contains("Data")) {
            steps.add(new RoadmapStep(1, "📚 Complete 10+2", 
                "Focus on Mathematics and Statistics",
                "Board Exams",
                "Science/Commerce with Maths",
                "2 years"));
            
            steps.add(new RoadmapStep(2, "🎓 Bachelor's Degree", 
                "Pursue B.Sc/B.Tech in relevant field",
                "University exams",
                "Statistics, Mathematics, Computer Science, Data Science",
                "3-4 years"));
            
            steps.add(new RoadmapStep(3, "💻 Learn Programming", 
                "Master Python, R, SQL",
                "Online certifications",
                "Data Analysis, Machine Learning",
                "6 months - 1 year"));
            
            steps.add(new RoadmapStep(4, "📊 Master's Degree (Optional)", 
                "Pursue M.Sc/M.Tech in Data Science",
                "Entrance exams (GATE, university specific)",
                "Data Science, AI/ML, Big Data",
                "2 years"));
            
            steps.add(new RoadmapStep(5, "🎯 Certifications", 
                "Get certified in data science tools",
                "Google Data Analytics, IBM Data Science",
                "Analytics, ML, Big Data",
                "3-6 months"));
            
            steps.add(new RoadmapStep(6, "🚀 Job Placement", 
                "Apply for data scientist positions",
                "Technical + Case study interviews",
                "Tech companies, Consulting, Finance",
                "Ongoing"));
            
        } else if (careerName.contains("Marketing")) {
            steps.add(new RoadmapStep(1, "📚 Complete 10+2", 
                "Any stream, focus on communication skills",
                "Board Exams",
                "Any stream (Commerce preferred)",
                "2 years"));
            
            steps.add(new RoadmapStep(2, "🎓 Bachelor's Degree", 
                "Pursue BBA/B.Com/BA in Marketing",
                "University exams",
                "Marketing, Business Administration, Mass Communication",
                "3 years"));
            
            steps.add(new RoadmapStep(3, "💼 Internships", 
                "Gain experience in marketing roles",
                "Company assessments",
                "Digital Marketing, Brand Management, Content",
                "6 months - 1 year"));
            
            steps.add(new RoadmapStep(4, "🎯 Certifications", 
                "Get digital marketing certifications",
                "Google Ads, Facebook Blueprint, HubSpot",
                "SEO, SEM, Social Media, Content Marketing",
                "3-6 months"));
            
            steps.add(new RoadmapStep(5, "📊 MBA (Optional)", 
                "Pursue MBA in Marketing",
                "CAT, XAT, GMAT, MAT",
                "Marketing, Brand Management",
                "2 years"));
            
            steps.add(new RoadmapStep(6, "🚀 Job Placement", 
                "Apply for marketing positions",
                "Case studies, Presentations",
                "FMCG, Tech, Consulting, Agencies",
                "Ongoing"));
            
        } else {
            // Default generic roadmap
            steps.add(new RoadmapStep(1, "📚 Complete Education", 
                "Complete required educational qualifications",
                "Relevant board/university exams",
                "Relevant stream/branch",
                "Varies"));
            
            steps.add(new RoadmapStep(2, "🎓 Specialized Training", 
                "Get specialized training in your field",
                "Professional exams/certifications",
                "Specialized courses",
                "1-2 years"));
            
            steps.add(new RoadmapStep(3, "💼 Gain Experience", 
                "Build practical experience",
                "Industry assessments",
                "Relevant specializations",
                "1-3 years"));
            
            steps.add(new RoadmapStep(4, "🚀 Career Growth", 
                "Advance in your career",
                "Performance reviews",
                "Leadership, Management",
                "Ongoing"));
        }
        
        return steps;
    }
}
