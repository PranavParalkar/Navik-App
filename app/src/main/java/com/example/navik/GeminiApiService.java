package com.example.navik;

import android.os.AsyncTask;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import okhttp3.*;
import java.io.IOException;

public class GeminiApiService {
    
    private static final String TAG = "GeminiApiService";
    private static final String API_KEY = "AIzaSyCi4hr7HzT70ZibgqHvG7sSwG0pHsNVbfM";
    private static final String BASE_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent";
    
    private OkHttpClient client;
    private Gson gson;
    
    public interface GeminiCallback {
        void onSuccess(String response);
        void onError(String error);
    }
    
    public GeminiApiService() {
        client = new OkHttpClient();
        gson = new Gson();
    }
    
    public void generateResponse(String userMessage, GeminiCallback callback) {
        new AsyncTask<Void, Void, String>() {
            private String errorMessage = null;
            
            @Override
            protected String doInBackground(Void... voids) {
                try {
                    // Create the career guidance prompt
                    String systemPrompt = "You are Navik AI, a career guidance assistant for students and professionals in India. " +
                            "Provide helpful, accurate, and encouraging career advice. Focus on:\n" +
                            "- Career paths and opportunities\n" +
                            "- Educational requirements and courses\n" +
                            "- Skills development\n" +
                            "- Salary information (in Indian Rupees)\n" +
                            "- Free learning resources\n" +
                            "- Industry trends\n" +
                            "Keep responses concise, practical, and motivating. Use emojis appropriately.";
                    
                    String fullPrompt = systemPrompt + "\n\nUser Question: " + userMessage;
                    
                    // Create request body
                    JsonObject requestBody = new JsonObject();
                    JsonArray contents = new JsonArray();
                    JsonObject content = new JsonObject();
                    JsonArray parts = new JsonArray();
                    JsonObject part = new JsonObject();
                    
                    part.addProperty("text", fullPrompt);
                    parts.add(part);
                    content.add("parts", parts);
                    contents.add(content);
                    requestBody.add("contents", contents);
                    
                    // Add generation config for better responses
                    JsonObject generationConfig = new JsonObject();
                    generationConfig.addProperty("temperature", 0.7);
                    generationConfig.addProperty("topK", 40);
                    generationConfig.addProperty("topP", 0.95);
                    generationConfig.addProperty("maxOutputTokens", 1024);
                    requestBody.add("generationConfig", generationConfig);
                    
                    // Create request
                    RequestBody body = RequestBody.create(
                        requestBody.toString(),
                        MediaType.parse("application/json")
                    );
                    
                    Request request = new Request.Builder()
                            .url(BASE_URL + "?key=" + API_KEY)
                            .post(body)
                            .addHeader("Content-Type", "application/json")
                            .build();
                    
                    // Execute request
                    Response response = client.newCall(request).execute();
                    
                    if (response.isSuccessful()) {
                        String responseBody = response.body().string();
                        Log.d(TAG, "API Response: " + responseBody);
                        
                        // Parse response
                        JsonObject jsonResponse = gson.fromJson(responseBody, JsonObject.class);
                        
                        if (jsonResponse.has("candidates")) {
                            JsonArray candidates = jsonResponse.getAsJsonArray("candidates");
                            if (candidates.size() > 0) {
                                JsonObject candidate = candidates.get(0).getAsJsonObject();
                                if (candidate.has("content")) {
                                    JsonObject contentObj = candidate.getAsJsonObject("content");
                                    if (contentObj.has("parts")) {
                                        JsonArray partsArray = contentObj.getAsJsonArray("parts");
                                        if (partsArray.size() > 0) {
                                            JsonObject partObj = partsArray.get(0).getAsJsonObject();
                                            if (partObj.has("text")) {
                                                return partObj.get("text").getAsString();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        
                        errorMessage = "No valid response from API";
                        return null;
                    } else {
                        errorMessage = "API Error: " + response.code() + " - " + response.message();
                        Log.e(TAG, errorMessage);
                        return null;
                    }
                    
                } catch (IOException e) {
                    errorMessage = "Network error: " + e.getMessage();
                    Log.e(TAG, errorMessage, e);
                    return null;
                } catch (Exception e) {
                    errorMessage = "Unexpected error: " + e.getMessage();
                    Log.e(TAG, errorMessage, e);
                    return null;
                }
            }
            
            @Override
            protected void onPostExecute(String result) {
                if (result != null) {
                    callback.onSuccess(result);
                } else {
                    callback.onError(errorMessage != null ? errorMessage : "Unknown error occurred");
                }
            }
        }.execute();
    }
    
    public String getFallbackResponse(String userMessage) {
        String lowerMessage = userMessage.toLowerCase();
        
        if (lowerMessage.contains("software") || lowerMessage.contains("developer") || lowerMessage.contains("programming")) {
            return "🚀 Software Development is an excellent career choice!\n\n" +
                    "💼 **Career Paths:**\n" +
                    "• Frontend Developer (React, Angular)\n" +
                    "• Backend Developer (Java, Python, Node.js)\n" +
                    "• Full-Stack Developer\n" +
                    "• Mobile App Developer\n\n" +
                    "💰 **Salary Range:** ₹3-15 lakhs per year\n\n" +
                    "📚 **Free Learning Resources:**\n" +
                    "• FreeCodeCamp\n" +
                    "• Coursera Programming Courses\n" +
                    "• YouTube coding tutorials\n\n" +
                    "Would you like specific guidance on any programming language?";
        } else if (lowerMessage.contains("data science") || lowerMessage.contains("data analyst") || lowerMessage.contains("machine learning")) {
            return "📊 Data Science is a high-demand field!\n\n" +
                    "💼 **Career Opportunities:**\n" +
                    "• Data Scientist\n" +
                    "• Data Analyst\n" +
                    "• Machine Learning Engineer\n" +
                    "• Business Intelligence Analyst\n\n" +
                    "💰 **Salary Range:** ₹4-20 lakhs per year\n\n" +
                    "🎓 **Skills Needed:**\n" +
                    "• Python/R Programming\n" +
                    "• Statistics & Mathematics\n" +
                    "• SQL & Databases\n" +
                    "• Machine Learning\n\n" +
                    "📚 **Start Learning:** Kaggle Learn, Coursera Data Science courses";
        } else if (lowerMessage.contains("marketing") || lowerMessage.contains("digital marketing")) {
            return "🎯 Digital Marketing is booming!\n\n" +
                    "💼 **Career Options:**\n" +
                    "• Digital Marketing Specialist\n" +
                    "• Social Media Manager\n" +
                    "• Content Marketing Manager\n" +
                    "• SEO/SEM Specialist\n\n" +
                    "💰 **Salary Range:** ₹2-10 lakhs per year\n\n" +
                    "🛠️ **Key Skills:**\n" +
                    "• Google Ads & Analytics\n" +
                    "• Social Media Platforms\n" +
                    "• Content Creation\n" +
                    "• Email Marketing\n\n" +
                    "📚 **Free Courses:** Google Digital Marketing, HubSpot Academy";
        } else {
            return "🤖 I'm here to help with your career questions!\n\n" +
                    "💡 **I can assist with:**\n" +
                    "• Career exploration & guidance\n" +
                    "• Educational pathways\n" +
                    "• Skill development advice\n" +
                    "• Salary information\n" +
                    "• Free learning resources\n\n" +
                    "🎯 **Popular Career Fields:**\n" +
                    "• Software Development 💻\n" +
                    "• Data Science 📊\n" +
                    "• Digital Marketing 🎯\n" +
                    "• Design & Creative 🎨\n\n" +
                    "What career area interests you most?";
        }
    }
}