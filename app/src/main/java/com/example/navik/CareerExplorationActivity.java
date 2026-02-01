package com.example.navik;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class CareerExplorationActivity extends AppCompatActivity {
    
    private ImageView btnBack;
    private EditText searchInput;
    private RecyclerView careerRecyclerView;
    private CareerAdapter careerAdapter;
    private List<Career> careerList;
    private LinearLayout navHome, navFiles, navBooks, navProfile;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career_exploration);
        
        // Handle window insets for proper status bar spacing
        getWindow().setDecorFitsSystemWindows(false);
        
        initializeViews();
        loadCareers();
        setupSearch();
        setupNavigation();
    }
    
    private void initializeViews() {
        btnBack = findViewById(R.id.btnBack);
        searchInput = findViewById(R.id.searchInput);
        careerRecyclerView = findViewById(R.id.careerRecyclerView);
        careerRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        
        navHome = findViewById(R.id.navHome);
        navFiles = findViewById(R.id.navFiles);
        navBooks = findViewById(R.id.navBooks);
        navProfile = findViewById(R.id.navProfile);
        
        btnBack.setOnClickListener(v -> finish());
    }
    
    private void loadCareers() {
        careerList = new ArrayList<>();
        
        careerList.add(new Career(
            "Software Engineer",
            "Designs and develops software systems using modern programming languages and frameworks",
            "Computer Science",
            "₹3L - ₹8L per year",
            R.drawable.img_career_software,
            "https://www.indeed.com/career-advice/finding-a-job/how-to-become-software-engineer"
        ));
        
        careerList.add(new Career(
            "Data Scientist",
            "Analyzes complex data to help businesses make informed decisions using AI and ML",
            "Mathematics or Statistics",
            "₹5L - ₹12L per year",
            R.drawable.img_career_data,
            "https://www.coursera.org/articles/what-is-a-data-scientist"
        ));
        
        careerList.add(new Career(
            "Marketing Manager",
            "Plans and executes marketing strategies to promote products and build brand awareness",
            "Marketing or Business",
            "₹4L - ₹8L per year",
            R.drawable.img_career_marketing,
            "https://www.indeed.com/career-advice/careers/what-does-a-marketing-manager-do"
        ));
        
        careerList.add(new Career(
            "UX/UI Designer",
            "Creates user-friendly interfaces and experiences for digital products",
            "Design or Computer Science",
            "₹2.5L - ₹7L per year",
            R.drawable.img_career_design,
            "https://www.coursera.org/articles/ui-ux-design"
        ));
        
        careerList.add(new Career(
            "Digital Marketing Specialist",
            "Manages online marketing campaigns and social media presence for brands",
            "Marketing or Communications",
            "₹2L - ₹6L per year",
            R.drawable.img_career_marketing,
            "https://www.indeed.com/career-advice/careers/what-does-a-digital-marketing-specialist-do"
        ));
        
        careerList.add(new Career(
            "Cybersecurity Analyst",
            "Protects organizations from cyber threats and ensures data security",
            "Computer Science or IT",
            "₹3.5L - ₹9L per year",
            R.drawable.img_career_software,
            "https://www.coursera.org/articles/cybersecurity-analyst-job-guide"
        ));
        
        careerList.add(new Career(
            "Product Manager",
            "Oversees product development from conception to launch in tech companies",
            "Business or Engineering",
            "₹6L - ₹15L per year",
            R.drawable.img_career_business,
            "https://www.indeed.com/career-advice/careers/what-does-a-product-manager-do"
        ));
        
        careerList.add(new Career(
            "Content Writer",
            "Creates engaging written content for websites, blogs, and marketing materials",
            "English or Journalism",
            "₹1.8L - ₹5L per year",
            R.drawable.img_career_design,
            "https://www.indeed.com/career-advice/careers/what-does-a-content-writer-do"
        ));
        
        careerList.add(new Career(
            "Financial Analyst",
            "Analyzes financial data to guide investment decisions and business strategy",
            "Finance or Economics",
            "₹3L - ₹8L per year",
            R.drawable.img_career_business,
            "https://www.coursera.org/articles/financial-analyst"
        ));
        
        careerList.add(new Career(
            "Mobile App Developer",
            "Develops applications for iOS and Android mobile platforms",
            "Computer Science",
            "₹2.5L - ₹7.5L per year",
            R.drawable.img_career_software,
            "https://www.indeed.com/career-advice/careers/what-does-a-mobile-developer-do"
        ));
        
        careerAdapter = new CareerAdapter(this, careerList);
        careerRecyclerView.setAdapter(careerAdapter);
    }
    
    private void setupSearch() {
        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                careerAdapter.filter(s.toString());
            }
            
            @Override
            public void afterTextChanged(Editable s) {}
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
