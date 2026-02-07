# 🗺️ Roadmap Feature - Navik Career Guidance App

## Overview
The Roadmap feature provides students with detailed, step-by-step career pathways for various professions. Users can explore different career options and view comprehensive roadmaps that guide them from their current education level to their dream job.

---

## ✨ Features

### 1. Career Selection
- Browse through curated career options
- View career details including:
  - Job title and description
  - Preferred degree/education
  - Expected salary range
  - Career-specific emoji and imagery
  - High demand indicators

### 2. Detailed Career Roadmaps
- Step-by-step guidance for each career path
- Timeline visualization with numbered steps
- Expandable/collapsible step details
- Each step includes:
  - 📚 Educational requirements
  - 📝 Entrance exams needed
  - 🎓 Recommended branches/specializations
  - ⏱️ Duration/timeline
  - Detailed descriptions

### 3. Visual Design
- Modern card-based UI
- Timeline visualization with connecting lines
- Color-coded step indicators
- Responsive layout with smooth animations
- Bottom navigation for easy access

---

## 🚀 How to Use

### For Users

1. **Access Roadmap**
   - Open the Navik app
   - From the Home screen, tap on the "Roadmap" card
   
2. **Browse Careers**
   - Scroll through available career options
   - Each card shows career title, description, and salary range
   - Tap on any career card to view its detailed roadmap

3. **Explore Roadmap Steps**
   - View the step-by-step career pathway
   - Tap "View Details ▼" to expand step information
   - See exams, branches, and duration for each step
   - Tap "Hide Details ▲" to collapse

4. **Navigate**
   - Use the back button to return to career selection
   - Use bottom navigation to access other app features

---

## 📱 Available Career Paths

Currently, the app includes roadmaps for:

1. **💻 Software Engineer**
   - 6-step pathway from 10+2 to job placement
   - Focus on Computer Science and programming
   - Includes entrance exams, degree, internships, certifications

2. **📊 Data Scientist**
   - 6-step pathway covering statistics and analytics
   - Python, R, SQL skill development
   - Optional master's degree path

3. **📢 Marketing Manager**
   - 6-step pathway for marketing careers
   - Digital marketing certifications
   - Optional MBA path

4. **🎨 UX/UI Designer**
   - Design-focused career pathway
   - Creative and technical skills
   - Portfolio development guidance

5. **💼 Product Manager**
   - Business and engineering combination
   - Leadership and strategy focus
   - High-growth career path

---

## 🏗️ Technical Architecture

### Components

#### Activities
- **RoadmapActivity**: Main screen displaying career list
- **CareerRoadmapDetailActivity**: Detailed roadmap view for selected career

#### Models
- **Career**: Data model for career information
  - title, description, degreePreferred, salary, imageResource
- **RoadmapStep**: Data model for individual roadmap steps
  - stepNumber, title, description, exams, branches, duration

#### Adapters
- **CareerAdapter**: RecyclerView adapter for career cards
- **RoadmapStepAdapter**: RecyclerView adapter for roadmap steps

#### Layouts
- `activity_roadmap_new.xml`: Career selection screen
- `activity_career_roadmap_detail.xml`: Detailed roadmap screen
- `item_career.xml`: Individual career card layout
- `item_roadmap_step.xml`: Individual step card layout

---

## 🔧 Implementation Details

### Data Flow
```
HomeActivity
    ↓ (cardRoadmap click)
RoadmapActivity
    ↓ (loads hardcoded careers)
CareerAdapter → RecyclerView
    ↓ (user selects career)
CareerRoadmapDetailActivity
    ↓ (loads career-specific steps)
RoadmapStepAdapter → RecyclerView
```

### Key Methods

**RoadmapActivity.java**
```java
private void loadCareers() {
    // Creates list of Career objects
    // Sets up CareerAdapter
    // Displays in RecyclerView
}
```

**CareerRoadmapDetailActivity.java**
```java
private List<RoadmapStep> getRoadmapSteps(String careerName) {
    // Returns career-specific roadmap steps
    // Handles different career types
    // Provides fallback for unknown careers
}
```

**RoadmapStepAdapter.java**
```java
public void onBindViewHolder(StepViewHolder holder, int position) {
    // Binds step data to views
    // Handles expand/collapse functionality
    // Manages connecting line visibility
}
```

---

## 📊 Current Limitations

### Data Storage
- ⚠️ **Hardcoded Data**: All career and roadmap data is currently hardcoded in Java
- ⚠️ **No Database**: Not using SQLite for data persistence
- ⚠️ **Limited Careers**: Only 5 career paths available

### Features Not Implemented
- ❌ Progress tracking (step completion)
- ❌ User-specific roadmap customization
- ❌ Personalized recommendations based on test results
- ❌ Bookmark/save favorite careers
- ❌ Share roadmap functionality
- ❌ Export to PDF
- ❌ Resource links (courses, books, websites)

---

## 🎯 Future Enhancements

### Phase 1: Database Integration
- [ ] Create SQLite tables for careers and roadmap steps
- [ ] Migrate hardcoded data to database
- [ ] Implement CRUD operations
- [ ] Add data validation and error handling

### Phase 2: Progress Tracking
- [ ] Add user_roadmap_progress table
- [ ] Implement step completion checkboxes
- [ ] Show progress percentage
- [ ] Save/load user progress
- [ ] Add visual indicators for completed steps

### Phase 3: Personalization
- [ ] Link roadmaps to user test results
- [ ] Recommend careers based on personality/interests
- [ ] Filter by education level
- [ ] Show "Recommended for You" badges
- [ ] Customize steps based on user profile

### Phase 4: Content Expansion
- [ ] Add 10-15 more career paths
- [ ] Include resource links for each step
- [ ] Add video tutorials
- [ ] Include success stories
- [ ] Add salary progression data

### Phase 5: Advanced Features
- [ ] Share roadmap with friends
- [ ] Export roadmap as PDF
- [ ] Set reminders for milestones
- [ ] Community feedback and ratings
- [ ] Real-time job market data integration
- [ ] Mentor matching based on career choice

---

## 🛠️ Development Guide

### Adding a New Career

1. **Add Career to RoadmapActivity.java**
```java
careerList.add(new Career(
    "Career Title",
    "Description",
    "Degree Required",
    "Salary Range",
    R.drawable.career_image
));
```

2. **Add Roadmap Steps in CareerRoadmapDetailActivity.java**
```java
if (careerName.contains("YourCareer")) {
    steps.add(new RoadmapStep(
        1, // step number
        "Step Title",
        "Description",
        "Exams",
        "Branches",
        "Duration"
    ));
    // Add more steps...
}
```

3. **Add Career Image**
- Place image in `res/drawable/`
- Reference in Career constructor

### Modifying Step Layout

Edit `item_roadmap_step.xml` to customize:
- Step card appearance
- Timeline visualization
- Expand/collapse behavior
- Text styles and colors

### Customizing Career Cards

Edit `item_career.xml` to modify:
- Card design
- Information displayed
- Image placement
- Badge styles

---

## 🐛 Known Issues

1. **Career Matching**: Uses string matching instead of IDs
   - Can cause issues if career names change
   - Solution: Implement unique career IDs

2. **No Error Handling**: No fallback for missing data
   - App may crash if data is null
   - Solution: Add null checks and default values

3. **Unused Layout**: `activity_roadmap.xml` exists but isn't used
   - Old static roadmap design
   - Solution: Remove or repurpose

4. **Limited Scalability**: Adding careers requires code changes
   - Not suitable for frequent updates
   - Solution: Implement database and admin panel

---

## 📞 Support & Contribution

### For Developers
- Follow the existing code structure
- Maintain consistent naming conventions
- Add comments for complex logic
- Test on multiple screen sizes

### For Content Creators
- Ensure roadmap steps are accurate and up-to-date
- Include relevant exams and certifications
- Provide realistic timelines
- Add helpful descriptions

---

## 📄 License
Part of the Navik Career Guidance App project.

---

## 📝 Changelog

### Version 1.0 (Current)
- ✅ Career selection screen with 5 careers
- ✅ Detailed roadmap view with timeline
- ✅ Expandable step details
- ✅ Bottom navigation integration
- ✅ Responsive UI design

### Planned for Version 2.0
- 🔄 Database integration
- 🔄 Progress tracking
- 🔄 10+ additional career paths
- 🔄 Personalized recommendations

---

**Last Updated**: February 2026  
**Maintained by**: Navik Development Team
