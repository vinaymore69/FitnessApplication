package in.edu.fitnessapplication;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import in.edu.fitnessapplication.R;
import in.edu.fitnessapplication.ui.AddExerciseActivity;
import in.edu.fitnessapplication.ui.AddFoodActivity;
import in.edu.fitnessapplication.ui.AddWaterActivity;
import in.edu.fitnessapplication.ui.ChartActivity;
import in.edu.fitnessapplication.ui.LoginActivity;
import in.edu.fitnessapplication.ui.StatsActivity;
import in.edu.fitnessapplication.util.SessionManager;

public class MainActivity extends AppCompatActivity {

    private Button btnAddWater, btnAddFood, btnAddExercise, btnViewStats, btnViewChart;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       ;

        SessionManager sessionManager = new SessionManager(this);
        int userId = sessionManager.getUserId();

        if (userId == -1) {
            Toast.makeText(this, "Please login first", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
        setContentView(R.layout.activity_main);
        btnAddWater = findViewById(R.id.btnAddWater);
        btnAddFood = findViewById(R.id.btnAddFood);
        btnAddExercise = findViewById(R.id.btnAddExercise);
        btnViewStats = findViewById(R.id.btnViewStats);
        btnViewChart = findViewById(R.id.btnViewChart);

        btnAddWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddWaterActivity.class));
            }
        });

        btnAddFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddFoodActivity.class));
            }
        });

        btnAddExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddExerciseActivity.class));
            }
        });

        btnViewStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, StatsActivity.class));
            }
        });

        btnViewChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ChartActivity.class));
            }
        });
    }
}
