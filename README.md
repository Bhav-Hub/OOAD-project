# Fitness Tracker Application

A comprehensive workout and fitness tracking application built using Java Swing with MVC architecture. This application helps you manage workout plans, track exercise progress, calculate BMI, log calories, schedule workouts, and more.

## Features

- Create and manage workout plans  
- Track exercise sets and reps during workout sessions  
- View workout progress through visual graphs  
- Calculate BMI and determine health category  
- Log and track calorie intake  
- Schedule workouts and set reminders  
- Browse exercise library  
- Dark and light mode themes  

## Requirements

- Java Development Kit (JDK) 8 or higher  
- SQLite JDBC Driver (included in commands below)  

## Getting Started

### Clone the Repository

```bash
git clone https://github.com/yourusername/fitness-tracker.git
cd OOAD-project
```

### Compile the Application

```bash
javac fitnesstracker/*.java fitnesstracker/model/*.java fitnesstracker/controller/*.java fitnesstracker/view/*.java
```

### Run the Application

**For Windows:**

```bash
java -cp "lib\sqlite-jdbc-3.49.1.0.jar;." fitnesstracker.Main
```

**For Linux/Mac:**

```bash
java -cp "lib/sqlite-jdbc-3.49.1.0.jar:." fitnesstracker.Main
```

> **Note:** Make sure to place the SQLite JDBC driver in a `lib` folder within your project directory. If the driver is located elsewhere, adjust the path accordingly.

## Usage

1. **Workout Plan Manager Tab** – Create and manage your workout plans  
2. **Workout Session Tab** – Start a workout session and track your exercises  
3. **Progress Graph Tab** – View your workout progress visually  
4. **Reminders Tab** – Set reminders for your workouts  
5. **Schedule Workout Tab** – Schedule your future workouts  
6. **Calorie Log Tab** – Log and track your calorie intake  
7. **BMI Calculator Tab** – Calculate your BMI and determine health category  
8. **Exercise Library Tab** – Browse and add exercises to your workout plans  

## Theme Toggle

Click the **Dark Mode** or **Light Mode** button at the top right to switch between themes.

## Additional Information

- The application uses an SQLite database to store exercise information.  
- The database is automatically created and populated with sample exercises when you first run the application.
