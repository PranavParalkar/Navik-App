# 🎯 Navik — AI-Powered Career Guidance App

Navik is a comprehensive Android application that empowers students and early-career professionals to make informed career decisions. It combines AI-driven insights, structured career roadmaps, psychometric assessments, and a mentor network into a single, easy-to-use platform.

## ✨ Features

### 🔐 Authentication
- Email/password login with Firebase Authentication
- Google Sign-In support
- Role selection (Student / Mentor) with persistent session management

### 🏠 Dashboard
- Clean, card-based home screen for quick access to all features
- Personalized welcome with user profile

### 🔍 Career Exploration
- Browse and search through curated career options
- Detailed career cards showing job descriptions, required qualifications, salary ranges, and market trends

### 🗺️ Career Roadmaps
- Step-by-step career pathways for 5+ professions (Software Engineer, Data Scientist, Marketing Manager, UX/UI Designer, Product Manager)
- Timeline visualization with expandable steps covering education, entrance exams, specializations, and duration
- Visual progress indicators

### 📝 Psychometric Assessments
- IQ, EQ, personality, and interest-based tests
- Memory tests and custom test-taking flow
- Test progress tracking and result analysis

### 🤖 AI Career Chatbot
- Powered by **Google Gemini AI**
- Real-time conversational career counseling
- Personalized advice and Q&A

### 👨‍🏫 Mentor Community
- Browse and connect with mentors
- Mentor registration and dashboard
- Dual role support (Student ↔ Mentor)

### 📚 Resources
- Curated book recommendations
- Skill-building platform links
- Job listings

### 📊 Reports & Analytics
- Assessment result summaries
- Career recommendation tracking via SQLite

## 🛠️ Tech Stack

| Layer | Technology |
|-------|-----------|
| Language | Java |
| Min SDK | 24 (Android 7.0) |
| Target SDK | 36 |
| UI | Material Design, RecyclerView, CardView, ConstraintLayout |
| Auth | Firebase Authentication, Google Sign-In |
| AI | Google Gemini Generative AI |
| Networking | OkHttp, Gson |
| Database | SQLite (DatabaseHelper) |
| Build | Gradle (Kotlin DSL) |

## 📁 Project Structure
