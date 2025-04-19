package fitnesstracker.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private Connection connection;
    private static final String DB_URL = "jdbc:sqlite:fitness_tracker.db";
    
    public DatabaseManager() {
        initializeDatabase();
    }
    
    private void initializeDatabase() {
        try {
            // Create a connection to the database
            connection = DriverManager.getConnection(DB_URL);
            
            // Create tables if they don't exist
            Statement statement = connection.createStatement();
            
            // Create exercises table
            statement.execute(
                "CREATE TABLE IF NOT EXISTS exercises (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "description TEXT," +
                "muscle_group TEXT" +
                ");"
            );
            
            // Check if the table is empty
            ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM exercises");
            if (rs.next() && rs.getInt(1) == 0) {
                // Populate with initial data
                populateExerciseTable();
            }
            
            statement.close();
        } catch (SQLException e) {
            System.err.println("Database initialization error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void populateExerciseTable() throws SQLException {
        String[] exercises = {
            "TEST1", "Test exercise for demonstration purposes", "Test",
            "Push-up", "A classic exercise targeting chest, shoulders, and triceps", "Chest",
            "Pull-up", "Upper body compound exercise targeting back and biceps", "Back",
            "Squat", "Compound lower body exercise targeting quadriceps and glutes", "Legs",
            "Deadlift", "Full body exercise focusing on posterior chain", "Back, Legs",
            "Bench Press", "Compound upper body exercise for chest, shoulders, and triceps", "Chest",
            "Plank", "Core strengthening isometric exercise", "Core",
            "Lunge", "Lower body exercise targeting quadriceps, hamstrings, and glutes", "Legs",
            "Bicep Curl", "Isolation exercise for biceps", "Arms",
            "Tricep Extension", "Isolation exercise for triceps", "Arms",
            "Shoulder Press", "Compound exercise targeting shoulders and triceps", "Shoulders"

        };
        
        String sql = "INSERT INTO exercises (name, description, muscle_group) VALUES (?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        
        for (int i = 0; i < exercises.length; i += 3) {
            pstmt.setString(1, exercises[i]);
            pstmt.setString(2, exercises[i + 1]);
            pstmt.setString(3, exercises[i + 2]);
            pstmt.executeUpdate();
        }
        
        pstmt.close();
        System.out.println("Exercise database initialized with default dataaaaaa");
    }
    
    public List<Exercise> getAllExercises() {
        List<Exercise> exercises = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT name, description, muscle_group FROM exercises");
            
            while (rs.next()) {
                String name = rs.getString("name");
                String description = rs.getString("description");
                String muscleGroup = rs.getString("muscle_group");
                
                Exercise exercise = new Exercise(name, description, muscleGroup);
                exercises.add(exercise);
            }
            
            rs.close();
            statement.close();
        } catch (SQLException e) {
            System.err.println("Error retrieving exercises: " + e.getMessage());
            e.printStackTrace();
        }
        
        return exercises;
    }
    
    public void addExercise(Exercise exercise) {
        try {
            String sql = "INSERT INTO exercises (name, description, muscle_group) VALUES (?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, exercise.getName());
            pstmt.setString(2, exercise.getDescription());
            pstmt.setString(3, exercise.getMuscleGroup());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            System.err.println("Error adding exercise: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void deleteExercise(String exerciseName) {
        try {
            String sql = "DELETE FROM exercises WHERE name = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, exerciseName);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            System.err.println("Error deleting exercise: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // Call this method when shutting down your application
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Error closing database connection: " + e.getMessage());
        }
    }
}