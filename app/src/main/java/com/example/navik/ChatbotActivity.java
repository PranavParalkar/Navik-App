package com.example.navik;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ChatbotActivity extends AppCompatActivity {
    
    private ImageView btnBack, btnCamera;
    private RecyclerView chatRecyclerView;
    private EditText messageInput;
    private TextView btnSend;
    private ChatAdapter chatAdapter;
    private List<ChatMessage> messages;
    private LinearLayout navHome, navFiles, navBooks, navProfile;
    private GeminiApiService geminiService;
    private boolean isWaitingForResponse = false;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot);
        
        geminiService = new GeminiApiService();
        
        initializeViews();
        loadInitialMessages();
        setupClickListeners();
        setupNavigation();
    }
    
    private void initializeViews() {
        btnBack = findViewById(R.id.btnBack);
        btnCamera = findViewById(R.id.btnCamera);
        chatRecyclerView = findViewById(R.id.chatRecyclerView);
        messageInput = findViewById(R.id.messageInput);
        btnSend = findViewById(R.id.btnSend);
        
        navHome = findViewById(R.id.navHome);
        navFiles = findViewById(R.id.navFiles);
        navBooks = findViewById(R.id.navBooks);
        navProfile = findViewById(R.id.navProfile);
        
        messages = new ArrayList<>();
        chatAdapter = new ChatAdapter(this, messages);
        chatRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        chatRecyclerView.setAdapter(chatAdapter);
    }
    
    private void loadInitialMessages() {
        messages.add(new ChatMessage("👋 Hi! I'm Navik AI, your career guidance assistant!\n\n" +
                "I can help you with:\n" +
                "• Career exploration & advice\n" +
                "• Educational pathways\n" +
                "• Skill development\n" +
                "• Salary information\n" +
                "• Free learning resources\n\n" +
                "What would you like to know about your career?", true));
        chatAdapter.notifyDataSetChanged();
    }
    
    private void setupClickListeners() {
        btnBack.setOnClickListener(v -> finish());
        
        btnCamera.setOnClickListener(v -> 
            Toast.makeText(this, "Camera feature coming soon", Toast.LENGTH_SHORT).show()
        );
        
        btnSend.setOnClickListener(v -> sendMessage());
    }
    
    private void sendMessage() {
        String message = messageInput.getText().toString().trim();
        if (message.isEmpty() || isWaitingForResponse) {
            return;
        }
        
        // Add user message
        messages.add(new ChatMessage(message, false));
        chatAdapter.notifyItemInserted(messages.size() - 1);
        chatRecyclerView.scrollToPosition(messages.size() - 1);
        messageInput.setText("");
        
        // Disable send button while waiting
        isWaitingForResponse = true;
        btnSend.setEnabled(false);
        btnSend.setAlpha(0.5f);
        
        // Add typing indicator
        messages.add(new ChatMessage("Thinking...", true));
        int typingPosition = messages.size() - 1;
        chatAdapter.notifyItemInserted(typingPosition);
        chatRecyclerView.scrollToPosition(typingPosition);
        
        // Get AI response
        geminiService.generateResponse(message, new GeminiApiService.GeminiCallback() {
            @Override
            public void onSuccess(String response) {
                runOnUiThread(() -> {
                    // Remove typing indicator
                    messages.remove(typingPosition);
                    chatAdapter.notifyItemRemoved(typingPosition);
                    
                    // Add AI response
                    messages.add(new ChatMessage(response, true));
                    chatAdapter.notifyItemInserted(messages.size() - 1);
                    chatRecyclerView.scrollToPosition(messages.size() - 1);
                    
                    // Re-enable send button
                    isWaitingForResponse = false;
                    btnSend.setEnabled(true);
                    btnSend.setAlpha(1.0f);
                });
            }
            
            @Override
            public void onError(String error) {
                runOnUiThread(() -> {
                    // Remove typing indicator
                    messages.remove(typingPosition);
                    chatAdapter.notifyItemRemoved(typingPosition);
                    
                    // Use fallback response
                    String fallbackResponse = geminiService.getFallbackResponse(message);
                    messages.add(new ChatMessage(fallbackResponse, true));
                    chatAdapter.notifyItemInserted(messages.size() - 1);
                    chatRecyclerView.scrollToPosition(messages.size() - 1);
                    
                    // Show error toast
                    Toast.makeText(ChatbotActivity.this, 
                            "Using offline mode: " + error, 
                            Toast.LENGTH_SHORT).show();
                    
                    // Re-enable send button
                    isWaitingForResponse = false;
                    btnSend.setEnabled(true);
                    btnSend.setAlpha(1.0f);
                });
            }
        });
    }
    
    private String getBotResponse(String userMessage) {
        String lowerMessage = userMessage.toLowerCase();
        
        if (lowerMessage.contains("software") || lowerMessage.contains("developer")) {
            return "That's great! Software developers design, code, and test applications or systems. They work with programming languages like Java, Python, and JavaScript.\n\n📚 Learn more:\n• FreeCodeCamp (free coding courses)\n• Coursera Programming courses\n• GitHub for practice projects\n\nWould you like to know more about the skills required?";
        } else if (lowerMessage.contains("data science") || lowerMessage.contains("data scientist")) {
            return "Data Science is an exciting field! Data scientists analyze complex data to help businesses make decisions.\n\n📊 Resources to get started:\n• Kaggle Learn (free courses)\n• Python for Data Science\n• Statistics and Mathematics courses\n\nSalary range: ₹5,00,000 to ₹12,00,000. High demand field!";
        } else if (lowerMessage.contains("marketing") || lowerMessage.contains("digital marketing")) {
            return "Digital Marketing is a rapidly growing field! It involves online campaigns, social media, and analytics.\n\n🎯 Learn Digital Marketing:\n• Google Digital Marketing courses\n• HubSpot Academy (free)\n• LinkedIn Learning\n\nGreat for creative and analytical minds!";
        } else if (lowerMessage.contains("career") || lowerMessage.contains("job")) {
            return "I can help you explore various career options! We have information about:\n• Software Engineering 💻\n• Data Science 📊\n• Digital Marketing 🎯\n• UX/UI Design 🎨\n• Cybersecurity 🔒\n\n📖 Check our Books section for free learning resources!\n\nWhich area interests you?";
        } else if (lowerMessage.contains("test") || lowerMessage.contains("assessment")) {
            return "Great idea! Taking assessments helps identify your strengths:\n\n🧠 Available Tests:\n• IQ Test (cognitive abilities)\n• Personality Assessment\n• Interest Inventory\n• Skill Assessment\n• Career Matching\n\n📝 Go to Tests section to start!\n\nWhich test would you like to take first?";
        } else if (lowerMessage.contains("course") || lowerMessage.contains("learn")) {
            return "Excellent! Here are some top free learning platforms:\n\n🎓 Free Courses:\n• Coursera (university courses)\n• Khan Academy (fundamentals)\n• FreeCodeCamp (programming)\n• Duolingo (languages)\n\n💡 Tip: Check our Books section for direct links to these platforms!\n\nWhat subject interests you?";
        } else if (lowerMessage.contains("salary") || lowerMessage.contains("pay")) {
            return "Salary varies by field and experience:\n\n💰 Average Ranges (India):\n• Software Engineer: ₹3-8 lakhs\n• Data Scientist: ₹5-12 lakhs\n• Digital Marketing: ₹2-6 lakhs\n• UX Designer: ₹2.5-7 lakhs\n\n📈 Tip: Skills and location greatly impact salary!\n\nWhich career interests you?";
        } else if (lowerMessage.contains("help") || lowerMessage.contains("guide")) {
            return "I'm here to help! You can ask me about:\n\n🎯 Career Topics:\n• Career options & salaries\n• Educational paths & courses\n• Skills required for jobs\n• Free learning resources\n• Tests and assessments\n• Roadmap planning\n\n💡 Try asking: 'Tell me about data science' or 'What courses should I take?'\n\nWhat would you like to know?";
        } else if (lowerMessage.contains("thank")) {
            return "You're welcome! 😊 I'm always here to help with your career journey.\n\n🚀 Next steps:\n• Take a career assessment\n• Explore our Books section\n• Check out specific careers\n\nFeel free to ask me anything else!";
        } else {
            return "That's interesting! I'm here to help you with career guidance.\n\n💡 You can ask me about:\n• Specific careers (software, marketing, etc.)\n• Learning resources and courses\n• Career assessments and tests\n• Salary information\n\n📚 Also check our Books section for free learning links!\n\nWhat career topic interests you?";
        }
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
