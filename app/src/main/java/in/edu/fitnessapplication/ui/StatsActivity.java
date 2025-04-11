package in.edu.fitnessapplication.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import in.edu.fitnessapplication.R;
import in.edu.fitnessapplication.service.StatsService;
import in.edu.fitnessapplication.util.SessionManager;

public class StatsActivity extends AppCompatActivity {

    // TextViews for displaying stats
    private TextView txtWater, txtFood, txtExercise, txtCaloriesBurned;
    private TextView txtWaterGoal, txtFoodGoal, txtExerciseGoal, txtCaloriesBurnedGoal;

    // Progress bars
    private ProgressBar progressWater, progressFood, progressExercise, progressCaloriesBurned;

    // Button


    // Service and data
    private StatsService statsService;
    private int userId;

    // Goals (could be personalized per user in a real app)
    private final int WATER_GOAL = 2000; // ml
    private final int CALORIE_GOAL = 2000; // kcal
    private final int EXERCISE_GOAL = 30; // minutes
    private final int CALORIES_BURNED_GOAL = 500; // kcal

    // Current stats
    private int totalWater;
    private int totalCalories;
    private int totalExercise;
    private int totalCaloriesBurned;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        // Initialize SessionManager
        SessionManager sessionManager = new SessionManager(this);
        userId = sessionManager.getUserId();

        // Initialize service
        statsService = new StatsService(this);

        // Initialize UI components
        initViews();

        // Fetch and display data
        fetchAndDisplayStats();

        // Set up summary button click listener

    }

    private void initViews() {
        // TextViews for stats
        txtWater = findViewById(R.id.txtWater);
        txtFood = findViewById(R.id.txtFood);
        txtExercise = findViewById(R.id.txtExercise);
        txtCaloriesBurned = findViewById(R.id.txtCaloriesBurned);

        // TextViews for goals
        txtWaterGoal = findViewById(R.id.txtWaterGoal);
        txtFoodGoal = findViewById(R.id.txtFoodGoal);
        txtExerciseGoal = findViewById(R.id.txtExerciseGoal);
        txtCaloriesBurnedGoal = findViewById(R.id.txtCaloriesBurnedGoal);

        // Progress bars
        progressWater = findViewById(R.id.progressWater);
        progressFood = findViewById(R.id.progressFood);
        progressExercise = findViewById(R.id.progressExercise);
        progressCaloriesBurned = findViewById(R.id.progressCaloriesBurned);

        // View Summary button


        // Set max progress values
        progressWater.setMax(WATER_GOAL);
        progressFood.setMax(CALORIE_GOAL);
        progressExercise.setMax(EXERCISE_GOAL);
        progressCaloriesBurned.setMax(CALORIES_BURNED_GOAL);

        // Set goal text
        txtWaterGoal.setText("Goal: " + WATER_GOAL + " ml");
        txtFoodGoal.setText("Goal: " + CALORIE_GOAL + " cal");
        txtExerciseGoal.setText("Goal: " + EXERCISE_GOAL + " min");
        txtCaloriesBurnedGoal.setText("Goal: " + CALORIES_BURNED_GOAL + " cal");
    }

    private void fetchAndDisplayStats() {
        // Get stats from service
        totalWater = statsService.getTotalWaterToday();
        totalCalories = statsService.getTotalCaloriesToday();
        totalExercise = statsService.getTotalExerciseDurationToday();
        totalCaloriesBurned = statsService.getTotalCaloriesBurnedToday();

        // Display stats in TextViews
        txtWater.setText(totalWater + " ml");
        txtFood.setText(totalCalories + " cal");
        txtExercise.setText(totalExercise + " min");
        txtCaloriesBurned.setText(totalCaloriesBurned + " cal");

        // Update progress bars
        progressWater.setProgress(totalWater);
        progressFood.setProgress(totalCalories);
        progressExercise.setProgress(totalExercise);
        progressCaloriesBurned.setProgress(totalCaloriesBurned);
    }





    @Override
    protected void onResume() {
        super.onResume();
        // Refresh data when activity resumes
        fetchAndDisplayStats();
    }
}