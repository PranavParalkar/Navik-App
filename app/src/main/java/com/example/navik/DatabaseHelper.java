package com.example.navik;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    
    private static final String DATABASE_NAME = "navik.db";
    private static final int DATABASE_VERSION = 2;
    
    // Assessment Results Table
    private static final String TABLE_ASSESSMENT = "assessment_results";
    private static final String COL_ID = "id";
    private static final String COL_USER_ID = "user_id";
    private static final String COL_IQ_SCORE = "iq_score";
    private static final String COL_EQ_SCORE = "eq_score";
    private static final String COL_PERSONALITY = "personality_type";
    private static final String COL_INTERESTS = "interests";
    private static final String COL_TIMESTAMP = "timestamp";
    
    // Career Recommendations Table
    private static final String TABLE_RECOMMENDATIONS = "career_recommendations";
    private static final String COL_REC_ID = "id";
    private static final String COL_REC_USER_ID = "user_id";
    private static final String COL_CAREER_1 = "career_option_1";
    private static final String COL_CAREER_2 = "career_option_2";
    private static final String COL_CAREER_3 = "career_option_3";
    private static final String COL_CAREER_4 = "career_option_4";
    private static final String COL_CAREER_5 = "career_option_5";
    private static final String COL_REC_TIMESTAMP = "timestamp";
    
    // Mentors Table
    private static final String TABLE_MENTORS = "mentors";
    private static final String COL_MENTOR_ID = "id";
    private static final String COL_MENTOR_NAME = "name";
    private static final String COL_MENTOR_EMAIL = "email";
    private static final String COL_MENTOR_PHONE = "phone";
    private static final String COL_MENTOR_EXPERTISE = "expertise";
    private static final String COL_MENTOR_DESCRIPTION = "description";
    private static final String COL_MENTOR_AVAILABILITY = "availability";
    private static final String COL_MENTOR_WEBSITE = "website_url";
    private static final String COL_MENTOR_LINKEDIN = "linkedin_url";
    private static final String COL_MENTOR_CREATED = "created_at";
    
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createAssessmentTable = "CREATE TABLE " + TABLE_ASSESSMENT + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_USER_ID + " TEXT, " +
                COL_IQ_SCORE + " INTEGER, " +
                COL_EQ_SCORE + " INTEGER, " +
                COL_PERSONALITY + " TEXT, " +
                COL_INTERESTS + " TEXT, " +
                COL_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP)";
        
        String createRecommendationsTable = "CREATE TABLE " + TABLE_RECOMMENDATIONS + " (" +
                COL_REC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_REC_USER_ID + " TEXT, " +
                COL_CAREER_1 + " TEXT, " +
                COL_CAREER_2 + " TEXT, " +
                COL_CAREER_3 + " TEXT, " +
                COL_CAREER_4 + " TEXT, " +
                COL_CAREER_5 + " TEXT, " +
                COL_REC_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP)";
        
        String createMentorsTable = "CREATE TABLE " + TABLE_MENTORS + " (" +
                COL_MENTOR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_MENTOR_NAME + " TEXT NOT NULL, " +
                COL_MENTOR_EMAIL + " TEXT NOT NULL, " +
                COL_MENTOR_PHONE + " TEXT, " +
                COL_MENTOR_EXPERTISE + " TEXT, " +
                COL_MENTOR_DESCRIPTION + " TEXT, " +
                COL_MENTOR_AVAILABILITY + " TEXT, " +
                COL_MENTOR_WEBSITE + " TEXT, " +
                COL_MENTOR_LINKEDIN + " TEXT, " +
                COL_MENTOR_CREATED + " DATETIME DEFAULT CURRENT_TIMESTAMP)";
        
        db.execSQL(createAssessmentTable);
        db.execSQL(createRecommendationsTable);
        db.execSQL(createMentorsTable);
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            // Add mentors table in version 2
            String createMentorsTable = "CREATE TABLE " + TABLE_MENTORS + " (" +
                    COL_MENTOR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_MENTOR_NAME + " TEXT NOT NULL, " +
                    COL_MENTOR_EMAIL + " TEXT NOT NULL, " +
                    COL_MENTOR_PHONE + " TEXT, " +
                    COL_MENTOR_EXPERTISE + " TEXT, " +
                    COL_MENTOR_DESCRIPTION + " TEXT, " +
                    COL_MENTOR_AVAILABILITY + " TEXT, " +
                    COL_MENTOR_WEBSITE + " TEXT, " +
                    COL_MENTOR_LINKEDIN + " TEXT, " +
                    COL_MENTOR_CREATED + " DATETIME DEFAULT CURRENT_TIMESTAMP)";
            db.execSQL(createMentorsTable);
        }
    }
    
    public boolean insertAssessmentResult(String userId, int iqScore, int eqScore, 
                                         String personality, String interests) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_USER_ID, userId);
        values.put(COL_IQ_SCORE, iqScore);
        values.put(COL_EQ_SCORE, eqScore);
        values.put(COL_PERSONALITY, personality);
        values.put(COL_INTERESTS, interests);
        
        long result = db.insert(TABLE_ASSESSMENT, null, values);
        return result != -1;
    }
    
    public boolean insertCareerRecommendations(String userId, String[] careers) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_REC_USER_ID, userId);
        values.put(COL_CAREER_1, careers.length > 0 ? careers[0] : "");
        values.put(COL_CAREER_2, careers.length > 1 ? careers[1] : "");
        values.put(COL_CAREER_3, careers.length > 2 ? careers[2] : "");
        values.put(COL_CAREER_4, careers.length > 3 ? careers[3] : "");
        values.put(COL_CAREER_5, careers.length > 4 ? careers[4] : "");
        
        long result = db.insert(TABLE_RECOMMENDATIONS, null, values);
        return result != -1;
    }
    
    public Cursor getAssessmentResults(String userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_ASSESSMENT, null, COL_USER_ID + "=?", 
                       new String[]{userId}, null, null, COL_TIMESTAMP + " DESC");
    }
    
    public Cursor getCareerRecommendations(String userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_RECOMMENDATIONS, null, COL_REC_USER_ID + "=?", 
                       new String[]{userId}, null, null, COL_REC_TIMESTAMP + " DESC");
    }
    
    // Mentor-related methods
    public boolean addMentor(Mentor mentor, String email, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_MENTOR_NAME, mentor.getName());
        values.put(COL_MENTOR_EMAIL, email);
        values.put(COL_MENTOR_PHONE, phone);
        values.put(COL_MENTOR_DESCRIPTION, mentor.getDescription());
        values.put(COL_MENTOR_AVAILABILITY, mentor.getAvailability());
        values.put(COL_MENTOR_WEBSITE, mentor.getWebsiteUrl());
        values.put(COL_MENTOR_LINKEDIN, mentor.getLinkedInUrl());
        
        long result = db.insert(TABLE_MENTORS, null, values);
        return result != -1;
    }
    
    public java.util.List<Mentor> getAllMentors() {
        java.util.List<Mentor> mentorList = new java.util.ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        
        Cursor cursor = db.query(TABLE_MENTORS, null, null, null, null, null, 
                                COL_MENTOR_CREATED + " DESC");
        
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COL_MENTOR_NAME));
                String description = cursor.getString(cursor.getColumnIndexOrThrow(COL_MENTOR_DESCRIPTION));
                String availability = cursor.getString(cursor.getColumnIndexOrThrow(COL_MENTOR_AVAILABILITY));
                String website = cursor.getString(cursor.getColumnIndexOrThrow(COL_MENTOR_WEBSITE));
                String linkedin = cursor.getString(cursor.getColumnIndexOrThrow(COL_MENTOR_LINKEDIN));
                
                Mentor mentor = new Mentor(name, description, availability, 
                                         R.drawable.ic_launcher_foreground, website, linkedin);
                mentorList.add(mentor);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return mentorList;
    }
    
    public boolean deleteMentor(String mentorName) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_MENTORS, COL_MENTOR_NAME + "=?", new String[]{mentorName});
        return result > 0;
    }
}
