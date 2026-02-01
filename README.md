# Navik - Career Guidance App

An Android application designed to help users explore career paths, get personalized recommendations, and connect with mentors.

## Features

- **Career Exploration**: Browse and search through various career options with detailed information including:
  - Job descriptions
  - Required degrees and qualifications
  - Salary ranges
  - Market trends

- **Career Roadmap**: View personalized career paths based on assessment results

- **Database Integration**: SQLite database for storing:
  - Assessment results (IQ, EQ, personality, interests)
  - Career recommendations

- **Dashboard**: Clean, card-based interface for easy navigation

## Tech Stack

- **Language**: Java
- **UI Components**: 
  - RecyclerView for career listings
  - CardView for dashboard items
  - SearchView for career filtering
- **Database**: SQLite with DatabaseHelper
- **Min SDK**: 24 (Android 7.0)
- **Target SDK**: 36

## Project Structure

```
app/src/main/java/com/example/navik/
├── MainActivity.java                    # Dashboard with navigation cards
├── CareerExplorationActivity.java       # Career browsing with search
├── RoadmapActivity.java                 # Career roadmap display
├── CareerAdapter.java                   # RecyclerView adapter
├── Career.java                          # Career model class
└── DatabaseHelper.java                  # SQLite database helper

app/src/main/res/layout/
├── activity_main.xml                    # Dashboard layout
├── activity_career_exploration.xml      # Career list layout
├── activity_roadmap.xml                 # Roadmap layout
└── item_career.xml                      # Career card item
```

## Setup

1. Clone the repository
2. Open in Android Studio
3. Sync Gradle files
4. Run on emulator or device

## Future Enhancements

- Mentor Community feature
- Reports & Analytics dashboard
- Assessment module integration
- AI-powered career recommendations
